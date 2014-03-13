package dk.cphbusiness.bank.view.frontController;

import dk.cphbusiness.bank.contract.BankManager;
import dk.cphbusiness.bank.contract.dto.AccountDetail;
import dk.cphbusiness.bank.contract.dto.AccountSummary;
import dk.cphbusiness.bank.contract.dto.CheckingAccountDetail;
import dk.cphbusiness.bank.contract.dto.CustomerIdentifier;
import dk.cphbusiness.bank.contract.eto.CustomerBannedException;
import dk.cphbusiness.bank.contract.eto.NoSuchCustomerException;
import dk.cphbusiness.bank.view.Factory;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import security.SecurityRole;

/**
 *
 * @author joachim
 */
public class SaveAccountCommand extends TargetCommand {

    public SaveAccountCommand(String target, List<SecurityRole> roles) {
        super(target,roles);
    }

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        BankManager manager = Factory.getInstance().getManager();

        CustomerIdentifier customer = new CustomerIdentifier(request.getParameter("customerCpr"));
        BigDecimal interest = BigDecimal.valueOf(Double.parseDouble(request.getParameter("accountInterest")));
        AccountDetail accountDetail = new CheckingAccountDetail(null, interest, null);

        try {
            manager.createAccount(customer, accountDetail);
        } catch (NoSuchCustomerException | CustomerBannedException ex) {
        }
        
        Collection<AccountSummary> accounts = Factory.getInstance().getManager().listCustomerAccounts(customer);
        
        session.setAttribute("accounts", accounts);
        request.setAttribute("message", "These are the account(s)");
        session.setAttribute("customer", customer);

        return super.execute(request);
    }
}
