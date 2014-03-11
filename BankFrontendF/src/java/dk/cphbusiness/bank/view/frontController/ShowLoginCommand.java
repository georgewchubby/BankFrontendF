package dk.cphbusiness.bank.view.frontController;

import java.util.List;
import security.SecurityRole;

  // @author Lars Mortensen
// This command is necessary because it give us a type to test against
public class ShowLoginCommand extends TargetCommand {

    public ShowLoginCommand(String target, List<SecurityRole> roles) {
        super(target, roles);
    }
}
