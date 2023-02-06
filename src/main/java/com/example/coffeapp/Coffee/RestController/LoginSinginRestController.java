package com.example.coffeapp.Coffee.RestController;

import com.example.coffeapp.Coffee.Model.Users.User;
import com.example.coffeapp.Coffee.Security.JwtUtil;
import com.example.coffeapp.Coffee.Service.MyUserDetailService;
import com.example.coffeapp.Coffee.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

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
    public User createUser(@RequestBody User user) throws Exception {
        user.setActive(true);
        user.setRole("ROLE_USER");
        User savedUser = userService.save(user);
        return savedUser;
    }

    @PutMapping("/registration")
    public User editUser(@RequestBody User user) throws Exception {
        User olduser = userService.findById(user.getId());
        user.setActive(olduser.isActive());
        user.setRole(olduser.getRole());
        User savedUser = userService.save(user);
        return savedUser;
    }

}
