package dk.cphbusiness.bank.view.frontController;

import dk.cphbusiness.bank.contract.BankManager;
import dk.cphbusiness.bank.contract.dto.AccountDetail;
import dk.cphbusiness.bank.contract.dto.AccountIdentifier;
import dk.cphbusiness.bank.contract.dto.TransferSummary;
import dk.cphbusiness.bank.contract.eto.InsufficientFundsException;
import dk.cphbusiness.bank.contract.eto.NoSuchAccountException;
import dk.cphbusiness.bank.contract.eto.TransferNotAcceptedException;
import dk.cphbusiness.bank.view.Factory;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import dk.cphbusiness.bank.security.SecurityRole;

/**
 * @author David Wroblewski
 */
public class TransferAmountCommand extends TargetCommand {

    public TransferAmountCommand(String target, List<SecurityRole> roles) {
        super(target, roles);
    }

    @Override
    public String execute(HttpServletRequest request) {
        BankManager manager = Factory.getInstance().getManager();
        AccountIdentifier accountNumber = AccountIdentifier.fromString(request.getParameter("accountNumber"));
        AccountIdentifier targetAccountNumber = AccountIdentifier.fromString(request.getParameter("targetAccountNumber"));
        Double amount = Double.parseDouble(request.getParameter("amount"));

        try {
            AccountDetail account = manager.transferAmount(BigDecimal.valueOf(amount),
                    accountNumber, targetAccountNumber);
            Collection<TransferSummary> transfers = account.getTransfers();
            request.setAttribute("transfer", transfers);
            request.setAttribute("message", "Details for account number");
            request.setAttribute("accountdetail", account);
        } catch (InsufficientFundsException | NoSuchAccountException | TransferNotAcceptedException ex) {

        }
        return super.execute(request);
    }
}
