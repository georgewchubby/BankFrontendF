package dk.cphbusiness.bank.view.frontController;

import dk.cphbusiness.bank.contract.dto.CustomerDetail;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import security.SecurityRole;

/**
 *
 * @author joachim
 */
public class CreateCustomerCommand extends TargetCommand {

    public CreateCustomerCommand(String target, List<SecurityRole> roles) {
        super(target);
    }

    @Override
    public String execute(HttpServletRequest request) {        
        
        CustomerDetail customer = new CustomerDetail(null, null, null, null, null, null, null, null, null);
        HttpSession session = request.getSession();
        session.setAttribute("customer", customer);
        
        return super.execute(request);
    }

}
