package dk.cphbusiness.bank.view.frontController;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import dk.cphbusiness.bank.security.SecurityRole;

public class TargetCommand implements Command {

    private final String target;
    private final List<SecurityRole> roles = new ArrayList();

//    public TargetCommand(String target) {
//        this.target = target;
//    }

    @Override
    public List<SecurityRole> getRoles() {
        return roles;
    }

    public TargetCommand(String target, List<SecurityRole> roles) {
        this.target = target;
        for (SecurityRole role : roles) {
            this.roles.add(role);
        }
    }

    @Override
    public String execute(HttpServletRequest request) {
        return target;
    }

}
