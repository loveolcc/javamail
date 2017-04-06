package service;

import java.awt.image.BufferedImage;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;

public interface UserService {

	public void regist(User u) throws SQLException, AddressException, MessagingException;
	public User findUserBycode(String code) throws Exception;
	public void active(User u) throws Exception;
	public boolean isRegistedUser(User u) throws Exception;
	public boolean login(User u) throws Exception;
	public HashMap getImage() throws Exception;
}
