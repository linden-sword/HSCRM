package com.hscrm.domain;

public class Track {
    private int t_id;
    private Customer customer;
    private Emp emp;
    private String record;
    private String intention;

    public Track() {
    }

    public Track(int t_id, Customer customer, Emp emp, String record, String intention) {
        this.t_id = t_id;
        this.customer = customer;
        this.emp = emp;
        this.record = record;
        this.intention = intention;
    }

    public int getT_id() {
        return t_id;
    }

    public void setT_id(int t_id) {
        this.t_id = t_id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Emp getEmp() {
        return emp;
    }

    public void setEmp(Emp emp) {
        this.emp = emp;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    public String getIntention() {
        return intention;
    }

    public void setIntention(String intention) {
        this.intention = intention;
    }
}
