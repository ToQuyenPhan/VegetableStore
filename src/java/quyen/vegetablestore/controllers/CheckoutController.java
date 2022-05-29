/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quyen.vegetablestore.controllers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import quyen.vegetablestore.shopping.Cart;
import quyen.vegetablestore.shopping.Order;
import quyen.vegetablestore.shopping.OrderDetail;
import quyen.vegetablestore.shopping.Product;
import quyen.vegetablestore.shopping.ProductDAO;
import quyen.vegetablestore.users.UserDTO;

/**
 *
 * @author To Quyen Phan
 */
@WebServlet(name = "CheckoutController", urlPatterns = {"/CheckoutController"})
public class CheckoutController extends HttpServlet {
    static final Logger LOGGER = Logger.getLogger(CheckoutController.class);
    private static final String ERROR = "viewCart.jsp";
    private static final String SUCCESS = "SendToEmailController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            double total = Double.parseDouble(request.getParameter("total"));
            if (loginUser != null) {//Đăng nhập mới được tính tiền
                if (session != null) {//Đã bỏ product vô cart mới được checkout
                    Cart cart = (Cart) session.getAttribute("CART");
                    double user = Math.random();
                    String orderID = loginUser.getUserID() + user;//Tạo orderID ngẫu nhiên(orderID là khoá chính)
                    ProductDAO dao = new ProductDAO();
                    boolean check = true;
                    int remainQuantity;
                    String error = "";
                    if (cart != null) {
                        //Kiểm tra số lượng hàng trong kho còn không, không còn báo lỗi
                        for (Product product : cart.getCart().values()) {
                            remainQuantity = dao.getQuantity(product.getProductID());
                            if (remainQuantity < product.getQuantity()) {
                                check = false;
                                error += "The " + product.getProductName() + " is not enough!</br>";
                            }
                        }
                        if (check) {//Nếu còn hàng thì tạo order(do order là bảng có primary key nên insert trước bảng orderDetail
                            Date date = Calendar.getInstance().getTime();
                            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                            String orderDate = dateFormat.format(date);
                            Order order = new Order(orderID, orderDate, total, loginUser.getUserID());
                            check = dao.createOrder(order);
                        }
                        if (check) {//Nếu đã tạo order thành công thì tạo tiếp orderDetail
                            for (Product product : cart.getCart().values()) {
                                String detailID = orderID;
                                OrderDetail orderDetail = new OrderDetail(detailID, product.getPrice(), product.getQuantity(), orderID, product.getProductID());
                                check = dao.createOrderDetail(orderDetail);
                            }
                        }
                        if (check) {//Nếu đã tạo thành công order và orderDetail thì cập nhật số lượng trong bảng product
                            int newQuantity = 0;
                            for (Product product : cart.getCart().values()) {
                                remainQuantity = dao.getQuantity(product.getProductID());
                                newQuantity = remainQuantity - product.getQuantity();
                                check = dao.updateQuantity(newQuantity, product.getProductID());
                            }
                        }
                        if (check) {
                            //Cập nhật thành công thì huỷ cart bằng cách ghi đè lên attribute CART với giá trị null
                            session.setAttribute("CART", null);
                            request.setAttribute("SUCCESS", "Checkout successfully!");//Lưu thông báo thành công
                            url = SUCCESS;
                        }
                        request.setAttribute("CHECKOUT_ERROR", error);//Thông báo lỗi
                    } else {
                        request.setAttribute("CHECKOUT_ERROR", "Empty cart!");
                    }
                } else {
                    request.setAttribute("CHECKOUT_ERROR", "Please choose your product");
                }
            } else {
                request.setAttribute("CHECKOUT_ERROR", "Please login to checkout!");
            }
        } catch (Exception e) {
            LOGGER.error("Error at Checkout Controller: " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
