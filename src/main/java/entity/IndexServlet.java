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

// servlet for index.jsp
@WebServlet("/index")
public class IndexServlet extends HttpServlet {

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
            //get the list of items
            List<Items> itemList = Main.getTable(em);
            //print out the list of items
//            System.out.println("List of items:");
//            Main.printTable(em);
            //set the list of items as an attribute
            request.setAttribute("items", itemList);
            //forward the request to index.jsp
            RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
            dispatcher.forward(request, response);

        } finally {
            // Close the EntityManager
            em.close();
        }
    }

}