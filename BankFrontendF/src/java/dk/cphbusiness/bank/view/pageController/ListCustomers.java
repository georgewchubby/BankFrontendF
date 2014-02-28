package dk.cphbusiness.bank.view.pageController;

import dk.cphbusiness.bank.contract.BankManager;
import dk.cphbusiness.bank.contract.dto.CustomerSummary;
import dk.cphbusiness.bank.view.Factory;
import java.io.IOException;
import java.util.Collection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author joachim
 */
@WebServlet(name = "ListCustomers", urlPatterns = {"/ListCustomers"})
public class ListCustomers extends HttpServlet {
    
    @Override
    protected void service(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        BankManager manager = Factory.getInstance().getManager();
        Collection<CustomerSummary> customers = manager.listCustomers();

        request.setAttribute("message", "This is a test");
        request.setAttribute("customer", customers);

        RequestDispatcher dispatcher = request.getRequestDispatcher("account-list.jsp");
        dispatcher.forward(request, response);
    }
}
