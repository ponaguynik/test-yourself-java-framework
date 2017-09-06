package com.ponagayba.projects.dao.user;


import com.ponagayba.projects.model.Role;

import java.sql.SQLException;
import java.util.List;

public interface RoleDAO {

    Role findByName(String name);

    List<Role> getUserRoles(int userId);

    List<Role> getAll();

    void deleteUserRoles(int userId);

    void addRoleToUser(int userId, Role role);

    Role findById(int roleId);
}
