package com.hscrm.service.impl;

import com.hscrm.dao.CustomerDao;
import com.hscrm.dao.TrackDao;
import com.hscrm.dao.impl.CustomerDaoImpl;
import com.hscrm.dao.impl.TrackDaoImpl;
import com.hscrm.domain.Customer;
import com.hscrm.service.CustomerService;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {
    CustomerDao customerDao = new CustomerDaoImpl();
    TrackDao trackDao = new TrackDaoImpl();
    @Override
    public int addCustomer(Customer customer) {
        return customerDao.addCustomer(customer);
    }

    @Override
    public int deleteCustomer(int c_id) {
        //级联删除
        trackDao.deleteTrackByCid(c_id);
        customerDao.deleteCustomer(c_id);
        return 1;
    }

    @Override
    public int updateCustomer(Customer customer) {
        return customerDao.updateCustomer(customer);
    }

    @Override
    public List<Customer> findAllCustomer() {
        return customerDao.findAllCustomer();
    }

    @Override
    public Customer findCustomerById(String c_id) {
        return customerDao.findCustomerById(Integer.parseInt(c_id));
    }
}
