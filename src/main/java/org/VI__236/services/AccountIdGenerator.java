package org.VI__236.services;

import org.VI__236.utill.MD5Hasher;

import java.time.LocalDateTime;
import java.util.*;

public class AccountIdGenerator {

    private MD5Hasher md5Hasher = new MD5Hasher();

    private HashSet<String> accountsSet = new HashSet<>();
    private List <String> accountsIdList;
    private StringBuilder defaultStringBuilder = new StringBuilder();

    public List<String> accountsIdGenerator(int numOfAccounts){
        for(int a = 0; a < numOfAccounts; a++){

            LocalDateTime currentTime = LocalDateTime.now();
            int salt = 1 + (int)(Math.random() * 1000);
            defaultStringBuilder.append(currentTime.toString() + salt);

            String defaultString = new String(defaultStringBuilder);

            accountsSet.add(md5Hasher.hashStrings(defaultString));

            defaultStringBuilder.setLength(0);
        }

        return accountsIdList = new ArrayList<>(accountsSet);
    }
}
