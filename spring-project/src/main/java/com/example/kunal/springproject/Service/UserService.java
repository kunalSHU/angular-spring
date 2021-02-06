package com.example.kunal.springproject.Service;

import com.example.kunal.springproject.Entity.Customer;

import java.util.List;
import java.util.UUID;

public interface UserService {

    Customer createUser(Customer user);
    Customer updateUser(Customer user);
    List<Customer> getAllUser();
    Customer getUserById(UUID id);
    void deleteUser(Long id);
    void testByteEnc();

}
