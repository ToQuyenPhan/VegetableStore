/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quyen.vegetablestore.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import quyen.vegetablestore.shopping.Cart;
import quyen.vegetablestore.shopping.Product;

/**
 *
 * @author To Quyen Phan
 */
@WebServlet(name = "AddController", urlPatterns = {"/AddController"})
public class AddController extends HttpServlet {
    static final Logger LOGGER = Logger.getLogger(AddController.class);
    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "ViewDetailController";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try{
            String productName = request.getParameter("productName");
            String productID = request.getParameter("productID");
            String image = request.getParameter("image");
            double price = Double.parseDouble(request.getParameter("price"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            String catagoryID = request.getParameter("catagoryID");
            String importDate = request.getParameter("importDate");
            String usingDate = request.getParameter("usingDate");
            boolean status = Boolean.parseBoolean(request.getParameter("status"));
            Product product = new Product(productID, productName, image, price, quantity, catagoryID, importDate, usingDate, status);
            HttpSession session = request.getSession();
            Cart cart = (Cart)session.getAttribute("CART");
            if(cart == null){
                cart = new Cart();
            }
            cart.add(product);
            session.setAttribute("CART", cart);
            request.setAttribute("MESSAGE", productName + " - " + quantity + " successfully!");
            url = SUCCESS;
        }catch(Exception e){
            LOGGER.error("Error at Add Controller: " + e.toString());
        }finally{
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
