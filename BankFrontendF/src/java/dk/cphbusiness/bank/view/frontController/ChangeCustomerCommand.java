package dk.cphbusiness.bank.view.frontController;

import dk.cphbusiness.bank.contract.BankManager;
import dk.cphbusiness.bank.contract.dto.CustomerDetail;
import dk.cphbusiness.bank.contract.dto.CustomerIdentifier;
import dk.cphbusiness.bank.contract.eto.NoSuchCustomerException;
import dk.cphbusiness.bank.view.Factory;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import security.SecurityRole;

/**
 *
 * @author joachim
 */
public class ChangeCustomerCommand extends TargetCommand {

    public ChangeCustomerCommand(String target, List<SecurityRole> roles) {
        super(target);
    }

    @Override
    public String execute(HttpServletRequest request) {
        BankManager manager = Factory.getInstance().getManager();
        CustomerIdentifier customerID = CustomerIdentifier.fromString(request.getParameter("custid"));
        
        try {
            CustomerDetail customer = manager.showCustomer(customerID);
            HttpSession session = request.getSession();
            session.setAttribute("customer", customer);
        } catch (NoSuchCustomerException ex) {
            Logger.getLogger(ChangeCustomerCommand.class.getName()).log(Level.SEVERE, null, ex);
        }        
        
        return super.execute(request);
    }

}
