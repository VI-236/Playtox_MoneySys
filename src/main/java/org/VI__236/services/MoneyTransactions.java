package org.VI__236.services;

import org.VI__236.models.Account;

public class MoneyTransactions {
    private int outAccountMoney;
    private int inAccountMoney;

    public void moneyTransactions(Account outAccount, Account inAccount, int transactionSum){

         outAccountMoney = outAccount.getMoney();
         inAccountMoney = inAccount.getMoney();

         if(outAccountMoney < transactionSum){
             System.out.println("Not enough money for that transaction.");
         }

         else {
             outAccount.setMoney(outAccountMoney - transactionSum);
             inAccount.setMoney(inAccountMoney + transactionSum);
         }
    }
}
