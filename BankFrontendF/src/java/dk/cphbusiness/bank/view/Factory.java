package dk.cphbusiness.bank.view;

import dk.cphbusiness.bank.contract.BankManager;
import dk.cphbusiness.dummy.bank.control.DummyBankManager;

public class Factory {

    private static Factory instance = null;
    private final BankManager manager;

    private Factory() {
        manager = new DummyBankManager();
    }

    public static Factory getInstance() {
        if (instance == null) {
            instance = new Factory();
        }
        return instance;
    }

    public BankManager getManager() {
        return manager;
    }

}
