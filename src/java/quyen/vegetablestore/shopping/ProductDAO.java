/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quyen.vegetablestore.shopping;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import quyen.vegetablestore.users.UserDAO;
import quyen.vegetablestore.utils.DBUtils;

/**
 *
 * @author To Quyen Phan
 */
public class ProductDAO {

    private static final String GET_ALL_PRODUCTS = "SELECT productID, productName, image, price, quantity, catagoryID,"
            + " importDate, usingDate, status FROM tblProduct";
    private static final String GET_ALL_CATEGORY = "SELECT catagoryName from tblcategory";
    private static final String SEARCH = "SELECT productID, productName, image, price, quantity, catagoryID,"
            + " importDate, usingDate, status FROM tblProduct WHERE productName like ?";
    private static final String SEARCH_CATEGORY = "SELECT productID, productName, image, price, quantity, "
            + "tblProduct.catagoryID, importDate, usingDate, status FROM tblProduct join tblCategory on"
            + " tblProduct.catagoryID = tblCategory.catagoryID WHERE catagoryName like ?";
    private static final String DELETE = "DELETE tblProduct WHERE productID = ?";
    private static final String UPDATE = "UPDATE tblProduct SET productName = ?, image = ?, price = ?, "
            + "quantity = ?, catagoryID = ?, importDate = ?, usingDate = ?, status = ? WHERE productID = ?";
    private static final String VIEW_DETAIL = "SELECT productID, productName, image, price, quantity, catagoryID,"
            + " importDate, usingDate, status FROM tblProduct WHERE productID = ?";
    private static final String CHECKOUT = "SELECT quantity FROM tblProduct WHERE productID = ?";
    private static final String CREATE_ORDER = "INSERT INTO tblOrder(orderID, orderDate, total, userID) "
            + "VALUES(?,?,?,?)";
    private static final String CREATE_ORDER_DETAIL = "INSERT INTO tblOrderDetail(detailID, price, quantity, orderID, productID) VALUES(?,?,?,?,?)";
    private static final String CHECKOUT_UPDATE = "UPDATE tblProduct SET quantity = ? WHERE productID = ?";

