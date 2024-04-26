package DAO.dao;

import model.UserAccountsModel;
import model.UserModel;
import utils.Connectdb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserBillPaymentDAO {
    public List<UserAccountsModel> getUserAccounts(String nic, String category) throws SQLException {
        Connection connection = Connectdb.getConnection();
        List<UserAccountsModel> account_list = new ArrayList<>();

        try {
            String tableName = selectTable(category);
            System.out.println("Hell");
            System.out.println(tableName);

            PreparedStatement statement = connection.prepareStatement(
                    "SELECT  account_number FROM " + tableName + " WHERE nic = ?"
            );

            statement.setString(1, nic);
            System.out.println(nic);


            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    UserAccountsModel model = new UserAccountsModel();
                    model.setAccount_number(result.getString("account_number"));

                    if (category.equals("WATER")) {
                        model.setType(UserAccountsModel.Acc.WATER);
                    } else {
                        model.setType(UserAccountsModel.Acc.ELECTRICITY);
                    }

                    account_list.add(model);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Connectdb.closeConnection(connection);
        }
        return account_list;
    }

    private String selectTable(String category) {
        String tableName;
        System.out.println(category);
        switch (category.toUpperCase()) {
            case "WATER":
                tableName = "wAccount_list";
                break;
            case "ELECTRICITY":
                tableName = "eAccount_list";
                break;
            default:
                throw new IllegalArgumentException("Invalid table name: " + category);
        }

        return tableName;
    }
}