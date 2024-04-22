package com.backend;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
@WebServlet("/story")
public class Store extends HttpServlet{


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve data from the form
        String iName = request.getParameter("iName");
        String itemIDParam = request.getParameter("itemID");
        String priceParam = request.getParameter("price");
        String qntyParam = request.getParameter("qnty");

        int itemID = 0;
        int price = 0;
        int qnty = 0;

        if (itemIDParam != null && !itemIDParam.isEmpty()) {
            try {
                itemID = Integer.parseInt(itemIDParam);
            } catch (NumberFormatException e) {
                // Handle the case where itemIDParam is not a valid integer
                // Log an error, return an error response, etc.
            }
        }

        if (priceParam != null && !priceParam.isEmpty()) {
            try {
                price = Integer.parseInt(priceParam);
            } catch (NumberFormatException e) {
                // Handle the case where priceParam is not a valid integer
                // Log an error, return an error response, etc.
            }
        }

        if (qntyParam != null && !qntyParam.isEmpty()) {
            try {
                qnty = Integer.parseInt(qntyParam);
            } catch (NumberFormatException e) {
                // Handle the case where qntyParam is not a valid integer
                // Log an error, return an error response, etc.
            }
        }


        request.setAttribute("iName", iName);
        RequestDispatcher rd = request.getRequestDispatcher("/public/HTML/solar/store.jsp");
        rd.forward(request, response);








    }
}
