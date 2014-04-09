package dk.cphbusiness.bank.view.frontController;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import dk.cphbusiness.bank.security.SecurityRole;

/**
 *
 * @author joachim
 */
public class CancelCreateAccountCommand extends TargetCommand {

    public CancelCreateAccountCommand(String target, List<SecurityRole> roles) {
        super(target,roles);
    }

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("message", "These are the account(s)");

        return super.execute(request);
    }
}
