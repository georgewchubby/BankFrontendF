package dk.cphbusiness.bank.view.frontController;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import security.SecurityRole;

/**
 *
 * @author joachim
 */

public class CreateAccountCommand extends TargetCommand {

    public CreateAccountCommand(String target, List<SecurityRole> roles) {
        super(target,roles);
    }
    
    @Override
    public String execute(HttpServletRequest request) {        
        
        return super.execute(request);
    }    
}
