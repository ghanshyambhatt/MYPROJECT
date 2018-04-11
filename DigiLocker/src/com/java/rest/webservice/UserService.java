package com.java.rest.webservice;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataParam;

import Controller.UserAuthentication;
import Controller.Validation;

import com.java.DAO.MyUserDAO;
import com.java.Helper.EmailUtility;
import com.java.Helper.SessionAttribute;
import com.java.Helper.TokenGenerator;
import com.java.model.User;

public class UserService {

	public boolean login(String username, String password) {

		User user = new User();
		user.setUsername(username);
		user.setPassword(password);

		UserAuthentication auth = new UserAuthentication(user);
		boolean result = auth.UserValidate();

		return result;
	}

	public String register(String username, String password, String name,
			String email, String mobile, String country) {

		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setName(name);
		user.setEmail(email);
		user.setMobile(mobile);
		user.setCountry(country);
		user.setStatus("active");// to be removed.
		// user.setStatus("inactive");

		MyUserDAO d = new MyUserDAO();

		Validation valid = new Validation();
		boolean usernameisvalid = valid.validateUsername(username);// validating
		// username

		if (usernameisvalid) {

			boolean emailisvalid = valid.validateEmail(email);
			if (emailisvalid) {
				boolean result = d.createNewUser(user);
				if (result) {
					// EmailVerification.emailVerify(user);//to verify and
					// activate account.
					return "success";// Registration Successful
				} else {
					return "internal_error";
				}
			} else {
				return "email_error";
			}
		} else {
			return "username_error";
		}

	}

	public boolean verify(String id) {

		MyUserDAO dao = new MyUserDAO();
		String Username = dao.verifyToken(id);

		if (Username != null) {
			boolean result = dao.setStatus(Username);
			dao.deleteToken(id);// deleting token from database.
			if (result) {
				return true;
			}
		}
		return false;

	}

	public boolean updateUser(String username, String name, String email,
			String mobile, String country) {

		User user = new User();
		user.setUsername(username);
		user.setName(name);
		user.setEmail(email);
		user.setMobile(mobile);
		user.setCountry(country);

		MyUserDAO dao = new MyUserDAO();
		boolean result = dao.UpdateUserInfo(user);

		if (result) {
			return true;
		} else {

			return false;
		}
	}

	public String resetPassword(String username) {

		if (username.equals("")) {
			return "Username cannot be blank. Please enter a valid username.";
		} else {

			MyUserDAO dao = new MyUserDAO();
			User user = dao.getUserbyUsername(username);
			if (user != null) {

				int newPassword = TokenGenerator.getToken();
				String msg = "Your new DigiLocker password is" + " "
						+ newPassword;

				try {
					EmailUtility
							.sendEmail("smtp.gmail.com", "587",
									"aashish.pd097@gmail.com", "password", user
											.getEmail(),
									"DigiLocker:New Passowrd", msg);

					// changing password in database.
					dao.changePassword(username, Integer.toString(newPassword));

				} catch (AddressException e) {
					e.printStackTrace();
					return "AddressExpbody";
					
				} catch (MessagingException e) {
				
					e.printStackTrace();
					return "Failed to send Email due to an intenal error. Please try again later.";
				}

				return "Email has been sent to your registered email account. Please login to access your new password.";

			} else {
				return "Invalid Username. User does not exists. Please enter a valid username.";
			}

		}
	}

}
