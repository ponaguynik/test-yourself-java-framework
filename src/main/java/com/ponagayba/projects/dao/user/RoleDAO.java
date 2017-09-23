package com.ponagayba.projects.dao.user;


import com.ponagayba.projects.model.Role;

import java.sql.SQLException;
import java.util.List;

public interface RoleDAO {

    Role getByName(String name);

    List<Role> getAll();

    Role getById(int roleId);
}
