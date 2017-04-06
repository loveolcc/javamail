package service.impl;

import dao.BookDao;
import dao.impl.BookDaoImpl;
import model.Book;
import service.BookService;

public class BookServiceImpl implements BookService {

	@Override
	public String findBookByName(Book book) throws Exception {
		// TODO Auto-generated method stub
		BookDao ba=new BookDaoImpl();
		String bookJson=ba.findBookByName(book);
		return bookJson;
	}
	@Override
	public String showAllBook() throws Exception {
		// TODO Auto-generated method stub
		BookDao ba=new BookDaoImpl();
		String bookJson=ba.showAllBook();
		return bookJson;
		
	}
	@Override
	public String findBookById(Book book) throws Exception {
		// TODO Auto-generated method stub
		BookDao ba=new BookDaoImpl();
		String bookJson=ba.findBookById(book);
		return bookJson;
	}

}
