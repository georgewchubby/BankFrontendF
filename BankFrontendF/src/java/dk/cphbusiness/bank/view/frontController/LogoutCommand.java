package dk.cphbusiness.bank.view.frontController;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import security.SecurityRole;

public class LogoutCommand extends TargetCommand {

    public LogoutCommand(String target, List<SecurityRole> roles) {
        super(target, roles);
    }

    @Override
    public String execute(HttpServletRequest request) {
        try {
            request.logout(); //Programmatic logout
        } catch (ServletException ex) {
            Logger.getLogger(LogoutCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        return super.execute(request);
    }
}
