package com.hscrm.domain;

public class Emp {
    private int e_id;
    private String e_name;
    private String e_sex;
    private String e_tel;
    private String username;
    private String passwd;

    public Emp() {
    }

    public Emp(int e_id, String e_name, String e_sex, String e_tel, String username, String passwd) {
        this.e_id = e_id;
        this.e_name = e_name;
        this.e_sex = e_sex;
        this.e_tel = e_tel;
        this.username = username;
        this.passwd = passwd;
    }

    public int getE_id() {
        return e_id;
    }

    public void setE_id(int e_id) {
        this.e_id = e_id;
    }

    public String getE_name() {
        return e_name;
    }

    public void setE_name(String e_name) {
        this.e_name = e_name;
    }

    public String getE_sex() {
        return e_sex;
    }

    public void setE_sex(String e_sex) {
        this.e_sex = e_sex;
    }

    public String getE_tel() {
        return e_tel;
    }

    public void setE_tel(String e_tel) {
        this.e_tel = e_tel;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
}
