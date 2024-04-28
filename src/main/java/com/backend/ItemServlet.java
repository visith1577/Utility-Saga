package com.backend;

import DAO.dao.ItemDetailsDAO;
import DAO.impl.ItemDetails;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.ItemModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.Constant;
import utils.MethodUtility;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@WebServlet("/item/*")
@MultipartConfig
public class ItemServlet extends HttpServlet {

    private final static Logger logger = LogManager.getLogger(ItemServlet.class);

    private ItemDetails itemDetails;


    public ItemServlet() {
        this.itemDetails = new ItemDetailsDAO();
    }

    @Override
    public void init() throws ServletException {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        logger.info("ItemServlet ::");
        String requestURI = req.getRequestURI();
        logger.info("ItemServlet :: doGet :: "+requestURI);

        if (requestURI.startsWith(req.getContextPath() + "/item/all")) {
            logger.info("Get All items..");
            try {
                showAllItems(req, resp);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else if (requestURI.equals(req.getContextPath() + "/item/edit")) {
            logger.info("Edit Item :: id = "+req.getParameter("itemId"));
            try {
                int id = Integer.parseInt(req.getParameter("itemId"));
                editItem(req, resp, id);
            } catch (NumberFormatException e) {
                throw new RuntimeException("Item ID should be a number");
            }

        }else if (requestURI.equals(req.getContextPath() + "/item/edit")) {
            logger.info("Edit Item :: id = "+req.getParameter("itemId"));
            try {
                int id = Integer.parseInt(req.getParameter("itemId"));
                editItem(req, resp, id);
            } catch (NumberFormatException e) {
                throw new RuntimeException("Item ID should be a number");
            }

        } else if (requestURI.equals(req.getContextPath() + "/item/supplierItems")) {
            try {
                int supplierId = Integer.parseInt(req.getParameter("supplierId"));
                logger.info("supplierId: " + supplierId);
                supplierItem(req, resp, supplierId);
            } catch (NumberFormatException | SQLException e) {
                logger.error("Item ID should be a number : received Supplier Id :: "+req.getParameter("supplierId"));
                throw new RuntimeException("Item ID should be a number");
            }
        }else if (requestURI.equals(req.getContextPath() + "/item/delete")) {
            try {
                logger.info("Delete the Item : id = "+req.getParameter(Constant.ITEM_KEY_ID));
                int requestId = Integer.parseInt(req.getParameter(Constant.ITEM_KEY_ID));
                int supplierId = Integer.parseInt(req.getParameter(Constant.ITEM_KEY_SUPPLIER_ID));
                Boolean isDeleted = itemDetails.deleteItemById(requestId);
                supplierItem(req, resp, supplierId);
            } catch (NumberFormatException e) {
                logger.error("Item ID should be a number : received Supplier Id :: "+req.getParameter("supplierId"));
                    throw new RuntimeException("Item ID should be a number");
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("Error");
            // Error
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        logger.info("requestURI: " + requestURI);

        if (requestURI.startsWith(req.getContextPath() + "/item/add")) {
            ItemModel itemModel = getItemModelRequestParameter(req);
            ItemDetails itemDetails = new ItemDetailsDAO();
            try {
                Integer itemID = itemDetails.addItem(itemModel);
                logger.info("Added Item ID : " + itemID);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            RequestDispatcher rd = req.getRequestDispatcher(Constant.SOLAR_STORE_JSP);
            rd.forward(req, resp);
        } else if(requestURI.startsWith(req.getContextPath() + "/item/edit")) {
            ItemModel itemModel = updateItemModelRequestParameter(req);
            itemModel.setItemID(Integer.parseInt(req.getParameter(Constant.ITEM_KEY_ID)));
            itemModel.setSupplierID(Integer.parseInt(req.getParameter(Constant.ITEM_KEY_SUPPLIER_ID)));

            try {
                itemDetails.updateItem( itemModel);
                supplierItem(req, resp, Integer.parseInt(req.getParameter(Constant.ITEM_KEY_SUPPLIER_ID)));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            ItemDetails itemDetails = new ItemDetailsDAO();
            try {
                Integer itemID = itemDetails.updateItem(itemModel);
                logger.info("Added Item ID : " + itemID);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }else {
            System.out.println("Error");
            // Error
        }

    }


    private ItemModel getItemModelRequestParameter(HttpServletRequest req) throws ServletException, IOException {
//        Integer id = (Integer) getValue(req.getParameter(Constant.ITEM_KEY_ID), Integer.class);
        String name = (String) getValue(req.getParameter(Constant.ITEM_KEY_NAME), String.class);
        String description = (String) getValue(req.getParameter(Constant.ITEM_KEY_DESC), String.class);
        Double cost = (Double) getValue(req.getParameter(Constant.ITEM_KEY_COST), Double.class);
        Double profitMargin = (Double) getValue(req.getParameter(Constant.ITEM_KEY_PROFIT_MARGIN), Double.class);
        Double price = (Double) getValue(req.getParameter(Constant.ITEM_KEY_PRICE), Double.class);
        Integer warrantyPeriod = (Integer) getValue(req.getParameter(Constant.ITEM_KEY_WARRANTY_PERIOD), Integer.class);
        Integer quantity = (Integer) getValue(req.getParameter(Constant.ITEM_KEY_QUANTITY), Integer.class);
        Integer supplierID = (Integer) getValue(req.getParameter(Constant.ITEM_KEY_SUPPLIER_ID), Integer.class);

        Part imageFile = req.getPart(Constant.ITEM_KEY_IMAGE_FILE);
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

        return itemModel;
    }

    private ItemModel updateItemModelRequestParameter(HttpServletRequest req) throws ServletException, IOException {

        String name = (String) getValue(req.getParameter(Constant.ITEM_KEY_NAME), String.class);
        String description = (String) getValue(req.getParameter(Constant.ITEM_KEY_DESC), String.class);
        Double cost = (Double) getValue(req.getParameter(Constant.ITEM_KEY_COST), Double.class);
        Double profitMargin = (Double) getValue(req.getParameter(Constant.ITEM_KEY_PROFIT_MARGIN), Double.class);
        Double price = (Double) getValue(req.getParameter(Constant.ITEM_KEY_PRICE), Double.class);
        Integer warrantyPeriod = (Integer) getValue(req.getParameter(Constant.ITEM_KEY_WARRANTY_PERIOD), Integer.class);
        Integer quantity = (Integer) getValue(req.getParameter(Constant.ITEM_KEY_QUANTITY), Integer.class);
        Integer supplierID = (Integer) getValue(req.getParameter(Constant.ITEM_KEY_SUPPLIER_ID), Integer.class);


        ItemModel itemModel = new ItemModel();

        itemModel.setItemName(name);
        itemModel.setDescription(description);
        itemModel.setCost(cost);
        itemModel.setProfitMargin(profitMargin);
        itemModel.setPrice(price);
        itemModel.setWarrantyPeriod(warrantyPeriod);
        itemModel.setQuantity(quantity);
        itemModel.setSupplierID(supplierID);
        return itemModel;
    }

    private static Object getValue(Object value, Class<?> clasz) {
        return MethodUtility.convertRequestParamValue(value, clasz);
    }

//    Need to resolve the below methods
private void supplierItem(HttpServletRequest req, HttpServletResponse resp, int supplierId) throws SQLException, ServletException, IOException {
    logger.info("Retrieve all the supplierItems");
    Optional<List<ItemModel>> optionalItemModels = itemDetails.selectAllItemsBySupplierId(supplierId);
    List<ItemModel> itemModels = new ArrayList<>();
    int itemCount = 0;
    if(optionalItemModels.isPresent()){
        itemCount = optionalItemModels.get().size();
        itemModels.addAll(optionalItemModels.get());
    }
    logger.info("Retrieved supplierItems : "+itemCount);
    req.setAttribute("listItems", itemModels);
    RequestDispatcher rd = req.getRequestDispatcher(Constant.SOLAR_ITEM_LIST_JSP);
    rd.forward(req, resp);
}

    private void editItem(HttpServletRequest req, HttpServletResponse resp, int id) {
        try {
            logger.info("Get Item from DB :: id = "+id);
            Optional<ItemModel> optionalItemModel = itemDetails.selectItemById(id);
            if (optionalItemModel.isEmpty()) {
                logger.error("Item not found : id = " + id);
                throw new RuntimeException("Item not found : id = " + id);
            }
            req.setAttribute("item", optionalItemModel.get());
            RequestDispatcher rd = req.getRequestDispatcher(Constant.SOLAR_EDITITEM_JSP);
            rd.forward(req, resp);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void showAllItems(HttpServletRequest req, HttpServletResponse res) throws SQLException, ServletException, IOException {
        logger.info("Retrieve all the Items");
        Optional<List<ItemModel>> optionalItemModels = itemDetails.selectAllItems();
        List<ItemModel> itemModels = new ArrayList<>();
        int itemCount = 0;
        if(optionalItemModels.isPresent()){
            itemCount = optionalItemModels.get().size();
            itemModels.addAll(optionalItemModels.get());
        }
        logger.info("Retrieved Items : "+itemCount);
        req.setAttribute("listItems", itemModels);
        RequestDispatcher rd = req.getRequestDispatcher(Constant.SOLAR_COMPANY_STORE_JSP);
        rd.forward(req, res);
    }


}
