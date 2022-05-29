/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quyen.vegetablestore.users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import quyen.vegetablestore.utils.DBUtils;

/**
 *
 * @author To Quyen Phan
 */
public class UserDAO {

    private static final String USER_EMAIL = "SELECT userID, fullName, password, roleID, address, birthday, "
            + "phone, status FROM tblUsers WHERE email like ?";
    private static final String LOGIN = "SELECT fullName, roleID, address, birthday, phone, email, status "
            + "FROM tblUsers WHERE userID = ? and password = ?";
    private static final String CHECK_ROLES = "SELECT roleName FROM tblRoles WHERE roleID = ?";
    private static final String CHECK_DUPLICATE_PHONE = "SELECT fullName FROM tblUsers "
            + "WHERE phone = ?";
    private static final String CHECK_DUPLICATE_EMAIL = "SELECT fullName FROM tblUsers "
            + "WHERE email = ?";
    private static final String CREATE = "INSERT INTO tblUsers(userID, fullName, password, roleID, address, "
            + "birthday, phone, email, status) VALUES(?,?,?,?,?,?,?,?,?)";
    
    //Hàm check login
    public UserDTO checkLogin(String userID, String password) throws SQLException {
        UserDTO user = null;
        Connection conn = null;
        PreparedStatement psm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                psm = conn.prepareStatement(LOGIN);
                psm.setString(1, userID);
                psm.setString(2, password);
                rs = psm.executeQuery();
                if (rs.next()) {
                    String fullName = rs.getString("fullName");
                    int roleID = rs.getInt("roleID");
                    String address = rs.getString("address");
                    String birthday = rs.getString("birthday");
                    String phone = rs.getString("phone");
                    String email = rs.getString("email");
                    boolean status = rs.getBoolean("status");
                    user = new UserDTO(userID, fullName, password, roleID, address, birthday, phone, email, status);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (psm != null) {
                psm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return user;
    }
    
    //Kiểm tra xem user có đăng nhập bằng email ko
    //Xuống DB để lấy thông tin người dùng
     public UserDTO checkEmailLogin(String email) throws SQLException {
        UserDTO user = null;
        Connection conn = null;
        PreparedStatement psm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                psm = conn.prepareStatement(USER_EMAIL);
                psm.setString(1, email);
                rs = psm.executeQuery();
                if (rs.next()) {
                    String userID = rs.getString("userID");
                    String fullName = rs.getString("fullName");
                    String password = rs.getString("password");
                    int roleID = rs.getInt("roleID");
                    String address = rs.getString("address");
                    String birthday = rs.getString("birthday");
                    String phone = rs.getString("phone");
                    boolean status = rs.getBoolean("status");
                    user = new UserDTO(userID, fullName, password, roleID, address, birthday, phone, email, status);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (psm != null) {
                psm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return user;
    }

    //Kiểm tra user thuộc tầng lớp nào
    public String checkRoles(int roleID) throws SQLException {
        String roleName = null;
        Connection conn = null;
        PreparedStatement psm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                psm = conn.prepareStatement(CHECK_ROLES);
                psm.setInt(1, roleID);
                rs = psm.executeQuery();
                if (rs.next()) {
                    roleName = rs.getString("roleName");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (psm != null) {
                psm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return roleName;
    }

    //Kiểm tra xem sđt đã tạo cho user khác chưa
    public boolean checkDuplicatePhone(String phone) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement psm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                psm = conn.prepareStatement(CHECK_DUPLICATE_PHONE);
                psm.setString(1, phone);
                rs = psm.executeQuery();
                if (rs.next()) {
                    check = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (psm != null) {
                psm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    //Kiểm tra email đã dùng cho user khác chưa
    public boolean checkDuplicateEmail(String email) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement psm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                psm = conn.prepareStatement(CHECK_DUPLICATE_EMAIL);
                psm.setString(1, email);
                rs = psm.executeQuery();
                if (rs.next()) {
                    check = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (psm != null) {
                psm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }
    
    //Do DB dùng dữ liệu bit mà bên này dùng boolean nên phải convert lại
    //Dùng static vì còn dùng tiếp trong class khác
    public static int convertStatus(boolean status){
        return status ? 1 : 0;
    }

    //Tạo user
    public boolean createUser(UserDTO user) throws SQLException, ClassNotFoundException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement psm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                psm = conn.prepareStatement(CREATE);
                psm.setString(1, user.getUserID());
                psm.setString(2, user.getFullName());
                psm.setString(3, user.getPassword());
                psm.setInt(4, user.getRoleID());
                psm.setString(5, user.getAddress());
                psm.setString(6, user.getBirthday());
                psm.setString(7, user.getPhone());
                psm.setString(8, user.getEmail());
                psm.setInt(9, convertStatus(user.isStatus()));
                check = psm.executeUpdate() > 0 ? true : false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (psm != null) {
                psm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }
}
