package org.VI__236.services;

public class RandomAccounts {
    private int [] randAccId = new int[2];

    public int [] randomAccounts(int sizeOfAccList){

        int randOutAcc = (int)(Math.random() * sizeOfAccList);
        int randInAcc = (int)(Math.random() * sizeOfAccList);

        if(randOutAcc == randInAcc){
            randomAccounts(sizeOfAccList);
        }

        randAccId[0] = randOutAcc;
        randAccId[1] = randInAcc;

        return randAccId;
    }
}