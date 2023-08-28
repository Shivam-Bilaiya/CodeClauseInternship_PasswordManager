package codeclause.androidintern.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import codeclause.androidintern.DTO.User_PasswordDTO;
import codeclause.androidintern.Service.UserPasswordService;
import codeclause.androidintern.entity.User_Password;

@RestController
@RequestMapping("/passwords")
public class Password_Controller {

	@Autowired
	private UserPasswordService userPasswordService;

	@PutMapping("/update_user_password/{passwordId}")
	private User_PasswordDTO updateUserPassword(@PathVariable Long passwordId,
			@RequestBody User_Password user_Password) {
		
		System.out.println(user_Password.getPasswordLabel() + " " + user_Password.getPassword());
		User_PasswordDTO user_PasswordDTO =  userPasswordService.updateUser_Password(passwordId, user_Password);
		return user_PasswordDTO;

	}
	
	
	@DeleteMapping("/delete_user_password/{passwordId}")
	private Boolean deleteUserPassword(@PathVariable Long passwordId) {
		Boolean result =  userPasswordService.deleteUserPassword(passwordId);
		System.out.println(result);
		return result;
	}
}
