package org.VI__236.services;

import org.VI__236.models.Account;

import java.util.ArrayList;
import java.util.List;

public class AccountsCreator {
    private List<Account> accountsList = new ArrayList<>();

    public List<Account> creator(List <String> accountsIdList){
        for(int a = 0; a < accountsIdList.size(); a++){
            accountsList.add(new Account(accountsIdList.get(a), 10000));
        }

        return accountsList;
    }
}
