package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Orders;
import model.ShopingCar;
import model.User;
import service.ShoppingCarService;
import service.impl.ShoppingCarServiceImp;

/**
 * Servlet implementation class ShoppingCarServlet
 */
@WebServlet("/ShoppingCarServlet")
public class ShoppingCarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShoppingCarServlet() {
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
		
		String action=request.getParameter("action");
		HttpSession session=request.getSession();
		Object email=session.getAttribute("email");
		String uemail=null;
		if(email!=null){
			uemail=(String)email;
		}else{
			response.getWriter().print("请先登陆");
			return;
		}
		if(action.equals("toshoppingcar")){
			try{
			ShopingCar sc=new ShopingCar();
			int bid=Integer.parseInt(request.getParameter("bid"));
			int buynum=Integer.parseInt(request.getParameter("buynum"));
			
			sc.setBuynum(buynum);
			sc.setBookId(bid);
			sc.setUemail(uemail);
			
			ShoppingCarService scs=new ShoppingCarServiceImp();
			int result=scs.toShoppingCar(sc);
			if(result>0){
				response.getWriter().print("成功加入购物车");
			}else{
				response.getWriter().print("添加失败");
			}
			}catch(Exception e){
				e.printStackTrace();
			}
		}else if(action.equals("showall")){
			try{
			User u=new User();
			u.setEmail(uemail);
			ShoppingCarService scs=new ShoppingCarServiceImp();
			String json=scs.showShoppingCarProducts(u);
			response.getWriter().print(json);
			}catch(Exception e){
				e.printStackTrace();
			}
		}else if(action.equals("buyall")){
			try{
			Orders or=new Orders();
			User u=new User();
			u.setEmail(uemail);
			String receiverName=request.getParameter("receiverName");
			String receiveAddress=request.getParameter("receiverAddress");
			String telephone=request.getParameter("telephone");
			String allPrice=request.getParameter("allPrice");
			or.setMoney(Double.parseDouble(allPrice));
			or.setPaystate(1);
			or.setReceiveAddress(receiveAddress);
			or.setReceiverName(receiverName);
			or.setReceiverPhone(telephone);
			or.setUid(uemail);
			ShoppingCarService scs=new ShoppingCarServiceImp();
			scs.buyAll(u, or);
			response.getWriter().print("购买成功");
			}catch(Exception e){
				e.printStackTrace();
			}
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
