package com.hscrm.dao;

import com.hscrm.domain.Customer;

import java.util.List;

public interface CustomerDao {
    /**
     * 添加新客户
     * @param customer
     * @return
     */
    int addCustomer(Customer customer);

    /**
     * 删除客户
     * @param c_id
     * @return
     */
    int deleteCustomer(int c_id);

    /**
     * 修改客户信息
     * @param customer
     * @return
     */
    int updateCustomer(Customer customer);

    /**
     * 查询所有客户
     * @return
     */
    List<Customer> findAllCustomer();

    /**
     * 通过id查找客户
     * @param c_id
     * @return
     */
    Customer findCustomerById(int c_id);
}
