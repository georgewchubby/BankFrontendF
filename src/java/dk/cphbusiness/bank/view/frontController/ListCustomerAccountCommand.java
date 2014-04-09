package dk.cphbusiness.bank.view.frontController;

import dk.cphbusiness.bank.contract.dto.AccountSummary;
import dk.cphbusiness.bank.contract.dto.CustomerIdentifier;
import dk.cphbusiness.bank.view.Factory;
import java.util.Collection;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import dk.cphbusiness.bank.security.SecurityRole;

/**
 *
 * @author joachim
 */
public class ListCustomerAccountCommand extends TargetCommand {

    public ListCustomerAccountCommand(String target, List<SecurityRole> roles) {
        super(target,roles);
    }

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        CustomerIdentifier customer = CustomerIdentifier.fromString(request.getParameter("custid"));
        Collection<AccountSummary> accounts = Factory.getInstance().getManager().listCustomerAccounts(customer);
        
        session.setAttribute("accounts", accounts);
        request.setAttribute("message", "These are the account(s)");
        session.setAttribute("customer", customer);
        
        return super.execute(request);
    }
}
