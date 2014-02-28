package dk.cphbusiness.bank.view.frontController;

import dk.cphbusiness.bank.contract.BankManager;
import dk.cphbusiness.bank.contract.dto.CustomerSummary;
import dk.cphbusiness.bank.view.Factory;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author joachim
 */

public class ListCustomersCommand extends TargetCommand {

    public ListCustomersCommand(String target) {
        super(target);
    }

    @Override
    public String execute(HttpServletRequest request) {
        BankManager manager = Factory.getInstance().getManager();
        Collection<CustomerSummary> customers = manager.listCustomers();

        request.setAttribute("customer", customers);

        return super.execute(request);
    }

}