package com.dp.springsecurity.config;

import com.dp.springsecurity.entity.UserInfo;
import com.dp.springsecurity.repository.UserInfoRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserInfoUserDetailsService implements UserDetailsService {

    @Autowired
    private  UserInfoRepo userInfoRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UserInfo> userInfoFromDB = userInfoRepo.findByName(username);
        return userInfoFromDB.map(UserInfoUserDetails::new)
                .orElseThrow(() -> new IllegalArgumentException("User not found" + username));
    }
}
