package entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

//servlet for add.jsp
@WebServlet("/add")
public class AddServlet extends HttpServlet {
    //create entity manager factory
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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = emf.createEntityManager();
        try {
            //get the list of items from the database
            List<Items> itemList = Main.getTable(em);
            //print out the list of items
//            System.out.println("List of items:");
//            Main.printTable(em);
            //set the attribute "items" to the list of items
            request.setAttribute("items", itemList);
            //forward the request to add.jsp
            RequestDispatcher dispatcher = request.getRequestDispatcher("/add.jsp");
            dispatcher.forward(request, response);

        } finally {
            // Close the EntityManager
            em.close();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //create entity manager
        EntityManager em = emf.createEntityManager();
        try {
            // Begin transaction
            em.getTransaction().begin();
            //add item to the database
            HelperServletFunctions.addItem(request, em);
            // Commit transaction
            em.getTransaction().commit();
            //redirect to index.jsp
            String contextPath = request.getContextPath();
            response.sendRedirect(contextPath + "/index");
        } catch (Exception e) {
            // Rollback transaction
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            // Close the EntityManager
            em.close();
        }
    }

}
