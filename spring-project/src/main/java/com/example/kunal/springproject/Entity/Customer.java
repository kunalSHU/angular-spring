package com.example.kunal.springproject.Entity;

import com.example.kunal.springproject.Exception.InvalidFormatEmailException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.Constraint;
import javax.validation.constraints.Email;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.SerializationUtils;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.UUID;

@Slf4j
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

//    @Override
//    public int hashCode() {
//        int result = 17;
////        System.out.println(email.hashCode());
//        if (email != null) {
//            result = 31 * result + email.hashCode(); // email hashcode the same, if email is the same
//        }
//        return result;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (o == this) {
//            return true;
//        }
//        if (!(o instanceof Customer)) {
//            return false;
//        }
//        Customer customer = (Customer) o;
//        return this.getEmail() == customer.getEmail();
//    }

    @javax.persistence.Id @GeneratedValue
    @JsonIgnore
    private UUID Id;

    @Email
    @Column(unique = true)
    private String email;

    private String password;

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return null;
//    }
//
//    @Override
//    public String getUsername() {
//        return null;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return false;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return false;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return false;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return false;
//    }
}
