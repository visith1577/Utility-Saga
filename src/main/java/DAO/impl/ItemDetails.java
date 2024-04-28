package DAO.impl;

import model.ItemModel;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface ItemDetails {
    Integer addItem(ItemModel itemModel) throws SQLException;
    Integer updateItem(ItemModel itemModel) throws SQLException;
    Boolean deleteItemById(Integer id) ;
    Optional<ItemModel> selectItemById(Integer id ) throws SQLException;
    Optional<List<ItemModel>> selectAllItemsBySupplierId(Integer id) throws SQLException;
    Optional<List<ItemModel>> selectAllItems() throws SQLException;
}
