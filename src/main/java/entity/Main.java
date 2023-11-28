package entity;

import javax.persistence.*;
import java.util.List;
import java.util.logging.Logger;

public class Main {

    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static List<Items> getTable(EntityManager em){
        Query q = em.createNamedQuery("getTable" , Items.class);
        List<Items> result = (List<Items>) q.getResultList();
        LOGGER.info("Items list retrieved, size: " + (result == null ? "null" : result.size()));

        return result;
    }

    public static void printTable(EntityManager em){
        Query q = em.createNamedQuery("getTable" , Items.class);
        List<Items> result = (List<Items>) q.getResultList();
        if(result.isEmpty()){
            System.out.println("No items in table");
            System.out.println();
        }
        for (Items item : result) {
            System.out.println(item.getItemId() + " : " + item.getItemName());
            System.out.println("Desc: " + item.getItemDescription());
            System.out.println("Is Done: " + item.getIsDone());
            System.out.println(); //blank line
        }
    }

    public static void delFromTable(EntityManager em, int idx){

        Query q = em.createNativeQuery("DELETE FROM Items WHERE itemID = ?1");
        q.setParameter(1, idx);
        q.executeUpdate();
    }

    public static void addToTable(EntityManager em, String name, String desc){
        Query q = em.createNativeQuery("INSERT INTO Items (itemName, itemDescription, isDone) VALUES (?1, ?2, 0)");
        q.setParameter(1, name);
        q.setParameter(2, desc);
        q.executeUpdate();
    }

    public static void updateIsDone(EntityManager em, int idx){

        Query q = em.createNativeQuery("UPDATE Items p SET p.isDone = 1 WHERE p.itemID = ?1");
        q.setParameter(1, idx);
        q.executeUpdate();

    }

    public static void main(String[] args) {
//
//        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        EntityTransaction transaction = entityManager.getTransaction();
//
//
//        boolean isExit = false;
//        int choice;
//        Scanner scanner = new Scanner(System.in);
//        while (!isExit) {
//            displayMenu();
//            choice = getChoice(scanner);
//            switch (choice) {
//                case 1:
//                    //ADD TODO ITEM
//                    try {
//                        transaction.begin();
//                        //get item name and description
//                        System.out.println("Enter item name: ");
//                        String itemName = scanner.nextLine();
//                        System.out.println("Enter item description: ");
//                        String itemDescription = scanner.nextLine();
//                        //set item name and description
//                        addToTable(entityManager, itemName, itemDescription);
//
//                        //TypedQuery<Employee> empByDeptQuery = entityManager.createNamedQuery("Employee.byDept", Employee.class);
//
//                        transaction.commit();
//                    } finally {
//                        if (transaction.isActive()) {
//                            transaction.rollback();
//                        }
//
//                    }
//                    break;
//                case 2:
//                    //DELETE TODO ITEM
//                    try{
//                        transaction.begin();
//                        printTable(entityManager);
//                        System.out.println("Enter item ID to delete: ");
//                        int itemId = scanner.nextInt();
//                        scanner.nextLine();
//                        delFromTable(entityManager, itemId);
//                    } finally {
//                        if (transaction.isActive()) {
//                            transaction.rollback();
//                        }
//
//                    }
//                    break;
//                case 3:
//                    //VIEW TODO ITEMS
//                    try{
//                        transaction.begin();
//                        printTable(entityManager);
//                        transaction.commit();
//                    } finally {
//                        if (transaction.isActive()) {
//                            transaction.rollback();
//                        }
//                    }
//                    break;
//                case 4:
//                    //MARK TODO ITEM AS DONE
//                    try{
//                        transaction.begin();
//                        printTable(entityManager);
//                        System.out.println("Enter item ID to mark as done: ");
//                        int itemId = scanner.nextInt();
//                        scanner.nextLine();
//                        updateIsDone(entityManager, itemId);
//                        transaction.commit();
//                    } finally {
//                        if (transaction.isActive()) {
//                            transaction.rollback();
//                        }
//
//                    }
//                    break;
//                case 5:
//                    //EXIT
//                    isExit = true;
//                    break;
//                default:
//                    System.out.println("Invalid choice");
//            }
//
//        }
//
//
//        entityManager.close();
//        entityManagerFactory.close();
   }

}
