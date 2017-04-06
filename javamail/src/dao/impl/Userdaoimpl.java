package dao.impl;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import dao.Userdao;
import model.User;
import util.JDBCConnect;

public class Userdaoimpl implements Userdao{

	@Override
	public void regist(User u) throws SQLException {
		// TODO Auto-generated method stub
		Date time=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String registerTime=sdf.format(time);
		Connection conn=JDBCConnect.getConnection();
		String sql="insert into user(username,PASSWORD,email,state,activeCode,gender,telephone,introduce,registTime) values(?,?,?,?,?,?,?,?,?)";
		PreparedStatement ptmt=conn.prepareStatement(sql);
		ptmt.setString(1, u.getUsername());
		ptmt.setString(2, u.getPassword());
		ptmt.setString(3, u.getEmail());
		ptmt.setInt(4, u.getState());
		ptmt.setString(5, u.getCode());
		ptmt.setString(6, u.getGender());
		ptmt.setString(7, u.getTelephone());
		ptmt.setString(8, u.getIntroduce());
		ptmt.setString(9, registerTime);
		ptmt.executeUpdate();
	}

	@Override
	public User findUserBycode(String code) throws Exception {
		// TODO Auto-generated method stub
		User u=null;
		Connection conn=JDBCConnect.getConnection();
		String sql="select id from user where activeCode=?";
		PreparedStatement ptmt=conn.prepareStatement(sql);
		ptmt.setString(1, code);
		ResultSet rs=ptmt.executeQuery();
		if(rs.next()){
			u=new User();
			u.setUid(rs.getInt("id"));
		}
		return u;
	}

	@Override
	public void active(User u) throws Exception {
		// TODO Auto-generated method stub
		Connection conn=JDBCConnect.getConnection();
		String sql="update user set state=? where id=?";
		PreparedStatement ptmt=conn.prepareStatement(sql);
		ptmt.setInt(1,u.getState());
		ptmt.setInt(2, u.getUid());
		ptmt.executeUpdate();
	}

	@Override
	public boolean isRegistedUser(User u) throws Exception {
		// TODO Auto-generated method stub
		Connection conn=JDBCConnect.getConnection();
		String email=u.getEmail();
		String sql="select * from user where email=?";
		PreparedStatement ptmt=conn.prepareStatement(sql);
		ptmt.setString(1, email);
		ResultSet rs=ptmt.executeQuery();
		if(rs.next())
			return true;
		return false;
	}

	@Override
	public boolean login(User u) throws Exception {
		// TODO Auto-generated method stub
		Connection conn=JDBCConnect.getConnection();
		String sql="select * from user where email=? and password=?";
		PreparedStatement ptmt=conn.prepareStatement(sql);
		ptmt.setString(1, u.getEmail());
		ptmt.setString(2, u.getPassword());
		ResultSet rs=ptmt.executeQuery();
		if(rs.next())
			return true;
		return false;
	}

}
