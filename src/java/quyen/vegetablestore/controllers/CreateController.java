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
import quyen.vegetablestore.users.UserDAO;
import quyen.vegetablestore.users.UserDTO;
import quyen.vegetablestore.users.UserError;

/**
 *
 * @author To Quyen Phan
 */
@WebServlet(name = "CreateController", urlPatterns = {"/CreateController"})
public class CreateController extends HttpServlet {
static final Logger LOGGER = Logger.getLogger(CreateController.class);
    private static final String ERROR = "create.jsp";
    private static final String SUCCESS = "MainController?action=GetSite";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        UserError userError = new UserError();
        try {
            String userID = request.getParameter("userID");
            String fullName = request.getParameter("fullName");
            int roleID = 2;
            String password = request.getParameter("password");
            String confirm = request.getParameter("confirm");
            String address = request.getParameter("address");
            String birthday = request.getParameter("birthday");
            String phone = request.getParameter("phone");
            String email = request.getParameter("email");
            boolean status = true;
            UserDAO dao = new UserDAO();
            boolean checkValidation = true;
            if (userID.length() < 2 || userID.length() > 10) {
                checkValidation = false;
                userError.setUserIDError("User ID must be [2,10]");
            }
            if (fullName.length() < 3 || fullName.length() > 20) {
                checkValidation = false;
                userError.setFullNameError("Full Name must be in [3,20]");
            }
            if (!password.equals(confirm)) {
                checkValidation = false;
                userError.setConfirmError("Two password are not the same!");
            }
            boolean checkDuplicate = dao.checkDuplicatePhone(phone);
            if (checkDuplicate) {//Kiểm tra sđt có trùng không
                checkValidation = false;
                userError.setPhoneError("Your phone has been used!");
            }
            if (!"".equals(email)) {//Nếu có email thì kiểm tra email
                checkDuplicate = dao.checkDuplicateEmail(email);
                if (checkDuplicate) {
                    checkValidation = false;
                    userError.setEmailError("Your email has been used!");
                }
            }
            if (checkValidation) {
                HttpSession session = request.getSession();
                UserDTO user = new UserDTO(userID, fullName, password, roleID, address, birthday, phone, email, status);
                session.setAttribute("LOGIN_USER", user);
                boolean checkCreate = dao.createUser(user);
                if (checkCreate) {
                    url = SUCCESS;
                }
            } else {
                request.setAttribute("USER_ERROR", userError);
            }
        } catch (Exception e) {
            if (e.toString().contains("duplicate")) {
                userError.setUserIDError("Your username has been used!");
                request.setAttribute("USER_ERROR", userError);
            }
            LOGGER.error("Error at Create Controller: " + e.toString());
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
