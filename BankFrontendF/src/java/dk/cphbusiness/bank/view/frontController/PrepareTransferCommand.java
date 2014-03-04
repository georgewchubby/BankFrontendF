package dk.cphbusiness.bank.view.frontController;

import dk.cphbusiness.bank.contract.dto.AccountIdentifier;
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
        AccountIdentifier account = AccountIdentifier.fromString(request.getParameter("number"));

        //request.setAttribute("cpr", request.getParameter("cpr"));
        request.setAttribute("message", "Preparing to make a transfer");
        request.setAttribute("account", account);

        return super.execute(request);
    }
}
