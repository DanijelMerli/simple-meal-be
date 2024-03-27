package com.simpletask.simplemeal.service;

import com.simpletask.simplemeal.model.Role;
import com.simpletask.simplemeal.model.User;
import com.simpletask.simplemeal.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService implements IRoleService{
    @Autowired
    private RoleRepository roleRepository;
    @Override
    public void assignChosenOneRole(User user) {
        Role chosenOneRole = roleRepository.findByName("ROLE_THE_CHOSEN_ONE");
        user.setRole(chosenOneRole);
    }

    @Override
    public void revertToUserRole(User user) {
        Role userRole = roleRepository.findByName("ROLE_USER");
        user.setRole(userRole);
    }
}
