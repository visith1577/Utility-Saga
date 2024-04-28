package DAO.dao;

import DAO.impl.ItemDetails;
import model.ItemModel;
import utils.DBConnectionManager;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class ItemDetailsDAO implements ItemDetails {
    private static final String DELETE_ITEM_BY_ID = "DELETE FROM item WHERE id = ?";
    private static final String SELECT_ALL_ITEMS = "SELECT * FROM item";
    private static final String SELECT_ITEM_BY_ID = "SELECT * FROM item WHERE id =?";
    private static final String SELECT_ALL_ITEMS_BY_SUPPLIER_ID = "SELECT * FROM item WHERE supplier_id =?";
    private static final String ADD_ITEM = "INSERT INTO item (name, description, cost, profit_margin, price, warranty_period, quantity, supplier_id , image_name, image) VALUES (?, ?, ?, ?, ?, ?, ?, ? , ?, ?)";
    private static final String UPDATE_ITEM = "UPDATE item SET name = ?, description = ?, cost = ?, profit_margin = ?, price = ?, warranty_period = ?, quantity = ?, supplier_id = ? WHERE id = ?" ;

    private DBConnectionManager dbConnectionManager;

    public ItemDetailsDAO() {
        try {
            dbConnectionManager = DBConnectionManager.getInstance();
        } catch (IOException e) {
            throw new RuntimeException("IOException : ", e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("ClassNotFoundException : ", e);
        } catch (SQLException e) {
            throw new RuntimeException("SQLException : ", e);
        }

    }


    @Override
    public Integer addItem(ItemModel itemModel) throws SQLException {
        Integer id = null;
        try(PreparedStatement preparedStatement = dbConnectionManager.getConnection().prepareStatement(ADD_ITEM, Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.setString(1, itemModel.getItemName());
            preparedStatement.setString(2, itemModel.getDescription());
            preparedStatement.setDouble(3, itemModel.getCost());
            preparedStatement.setDouble(4, itemModel.getProfitMargin());
            preparedStatement.setDouble(5, itemModel.getPrice());
            preparedStatement.setInt(6, itemModel.getWarrantyPeriod());
            preparedStatement.setInt(7, itemModel.getQuantity());
            preparedStatement.setInt(8, itemModel.getSupplierID());
            preparedStatement.setString(9, itemModel.getImageName());
            preparedStatement.setBlob(10, itemModel.getImageStream());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            rs.next();
            id = rs.getInt(1);
            System.out.println("Item Id: " + id);
        }
        return id;
    }

    @Override
    public Integer updateItem(ItemModel itemModel) throws SQLException {

        try(PreparedStatement preparedStatement = dbConnectionManager.getConnection().prepareStatement(UPDATE_ITEM)){
            preparedStatement.setString(1, itemModel.getItemName());
            preparedStatement.setString(2, itemModel.getDescription());
            preparedStatement.setDouble(3, itemModel.getCost());
            preparedStatement.setDouble(4, itemModel.getProfitMargin());
            preparedStatement.setDouble(5, itemModel.getPrice());
            preparedStatement.setInt(6, itemModel.getWarrantyPeriod());
            preparedStatement.setInt(7, itemModel.getQuantity());
            preparedStatement.setInt(8, itemModel.getSupplierID());
            preparedStatement.setInt(9, itemModel.getItemID());
            preparedStatement.executeUpdate();
//            ResultSet rs = preparedStatement.getGeneratedKeys();
//            rs.next();
        }


        return null;
    }

    @Override
    public Boolean deleteItemById(Integer id) {
        PreparedStatement preparedStatement = null;
        boolean falg = false;
        try{
            preparedStatement = this.dbConnectionManager.getConnection().prepareStatement(DELETE_ITEM_BY_ID);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            falg = true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return falg;
    }

    @Override
    public Optional<ItemModel> selectItemById(Integer id) {
        ResultSet rs = null;
        PreparedStatement preparedStatement = null;
        ItemModel item = null;
        try {
            preparedStatement = this.dbConnectionManager.getConnection().prepareStatement(SELECT_ITEM_BY_ID);
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                item = new ItemModel();
                item.setItemID(rs.getInt("id"));
                item.setItemName(rs.getString("name"));
                item.setDescription(rs.getString("description"));
                item.setCost(rs.getDouble("cost"));
                item.setProfitMargin(rs.getDouble("profit_margin"));
                item.setPrice(rs.getDouble("price"));
                item.setWarrantyPeriod(rs.getInt("warranty_period"));
                item.setQuantity(rs.getInt("quantity"));
                item.setSupplierID(rs.getInt("supplier_id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.ofNullable(item);
    }

    @Override
    public Optional<List<ItemModel>> selectAllItemsBySupplierId(Integer supplierID) throws SQLException {
        List<ItemModel> items = new ArrayList<>();
        ResultSet rs =null;
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = this.dbConnectionManager.getConnection().prepareStatement(SELECT_ALL_ITEMS_BY_SUPPLIER_ID);
            preparedStatement.setInt(1 , supplierID);
            rs = preparedStatement.executeQuery();
            System.out.println(supplierID);
            while(rs.next()) {
                ItemModel item = new ItemModel();
                item.setItemID(rs.getInt("id"));
                item.setItemName(rs.getString("name"));
                item.setDescription(rs.getString("description"));
                item.setCost(rs.getDouble("cost"));
                item.setProfitMargin(rs.getDouble("profit_margin"));
                item.setPrice(rs.getDouble("price"));
                item.setWarrantyPeriod(rs.getInt("warranty_period"));
                item.setQuantity(rs.getInt("quantity"));
                item.setSupplierID(rs.getInt("supplier_id"));
                items.add(item);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println(items.size());
        return Optional.ofNullable(items);
    }

    @Override
    public Optional<List<ItemModel>> selectAllItems() throws SQLException {
        ResultSet rs = this.dbConnectionManager.executeQuery(SELECT_ALL_ITEMS);
        List<ItemModel> items = new ArrayList<>();
        while(rs.next()) {
            ItemModel item = new ItemModel();
            item.setItemID(rs.getInt("id"));
            item.setItemName(rs.getString("name"));
            item.setDescription(rs.getString("description"));
            item.setCost(rs.getDouble("cost"));
            item.setProfitMargin(rs.getDouble("profit_margin"));
            item.setPrice(rs.getDouble("price"));
            item.setWarrantyPeriod(rs.getInt("warranty_period"));
            item.setQuantity(rs.getInt("quantity"));
            item.setSupplierID(rs.getInt("supplier_id"));
            item.setImageName(rs.getString("image_name"));
            item.setImageStream(rs.getBlob("image").getBinaryStream());

            items.add(item);
        }
        return Optional.ofNullable(items);
    }
}
