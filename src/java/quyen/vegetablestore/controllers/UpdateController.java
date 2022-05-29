/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quyen.vegetablestore.controllers;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import quyen.vegetablestore.shopping.Product;
import quyen.vegetablestore.shopping.ProductDAO;

/**
 *
 * @author To Quyen Phan
 */
@WebServlet(name = "UpdateController", urlPatterns = {"/UpdateController"})
public class UpdateController extends HttpServlet {
    static final Logger LOGGER = Logger.getLogger(UpdateController.class);
    private static final String ERROR = "SearchController";
    private static final String SUCCESS = "SearchController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String productID = request.getParameter("productID");
            String productName = request.getParameter("productName");
            String image = request.getParameter("image");
            double price = Double.parseDouble(request.getParameter("price"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            String catagoryID = request.getParameter("catagoryID");
            String importDate = request.getParameter("importDate");
            String usingDate = request.getParameter("usingDate");
            boolean status = Boolean.parseBoolean(request.getParameter("status"));
            if (quantity > 0) {//Số lượng phải lớn hơn 0
                Date importDatetmp = new SimpleDateFormat("dd/MM/yyyy").parse(importDate);
                Date usingDatetmp = new SimpleDateFormat("dd/MM/yyyy").parse(usingDate);
                if (usingDatetmp.compareTo(importDatetmp) >= 0) {//Using Date phải sau Import Date mới cập nhật
                    Product product = new Product(productID, productName, image, price, quantity, catagoryID, importDate, usingDate, status);
                    ProductDAO dao = new ProductDAO();
                    boolean check = dao.updateProduct(product);
                    if (check) {
                        url = SUCCESS;
                    }
                }else{
                    request.setAttribute("ERROR", "The Using Date is invalid!");
                }
            } else {
                request.setAttribute("ERROR", "The quantity of " + productName + " is not suitable!");
            }
        } catch (Exception e) {
            log("Error at Update Controller: " + e.toString());
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
