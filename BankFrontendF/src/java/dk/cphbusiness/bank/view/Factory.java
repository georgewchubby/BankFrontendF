package dk.cphbusiness.bank.view;

import dk.cphbusiness.bank.contract.BankManager;
import dk.cphbusiness.bank.view.frontController.BackCommand;
import dk.cphbusiness.bank.view.frontController.CancelCreateAccountCommand;
import dk.cphbusiness.bank.view.frontController.CancelTransferCommand;
import dk.cphbusiness.bank.view.frontController.ChangeCustomerCommand;
import dk.cphbusiness.bank.view.frontController.Command;
import dk.cphbusiness.bank.view.frontController.CreateAccountCommand;
import dk.cphbusiness.bank.view.frontController.CreateCustomerCommand;
import dk.cphbusiness.bank.view.frontController.ListCustomerAccountCommand;
import dk.cphbusiness.bank.view.frontController.ListCustomersCommand;
import dk.cphbusiness.bank.view.frontController.LoginCommand;
import dk.cphbusiness.bank.view.frontController.LogoutCommand;
import dk.cphbusiness.bank.view.frontController.PrepareTransferCommand;
import dk.cphbusiness.bank.view.frontController.SaveAccountCommand;
import dk.cphbusiness.bank.view.frontController.SaveCustomerCommand;
import dk.cphbusiness.bank.view.frontController.SayHelloCommand;
import dk.cphbusiness.bank.view.frontController.ShowAccountDetailCommand;
import dk.cphbusiness.bank.view.frontController.ShowLoginCommand;
import dk.cphbusiness.bank.view.frontController.TargetCommand;
import dk.cphbusiness.bank.view.frontController.TransferAmountCommand;
import dk.cphbusiness.dummy.bank.control.DummyBankManager;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import security.SecurityRole;

public class Factory {

    //BankManager bankManagerBean = lookupBankManagerBeanRemote();

    private static Factory instance = new Factory();
    private BankManager manager;
    private final Map<String, Command> commands = new HashMap<>();

    private Factory() {
        //manager = new DummyBankManager();
        manager = lookupBankManagerBeanRemote();

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
        commands.put("hello", new SayHelloCommand("hello.jsp", Arrays.asList(SecurityRole.All)));

        Map<SecurityRole, String> roles = new HashMap();
        roles.put(SecurityRole.Employee, "/employees/startEmployeePage.jsp");
        roles.put(SecurityRole.SuperEmployee, "customer-edit.jsp");
        roles.put(SecurityRole.Customer, "/customers/startCustomerPage.jsp");
        commands.put("login", new LoginCommand(roles, "/login/login.jsp"));
    }

    public static Factory getInstance() {
        if (instance == null) {
            instance = new Factory();
        }
        return instance;
    }

    public Command getCommand(String cmdStr, HttpServletRequest request) {
        if (cmdStr == null) {
            cmdStr = "main";
        }
        Command cmd = commands.get(cmdStr);
        securityCheck(cmd, request);
        System.out.println("Roles #1 " + cmd.getRoles().size());
        System.out.println("Roles #1 " + cmd.getRoles().size());

        return cmd;
    }

    public BankManager getManager() {
        return manager;
    }

    private void securityCheck(Command command, HttpServletRequest request) throws SecurityException {
        if (command instanceof TargetCommand) {
//            List<SecurityRole> requiredRoles = ((TargetCommand) command).getRoles();
            List<SecurityRole> requiredRoles = command.getRoles();
            System.out.println("Roles " + requiredRoles.size());
            boolean requiredRoleFound = false;
            for (SecurityRole requiredRole : requiredRoles) {
                System.out.println("Role " + requiredRole + " " + request.isUserInRole(requiredRole.toString()));
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

    private BankManager lookupBankManagerBeanRemote() {
        try {
            Context c = new InitialContext();
            return (BankManager) c.lookup("java:global/BankBackendF/BankManagerBean!dk.cphbusiness.bank.contract.BankManager");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
