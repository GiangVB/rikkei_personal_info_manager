package vn.rikkei.personalinfomanager.config.security;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import vn.rikkei.personalinfomanager.model.entity.User;
import vn.rikkei.personalinfomanager.service.UserService;

@Component
public class WebSecurity {
    private final UserService userService;

    public WebSecurity(UserService userService) {
        this.userService = userService;
    }

    public boolean checkUserId(Authentication authentication, int userId) {
		String email = authentication.getName();
        User user = userService.loadUserByEmail(email);
        return user != null && user.getUserId() == userId;
    }
}
