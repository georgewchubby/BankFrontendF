package dk.cphbusiness.bank.view.frontController;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import security.SecurityRole;

/**
 * @author David Wroblewski
 */
public class PrepareTransferCommand extends TargetCommand {

    public PrepareTransferCommand(String target, List<SecurityRole> roles) {
        super(target);
    }

    @Override
    public String execute(HttpServletRequest request) {

        request.setAttribute("message", "Preparing to make a transfer");

        return super.execute(request);
    }
}
