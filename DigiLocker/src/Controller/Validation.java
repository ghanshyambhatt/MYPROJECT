package Controller;

import com.java.DAO.MyUserDAO;
import com.java.model.User;

public class Validation {

	public boolean validateUsername(String username) {

		MyUserDAO d=new MyUserDAO();
		User u = d.getUserbyUsername(username);// validating username.
		if (u == null) {
			return true;

		}
		return false;
	}

	public boolean validateEmail(String email) {
		MyUserDAO d=new MyUserDAO();
		User u = d.getUserbyEmail(email);// validating Email.
		if (u == null) {
			return true;

		}
		return false;
	}
}