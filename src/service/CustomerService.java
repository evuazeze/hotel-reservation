package service;

import model.Customer;

import java.util.*;

public class CustomerService {

    private final Map<String, Customer> customers = new HashMap<>();

    private CustomerService() {}

    private static CustomerService myInstance;

    public static CustomerService getInstance() {
        if (myInstance == null) {
            myInstance = new CustomerService();
        }
        return myInstance;
    }

    public void addCustomer(String email, String firstName, String lastName) {
        Customer customer = new Customer(firstName, lastName, email);
        customers.put(email, customer);
    }

    public Customer getCustomer(String customerEmail) {
        return customers.get(customerEmail);
    }

    public Collection<Customer> getAllCustomers() {
        return customers.values();
    }
}
