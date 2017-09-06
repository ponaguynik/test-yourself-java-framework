package com.ponagayba.projects.service.user;

import com.ponagayba.projects.model.Role;
import com.ponagayba.projects.model.User;

import java.sql.SQLException;
import java.util.List;

public interface RoleService {

    Role findByName(String name) throws SQLException;

    List<Role> getAll() throws SQLException;

    Role findById(int roleId) throws SQLException;

    void updateUserRoles(User user) throws SQLException;
}
