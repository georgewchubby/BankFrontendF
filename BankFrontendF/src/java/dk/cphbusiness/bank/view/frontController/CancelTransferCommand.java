package dk.cphbusiness.bank.view.frontController;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author joachim
 */
public class CancelTransferCommand extends TargetCommand {

    public CancelTransferCommand(String target) {
        super(target);
    }

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("message", "These are the account(s)");
        
        return super.execute(request);
    }
}
