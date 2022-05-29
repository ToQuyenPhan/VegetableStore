/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quyen.vegetablestore.users;

/**
 *
 * @author To Quyen Phan
 */
public class UserRoles {
    //Lấy thông tin từ bản role
    private int roleID;
    private String roleName;

    public UserRoles() {
        this.roleID = 0;
        this.roleName = "";
    }

    public UserRoles(int roleID, String roleName) {
        this.roleID = roleID;
        this.roleName = roleName;
    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
