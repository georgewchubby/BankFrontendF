package dk.cphbusiness.bank.view.frontController;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import dk.cphbusiness.bank.security.SecurityRole;

/**
 *
 * @author joachim
 */
public class CancelTransferCommand extends TargetCommand {

    public CancelTransferCommand(String target, List<SecurityRole> roles) {
        super(target,roles);
    }

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("message", "These are the account(s)");
        
        return super.execute(request);
    }
}
