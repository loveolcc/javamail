package dao.impl;

import model.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.alibaba.fastjson.JSON;

import dao.BookDao;
import util.JDBCConnect;

public class BookDaoImpl implements BookDao {

	@Override
	public String findBookByName(Book book) throws Exception {
		// TODO Auto-generated method stub
		Book b=null;
		ArrayList<Book> list=new ArrayList<Book>();
		Connection conn=JDBCConnect.getConnection();
		String sql="select * from products where name=?";
		PreparedStatement ptmt=conn.prepareStatement(sql);
		ptmt.setString(1, book.getName());
		ResultSet rs=ptmt.executeQuery();
		while(rs.next()){
			b=new Book();
			b.setBid(rs.getInt("id"));
			b.setCategory(rs.getString("category"));
			b.setDesciption(rs.getString("description"));
			b.setImgurl(rs.getString("imgurl"));
			b.setName(rs.getString("name"));
			b.setPnum(rs.getInt("pnum"));
			b.setPrice(rs.getDouble("price"));
			list.add(b);
		}
		String bookJson=JSON.toJSONString(list);
		return bookJson;
	}

	@Override
	public String showAllBook() throws Exception {
		// TODO Auto-generated method stub
		Connection conn=JDBCConnect.getConnection();
		ArrayList<Book> list=new ArrayList<Book>();
		Book b=null;
		String sql="select * from products";
		PreparedStatement ptmt=conn.prepareStatement(sql);
		ResultSet rs=ptmt.executeQuery();
		while(rs.next()){
			b=new Book();
			b.setBid(rs.getInt("id"));
			b.setCategory(rs.getString("category"));
			b.setDesciption(rs.getString("description"));
			b.setImgurl(rs.getString("imgurl"));
			b.setName(rs.getString("name"));
			b.setPnum(rs.getInt("pnum"));
			b.setPrice(rs.getDouble("price"));
			list.add(b);
		}
		String bookJson=JSON.toJSONString(list);
		return bookJson;
	}

	@Override
	public String findBookById(Book book) throws Exception {
		// TODO Auto-generated method stub
		Book b=null;
		Connection conn=JDBCConnect.getConnection();
		String sql="select * from products where id=?";
		PreparedStatement ptmt=conn.prepareStatement(sql);
		ptmt.setInt(1, book.getBid());
		ResultSet rs=ptmt.executeQuery();
		if(rs.next()){
			b=new Book();
			b.setBid(rs.getInt("id"));
			b.setCategory(rs.getString("category"));
			b.setDesciption(rs.getString("description"));
			b.setImgurl(rs.getString("imgurl"));
			b.setName(rs.getString("name"));
			b.setPnum(rs.getInt("pnum"));
			b.setPrice(rs.getDouble("price"));
		}
		String bookJson=JSON.toJSONString(b);
		return bookJson;
	}

}
