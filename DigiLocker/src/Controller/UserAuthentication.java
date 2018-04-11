package Controller;

import com.java.DAO.MyUserDAO;
import com.java.interfaces.UserDAO;
import com.java.model.User;

public class UserAuthentication {
	String username, password;
	User user;

	public UserAuthentication(User u1) {
		username = u1.getUsername();
		password = u1.getPassword();

		UserDAO UD = new MyUserDAO();
		user = UD.getUserbyUsername(username);
	}

	public boolean UserValidate() {
		if (user != null) {
			//checking user password and current status of account.
			if (user.getPassword().equals(password)&& user.getStatus().equalsIgnoreCase("active")) {
				return true;
			}
		}
		return false;

	}
}
