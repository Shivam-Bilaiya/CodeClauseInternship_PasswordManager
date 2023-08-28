package codeclause.androidintern.Service;

import org.springframework.stereotype.Service;

import java.util.List;

import codeclause.androidintern.entity.User;
import codeclause.androidintern.entity.User_Password;

@Service
public interface UserService {

	
	public User addUserData(User user);
	
	public User updateUserData(Long userId,User user);
	
	public void deleteUserData(Long userId);
	
	public User getUserData(Long userId);
	
	public List<User_Password> getUserPasswords(Long userId);
	
	public User addUserPassword(Long userId,User_Password user_Password);

	public User loginUserAccount(User user);
	
}
