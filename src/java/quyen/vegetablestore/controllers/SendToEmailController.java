/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quyen.vegetablestore.controllers;

import java.io.IOException;
import java.util.Properties;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import quyen.vegetablestore.users.UserDTO;

/**
 *
 * @author To Quyen Phan
 */
@WebServlet(name = "SendToEmailController", urlPatterns = {"/SendToEmailController"})
public class SendToEmailController extends HttpServlet {
static final Logger LOGGER = Logger.getLogger(SendToEmailController.class);
    private static final String ERROR = "viewCart.jsp";
    private static final String SUCCESS = "SendToEmailController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession sessionUser = request.getSession();
            UserDTO loginUser = (UserDTO) sessionUser.getAttribute("LOGIN_USER");//Lấy thông tin user đang đăng nhập
            String email = loginUser.getEmail();//Lấy email
            if (email != null) {//Nếu có email
                String sender = "quyenlh123";//email của admin
                String host = "smtp.gmail.com";
                Properties properties = System.getProperties();
                properties.put("mail.smtp.starttls.enable", "true");
                properties.put("mail.smtp.host", host);
                properties.put("mail.smtp.user", sender);
                properties.put("mail.smtp.password", "********");//mật khẩu
                properties.put("mail.smtp.port", "587");
                properties.put("mail.smtp.auth", "true");
                Session session = javax.mail.Session.getDefaultInstance(properties);
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(sender));
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
                message.setSubject("Notification of Quyen's Store");//Tiêu đề
                message.setText("You paid for an invoice in Quyen's Store!");//Nội dung email
                Transport transport = session.getTransport("smtp");
                transport.connect(host, sender, "Quyenlh18032001");
                transport.sendMessage(message, message.getAllRecipients());
                transport.close();
            }
        } catch (Exception e) {
            LOGGER.error("Error at Send To Email Controller: " + e.toString());
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
