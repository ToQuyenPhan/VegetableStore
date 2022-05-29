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
import org.apache.log4j.Logger;
import quyen.vegetablestore.loginwithgoogle.GooglePojo;
import quyen.vegetablestore.utils.GoogleUtils;

/**
 *
 * @author To Quyen Phan
 */
@WebServlet(name = "LoginGoogleController", urlPatterns = {"/login-google"})
public class LoginGoogleController extends HttpServlet {
    //sẽ nhận đoạn mã trả về từ google, đổi mã đó thành access-token rồi dùng access-token 
    //để truy cập các thông tin trong tài khoản google như email, name, id…
    private static final long serialVersionUID = 1L;
    private static final String ERROR = "login.jsp";
    private static final String SUCCESS = "LoginController";
    static final Logger LOGGER = Logger.getLogger(LoginGoogleController.class);

    public LoginGoogleController() {
        super();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String code = request.getParameter("code");
            if (code != null && !code.isEmpty()) {
                url = SUCCESS;
                String accessToken = GoogleUtils.getToken(code);
                GooglePojo googlePojo = GoogleUtils.getUserInfo(accessToken);
                request.setAttribute("ID", googlePojo.getId());
                request.setAttribute("NAME", googlePojo.getName());
                request.setAttribute("EMAIL", googlePojo.getEmail());//Tạo attribute email trên request scope
            }
        } catch (Exception e) {
            LOGGER.error("Error at Login Google Controller: " + e.toString());
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
