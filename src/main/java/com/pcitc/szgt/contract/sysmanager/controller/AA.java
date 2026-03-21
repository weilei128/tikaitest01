package com.pcitc.szgt.contract.sysmanager.controller;

public class AA {
    private String name;
    private String money;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AA{");
        sb.append("name='").append(name).append('\'');
        sb.append(", money='").append(money).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
