//package com.sumanta.gateway.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
//import org.springframework.security.config.web.server.ServerHttpSecurity;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
//import org.springframework.security.core.session.SessionRegistryImpl;
//import org.springframework.security.oauth2.core.oidc.user.OidcUserAuthority;
//import org.springframework.security.oauth2.core.user.OAuth2UserAuthority;
//import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
//import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
//import org.springframework.security.web.server.SecurityWebFilterChain;
//
//import java.util.*;
//import java.util.stream.Collectors;
//
//@Configuration
//@EnableWebFluxSecurity
//public class SecurityConfig {
//    private static final String GROUPS = "groups";
//    private static final String REALM_ACCESS_CLAIM = "realm_Access";
//    private static final String ROLES_CLAIM = "roles";
//    @Bean
//    public SecurityWebFilterChain securityWebFilterChain(HttpSecurity http) throws Exception {
//         http.csrf().disable();
//                http.authorizeRequests(exchanges -> exchanges
//                        .antMatchers("/eureka/**").permitAll()
//                        .anyRequest().hasRole("user"));
//               http.oauth2ResourceServer().jwt();
//
//    }
//
//
//    public GrantedAuthoritiesMapper userAuthoritiesMapperForKeycloak() {
//        return authorities -> {
//            Set mappedAuthorities = new HashSet<>();
//            var authority = authorities.iterator().next();
//            boolean isOidc = authority instanceof OidcUserAuthority;
//            if (isOidc) {
//                var oidcUserAuthority = (OidcUserAuthority) authority;
//                var userInfo = oidcUserAuthority.getUserInfo();
//                if(userInfo.hasClaim(REALM_ACCESS_CLAIM)) {
//                    var realmAccess = userInfo.getClaimAsMap(REALM_ACCESS_CLAIM);
//                    var roles =(Collection) realmAccess.get(ROLES_CLAIM);
//                    mappedAuthorities.addAll(generateAuthoritiesFromClaims(roles));
//                } else if (userInfo.hasClaim(GROUPS)) {
//                    Collection roles = (Collection) userInfo.getClaim(GROUPS);
//                    mappedAuthorities.addAll(generateAuthoritiesFromClaims(roles));
//
//                } else {
//                    var oauth2UserAuthority = (OAuth2UserAuthority) authority;
//                    Map<String, Object> userAttributes = oauth2UserAuthority.getAttributes();
//                    if  (userAttributes.containsKey(REALM_ACCESS_CLAIM)) {
//                        Map<String, Object> realmAccess = (Map<String, Object>) userAttributes.get(REALM_ACCESS_CLAIM);
//                        Collection roles = (Collection) realmAccess.get(ROLES_CLAIM);
//                        mappedAuthorities.addAll(generateAuthoritiesFromClaims(roles));
//                    }
//                }
//
//            }
//            return mappedAuthorities;
//        };
//    }
//
//    private Collection generateAuthoritiesFromClaims(Collection roles) {
//        return (Collection) roles.stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role)).collect(Collectors.toList());
//    }
//
//
//}
