package dk.cphbusiness.bank.view.frontController;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import security.SecurityRole;

//@author Lars Mortensen
public class LoginCommand implements Command {

    private final Map<SecurityRole, String> roleToTarget;
    private final String loginFailed;

    public LoginCommand(Map<SecurityRole, String> targetRoles, String logInFaildedPage) {
        this.roleToTarget = targetRoles;
        this.loginFailed = logInFaildedPage;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String userName;
        String password;
        if (request.getAttribute("autoLogon") != null) {
            userName = (String) request.getAttribute("username");
            password = (String) request.getAttribute("password");
        } else {
            userName = request.getParameter("username");
            password = request.getParameter("password");
        }
        String nextPage = loginFailed;
        try {
            //This performs a programmatic login
            request.login(userName, password);
            //Set next page depending on the users role
            for (SecurityRole role : roleToTarget.keySet()) {
                if (request.isUserInRole(role.toString())) {
                    nextPage = roleToTarget.get(role);
                    break;
                }
            }
        } catch (ServletException ex) {
            request.setAttribute("loginerror", "You failed to login");
            Logger.getLogger(LoginCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nextPage;
    }

    @Override
    public List<SecurityRole> getRoles() {
        return Arrays.asList();
    }
}
