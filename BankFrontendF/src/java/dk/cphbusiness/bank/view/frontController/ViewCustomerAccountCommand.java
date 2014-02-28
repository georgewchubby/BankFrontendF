package dk.cphbusiness.bank.view.frontController;

import dk.cphbusiness.bank.view.Factory;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author joachim
 */
public class ViewCustomerAccountCommand extends TargetCommand{

  public ViewCustomerAccountCommand(String target) {
    super(target);
  }

  @Override
  public String execute(HttpServletRequest request) {
    String idAsStr = request.getParameter("custid");
    long id = Long.parseLong(idAsStr);
    Customer cust = Factory.getInstance().getBankController().getCustomer(id);
    request.setAttribute("customer", cust);
    return super.execute(request); 
  }
}