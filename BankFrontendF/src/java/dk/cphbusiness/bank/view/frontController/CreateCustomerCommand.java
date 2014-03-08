package dk.cphbusiness.bank.view.frontController;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author joachim
 */
public class CreateCustomerCommand extends TargetCommand {

    public CreateCustomerCommand(String target) {
        super(target);
    }

    @Override
    public String execute(HttpServletRequest request) {        
        
        return super.execute(request);
    }

}
