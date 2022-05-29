/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quyen.vegetablestore.shopping;

/**
 *
 * @author To Quyen Phan
 */
public class Order { //Tạo order để lưu thông tin order
    private String orderID;
    private String orderDate;
    private double total;
    private String userID;

    public Order() {
        this.orderID = "";
        this.orderDate = "";
        this.total = 0;
        this.userID = "";
    }

    public Order(String orderID, String orderDate, double total, String userID) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.total = total;
        this.userID = userID;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
