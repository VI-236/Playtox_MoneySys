package org.VI__236;

import org.VI__236.models.Account;
import org.VI__236.services.AccountsCreator;
import org.VI__236.services.AccountIdGenerator;
import org.VI__236.services.MoneyTransactions;
import org.VI__236.services.RandomAccounts;

import java.util.List;

public class Starter {

    private MoneyTransactions moneyTransactions;
    private AccountsCreator accountCreator;
    private AccountIdGenerator accountIdGenerator;
    private RandomAccounts randomAccounts;

    private int numOfAccounts = 4 + (int)(Math.random() * 10);

    public void starter(){

        List<Account> accountsList = accountCreator.accountCreator(accountIdGenerator.accountsIdGenerator(numOfAccounts));

        for (int a = 0; a < 30; a++){
            int [] inAndOutAccounts = randomAccounts.randomAccounts(numOfAccounts);
            int transactionSum = (int)(Math.random() * 10000);

            moneyTransactions.moneyTransactions(accountsList.get(inAndOutAccounts[0]),
                                                accountsList.get(inAndOutAccounts[1]),
                                                transactionSum);
        }
    }
}
