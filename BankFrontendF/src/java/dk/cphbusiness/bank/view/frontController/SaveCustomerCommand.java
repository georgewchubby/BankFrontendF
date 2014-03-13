package dk.cphbusiness.bank.view.frontController;

import dk.cphbusiness.bank.contract.BankManager;
import dk.cphbusiness.bank.contract.dto.CustomerDetail;
import dk.cphbusiness.bank.contract.dto.CustomerSummary;
import dk.cphbusiness.bank.view.Factory;
import java.util.Collection;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import security.SecurityRole;

/**
 *
 * @author joachim
 */
public class SaveCustomerCommand extends TargetCommand {

    public SaveCustomerCommand(String target, List<SecurityRole> roles) {
        super(target,roles);
    }

    @Override
    public String execute(HttpServletRequest request) {
        BankManager manager = Factory.getInstance().getManager();
        
        //String cpr = request.getParameter("customerCPR");
        String title = request.getParameter("customerTitle");
        String firstName = request.getParameter("customerFirstName");
        String lastName = request.getParameter("customerLastName");
        String street = request.getParameter("customerStreet");
        String postalCode = request.getParameter("customerPostalCode");
        String postalDistrict = request.getParameter("customerPostalDistrict");
        String phone = request.getParameter("customerPhone");
        String email = request.getParameter("customerEmail");
        
        HttpSession session = request.getSession();
        CustomerDetail customer = (CustomerDetail) session.getAttribute("customer");
        customer.setTitle(title);
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setStreet(street);
        customer.setPostalCode(postalCode);
        customer.setPostalDistrict(postalDistrict);
        customer.setPhone(phone);
        customer.setEmail(email);
        
        manager.saveCustomer(customer);
        Collection<CustomerSummary> customers = manager.listCustomers();
        
        request.setAttribute("customer", customers);

        return super.execute(request);
    }
}
