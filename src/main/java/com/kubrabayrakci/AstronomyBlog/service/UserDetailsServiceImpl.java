package com.kubrabayrakci.AstronomyBlog.service;

import com.kubrabayrakci.AstronomyBlog.config.MyUserDetails;
import com.kubrabayrakci.AstronomyBlog.model.User;
import com.kubrabayrakci.AstronomyBlog.repository.RoleRepository;
import com.kubrabayrakci.AstronomyBlog.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;



public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUserName(username);

        if(user==null){

            logger.error("Could not find the user with username: {}", username);
            throw new UsernameNotFoundException("Username could not found!");
        }

        return new MyUserDetails(user);
    }
}
