/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quyen.vegetablestore.controllers;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import quyen.vegetablestore.shopping.Product;
import quyen.vegetablestore.shopping.ProductDAO;

/**
 *
 * @author To Quyen Phan
 */
@WebServlet(name = "SiteController", urlPatterns = {"/SiteController"})
public class SiteController extends HttpServlet {
static final Logger LOGGER = Logger.getLogger(SiteController.class);
    private static final String ERROR = "index.jsp";
    private static final String SUCCESS = "site.jsp";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try{
            ProductDAO dao = new ProductDAO();
            List<Product> listAllProducts = dao.getAllProducts();//Lấy hết các product
            List<String> listCategory = dao.getAllCategory();//Lấy hết các tên loại product
            if(listAllProducts.size() > 0){
                HttpSession session = request.getSession();
                request.setAttribute("LIST_ALL_PRODUCTS", listAllProducts);
                session.setAttribute("LIST_CATEGORY", listCategory);
                url = SUCCESS;
            }
        }catch(Exception e){
            LOGGER.error("Error at Site Controller: " + e.toString());
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
