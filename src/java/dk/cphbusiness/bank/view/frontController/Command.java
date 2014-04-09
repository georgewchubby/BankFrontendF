package dk.cphbusiness.bank.view.frontController;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import dk.cphbusiness.bank.security.SecurityRole;

public interface Command {
    List<SecurityRole> getRoles();
    String execute(HttpServletRequest request);
}
