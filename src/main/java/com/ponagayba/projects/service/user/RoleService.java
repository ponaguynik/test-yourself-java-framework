package com.ponagayba.projects.service.user;

import com.ponagayba.projects.model.Role;
import com.ponagayba.projects.model.User;

import java.sql.SQLException;
import java.util.List;

public interface RoleService {

    Role getByName(String name);

    List<Role> getAll();

    Role getById(int roleId);
}
