package com.bei.libary.service;

import com.bei.libary.payload.ApiResponse;
import com.bei.libary.payload.InfoRequest;
import com.bei.libary.payload.UserIdentityAvailability;
import com.bei.libary.payload.UserSummary;
import com.bei.libary.security.UserPrincipal;
import com.bei.libary.model.user.User;


public interface UserService {

	UserSummary getCurrentUser( UserPrincipal currentUser);

	UserIdentityAvailability checkUsernameAvailability(String username);

	UserIdentityAvailability checkEmailAvailability(String email);

	User addUser(User user);

	User updateUser(User newUser, String username, UserPrincipal currentUser);

	ApiResponse deleteUser(String username, UserPrincipal currentUser);

	ApiResponse giveAdmin(String username);

	ApiResponse removeAdmin(String username);

}