package com.example.coffeapp.Coffee.RestController;

import com.example.coffeapp.Coffee.Model.Order.Order;
import com.example.coffeapp.Coffee.Model.Users.User;
import com.example.coffeapp.Coffee.Model.Users.UserDTO;
import com.example.coffeapp.Coffee.Security.JwtUtil;
import com.example.coffeapp.Coffee.Service.MyUserDetailService;
import com.example.coffeapp.Coffee.Service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.Info;
import io.swagger.annotations.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class LoginSinginRestController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private MyUserDetailService userDetailService;
    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtil jwtTokenUtil;


    @PostMapping("/authenticate")
        public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }
        final UserDetails userDetails = userDetailService
                .loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @PostMapping("/registration")
    public @ResponseBody User createUser(@RequestBody UserDTO userDto) throws Exception {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setName(user.getName());
        user.setPassword(userDto.getPassword());
        user.setLanguage(userDto.getLanguage());
        user.setDateOfBirth(userDto.getDateOfBirth());
        user.setDateOfRegistry(new Date());
        user.setActive(true);
        user.setRole("ROLE_USER");
        User savedUser = userService.save(user);
        return savedUser;
    }

    @PutMapping("/registration")
    public @ResponseBody User editUser(@RequestBody UserDTO userDto) throws Exception {
        User user = new User();
        user.setId(userDto.getId());
        user.setUsername(userDto.getUsername());
        user.setName(user.getName());
        user.setPassword(userDto.getPassword());
        user.setLanguage(userDto.getLanguage());
        user.setDateOfBirth(userDto.getDateOfBirth());
        User savedUser = userService.save(user);
        return savedUser;
    }

//    @GetMapping(path = "/user")
//    public @ResponseBody UserDTO getUserFromToken(HttpServletRequest request) {
//        final String authorizationHeader = request.getHeader("Authorization");
//        String username = null;
//        String jwt = null;
//        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
//            jwt = authorizationHeader.substring(7);
//            System.out.println(jwt);
//            System.out.println(jwtTokenUtil.extractUsername(jwt));
//            username = jwtTokenUtil.extractUsername(jwt);
//        }
//        User user = userService.findByUserName(username);
////        UserDTO userDTO = new UserDTO(user.getId(),user.getUsername(), user.getPassword(),
////                user.getName(), user.getLanguage(), user.getDateOfBirth());
//        return userDTO;
//    }

}
