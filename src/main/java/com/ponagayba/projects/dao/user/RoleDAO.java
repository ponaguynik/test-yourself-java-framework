package com.ponagayba.projects.dao.user;


import com.ponagayba.projects.model.Role;

import java.sql.SQLException;
import java.util.List;

public interface RoleDAO {

    Role findByName(String name) throws SQLException;

    List<Role> getUserRoles(int userId) throws SQLException;

    List<Role> getAll() throws SQLException;

    void deleteUserRoles(int userId) throws SQLException;

    void addRoleToUser(int userId, Role role) throws SQLException;

    Role findById(int roleId) throws SQLException;
}
