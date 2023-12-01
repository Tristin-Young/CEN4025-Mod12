package entity;

import javax.persistence.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

// This class contains helper functions for the servlets
public class HelperServletFunctions extends HttpServlet {

    //add item to database
    public static void addItem(HttpServletRequest request, EntityManager em) {
        //get parameters from request
        String itemName = request.getParameter("itemName");
        String itemDescription = request.getParameter("itemDesc");
        //add item to database through Main class
        Main.addToTable(em, itemName, itemDescription);
    }
    //delete item from database
    public static void deleteItem(HttpServletRequest request, EntityManager em) {
        //get parameters from request
        int itemId = Integer.parseInt(request.getParameter("itemId"));
        //delete item from database through Main class
        Main.delFromTable(em, itemId);
    }
    //delete item maliciously from database
//    public static void deleteItemMalicious(HttpServletRequest request, EntityManager em) {
//        //get parameters from request as a string, not an int
//        String itemId = request.getParameter("itemId");
//        //delete item from database through Main class
//        Main.delFromTableMalicious(em, itemId);
//    }
    //update item in database
    public static void updateItem(HttpServletRequest request, EntityManager em) {
        //get parameters from request
        int itemId = Integer.parseInt(request.getParameter("itemId"));
        //update item in database through Main class
        Main.updateIsDone(em, itemId);

    }
}
