package codeclause.androidintern.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import codeclause.androidintern.Service.UserService;
import codeclause.androidintern.entity.User;
import codeclause.androidintern.entity.User_Password;

@RestController
@RequestMapping("/users")
public class User_Controller {

	@Autowired(required = true)
	public UserService userService;

	@PostMapping("/add_new_user_data")
	public User addUser(@RequestBody User user) {
		return userService.addUserData(user);
	}

	@GetMapping("/get_user_password_list/{userId}")
	public List<User_Password> getUserPassword(@PathVariable Long userId) {
		List<User_Password> list = userService.getUserPasswords(userId);
		return list;
	}

	@PutMapping("/update_user_data/{userId}")
	public User updateUserData(@PathVariable Long userId, @RequestBody User user) {
		return userService.updateUserData(userId, user);
	}

	@GetMapping("/get_user_data/{userId}")
	public User getUserData(@PathVariable Long userId) {
		return userService.getUserData(userId);
	}

	@PostMapping("/add_new_user_password/{userId}")
	public User addUserPassword(@PathVariable Long userId, @RequestBody User_Password user_Password) {
		return userService.addUserPassword(userId, user_Password);
	}


	@DeleteMapping("/delete_user_data/{userId}")
	public void deleteUserData(@PathVariable Long userId) {
		userService.deleteUserData(userId);
	}
	
	@PostMapping("/login_user_data")
	public User loginUserAccount(@RequestBody User user) {
		return userService.loginUserAccount(user);
	}

}
