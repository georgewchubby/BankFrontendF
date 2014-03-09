package dk.cphbusiness.bank.view.frontController;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author joachim
 */
public class CancelCreateAccountCommand extends TargetCommand {

    public CancelCreateAccountCommand(String target) {
        super(target);
    }

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("message", "These are the account(s)");

        return super.execute(request);
    }
}
