package com.example.kunal.springproject.Service;

import com.example.kunal.springproject.Entity.Customer;
import com.example.kunal.springproject.Exception.EmptyEmailException;
import com.example.kunal.springproject.Exception.ResourceNotFoundException;
import com.example.kunal.springproject.Repository.UserRepository;
import com.example.kunal.springproject.Utils.PasswordEncDec;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Example;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
//@Profile({"dev"})
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Customer createUser(Customer user){
        System.out.println("checking to see contains T/F");

        if (user.getEmail().isEmpty()) {
            throw new EmptyEmailException("Email cannot be empty");
        }

        //encrypting password before saving
        user.setPassword(PasswordEncDec.encrypt(user.getPassword()));
        Customer usr = this.userRepository.save(user);
        return usr;
    }

    @Override
    public Customer updateUser(Customer user) {
        Optional<Customer> userDb = this.userRepository.findById(user.getId());
        // update the user since it is in the db
        if(userDb.isPresent()){
            Customer userUpdate = userDb.get();
            userUpdate.setId(user.getId());
            userUpdate.setEmail(user.getEmail());
            userUpdate.setPassword(user.getPassword());
            userRepository.save(userUpdate);
            return userUpdate;
        }
        else {
            throw new ResourceNotFoundException("User not found with id : " + user.getId());
        }

    }

    @Override
    public List<Customer> getAllUser() {
        System.out.println(this.userRepository.findAll());
        return this.userRepository.findAll();
    }

    @Override
    public Customer getUserById(UUID id) {

        Optional<Customer> userDb = this.userRepository.findById(id);

        if(userDb.isPresent()){
            return userDb.get();
        }
        else{
            throw new ResourceNotFoundException("User not found with id : " + id);
        }
    }

    @Override
    public void deleteUser(Long id) {

    }

    public void testByteEnc() {
        Customer customer = Customer.builder()
                .password("1244")
                .email("kunal@mail.com")
                .build();
        this.userRepository.save(customer);
    }

    @Override
    public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {

        if(this.userRepository.findAll().stream().anyMatch(customer -> customer.getEmail().equals(email))) {
            List<Customer> c = this.userRepository.findAll().stream()
                    .filter(customer -> customer.getEmail().equals(email)).collect(Collectors.toList());
            final String password  = c.get(0).getPassword();

            // if that email and password exists in the db, then I should be authenticated
            return new User(email, password, new ArrayList<>());
        }

        return null;
    }

}
