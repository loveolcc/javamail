package servlet;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;
import service.UserService;
import service.impl.UserServiceImpl;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try{
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		//��������
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String dcode=request.getParameter("dcode");
		//��װ
		User u=new User();
		u.setEmail(username);
		u.setPassword(password);
		
		//ҵ����
		UserService us=new UserServiceImpl();
		HttpSession session=request.getSession();
		String code=(String) session.getAttribute("code");
		//�ж���֤���Ƿ���ȷ
		if(!dcode.equals(code)){
			response.getWriter().write("��֤�����");
			return;
		}
		//�ж��û��Ƿ���ע��
		if(us.isRegistedUser(u)){
			if(us.login(u)){
				session.setAttribute("email", u.getEmail());
				response.sendRedirect("admin/index.jsp");
			}else{
				response.getWriter().write("�������");
			}
		}else{
			response.getWriter().write("�޴��û�������ע��");
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
