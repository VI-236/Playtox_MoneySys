package org.VI__236.services;

import org.VI__236.models.Account;

public class MoneyTransactions {
    private int outAccountMoney;
    private int inAccountMoney;


    public boolean moneyTransactions(Account outAccount, Account inAccount, int transactionSum){

         outAccountMoney = outAccount.getMoney();
         inAccountMoney = inAccount.getMoney();

         if(outAccountMoney < transactionSum){
             return false;
         }

         else {
             outAccount.setMoney(outAccountMoney - transactionSum);
             inAccount.setMoney(inAccountMoney + transactionSum);
             return true;
         }
    }
}
