package org.VI__236.services;

import org.VI__236.models.Account;

import java.util.List;

public class Starter {

    private MoneyTransactions moneyTransactions = new MoneyTransactions();
    private AccountsCreator accountCreator = new AccountsCreator();
    private AccountIdGenerator accountIdGenerator = new AccountIdGenerator();
    private RandomAccounts randomAccounts = new RandomAccounts();

    private int numOfAccounts = 4 + (int)(Math.random() * 10);

    public void starter(){

        List<Account> accountsList = accountCreator.creator(accountIdGenerator.accountsIdGenerator(numOfAccounts));

        int fullMoney1 = 0;
        for(int b = 0; b < accountsList.size(); b++){
            System.out.println("ID аккаунта: " + accountsList.get(b).getId() + " Сумма на счету: " + accountsList.get(b).getMoney());
            fullMoney1 += accountsList.get(b).getMoney();
        }
        System.out.println("Всего денег в системе: " + fullMoney1);
        System.out.println("");

        for (int a = 0; a < 30; a++){
            int [] inAndOutAccounts = randomAccounts.randomAccounts(numOfAccounts);
            int transactionSum = (int)(Math.random() * 10000);

            boolean success;

            System.out.println("Номер операции: " + a);
            System.out.println("Сумма транзакции: " + transactionSum);
            System.out.println("Счёт списания: " + accountsList.get(inAndOutAccounts[0]).getId());
            System.out.println("Состояние счёта списания: " + accountsList.get(inAndOutAccounts[0]).getMoney());
            System.out.println("Счёт зачисления: " + accountsList.get(inAndOutAccounts[1]).getId());
            System.out.println("Состояние счёта зачисления: " + accountsList.get(inAndOutAccounts[1]).getMoney());

            moneyTransactions.moneyTransactions(accountsList.get(inAndOutAccounts[0]),
                                                accountsList.get(inAndOutAccounts[1]),
                                                transactionSum);


            System.out.println("Состояние счёта списания после: " + accountsList.get(inAndOutAccounts[0]).getMoney());
            System.out.println("Состояние счёта зачисления после: " + accountsList.get(inAndOutAccounts[1]).getMoney());
            System.out.println("");

        }

        int fullMoney2 = 0;
        for(int b = 0; b < accountsList.size(); b++){
            System.out.println("Номер итерации: " + b);
            System.out.println("ID аккаунта: " + accountsList.get(b).getId() + " Сумма на счету: " + accountsList.get(b).getMoney());
            System.out.println("До зачисления: " + fullMoney2);
            fullMoney2 += accountsList.get(b).getMoney();
            System.out.println("После зачисления: " + fullMoney2);
            System.out.println("");
        }
        System.out.println("Всего денег в системе: " + fullMoney2);
    }
}
