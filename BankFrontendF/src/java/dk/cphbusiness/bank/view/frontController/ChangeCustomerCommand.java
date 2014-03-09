package dk.cphbusiness.bank.view.frontController;

import dk.cphbusiness.bank.contract.BankManager;
import dk.cphbusiness.bank.contract.dto.CustomerDetail;
import dk.cphbusiness.bank.contract.dto.CustomerIdentifier;
import dk.cphbusiness.bank.contract.eto.NoSuchCustomerException;
import dk.cphbusiness.bank.view.Factory;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author joachim
 */
public class ChangeCustomerCommand extends TargetCommand {

    public ChangeCustomerCommand(String target) {
        super(target);
    }

    @Override
    public String execute(HttpServletRequest request) {
        BankManager manager = Factory.getInstance().getManager();
        CustomerIdentifier customerID = CustomerIdentifier.fromString(request.getParameter("custid"));
        
        try {
            CustomerDetail customer = manager.showCustomer(customerID);
            request.setAttribute("customer", customer);
        } catch (NoSuchCustomerException ex) {
            Logger.getLogger(ChangeCustomerCommand.class.getName()).log(Level.SEVERE, null, ex);
        }        
        
        return super.execute(request);
    }

}
