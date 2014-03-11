package dk.cphbusiness.bank.view.frontController;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import security.SecurityRole;

/**
 *
 * @author joachim
 */
@WebServlet(name = "FrontController", urlPatterns = {"/FrontController"})
public class FrontController extends HttpServlet {

    private final Map<String, Command> commands = new HashMap<>();
    private int PORT_NON_SSL;
    private int PORT_SSL;
    private boolean useSSL = true;
    private boolean autoLogon = false;
    private String autoLogonUser;
    private String autoLogonPassword;

    public FrontController() {
        commands.put("list-customers", new ListCustomersCommand("customer-list.jsp", Arrays.asList(SecurityRole.Employee, SecurityRole.SuperEmployee)));
        commands.put("create-customer", new CreateCustomerCommand("customer-edit.jsp", Arrays.asList(SecurityRole.SuperEmployee)));
        commands.put("save-customer", new SaveCustomerCommand("customer-list.jsp", Arrays.asList(SecurityRole.SuperEmployee)));
        commands.put("change-customer", new ChangeCustomerCommand("customer-edit.jsp", Arrays.asList(SecurityRole.SuperEmployee)));

        commands.put("list-accounts", new ListCustomerAccountCommand("account-list.jsp", Arrays.asList(SecurityRole.Employee, SecurityRole.SuperEmployee)));
        commands.put("account-detail", new ShowAccountDetailCommand("account-detail.jsp", Arrays.asList(SecurityRole.Employee, SecurityRole.SuperEmployee)));
        commands.put("create-account", new CreateAccountCommand("account-edit.jsp", Arrays.asList(SecurityRole.Employee, SecurityRole.SuperEmployee)));
        commands.put("save-account", new SaveAccountCommand("account-list.jsp", Arrays.asList(SecurityRole.Employee, SecurityRole.SuperEmployee)));
        commands.put("cancel-create-account", new CancelCreateAccountCommand("account-list.jsp", Arrays.asList(SecurityRole.Employee, SecurityRole.SuperEmployee)));

        commands.put("prepare-transfer", new PrepareTransferCommand("transfer-edit.jsp", Arrays.asList(SecurityRole.Employee, SecurityRole.SuperEmployee)));
        commands.put("transfer-amount", new TransferAmountCommand("account-detail.jsp", Arrays.asList(SecurityRole.Employee, SecurityRole.SuperEmployee)));
        commands.put("cancel-transfer", new CancelTransferCommand("account-list.jsp", Arrays.asList(SecurityRole.Employee, SecurityRole.SuperEmployee)));

        commands.put("showlogin", new ShowLoginCommand("/login/login.jsp", Arrays.asList(SecurityRole.All)));
        commands.put("logout", new LogoutCommand("main.jsp", Arrays.asList(SecurityRole.All)));
        commands.put("back", new BackCommand("main.jsp", Arrays.asList(SecurityRole.All)));
        commands.put("main", new TargetCommand("main.jsp", Arrays.asList(SecurityRole.All)));

        Map<SecurityRole, String> roles = new HashMap();
        roles.put(SecurityRole.Employee, "/employees/startEmployeePage.jsp");
        roles.put(SecurityRole.SuperEmployee, "/superEmployee/addCustomer.jsp");
        roles.put(SecurityRole.Customer, "/customers/startCustomerPage.jsp");
        commands.put("login", new LoginCommand(roles, "/login/login.jsp"));
    }

    public Command getCommand(String cmdStr, HttpServletRequest request) {
        if (cmdStr == null) {
            cmdStr = "main";
        }
        Command cmd = commands.get(cmdStr);
        securityCheck(cmd, request);
        return cmd;
    }

    private void securityCheck(Command command, HttpServletRequest request) throws SecurityException {
        if (command instanceof TargetCommand) {
            List<SecurityRole> requiredRoles = ((TargetCommand) command).getRoles();
            boolean requiredRoleFound = false;
            for (SecurityRole requiredRole : requiredRoles) {
                if (requiredRole == SecurityRole.All || request.isUserInRole(requiredRole.toString())) {
                    requiredRoleFound = true;
                    break;
                }
            }
            if (!requiredRoleFound) {
                throw new SecurityException("You don't have the necessary rights to perform this command");
            }
        }
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

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String cmdStr = request.getParameter("command");
        cmdStr = cmdStr != null ? cmdStr : "main";
        //Provides auto login. See LoginCommand for the full story
        if (request.getRemoteUser() == null && autoLogon) {
            cmdStr = "login";
            request.setAttribute("autoLogon", "YES");
            request.setAttribute("username", autoLogonUser);
            request.setAttribute("password", autoLogonPassword);
        }

        Command command = getCommand(cmdStr, request);

        String path = command.execute(request);

        if (useSSL && !autoLogon) { //So we can disable the SSL switch
            SSL_Switch(command, request, response, path);
        } else {
            request.getRequestDispatcher(path).forward(request, response);
        }

    }

    private void SSL_Switch(Command command, HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException {
        if (command instanceof ShowLoginCommand && !request.isSecure()) {
            String SSL = "https://" + request.getServerName() + ":" + PORT_SSL + request.getRequestURI() + "?command=showlogin";
            System.out.println("SSL redirect: " + SSL);
            response.sendRedirect(SSL);
        } else if (command instanceof LogoutCommand) {
            String nonSSL = "http://" + request.getServerName() + ":" + PORT_NON_SSL + request.getRequestURI();
            System.out.println("Non SSL redirect: " + nonSSL);
            response.sendRedirect(nonSSL);
        } else {
            request.getRequestDispatcher(path).forward(request, response);
        }
    }

    @Override
    public void init() throws ServletException {
        PORT_NON_SSL = Integer.parseInt(getServletContext().getInitParameter("portNonSSL"));
        PORT_SSL = Integer.parseInt(getServletContext().getInitParameter("portSSL"));
        useSSL = Boolean.parseBoolean(getServletContext().getInitParameter("useSSL"));
        autoLogon = Boolean.parseBoolean(getServletContext().getInitParameter("autoLogon"));
        autoLogonUser = getServletContext().getInitParameter("autoLogonUser");
        autoLogonPassword = getServletContext().getInitParameter("autoLogonPassword");
    }
}
