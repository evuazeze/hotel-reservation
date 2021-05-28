package service;

import model.Customer;

import java.util.Collection;

public class CustomerService {

    private CustomerService() {}

    private static CustomerService myInstance;

    public static CustomerService getInstance() {
        if (myInstance == null) {
            myInstance = new CustomerService();
        }
        return myInstance;
    }

    public void addCustomer(String email, String firstName, String lastName) {

    }

    public Customer getCustomer(String customerEmail) {
        return null;
    }

    public Collection<Customer> getAllCustomers() {
        return null;
    }
}
