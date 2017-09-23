package com.ponagayba.projects.service.user;

import com.ponagayba.projects.dao.user.RoleDAO;
import com.ponagayba.projects.model.Role;
import com.ponagayba.projects.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Transactional
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDAO roleDAO;

    @Override
    public Role getByName(String name) throws SQLException {
        return roleDAO.getByName(name);
    }

    @Override
    public List<Role> getAll() throws SQLException {
        return roleDAO.getAll();
    }

    @Override
    public Role getById(int roleId) throws SQLException {
        return roleDAO.getById(roleId);
    }

}
