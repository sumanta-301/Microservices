package com.sumanta.userService.impl;

import com.sumanta.userService.entities.Item;
import com.sumanta.userService.entities.Retailer;
import com.sumanta.userService.entities.User;
import com.sumanta.userService.exception.ResourceNotFoundException;
import com.sumanta.userService.externalServices.ItemService;
import com.sumanta.userService.externalServices.RetailerService;
import com.sumanta.userService.repositories.UserRepository;
import com.sumanta.userService.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ItemService itemService;
    private final RetailerService retailerService;
    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    public UserServiceImpl(UserRepository userRepository, ItemService itemService, RetailerService retailerService) {
        this.userRepository = userRepository;
        this.itemService = itemService;
        this.retailerService = retailerService;
    }

//    public UserServiceImpl(UserRepository userRepository, ItemService itemService, RetailerService retailerService) {
//        this.userRepository = userRepository;
//        this.itemService = itemService;
//        this.retailerService = retailerService;
//    }

    @Override
    public User saveUser(User user) {
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {

        return userRepository.findAll();
    }

//    @Override
//    public User getUser(String userId) {
//        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user with given id is not found on server"));
//       // fetch Retailers of above users Id
//        logger.info("{}",user.toString());
//
//        Retailer[] retailersOfUsers = restTemplate.getForObject("http://localhost:9092/retailers/users/"+user.getUserId(), Retailer[].class);
//        logger.info("1st Object{}", retailersOfUsers);
//
//            logger.info("{}",retailersOfUsers);
//        List<Retailer> retailersList = Arrays.stream(retailersOfUsers).toList();
//
//        retailersList.stream().map(retailer ->{
//            String retailerId = retailer.getRetailerId();
//            logger.info("retailerId{}", retailerId);
//            ResponseEntity<Item> forEntity = restTemplate.getForEntity("http://localhost:9091/items/retailerId/f28743fc-087a-456c-8d2e-e9a39917a7d4" , Item.class);
//            Item items = forEntity.getBody();
//            logger.info("{}", items);
//            return retailer;
//
//        }).collect(Collectors.toList());
//        user.setRetailers(retailersList);
//
//
//        return user;
//    }

    @Override
    public User getUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given id is not found on the server"));
        logger.info("User with given id found on db{} ", user.getUserId()+ " userName: "+ user.getName());

        Retailer[] retailers = retailerService.getRetailer(userId);
        List<Retailer> retailerList = Arrays.stream(retailers).toList();
        logger.info("response from retailer feign client{}", retailers);
        if(!retailerList.isEmpty()) {
            retailerList.stream().peek(retailer -> {
                try {
                List<Item> itemsByRetailerId = itemService.getItem(retailer.getRetailerId());
                if(!itemsByRetailerId.isEmpty()) {
                    itemsByRetailerId.forEach(retailer::setItem);
                }
                } catch(Exception ex){
                    //throw new ResourceNotFoundException(ex.getMessage());
                    logger.warn("No items found By retailerId {}",ex.getMessage());
                    retailer.setItem(null);
                }
            }).collect(Collectors.toList());
        }

       user.setRetailers(retailerList);
        return user;
    }

    @Override
    public User deleteUser(String userId) {
        return null;
    }
}
