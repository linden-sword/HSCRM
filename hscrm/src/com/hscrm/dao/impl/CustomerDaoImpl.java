package com.hscrm.dao.impl;

import com.hscrm.dao.CustomerDao;
import com.hscrm.domain.Customer;
import com.hscrm.util.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {
    private final DBUtil dbUtil = new DBUtil();
    @Override
    public int addCustomer(Customer customer) {
        String sql = "insert into customer values(null,?,?,?,?,?)";
        Object[] objs = {customer.getC_name(),customer.getC_sex(),customer.getC_tel(),customer.getC_job(),customer.getC_company()};
        int flag = dbUtil.update(sql,objs);
        dbUtil.close();
        return flag;
    }

    @Override
    public int deleteCustomer(int c_id) {
        String sql = "delete from customer where c_id = ?";
        Object[] objs = {c_id};
        int flag = dbUtil.update(sql,objs);
        dbUtil.close();
        return flag;
    }

    @Override
    public int updateCustomer(Customer customer) {
        String sql = "update customer set c_name = ?,c_sex = ?,c_tel = ?,c_job = ?,c_company = ? where c_id = ?";
        Object[] objs = {customer.getC_name(),customer.getC_sex(),customer.getC_tel(),customer.getC_job(),customer.getC_company(),customer.getC_id()};
        int flag = dbUtil.update(sql,objs);
        dbUtil.close();
        return flag;
    }

    @Override
    public List<Customer> findAllCustomer() {
        String sql = "select * from customer";
        Object[] objs = {};
        ResultSet resultSet = dbUtil.select(sql,objs);
        List<Customer> customerList = new ArrayList<>();
        try {
            while (resultSet.next()){
                Customer customer = new Customer(resultSet.getInt("c_id"),resultSet.getString("c_name"),resultSet.getString("c_sex"),resultSet.getString("c_tel"),resultSet.getString("c_job"),resultSet.getString("c_company"));
                customerList.add(customer);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            dbUtil.close();
        }
        return customerList;
    }

    @Override
    public Customer findCustomerById(int c_id) {
        String sql = "select * from customer where c_id = ?";
        Object[] objs = {c_id};
        ResultSet resultSet = dbUtil.select(sql,objs);
        Customer customer = null;
        try {
            if (resultSet.next()){
                customer = new Customer(resultSet.getInt("c_id"),resultSet.getString("c_name"),resultSet.getString("c_sex"),resultSet.getString("c_tel"),resultSet.getString("c_job"),resultSet.getString("c_company"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            dbUtil.close();
        }
        return customer;
    }
}
