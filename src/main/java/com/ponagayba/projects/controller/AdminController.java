package com.ponagayba.projects.controller;

import com.ponagayba.projects.exception.EmailExistsException;
import com.ponagayba.projects.exception.UserNotFoundException;
import com.ponagayba.projects.exception.UsernameExistsException;
import com.ponagayba.projects.model.Role;
import com.ponagayba.projects.model.User;
import com.ponagayba.projects.service.user.RoleService;
import com.ponagayba.projects.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(method = RequestMethod.GET)
    public String admin() {
        return "redirect:/admin/users";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ModelAndView users() {
        ModelAndView mv = new ModelAndView("admin/users");
        List<User> users = userService.getAll();
        mv.addObject("users", users);
        return mv;
    }

    @RequestMapping(value = "/users/manage", method = RequestMethod.GET)
    public ModelAndView manage(@RequestParam int userId) throws UserNotFoundException {
        ModelAndView mv = new ModelAndView("admin/manageUser");
        User user = userService.getById(userId);
        if (user == null) {
            throw new UserNotFoundException();
        }
        mv.addObject("user", user);
        mv.addObject("updatedUser", user);
        mv.addObject("roles", roleService.getAll());
        return mv;
    }

    @RequestMapping(value = "/users/manage/update", method = RequestMethod.POST)
    public ModelAndView updateUser(@ModelAttribute("updatedUser") User updatedUser,
                                   @RequestParam("userId") int userId, Locale locale) {
        ModelAndView mv = new ModelAndView("admin/manageUser");
        User user = userService.getById(userId);
        try {
            checkForExistence(user, updatedUser);
            mergeUsers(user, updatedUser);
            userService.updateUser(user);
        } catch (UsernameExistsException e) {
            mv.addObject("error", messageSource.getMessage("user.username.exists", null, locale));
        } catch (EmailExistsException e) {
            mv.addObject("error", messageSource.getMessage("user.email.exists", null, locale));
        }
        mv.addObject("user", user);
        mv.addObject("updatedUser", user);
        mv.addObject("roles", roleService.getAll());
        return mv;
    }

    @RequestMapping(value = "/users/delete", method = RequestMethod.POST)
    public String deleteUser(@RequestParam(name = "userId") int userId) {
        User user = userService.getById(userId);
        if (user != null) {
            userService.deleteUser(user);
        }
        return "redirect:/admin/users";
    }

    @RequestMapping(value = "/users/manage/password", method = RequestMethod.POST)
    public ModelAndView changePassword(@RequestParam("password") String password,
                                       @RequestParam("newPassword") String newPassword,
                                       @RequestParam("userId") int userId,
                                       Locale locale) {
        User user = userService.getById(userId);
        if (user.getPassword().equals(password)) {
            user.setPassword(newPassword);
            userService.updateUser(user);
            return new ModelAndView("redirect:/admin/users/manage?userId=" + userId);
        }
        ModelAndView mv = new ModelAndView("admin/manageUser");
        mv.addObject("user", user);
        mv.addObject("updatedUser", user);
        mv.addObject("roles", roleService.getAll());
        mv.addObject("passwordError", messageSource.getMessage("incorrect.password", null, locale));
        return mv;
    }

    private void checkForExistence(User user, User updatedUser) throws UsernameExistsException, EmailExistsException {
        if (!user.getUsername().equals(updatedUser.getUsername())
                && userService.usernameExists(updatedUser.getUsername())) {
            throw new UsernameExistsException();
        }
        if (!user.getEmail().equals(updatedUser.getEmail())
                && userService.emailExists(updatedUser.getEmail())) {
            throw new EmailExistsException();
        }
    }

    private void mergeUsers(User user, User updatedUser) {
        user.setUsername(updatedUser.getUsername());
        user.setEmail(updatedUser.getEmail());
        List<Role> roles = new ArrayList<>();
        for (Role role : updatedUser.getRoles()) {
            roles.add(roleService.getByName(role.getName()));
        }
        user.setRoles(roles);
    }
}
