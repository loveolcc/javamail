package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import service.impl.UserServiceImpl;
import util.UUIDUtil;

/**
 * Servlet implementation class Regist
 */
public class RegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistServlet() {
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
		//��������
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String email=request.getParameter("email");
		String gender=request.getParameter("gender");
		String telephone=request.getParameter("telephone");
		String introduce=request.getParameter("introduce");
		
		//��װ
		User u=new User();
		u.setEmail(email);
		u.setPassword(password);
		u.setUsername(username);
		u.setState(0);
		u.setCode(UUIDUtil.getUUID());
		u.setGender(gender);
		u.setTelephone(telephone);
		u.setIntroduce(introduce);
		
		//ҵ���ʵ��
		UserServiceImpl usi=new UserServiceImpl();
		if(usi.isRegistedUser(u)){
			response.getWriter().print("������ע��");
			return;
		}
		response.getWriter().print("ע��ɹ�,�ѷ���һ���ʼ�����������,�뼤�������˺�");
		usi.regist(u);
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
