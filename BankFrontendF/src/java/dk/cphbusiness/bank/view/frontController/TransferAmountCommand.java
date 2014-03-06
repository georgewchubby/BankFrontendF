package dk.cphbusiness.bank.view.frontController;

import dk.cphbusiness.bank.contract.BankManager;
import dk.cphbusiness.bank.contract.dto.AccountIdentifier;
import dk.cphbusiness.bank.contract.dto.TransferSummary;
import dk.cphbusiness.bank.contract.eto.InsufficientFundsException;
import dk.cphbusiness.bank.contract.eto.NoSuchAccountException;
import dk.cphbusiness.bank.contract.eto.TransferNotAcceptedException;
import dk.cphbusiness.bank.view.Factory;
import java.math.BigDecimal;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;

/**
 * @author David Wroblewski
 */
public class TransferAmountCommand extends TargetCommand {

    public TransferAmountCommand(String target) {
        super(target);
    }

    @Override
    public String execute(HttpServletRequest request) {
        BankManager manager = Factory.getInstance().getManager();
        AccountIdentifier accountNumber = AccountIdentifier.fromString(request.getParameter("accountNumber"));
        AccountIdentifier targetAccountNumber = AccountIdentifier.fromString(request.getParameter("targetAccountNumber"));
        Double amount = Double.parseDouble(request.getParameter("amount"));

        try {
            Collection<TransferSummary> transfers = manager.transferAmount(BigDecimal.valueOf(amount),
                    accountNumber, targetAccountNumber).getTransfers();
            request.setAttribute("transfer", transfers);
            request.setAttribute("message", "Details for account number");
            request.setAttribute("accountdetail", accountNumber);
        } catch (InsufficientFundsException | NoSuchAccountException | TransferNotAcceptedException ex) {
            
        }
        return super.execute(request);
    }
}
