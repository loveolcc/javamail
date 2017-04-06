package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Book;
import service.BookService;
import service.impl.BookServiceImpl;

/**
 * Servlet implementation class BookSeachServlet
 */
public class BookSeachServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookSeachServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		try{
		String action=request.getParameter("method");
		if(action.equals("name")){
		String bookName=request.getParameter("bookName");
		
		Book book=new Book();
		book.setName(bookName);
		
		BookService bs=new BookServiceImpl();
		String bookJson=bs.findBookByName(book);
		response.getWriter().print(bookJson);
		}else if(action.equals("id")){
			int bid=Integer.parseInt(request.getParameter("bid"));
			Book book=new Book();
			book.setBid(bid);
			
			BookService bs=new BookServiceImpl();
			String bookJson=bs.findBookById(book);
			response.getWriter().print(bookJson);
		}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
