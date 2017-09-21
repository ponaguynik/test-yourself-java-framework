package com.ponagayba.projects.model;

import com.ponagayba.projects.validator.EmailNotExists;
import com.ponagayba.projects.validator.PasswordsMatch;
import com.ponagayba.projects.validator.UsernameNotExists;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@PasswordsMatch
public class User {

    private Integer id;

    @NotNull
    @Size(min = 4, max = 12)
    @UsernameNotExists
    private String username;

    @NotNull
    @Size(min = 5, max = 254)
    @Email
    @EmailNotExists
    private String email;

    @NotNull
    @Size(min = 4, max = 20)
    private String password;

    private boolean enabled;

    private String confPassword;

    private String token;
    private Integer lastResult;
    private Integer bestResult;
    private List<Role> roles;
    private boolean admin;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String email, String password) {
        this(username, password);
        this.email = email;
    }

    public User(Integer id, String username, String email, String password, String token, Integer lastResult,
                Integer bestResult, boolean enabled) {
        this(username, email, password);
        this.id = id;
        this.token = token;
        this.lastResult = lastResult;
        this.bestResult = bestResult;
        this.enabled = enabled;
    }

    public User(User user) {
        this(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                user.getToken(),
                user.getLastResult(),
                user.getBestResult(),
                user.isEnabled()
        );
        this.roles = user.getRoles();
        this.admin = user.isAdmin();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getLastResult() {
        return lastResult;
    }

    public int getBestResult() {
        return bestResult;
    }

    public void setLastResult(Integer lastResult) {
        this.lastResult = lastResult;
    }

    public void setBestResult(Integer bestResult) {
        this.bestResult = bestResult;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setRoles(List<Role> roles) {
        admin = roles.contains(new Role(0, "admin"));
        this.roles = roles;
    }

    public String getConfPassword() {
        return confPassword;
    }

    public void setConfPassword(String confPassword) {
        this.confPassword = confPassword;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
