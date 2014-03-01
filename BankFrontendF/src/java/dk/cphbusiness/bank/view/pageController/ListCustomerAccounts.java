package dk.cphbusiness.bank.view.pageController;

import dk.cphbusiness.bank.contract.BankManager;
import dk.cphbusiness.bank.contract.dto.AccountSummary;
import dk.cphbusiness.bank.contract.dto.CustomerIdentifier;
import dk.cphbusiness.bank.contract.dto.CustomerSummary;
import dk.cphbusiness.bank.view.Factory;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "ListCustomerAccounts", urlPatterns = {"/ListCustomerAccounts"})
public class ListCustomerAccounts extends HttpServlet {

    @Override
    protected void service(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        BankManager manager = Factory.getInstance().getManager();
        CustomerIdentifier id = CustomerIdentifier.fromString(request.getParameter("custid"));
        Collection<AccountSummary> accounts = manager.listCustomerAccounts(id);

        RequestDispatcher dispatcher = request.getRequestDispatcher("account-list.jsp");
        dispatcher.forward(request, response);
    }
}
