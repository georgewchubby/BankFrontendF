package dk.cphbusiness.bank.view.frontController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author joachim
 */

public class BackCommand extends TargetCommand {

    public BackCommand(String target) {
        super(target);
    }

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        
        session.invalidate();
        
        return super.execute(request);
    }
}
