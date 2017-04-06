package dao;

import java.awt.print.Book;
import java.sql.SQLException;

import model.User;

public interface Userdao {

	public void regist(User u) throws SQLException;
	public User findUserBycode(String code) throws Exception;
	public void active(User u) throws Exception;
	public boolean isRegistedUser(User u) throws Exception;
	public boolean login(User u) throws Exception;
}
