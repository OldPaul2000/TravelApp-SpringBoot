package com.travelapp.travelapp.config;

import com.travelapp.travelapp.model.userrelated.User;
import com.travelapp.travelapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try{
            User user = userRepository.findUserByUsernameWithRoles(username);
            List<SimpleGrantedAuthority> authorities = user.getRoles().stream().map(role -> {
                return new SimpleGrantedAuthority(role.getRole());
            }).toList();

            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
        }
        catch (EmptyResultDataAccessException e){
            throw new UsernameNotFoundException("Username not found: " + username);
        }
    }
}
