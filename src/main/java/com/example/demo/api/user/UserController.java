package com.example.demo.api.user;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping("api")
@Slf4j
public class UserController {
    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<AppUser>> getUsers() {
        return ResponseEntity.ok().body(userService.getUsers());
    }
    @GetMapping("/user")
    public ResponseEntity<AppUser> getUsers(@RequestParam String UserName) {
        return ResponseEntity.ok().body(userService.getUser(UserName).orElseThrow());
    }

    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token) {
        return userService.confirmToken(token);
    }

    @GetMapping("/getAllUsersWithNoRole")
        public  ResponseEntity<List<AppUser>> getAllUsersWithNoRole(){
        return ResponseEntity.ok().body(userService.getUsersWhereUserRoleIsNewUser());
    }
    @GetMapping("/getAllUsersWithRoleBusiness")
    public  ResponseEntity<List<AppUser>> getAllUsersWithRoleBusiness(){
        System.out.println(userService.getUsersWhereUserRoleIsBusiness());
        return ResponseEntity.ok().body(userService.getUsersWhereUserRoleIsBusiness());
    }
    @GetMapping("/getAllUsersWithRoleCourier")
    public  ResponseEntity<List<AppUser>> getUsersWhereUserRoleIsCouriers(){
        return ResponseEntity.ok().body(userService.getUsersWhereUserRoleIsCouriers());
    }
    @GetMapping("/getAllUsersWithRoleUsers")
    public  ResponseEntity<List<AppUser>> getUsersWhereUserRoleIsUsers(){
        return ResponseEntity.ok().body(userService.getUsersWhereUserRoleIsUser());
    }
    @GetMapping("/getAllUsersWithNoBusiness")
    public  ResponseEntity<List<AppUser>> getAllUsersWithNoBusiness(){
        return ResponseEntity.ok().body(userService.getUsersWhereBusinessIsNull());
    }
    @GetMapping("/getAllUsersWithBusiness")
    public  ResponseEntity<List<AppUser>> getAllUsersWithBusiness(@RequestParam Long BusinessId){
        return ResponseEntity.ok().body(userService.getUsersWhereBusinessIs(BusinessId));
    }
    @PostMapping("/setRole")
    public ResponseEntity<AppUser> modifyUserRule(@RequestParam("Rule") String role, @RequestParam("username") String username) {
        Role userRole = Role.valueOf(role);
        if (userService.addRoleToUser(username, userRole)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(405).build();
    }
    @PostMapping("/assignUserToBusiness")
    public ResponseEntity<AppUser> assignUserToBusiness(@RequestParam("userName") String userName, @RequestParam("businessId") Long businessId) {
        if (userService.addBusinessToUser(userName, businessId)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(409).build();
        }
    }

    @PostMapping("/users")
    public ResponseEntity<AppUser> saveUser(@RequestBody AppUser user) {
        if (userService.checkIfUserExists(user.getUsername(), user.getEmail(), user.getPhoneNumber())) {
            return ResponseEntity.status(409).build();
        }
        log.info(userService.saveUser(user).toString());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                String refreshToken = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify((refreshToken));
                String username = decodedJWT.getSubject();
                Optional<AppUser> user = userService.getUser(username);
                String access_token = JWT.create()
                        .withSubject(user.get().getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                        .withIssuer(request.getRequestURI())
                        .withClaim("roles", user.get().getRoles().name())
                        .sign(algorithm);
                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", access_token);
                tokens.put("refresh_token", refreshToken);
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);

            } catch (Exception exception) {
                log.info("err");
                response.setHeader("error", exception.getMessage());
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                Map<String, String> error = new HashMap<>();
                error.put("error_message", exception.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        } else {
            throw new RuntimeException("Refresh token is missing");
        }

    }
}