package com.sumanta.userService.controller;

import com.sumanta.userService.dto.ItemMessage;
import com.sumanta.userService.entities.User;
import com.sumanta.userService.messaging.UserMessageProducer;
import com.sumanta.userService.services.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;


@RestController
@RequestMapping("/users")
@Validated
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;
    @Autowired
    UserMessageProducer userMessageProducer;
    @PostMapping()
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
      User user1 = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);

    }
    @PostMapping("/messages")
    public void createItems(@RequestBody ItemMessage itemMessage) {
        userMessageProducer.sendMessage(itemMessage);

    }
    int retryCount =1;
    @GetMapping("/{userId}")
    //@CircuitBreaker(name="retailerItemBreaker")
    //@Retry(name = "retailerItemRetry", fallbackMethod = "retailerItemFallback")
    //@RateLimiter(name="retailerItemRateLimiter", fallbackMethod = "retailerItemFallback")
    public ResponseEntity<User> getSingleUserId(@PathVariable String userId){

        User user = userService.getUser(userId);

        logger.info("retry Count:{}", retryCount);
        retryCount++;
        System.out.println("retry count:"+ retryCount);
        //fetching retailer list from retailer service
        return ResponseEntity.ok(user);
    }

    // creating fallback method for circuit breaker
    public ResponseEntity<User> retailerItemFallback(String userId, Exception ex) {

        System.out.println("Fallback method for circuit breaker executed because service is down"+ ex.getMessage());
//        Logger logger = (Logger) LoggerFactory.getLogger("userController");
//        logger.info("Fallback method for circuit breaker executed");

        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<?>> getAllUser() {
        List<?> allUser = userService.getAllUser();
        return ResponseEntity.ok(allUser);

    }

}
