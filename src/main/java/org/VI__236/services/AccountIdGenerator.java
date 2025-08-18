package org.VI__236.services;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AccountIdGenerator {

    private Set<String> accountsSet;
    private List <String> accountsIdList;
    private StringBuilder defaultStringBuilder = new StringBuilder();

    public List<String> accountsIdGenerator(int numOfAccounts){
        while(accountsSet.size() < numOfAccounts){

            LocalDateTime currentTime = LocalDateTime.now();

            int salt = 1 + (int)(Math.random() * 1000);

            defaultStringBuilder.append(currentTime.toString() + salt);
            String defaultString = new String(defaultStringBuilder);

            try {
                MessageDigest md = MessageDigest.getInstance("MD5");
                byte[] messageDigest = md.digest(defaultString.getBytes());
                accountsSet.add(new String(messageDigest));

            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        }

        return accountsIdList = new ArrayList<>(accountsSet);
    }
}
