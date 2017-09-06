package com.ponagayba.projects.service.user;

import com.ponagayba.projects.dao.user.RoleDAO;
import com.ponagayba.projects.model.Role;
import com.ponagayba.projects.model.User;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleDAO roleDAO;

    public RoleServiceImpl(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    @Override
    public Role findByName(String name) throws SQLException {
        return roleDAO.findByName(name);
    }

    @Override
    public List<Role> getAll() throws SQLException {
        return roleDAO.getAll();
    }

    @Override
    public Role findById(int roleId) throws SQLException {
        return roleDAO.findById(roleId);
    }

    @Override
    public void updateUserRoles(User user) throws SQLException {
        roleDAO.deleteUserRoles(user.getId());
        for (Role role : user.getRoles()) {
            roleDAO.addRoleToUser(user.getId(), role);
        }
    }
}
