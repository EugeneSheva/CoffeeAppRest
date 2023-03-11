package com.example.coffeapp.Coffee.Service;

import com.example.coffeapp.Coffee.Model.Users.User;
import com.example.coffeapp.Coffee.Model.Users.UserDetailsImpl;
import com.example.coffeapp.Coffee.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        return new UserDetailsImpl(user);
    }
}
