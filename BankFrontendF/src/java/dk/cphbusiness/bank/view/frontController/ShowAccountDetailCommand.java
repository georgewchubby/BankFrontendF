package dk.cphbusiness.bank.view.frontController;

import dk.cphbusiness.bank.contract.dto.AccountDetail;
import dk.cphbusiness.bank.contract.dto.AccountIdentifier;
import dk.cphbusiness.bank.contract.dto.TransferSummary;
import dk.cphbusiness.bank.view.Factory;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author joachim
 */
public class ShowAccountDetailCommand extends TargetCommand {

    public ShowAccountDetailCommand(String target) {
        super(target);
    }

    @Override
    public String execute(HttpServletRequest request) {
        AccountIdentifier accid = AccountDetail.fromString(request.getParameter("accid"));
        AccountDetail adetail = Factory.getInstance().getManager().showAccountHistory(accid);
        //Collection<TransferSummary> transfers = adetail.getTransfers();

        //request.setAttribute("accountid", accid);
        request.setAttribute("message", "Details for account number");
        request.setAttribute("accountdetail", adetail);
        //request.setAttribute("transfers", transfers);

        return super.execute(request);
    }
}
