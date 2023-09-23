package com.example.raksha;

public class AgenecyUsers {
    String ag_name, h_no, password, type, admin;

    public AgenecyUsers(String agency, String helpline, String adminKey, String password, String type) {
    }

    public AgenecyUsers(String ag_name, String h_no, String password) {
        this.ag_name = ag_name;
        this.h_no = h_no;
        this.password = password;
        this.admin = admin;
        this.type = type;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAg_name() {
        return ag_name;
    }

    public void setAg_name(String ag_name) {
        this.ag_name = ag_name;
    }

    public String getH_no() {
        return h_no;
    }

    public void setH_no(String h_no) {
        this.h_no = h_no;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
