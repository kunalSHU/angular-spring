package com.example.kunal.springproject.Service;

import com.example.kunal.springproject.Entity.Customer;
import org.springframework.stereotype.Service;

import javax.xml.ws.WebServiceClient;
import java.util.List;
import java.util.UUID;

public class UserWebServiceImpl implements UserService{
    @Override
    public Customer createUser(Customer user) {
        return null;
    }

    @Override
    public Customer updateUser(Customer user) {
        return null;
    }

    @Override
    public List<Customer> getAllUser() {
        return null;
    }

    @Override
    public Customer getUserById(UUID id) {
        return null;
    }

    @Override
    public void deleteUser(Long id) {

    }

    public void testByteEnc() {

    }
}
