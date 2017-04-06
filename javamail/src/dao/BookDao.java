package dao;

import model.Book;

public interface BookDao {
	public String findBookByName(Book book) throws Exception;
	public String showAllBook() throws Exception;
	public String findBookById(Book book) throws Exception;
}
