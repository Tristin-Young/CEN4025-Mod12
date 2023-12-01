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

//servlet for root page
@WebServlet("/")
public class RootServlet extends HttpServlet {
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
            //get list of items
            List<Items> itemList = Main.getTable(em);
            //print out the list of items
//            System.out.println("List of items:");
//            Main.printTable(em);
            //st attribute to request
            request.setAttribute("items", itemList);
            //forward request to root
            RequestDispatcher dispatcher = request.getRequestDispatcher("/");
            dispatcher.forward(request, response);
            //redirect to index
            String contextPath = request.getContextPath();
            response.sendRedirect(contextPath + "/index");

        } finally {
            // Close the EntityManager
            em.close();
        }
    }
}

