
package entity; // Replace with your actual package name

import javax.persistence.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;



@WebServlet("/todo")
public class TodoListServlet extends HttpServlet {

    private EntityManagerFactory emf;

    @Override
    public void init() {
        // Initialize the EntityManagerFactory
        emf = Persistence.createEntityManagerFactory("default");
    }

    @Override
    public void destroy() {
        // Close the EntityManagerFactory when the servlet is destroyed
        emf.close();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = emf.createEntityManager();
        try {
            // Your existing GET handling code, using the 'em'
            // For example:
            List<Items> itemList = Main.getTable(em);
            //print out the list of items
            System.out.println("List of items:");
            Main.printTable(em);
            request.setAttribute("items", itemList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
            dispatcher.forward(request, response);
        } finally {
            em.close();
        }
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        EntityManager em = emf.createEntityManager();
        try {
            // Begin transaction
            em.getTransaction().begin();

            // Your existing POST handling code, using the 'em'
            // For example:
            String action = request.getParameter("action");
            switch (action) {
                case "add" -> addItem(request, em);
                case "delete" -> deleteItem(request, em);
                case "update" -> updateItem(request, em);
            }

            // Commit transaction
            em.getTransaction().commit();

            response.sendRedirect("todo");
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    private void addItem(HttpServletRequest request, EntityManager em) {
        String itemName = request.getParameter("itemName");
        String itemDescription = request.getParameter("itemDesc");
        Main.addToTable(em, itemName, itemDescription);
    }

    private void deleteItem(HttpServletRequest request, EntityManager em) {
        int itemId = Integer.parseInt(request.getParameter("itemId"));
        Main.delFromTable(em, itemId);
    }

    private void updateItem(HttpServletRequest request, EntityManager em) {
        int itemId = Integer.parseInt(request.getParameter("itemId"));
        Main.updateIsDone(em, itemId);

    }
}
