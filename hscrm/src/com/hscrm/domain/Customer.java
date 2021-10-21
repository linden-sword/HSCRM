package com.hscrm.domain;

public class Customer {
    private int c_id;
    private String c_name;
    private String c_sex;
    private String c_tel;
    private String c_job;
    private String c_company;

    public Customer() {
    }

    public Customer(int c_id, String c_name, String c_sex, String c_tel, String c_job, String c_company) {
        this.c_id = c_id;
        this.c_name = c_name;
        this.c_sex = c_sex;
        this.c_tel = c_tel;
        this.c_job = c_job;
        this.c_company = c_company;
    }

    public int getC_id() {
        return c_id;
    }

    public void setC_id(int c_id) {
        this.c_id = c_id;
    }

    public String getC_name() {
        return c_name;
    }

    public void setC_name(String c_name) {
        this.c_name = c_name;
    }

    public String getC_sex() {
        return c_sex;
    }

    public void setC_sex(String c_sex) {
        this.c_sex = c_sex;
    }

    public String getC_tel() {
        return c_tel;
    }

    public void setC_tel(String c_tel) {
        this.c_tel = c_tel;
    }

    public String getC_job() {
        return c_job;
    }

    public void setC_job(String c_job) {
        this.c_job = c_job;
    }

    public String getC_company() {
        return c_company;
    }

    public void setC_company(String c_company) {
        this.c_company = c_company;
    }
}
