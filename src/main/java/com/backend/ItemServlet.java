package com.backend;

import DAO.dao.ItemDetailsDAO;
import DAO.impl.ItemDetails;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.ItemModel;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/item/*")
public class ItemServlet extends HttpServlet {
    private ItemDetails itemDetails;


    public ItemServlet() {
        this.itemDetails = new ItemDetailsDAO();
    }

    @Override
    public void init() throws ServletException {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();

        if (requestURI.startsWith(req.getContextPath() + "/item/all")) {
            System.out.println("All items");
            try {
                showAllItems(req, resp);
                System.out.println("All items");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else if (requestURI.equals(req.getContextPath() + "/item/edit")) {
            System.out.println("item with id 1");
            try {
                int id = Integer.parseInt(req.getParameter("itemId"));
                System.out.println("Item Id: " + id);
                editItem(req, resp, id);
            } catch (NumberFormatException e) {
                throw new RuntimeException("Item ID should be a number");
            }

        } else if (requestURI.equals(req.getContextPath() + "/item/supplierItems")) {

            try {
                int supplierId = Integer.parseInt(req.getParameter("supplierId"));
                System.out.println("Item Id: " + supplierId);
                supplierItem(req, resp, supplierId);
            } catch (NumberFormatException | SQLException e) {
                throw new RuntimeException("Item ID should be a number");
            }
        } else {
            System.out.println("Error");
            // Error
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        System.out.println(requestURI);
        if (requestURI.startsWith(req.getContextPath() + "/item/add")) {

            String name = req.getParameter("itemName");
            String description = req.getParameter("description");
            String costParam = req.getParameter("cost");
            String profitMarginParam = req.getParameter("profit_margin");
            String priceParam = req.getParameter("price");
            String warrantyPeriodParam = req.getParameter("warranty_period");
            String quantityParam = req.getParameter("quantity");
            String supplierIDParam = req.getParameter("supplier_id");

            double cost = Double.parseDouble(costParam.trim());
            double profitMargin = Double.parseDouble(profitMarginParam.trim());
            double price = Double.parseDouble(priceParam.trim());
            int warrantyPeriod = Integer.parseInt(warrantyPeriodParam.trim());
            int quantity = Integer.parseInt(quantityParam.trim());
            int supplierID = Integer.parseInt(supplierIDParam.trim());

            Part imageFile = req.getPart("imageFile");
            String imageName = imageFile.getSubmittedFileName();
            InputStream imageStream = imageFile.getInputStream();

            ItemModel itemModel = new ItemModel();
            itemModel.setItemName(name);
            itemModel.setDescription(description);
            itemModel.setCost(cost);
            itemModel.setProfitMargin(profitMargin);
            itemModel.setPrice(price);
            itemModel.setWarrantyPeriod(warrantyPeriod);
            itemModel.setQuantity(quantity);
            itemModel.setSupplierID(supplierID);
            itemModel.setImageName(imageName);
            itemModel.setImageStream(imageStream);

            ItemDetails itemDetails = new ItemDetailsDAO();
            try {
                Integer itemID = itemDetails.addItem(itemModel);
                System.out.println("item Id: " + itemID);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        RequestDispatcher rd = req.getRequestDispatcher("/public/HTML/solar/store.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("PUT");
        int id = Integer.parseInt(req.getParameter("itemId"));
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        double cost = Double.parseDouble(req.getParameter("cost"));
        double profitMargin = Double.parseDouble(req.getParameter("profit_margin"));
        double price = Double.parseDouble(req.getParameter("price"));
        int warrantyPeriod = Integer.parseInt(req.getParameter("warranty_period"));
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        int supplierID = Integer.parseInt(req.getParameter("supplier_id"));

        ItemModel itemModel = new ItemModel();
        itemModel.setItemName(name);
        itemModel.setDescription(description);
        itemModel.setCost(cost);
        itemModel.setProfitMargin(profitMargin);
        itemModel.setPrice(price);
        itemModel.setWarrantyPeriod(warrantyPeriod);
        itemModel.setQuantity(quantity);
        itemModel.setSupplierID(supplierID);

        try {
            itemDetails.updateItem(itemModel , id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int requestId = Integer.parseInt(req.getParameter("itemId"));
        Boolean isDeleted = itemDetails.deleteItemById(requestId);
    }

    private void supplierItem(HttpServletRequest req, HttpServletResponse resp, int supplierId) throws SQLException, ServletException, IOException {
        List<ItemModel> items = itemDetails.selectAllItemsBySupplierId(supplierId);
        req.setAttribute("listItems", items);
        RequestDispatcher rd = req.getRequestDispatcher("/public/HTML/solar/itemList.jsp");
        rd.forward(req, resp);
    }

    private void editItem(HttpServletRequest req, HttpServletResponse resp, int id) {
        try {
            ItemModel item = itemDetails.selectItemById(id);
            if (item == null) {
                throw new RuntimeException("Item not found : id = " + id);
            }
            req.setAttribute("item", item);
            RequestDispatcher rd = req.getRequestDispatcher("/public/HTML/solar/editItem.jsp");
            rd.forward(req, resp);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void showAllItems(HttpServletRequest req, HttpServletResponse res) throws SQLException, ServletException, IOException {

        List<ItemModel> items = itemDetails.selectAllItems();
        req.setAttribute("listItems", items);
        RequestDispatcher rd = req.getRequestDispatcher("/public/HTML/solar/itemList.jsp");
        rd.forward(req, res);

    }


}