    //L???y h???t s???n ph???m ????? hi???n th???
    public List<Product> getAllProducts() throws SQLException {
        List<Product> listAllProducts = new ArrayList<>();
        Connection conn = null;
        PreparedStatement psm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                psm = conn.prepareStatement(GET_ALL_PRODUCTS);
                rs = psm.executeQuery();
                while (rs.next()) {
                    String productID = rs.getString("productID");
                    String productName = rs.getString("productName");
                    String image = rs.getString("image");
                    double price = rs.getDouble("price");
                    int quantity = rs.getInt("quantity");
                    String catagoryID = rs.getString("catagoryID");
                    String importDate = rs.getString("importDate");
                    String usingDate = rs.getString("usingDate");
                    boolean status = rs.getBoolean("status");
                    listAllProducts.add(new Product(productID, productName, image, price, quantity, catagoryID, importDate, usingDate, status));
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
        return listAllProducts;
    }
    
    //L???y h???t lo???i product tron shop cho user ch???n
    public List<String> getAllCategory() throws SQLException {
        List<String> listCategory = new ArrayList<>();
        Connection conn = null;
        PreparedStatement psm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                psm = conn.prepareStatement(GET_ALL_CATEGORY);
                rs = psm.executeQuery();
                while (rs.next()) {
                    String categoryName = rs.getString("catagoryName");
                    listCategory.add(categoryName);
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
        return listCategory;
    }

    //T??m product theo t??n
    public List<Product> getListProduct(String search) throws SQLException {
        List<Product> listProduct = new ArrayList<>();
        Connection conn = null;
        PreparedStatement psm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                psm = conn.prepareStatement(SEARCH);
                psm.setString(1, "%" + search + "%");
                rs = psm.executeQuery();
                while (rs.next()) {
                    String productID = rs.getString("productID");
                    String productName = rs.getString("productName");
                    String image = rs.getString("image");
                    double price = rs.getDouble("price");
                    int quantity = rs.getInt("quantity");
                    String catagoryID = rs.getString("catagoryID");
                    String importDate = rs.getString("importDate");
                    String usingDate = rs.getString("usingDate");
                    boolean status = rs.getBoolean("status");
                    listProduct.add(new Product(productID, productName, image, price, quantity, catagoryID,
                            importDate, usingDate, status));
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
        return listProduct;
    }
    
    //T??m product theo lo???i
    public List<Product> getListCategory(String search) throws SQLException {
        List<Product> listProduct = new ArrayList<>();
        Connection conn = null;
        PreparedStatement psm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                psm = conn.prepareStatement(SEARCH_CATEGORY);
                psm.setString(1, "%" + search + "%");
                rs = psm.executeQuery();
                while (rs.next()) {
                    String productID = rs.getString("productID");
                    String productName = rs.getString("productName");
                    String image = rs.getString("image");
                    double price = rs.getDouble("price");
                    int quantity = rs.getInt("quantity");
                    String catagoryID = rs.getString("catagoryID");
                    String importDate = rs.getString("importDate");
                    String usingDate = rs.getString("usingDate");
                    boolean status = rs.getBoolean("status");
                    listProduct.add(new Product(productID, productName, image, price, quantity, catagoryID,
                            importDate, usingDate, status));
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
        return listProduct;
    }

    //Xo?? product b???ng productID
    public boolean deleteProduct(String productID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement psm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                psm = conn.prepareStatement(DELETE);
                psm.setString(1, productID);
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

    //C???p nh???t l???i product (trang admin nka)
    public boolean updateProduct(Product product) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement psm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                psm = conn.prepareStatement(UPDATE);
                psm.setString(1, product.getProductName());
                psm.setString(2, product.getImage());
                psm.setDouble(3, product.getPrice());
                psm.setInt(4, product.getQuantity());
                psm.setString(5, product.getCatagoryID());
                psm.setString(6, product.getImportDate());
                psm.setString(7, product.getUsingDate());
                psm.setInt(8, UserDAO.convertStatus(product.isStatus()));
                psm.setString(9, product.getProductID());
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

    //Hi???n th??? th??ng tin chi ti???t 1 product b???ng vi???c t??m productID c???a product ????
    public Product viewDetailProduct(String productID) throws SQLException {
        Product product = null;
        Connection conn = null;
        PreparedStatement psm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                psm = conn.prepareStatement(VIEW_DETAIL);
                psm.setString(1, productID);
                rs = psm.executeQuery();
                if (rs.next()) {
                    productID = rs.getString("productID");
                    String productName = rs.getString("productName");
                    String image = rs.getString("image");
                    double price = rs.getDouble("price");
                    int quantity = rs.getInt("quantity");
                    String catagoryID = rs.getString("catagoryID");
                    String importDate = rs.getString("importDate");
                    String usingDate = rs.getString("usingDate");
                    boolean status = rs.getBoolean("status");
                    product = new Product(productID, productName, image, price, quantity, catagoryID,
                            importDate, usingDate, status);
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
        return product;
    }

    //L???y s??? l?????ng product c??n trong kho trong qu?? tr??nh checkout
    public int getQuantity(String productID) throws SQLException {
        int quantity = 0;
        Connection conn = null;
        PreparedStatement psm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                psm = conn.prepareStatement(CHECKOUT);
                psm.setString(1, productID);
                rs = psm.executeQuery();
                if (rs.next()) {
                    quantity = rs.getInt("quantity");
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
        return quantity;
    }

    //T???o 1 order khi user checkout
    public boolean createOrder(Order order) throws SQLException, ClassNotFoundException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement psm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                psm = conn.prepareStatement(CREATE_ORDER);
                psm.setString(1, order.getOrderID());
                psm.setString(2, order.getOrderDate());
                psm.setDouble(3, order.getTotal());
                psm.setString(4, order.getUserID());
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

    //t???o th??ng tin chi ti???t c??c product ???? checkout
    public boolean createOrderDetail(OrderDetail orderDetail) throws SQLException, ClassNotFoundException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement psm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                psm = conn.prepareStatement(CREATE_ORDER_DETAIL);
                psm.setString(1, orderDetail.getDetailID());
                psm.setDouble(2, orderDetail.getPrice());
                psm.setInt(3, orderDetail.getQuantity());
                psm.setString(4, orderDetail.getOrderID());
                psm.setString(5, orderDetail.getProductID());
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

    //C???p nh???t l???i s??? l?????ng trong kho
    public boolean updateQuantity(int newQuantity, String productID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement psm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                psm = conn.prepareStatement(CHECKOUT_UPDATE);
                psm.setInt(1, newQuantity);
                psm.setString(2, productID);
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
