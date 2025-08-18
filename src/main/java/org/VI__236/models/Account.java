package org.VI__236.models;

public class Account {
    private String id;
    private int money;

    public Account(String id, int money){
        this.id = id;
        this.money = money;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        if (money >= 0) {
            this.money = money;
        }
        else {
            System.out.println("Sum can't be less then 0.");
        }
    }
}
