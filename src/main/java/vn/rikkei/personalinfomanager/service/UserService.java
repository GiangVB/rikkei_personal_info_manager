package vn.rikkei.personalinfomanager.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.rikkei.personalinfomanager.error.UserAlreadyExistException;
import vn.rikkei.personalinfomanager.model.DTO.RegisterForm;
import vn.rikkei.personalinfomanager.model.entity.User;
import vn.rikkei.personalinfomanager.repository.UserMapper;

@Service
public class UserService {
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;

    public UserService(UserMapper userMapper, PasswordEncoder passwordEncoder, UserDetailsService userDetailsService) {
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
    }

    public User loadUserByUserId(Integer userId) {
        return userMapper.findById(userId);
    }

    public User loadUserByEmail(String email) {
        return userMapper.findByEmail(email);
    }

    public void registerUser(RegisterForm registerForm) {
        if (emailExists(registerForm.getEmail())) {
            throw new UserAlreadyExistException("Email already exists");
        }
        User user = new User();
        user.setEmail(registerForm.getEmail());
        user.setPasscode(registerForm.getPasscode());
        String encodedPassword = passwordEncoder.encode(user.getPasscode());
        user.setPasscode(encodedPassword);
        userMapper.addUser(user);
    }



    public void updateUserInfo(User user) {
        userMapper.updateUser(user);
    }

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = userDetailsService.loadUserByUsername(authentication.getName());
        return userMapper.findByEmail(userDetails.getUsername());
    }

    private boolean emailExists(String email) {
        return userMapper.findByEmail(email) != null;
    }

    public void changePassword(User user, String newPassword) {
        user.setPasscode(passwordEncoder.encode(newPassword));
        userMapper.updateUserPassword(user);
    }
}
