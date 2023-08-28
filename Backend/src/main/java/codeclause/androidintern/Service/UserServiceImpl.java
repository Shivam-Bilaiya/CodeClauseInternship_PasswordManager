package codeclause.androidintern.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import codeclause.androidintern.Repository.UserRepo;
import codeclause.androidintern.entity.User;
import codeclause.androidintern.entity.User_Password;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;

	@Override
	public User addUserData(User user) {
		return userRepo.save(user);
	}

	@Override
	public User updateUserData(Long userId, User user) {
		User user2 = userRepo.findById(userId).get();
		user2.setUserEmail(user.getUserEmail());
		user2.setUserName(user.getUserName());
		return userRepo.save(user2);
	}

	@Override
	public void deleteUserData(Long userId) {
		userRepo.deleteById(userId);

	}

	@Override
	public User getUserData(Long userId) {
		return userRepo.findById(userId).get();

	}

	@Override
	public List<User_Password> getUserPasswords(Long userId) {
		System.out.println("vanshika user id " + userId);
		User user = userRepo.findById(userId).get();
		System.out.println("PASSWORDS ARE AS " + user.getUserPasswordsList());
		return user.getUserPasswordsList();
	}

	@Override
	public User addUserPassword(Long userId, User_Password user_Password) {
		User user = userRepo.findById(userId).get();
		user.getUserPasswordsList().add(user_Password);
		return userRepo.save(user);
	}

	@Override
	public User loginUserAccount(User user) {
	return userRepo.findByUserEmail(user.getUserEmail());
		
	}
	
	

}
