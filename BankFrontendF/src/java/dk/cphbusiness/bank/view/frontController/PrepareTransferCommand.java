package dk.cphbusiness.bank.view.frontController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author David Wroblewski
 */
public class PrepareTransferCommand extends TargetCommand {

    public PrepareTransferCommand(String target) {
        super(target);
    }

    @Override
    public String execute(HttpServletRequest request) {

        request.setAttribute("message", "Preparing to make a transfer");

        return super.execute(request);
    }
}
