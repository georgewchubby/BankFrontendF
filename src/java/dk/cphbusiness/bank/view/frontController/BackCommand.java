package dk.cphbusiness.bank.view.frontController;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import dk.cphbusiness.bank.security.SecurityRole;

/**
 *
 * @author joachim
 */
public class BackCommand extends TargetCommand {

    public BackCommand(String target, List<SecurityRole> roles) {
        super(target,roles);
    }

    @Override
    public String execute(HttpServletRequest request) {
//        HttpSession session = request.getSession();
//
//        session.invalidate();

        return super.execute(request);
    }
}
