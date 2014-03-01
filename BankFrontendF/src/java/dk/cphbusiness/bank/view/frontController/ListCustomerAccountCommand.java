package dk.cphbusiness.bank.view.frontController;

import dk.cphbusiness.bank.contract.dto.AccountSummary;
import dk.cphbusiness.bank.contract.dto.CustomerIdentifier;
import dk.cphbusiness.bank.contract.dto.CustomerSummary;
import dk.cphbusiness.bank.view.Factory;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author joachim
 */
public class ListCustomerAccountCommand extends TargetCommand {

    public ListCustomerAccountCommand(String target) {
        super(target);
    }

    @Override
    public String execute(HttpServletRequest request) {
        CustomerIdentifier customer = CustomerIdentifier.fromString(request.getParameter("custid"));
        Collection<AccountSummary> accounts = Factory.getInstance().getManager().listCustomerAccounts(customer);
        
        request.setAttribute("accounts", accounts);
        request.setAttribute("message", "These are the account(s)");
        request.setAttribute("customer", customer);
        
        return super.execute(request);
    }
}
