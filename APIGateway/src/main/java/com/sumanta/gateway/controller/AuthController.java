//package com.sumanta.gateway.controller;
//
//import com.sumanta.gateway.models.AuthResponse.AuthResponse;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
//import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
//import org.springframework.security.oauth2.core.oidc.user.OidcUser;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@RestController
//@RequestMapping("/auth")
//public class AuthController {
//private Logger logger = LoggerFactory.getLogger(AuthController.class);
//@GetMapping("/login")
//
//public ResponseEntity<AuthResponse> login(@RegisteredOAuth2AuthorizedClient("okta") OAuth2AuthorizedClient oauth2AuthorizedClient,
//
//@AuthenticationPrincipal OidcUser user,
// Model model
//
//){
//logger.info("User email Id:{}",user.getEmail());
//
////creating auth response object
//AuthResponse response =new AuthResponse();
////setting email to auth response
//response.setUserId(user.getEmail());
////setting token to auth response
//response.setAccessToken(oauth2AuthorizedClient.getAccessToken().getTokenValue());
//response.setRefreshToken(oauth2AuthorizedClient.getRefreshToken().getTokenValue());
//response.setExpireAt(oauth2AuthorizedClient.getAccessToken().getExpiresAt().getEpochSecond());
//
//    List<String> authorities = user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
//
//    response.setAuthorities(authorities);
//    return new ResponseEntity<>(response, HttpStatus.OK);
//}
//
//}
