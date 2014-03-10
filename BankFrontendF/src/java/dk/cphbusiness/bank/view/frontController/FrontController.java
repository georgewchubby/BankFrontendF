package dk.cphbusiness.bank.view.frontController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author joachim
 */
@WebServlet(name = "FrontController", urlPatterns = {"/FrontController"})
public class FrontController extends HttpServlet {

    private final Map<String, Command> commands = new HashMap<>();

    public FrontController() {
        commands.put("list-customers", new ListCustomersCommand("customer-list.jsp"));
        commands.put("create-customer", new CreateCustomerCommand("customer-edit.jsp"));
        commands.put("save-customer", new SaveCustomerCommand("customer-list.jsp"));
        commands.put("change-customer", new ChangeCustomerCommand("customer-edit.jsp"));
        
        commands.put("list-accounts", new ListCustomerAccountCommand("account-list.jsp"));
        commands.put("account-detail", new ShowAccountDetailCommand("account-detail.jsp"));
        commands.put("create-account", new CreateAccountCommand("account-edit.jsp"));
        commands.put("save-account", new SaveAccountCommand("account-list.jsp"));
        commands.put("cancel-create-account", new CancelCreateAccountCommand("account-list.jsp"));
        
        commands.put("prepare-transfer", new PrepareTransferCommand("transfer-edit.jsp"));
        commands.put("transfer-amount", new TransferAmountCommand("account-detail.jsp"));
        commands.put("cancel-transfer", new CancelTransferCommand("account-list.jsp"));
        
        commands.put("back", new BackCommand("main.jsp"));
        commands.put("main", new TargetCommand("main.jsp"));
    }

    @Override
    protected void service(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        String key = request.getParameter("command");
        if (key == null) {
            key = "main";
        }
        Command command = commands.get(key);
        String target = command.execute(request);
        RequestDispatcher dispatcher = request.getRequestDispatcher(target);
        dispatcher.forward(request, response);
    }
}
