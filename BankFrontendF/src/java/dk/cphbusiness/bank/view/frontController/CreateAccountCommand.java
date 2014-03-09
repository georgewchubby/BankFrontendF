package dk.cphbusiness.bank.view.frontController;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author joachim
 */

public class CreateAccountCommand extends TargetCommand {

    public CreateAccountCommand(String target) {
        super(target);
    }
    
    @Override
    public String execute(HttpServletRequest request) {        
        
        return super.execute(request);
    }    
}
