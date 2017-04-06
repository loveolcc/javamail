package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.spi.DirStateFactory.Result;

import com.alibaba.fastjson.JSON;

import dao.ShoppingCarDao;
import model.Book;
import model.Orders;
import model.ShopingCar;
import model.User;
import util.JDBCConnect;
import util.UUIDUtil;

public class ShoppingCarDaoImp implements ShoppingCarDao{

	@Override
	public int toShoppingCar(ShopingCar sc) throws Exception {
		//  Auto-generated method stub
		Connection conn=JDBCConnect.getConnection();
		String sql="insert into shopping_car(user_id,book_id,buynum) values(?,?,?)";
		PreparedStatement ptmt=conn.prepareStatement(sql);
		ptmt.setString(1, sc.getUemail());
		ptmt.setInt(2, sc.getBookId());
		ptmt.setInt(3, sc.getBuynum());
		int result=ptmt.executeUpdate();
		return result;
	}

	@Override
	public String showShoppingCarProducts(User u) throws Exception {
		// TODO Auto-generated method stub
		ArrayList<ShopingCar> list=new ArrayList<ShopingCar>();
		ArrayList<Integer> bidArr=new ArrayList<Integer>();
		ShopingCar sc=null;
		Connection conn=JDBCConnect.getConnection();
		String sql="select * from shopping_car where user_id=?";
		PreparedStatement ptmt=conn.prepareStatement(sql);
		ptmt.setString(1, u.getEmail());
		ResultSet rs=ptmt.executeQuery();
		while(rs.next()){
			sc=new ShopingCar();
			sc.setBookId(rs.getInt("book_id"));
			sc.setBuynum(rs.getInt("buynum"));
			bidArr.add(rs.getInt("book_id"));
			list.add(sc);
		}
		ArrayList<Book> book=findBookById(bidArr);
		for(int i=0;i<book.size();i++){
			list.get(i).setName(book.get(i).getName());
			list.get(i).setPrice(book.get(i).getPrice());
		}
		String json=JSON.toJSONString(list);
		return json;
	}

	@Override
	public ArrayList<Book> findBookById(ArrayList<Integer> bid) throws Exception {
		// TODO Auto-generated method stub
		Connection conn=JDBCConnect.getConnection();
		Book book=null;
		ArrayList<Book> bArray=new ArrayList<Book>();
		for(int b:bid){
		String sql="select * from products where id=?";
		PreparedStatement ptmt=conn.prepareStatement(sql);
		ptmt.setInt(1, b);
		ResultSet rs=ptmt.executeQuery();
		if(rs.next()){
			book=new Book();
			book.setCategory(rs.getString("category"));
			book.setDesciption(rs.getString("description"));
			book.setImgurl(rs.getString("imgUrl"));
			book.setName(rs.getString("name"));
			book.setPrice(rs.getDouble("price"));
			bArray.add(book);
			}
		}
		return bArray;
	}

	@Override
	public void clearShoppingCar(User u) throws Exception {
		// TODO Auto-generated method stub
		Connection conn=JDBCConnect.getConnection();
		String sql="delete from shopping_car where user_id=?";
		PreparedStatement ptmt=conn.prepareStatement(sql);
		ptmt.setString(1, u.getEmail());
		ptmt.executeUpdate();
	}
	
	@Override
	public void createOrder(Orders or) throws Exception {
		// TODO Auto-generated method stub
		Connection conn=JDBCConnect.getConnection();
		String sql="insert into orders(money,receiverAddress,receiverName,receiverPhone,paystate,user_id,id) values(?,?,?,?,?,?,?)";
		PreparedStatement ptmt=conn.prepareStatement(sql);
		ptmt.setString(7, UUIDUtil.getUUID());
		ptmt.setDouble(1, or.getMoney());
		ptmt.setString(2, or.getReceiveAddress());
		ptmt.setString(3, or.getReceiverName());
		ptmt.setString(4, or.getReceiverPhone());
		ptmt.setInt(5, or.getPaystate());
		ptmt.setString(6, or.getUid());
		ptmt.executeUpdate();
	}

	@Override
	public void buyAll(User u,Orders or) throws Exception {
		// TODO Auto-generated method stub
		createOrder(or);
		resetPnum(u);
		clearShoppingCar(u);
	}

	@Override
	public void resetPnum(User u) throws Exception {
		// TODO Auto-generated method stub
		Connection conn=JDBCConnect.getConnection();
		String sql="select * from shopping_car where user_id=?";
		PreparedStatement ptmt=conn.prepareStatement(sql);
		ptmt.setString(1, u.getEmail());
		ResultSet rs=ptmt.executeQuery();
		while(rs.next()){
			String sqlu="update products set pnum=pnum-? where id=?";
			PreparedStatement ptmt1=conn.prepareStatement(sqlu);
			ptmt1.setInt(1, rs.getInt("buynum"));
			ptmt1.setInt(2, rs.getInt("book_id"));
			ptmt1.executeUpdate();
		}
	}
}