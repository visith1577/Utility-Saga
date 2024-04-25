package DAO.impl;

import model.ItemModel;

import java.sql.SQLException;
import java.util.List;

public interface ItemDetails {
    Integer addItem(ItemModel itemModel) throws SQLException;
    void updateItem(ItemModel itemModel , Integer id) throws SQLException;
    Integer deleteItemById(Integer id) ;
    ItemModel selectItemById(Integer id);
    List<ItemModel> selectAllItemsBySupplierId(Integer id) throws SQLException;
    List<ItemModel> selectAllItems() throws SQLException;
}
