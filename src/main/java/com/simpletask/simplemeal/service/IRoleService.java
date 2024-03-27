package com.simpletask.simplemeal.service;

import com.simpletask.simplemeal.model.User;

public interface IRoleService {
	public void assignChosenOneRole(User user);

	public void revertToUserRole(User user);
}
