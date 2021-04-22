package com.example.kunal.springproject.Controller;

import com.example.kunal.springproject.Entity.Customer;
import com.example.kunal.springproject.Model.AuthenticationRequest;
import com.example.kunal.springproject.Model.AuthenticationResponse;
import com.example.kunal.springproject.Service.UserService;
import com.example.kunal.springproject.Service.UserServiceImpl;
import com.example.kunal.springproject.Utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.util.SerializationUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.UUID;

/**
 * Will handle the http requests (GET, POST, PUT, DELETE)
 * Rest controller advice - how to use it - DONE
 * REST Exception Handling by using controller advice - DONE
 * Data validation in rest controller, JSR 303, JSR 349, JSR 380 (Bean validation framework) (Custom Annotation for validation)
 * SOAP, wsdl services - Canada post free SOAP service
 * Understand the SOAP msg structure
 * JAX-WS SOAP service, Spring Web Services, Apache CXF
 * Everything should be dockerized - DONE
 * Spring Data
 * Actuator
 * Adding swagger in spring boot - DONE
 * Security - Basic Auth, Digest Auth, One way SSL, Two way SSL (mutual auth), and OAuth grants/flows (free dev account by okta)
 * Certificate generation, Java Keystore, Java truststore
 * How to use Open SSL command line to generate Keystore and Truststore
 * curl command line, http pie
 * Later on: Spring Cloud Netflix OSS (Service Registry = Eureka, Config Server, Hystryx Server based on bulkhead design pattern)
 */

@Slf4j
@Controller
@RequestMapping("/api")
public class HomeController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/name")
    public ResponseEntity<String> getName() {
        return ResponseEntity.ok("Kunal Shukla");
    }

    @GetMapping("/users")
    public ResponseEntity<List<Customer>> getAllUsers() {
        System.out.println(this.userService.getAllUser());
        System.out.println(ResponseEntity.ok(this.userService.getAllUser()));
        return ResponseEntity.ok(this.userService.getAllUser());
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Customer> getUserById(@PathVariable UUID id) {

        Customer responseEntity = this.userService.getUserById(id);

        return ResponseEntity.ok(this.userService.getUserById(id));
    }

    @GetMapping("/principal")
    public ResponseEntity<String> getPrincipal(Principal principal) {
        return ResponseEntity.ok(principal.toString());
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticateUser(@RequestBody AuthenticationRequest authReq) throws Exception {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
                    (authReq.getEmail(), authReq.getPassword()));
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username/password", e);
        }

        final UserDetails userDetails = userServiceImpl.loadUserByUsername(authReq
                .getEmail());

        log.info("User details {} ", userDetails);

        final String jwt = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @PostMapping("/register")
    public ResponseEntity<Customer> createUser(@RequestBody Customer user) {
        System.out.println("the user in post mapping users endpoint");
        System.out.println(user.getEmail());
        System.out.println(user.getPassword());
        System.out.println(this.userService.createUser(user));

        return ResponseEntity.ok(this.userService.createUser(user));
    }

    // This method is automatically executed upon application start up
    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() {
        this.userService.testByteEnc();
        System.out.println("hello world, I have just started up");

    }

}
