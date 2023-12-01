package entity;

import javax.persistence.*;
import java.util.List;


public class Main {

    //function to get list of items from database
    public static List<Items> getTable(EntityManager em) {
        //create query (getTable is a named query in Items.java)
        Query q = em.createNamedQuery("getTable", Items.class);
        //get result list and return
        List<Items> result = (List<Items>) q.getResultList();
        return result;
    }

    //function to display table
    public static void printTable(EntityManager em) {
        //create query (getTable is a named query in Items.java)
        Query q = em.createNamedQuery("getTable", Items.class);
        //get result list
        List<Items> result = (List<Items>) q.getResultList();
        //if list is empty, print message
        if (result.isEmpty()) {
            System.out.println("No items in table");
            System.out.println();
        }
        //otherwise, for each item in the result list, print item details
        for (Items item : result) {
            System.out.println(item.getItemId() + " : " + item.getItemName());
            System.out.println("Desc: " + item.getItemDescription());
            System.out.println("Is Done: " + item.getIsDone());
            System.out.println(); //blank line
        }
    }

    //function to delete from table
    public static void delFromTable(EntityManager em, int idx) {
        //create query
        Query q = em.createNativeQuery("DELETE FROM Items WHERE itemID = ?1");
        //set parameter
        q.setParameter(1, idx);
        //execute query
        q.executeUpdate();
    }

    //function to delete from table maliciously
//    public static void delFromTableMalicious(EntityManager em, String idx) {
//        //create query (but pass in the string directly)
//        Query q = em.createNativeQuery("DELETE FROM Items WHERE itemID = " + idx);
//        //execute query
//        q.executeUpdate();
//    }

    //function to add to table
    public static void addToTable(EntityManager em, String name, String desc) {
        //create query
        Query q = em.createNativeQuery("INSERT INTO Items (itemName, itemDescription, isDone) VALUES (?1, ?2, 0)");
        //set parameters
        q.setParameter(1, name);
        q.setParameter(2, desc);
        //execute query
        q.executeUpdate();
    }

    //function to update item in table to completed
    public static void updateIsDone(EntityManager em, int idx) {
        //create query
        Query q = em.createNativeQuery("UPDATE Items p SET p.isDone = 1 WHERE p.itemID = ?1");
        //set parameter
        q.setParameter(1, idx);
        //execute query
        q.executeUpdate();
    }

}
