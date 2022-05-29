/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quyen.vegetablestore.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import quyen.vegetablestore.users.UserDAO;
import quyen.vegetablestore.users.UserDTO;

/**
 *
 * @author To Quyen Phan
 */
@WebServlet(name = "LoginController", urlPatterns = {"/LoginController"})
public class LoginController extends HttpServlet {
    static final Logger LOGGER = Logger.getLogger(LoginController.class);
    private static final String ERROR = "login.jsp";
    private static final String ADMIN = "AD";
    private static final String USER = "US";
    private static final String ADMIN_PAGE = "MainController?action=Search&search=";//Hiển thị danh sách các product
    private static final String USER_PAGE = "MainController?action=GetSite";//Hiển thị danh sách các product

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String userID = request.getParameter("userID");
            String password = request.getParameter("password");
            String email = (String) request.getAttribute("EMAIL");
            String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
            if (!"".equals(gRecaptchaResponse)) {//Nếu user có check recaptcha
                UserDAO dao = new UserDAO();
                UserDTO loginUser = new UserDTO();
                if (email != null) {//user đăng nhập bằng email
                    loginUser = dao.checkEmailLogin(email);
                } else {
                    loginUser = dao.checkLogin(userID, password);
                }
                if (loginUser != null) {
                    if (loginUser.isStatus()) {//Nếu tài khoản còn dùng đc
                        int roleID = loginUser.getRoleID();//Kiểm tra role
                        String roleName = dao.checkRoles(roleID);
                        HttpSession session = request.getSession();
                        session.setAttribute("LOGIN_USER", loginUser);
                        if (ADMIN.equals(roleName)) {//Nếu user là admin
                            url = ADMIN_PAGE;
                        } else if (USER.equals(roleName)) {//Nếu user không phải admin
                            url = USER_PAGE;
                        } else {
                            request.setAttribute("ERROR", "Your role is not supported!");
                        }
                    } else {
                        request.setAttribute("ERROR", "Your account is inactivated!");
                    }
                } else {
                    request.setAttribute("ERROR", "Incorrect User ID or Password!");
                }
            } else {
                request.setAttribute("ERROR", "Please check reCAPTCHA!");
            }
        } catch (Exception e) {
            LOGGER.error("Error at Login Cotroller: " + e.toString());
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
