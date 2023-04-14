package vn.rikkei.personalinfomanager.service;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.rikkei.personalinfomanager.model.entity.User;
import vn.rikkei.personalinfomanager.repository.UserMapper;

import java.util.Collections;

@Service
@Transactional
public class UserDetailService implements UserDetailsService {
    private final UserMapper userMapper;

    public UserDetailService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userMapper.findByEmail(email);
        if(user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPasscode(),
                Collections.singleton(new SimpleGrantedAuthority("USER")));
    }
}
