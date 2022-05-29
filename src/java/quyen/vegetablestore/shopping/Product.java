/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quyen.vegetablestore.shopping;

import java.util.Comparator;

/**
 *
 * @author To Quyen Phan
 */
public class Product { //Tạo đối tượng product
    private String productID;
    private String productName;
    private String image;
    private double price;
    private int quantity;
    private String catagoryID;
    private String importDate;
    private String usingDate;
    private boolean status;
    
    public Product() {
        this.productID = "";
        this.productName = "";
        this.image = "";
        this.price = 0;
        this.quantity = 0;
        this.catagoryID = "";
        this.importDate = "";
        this.usingDate = "";
        this.status = false;
    }

    public Product(String productID, String productName, String image, double price, int quantity, String catagoryID, String importDate, String usingDate, boolean status) {
        this.productID = productID;
        this.productName = productName;
        this.image = image;
        this.price = price;
        this.quantity = quantity;
        this.catagoryID = catagoryID;
        this.importDate = importDate;
        this.usingDate = usingDate;
        this.status = status;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCatagoryID() {
        return catagoryID;
    }

    public void setCatagoryID(String catagoryID) {
        this.catagoryID = catagoryID;
    }

    public String getImportDate() {
        return importDate;
    }

    public void setImportDate(String importDate) {
        this.importDate = importDate;
    }

    public String getUsingDate() {
        return usingDate;
    }

    public void setUsingDate(String usingDate) {
        this.usingDate = usingDate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    //So sánh giá, để dùng cho việc hiển thị product với giá từ thấp đến cao
     public static Comparator comparePrice = new Comparator(){
        public int compare(Object obj1, Object obj2){
            Product pd1 = (Product) obj1;
            Product pd2 = (Product) obj2;
            int t = 0;
            if(pd1.getPrice() < pd2.getPrice()){
                t = -1;
            }else if(pd1.getPrice() > pd2.getPrice()){
                t = 1;
            }
            return t;
        }
    };
}
