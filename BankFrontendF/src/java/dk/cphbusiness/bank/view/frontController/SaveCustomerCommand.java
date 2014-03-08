package dk.cphbusiness.bank.view.frontController;

import dk.cphbusiness.bank.contract.BankManager;
import dk.cphbusiness.bank.contract.dto.AccountIdentifier;
import dk.cphbusiness.bank.contract.dto.CustomerDetail;
import dk.cphbusiness.bank.contract.dto.CustomerSummary;
import dk.cphbusiness.bank.view.Factory;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author joachim
 */
public class SaveCustomerCommand extends TargetCommand {

    public SaveCustomerCommand(String target) {
        super(target);
    }

    @Override
    public String execute(HttpServletRequest request) {
        BankManager manager = Factory.getInstance().getManager();
        String cpr = request.getParameter("customerCPR");
        String title = request.getParameter("customerTitle");
        String firstName = request.getParameter("customerFirstName");
        String lastName = request.getParameter("customerLastName");
        String street = request.getParameter("customerStreet");
        String postalCode = request.getParameter("customerPostalCode");
        String postalDistrict = request.getParameter("customerPostalDistrict");
        String phone = request.getParameter("customerPhone");
        String email = request.getParameter("customerEmail");
        
        CustomerDetail customer = new CustomerDetail(cpr, title, firstName,
                lastName, street, postalCode, postalDistrict, phone, email);
        
        manager.saveCustomer(customer);
        Collection<CustomerSummary> customers = manager.listCustomers();
        
        request.setAttribute("customer", customers);

        return super.execute(request);
    }
}
