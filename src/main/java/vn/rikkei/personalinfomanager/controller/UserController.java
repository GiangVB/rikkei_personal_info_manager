package vn.rikkei.personalinfomanager.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import vn.rikkei.personalinfomanager.error.UserAlreadyExistException;
import vn.rikkei.personalinfomanager.model.DTO.ChangePasswordForm;
import vn.rikkei.personalinfomanager.model.DTO.RegisterForm;
import vn.rikkei.personalinfomanager.model.entity.User;
import vn.rikkei.personalinfomanager.service.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class UserController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/signup")
    public ModelAndView showSignUpForm() {
        ModelAndView modelAndView = new ModelAndView("register");
        modelAndView.addObject("registerForm", new RegisterForm());
        return modelAndView;
    }

    @PostMapping("/signup")
    public String submitSignUpForm(@ModelAttribute("registerForm") @Valid RegisterForm registerForm, Model model) {
        try {
            userService.registerUser(registerForm);
        }  catch (UserAlreadyExistException uaeEx) {
            model.addAttribute("emailError", "Email already exists");
            return "register";
        }
        return "redirect:/";
    }

    @GetMapping("/login")
    public ModelAndView showLoginForm() {
        ModelAndView modelAndView = new ModelAndView("login");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }
    @PostMapping("/login")
    public String authenticate(@ModelAttribute("user") User user, Model model) {
        User dbUser = userService.loadUserByEmail(user.getEmail());
        if (dbUser != null && passwordEncoder.matches(user.getPasscode(), dbUser.getPasscode())) {
            Integer userId = dbUser.getUserId();
            return "redirect:/user/" + userId;
        } else {
            model.addAttribute("error", "Invalid email or password");
            return "login";
        }
    }

    @GetMapping("/sign-out")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    @GetMapping("/user/{userId}")
    public ModelAndView getUserByUserId(@PathVariable("userId") Integer userId) {
        ModelAndView modelAndView = new ModelAndView("user");
        User user = userService.loadUserByUserId(userId);
        modelAndView.addObject(user);
        return modelAndView;
    }

    @PostMapping("/user/{userId}/update")
    public ModelAndView updateInfo(@PathVariable("userId") Integer userId, @ModelAttribute("user")User user) {
        ModelAndView modelAndView = new ModelAndView("user");
        user.setUserId(userId);
        userService.updateUserInfo(user);
        modelAndView.addObject(user);
        return modelAndView;
    }

    @PostMapping("/user/{userId}/change-password")
    public ModelAndView changePassword(@RequestParam("currentPassword") String currentPassword,
                                 @RequestParam("newPassword") String newPassword,
                                 @PathVariable("userId") Integer userId,
                                 Model model) {
        ModelAndView modelAndView = new ModelAndView("user");
        User dbUser = userService.loadUserByUserId(userId);
        if (!passwordEncoder.matches(currentPassword, dbUser.getPasscode())) {
            model.addAttribute("changeError", "Current password is incorrect");
            modelAndView.addObject(dbUser);
            return modelAndView;
        }
        userService.changePassword(dbUser, newPassword);
        modelAndView.addObject(dbUser);
        return modelAndView;
    }
}
