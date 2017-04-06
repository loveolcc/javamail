package service.impl;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;

import java.util.Map;

import dao.Userdao;
import dao.impl.Userdaoimpl;
import model.User;
import service.UserService;
import util.MailUtil;

public class UserServiceImpl implements UserService{

	private Userdao userDao;
	public void setUserDao(Userdao userDao) {
		this.userDao = userDao;
	}

	@Override
	public void regist(User u) throws SQLException, AddressException, MessagingException {
		// TODO Auto-generated method stub
		Userdao udi=new Userdaoimpl();
		udi.regist(u);
		
		//·¢ËÍ¼¤»îÓÊ¼þ
		MailUtil.sendMail(u.getEmail(), u.getCode());
	}

	@Override
	public User findUserBycode(String code) throws Exception {
		// TODO Auto-generated method stub
		Userdao udi=new Userdaoimpl();
		User u=udi.findUserBycode(code);
		return u;
	}

	@Override
	public void active(User u) throws Exception {
		// TODO Auto-generated method stub
		Userdao udi=new Userdaoimpl();
		udi.active(u);
	}

	@Override
	public boolean isRegistedUser(User u) throws Exception {
		// TODO Auto-generated method stub
		Userdao udi=new Userdaoimpl();
		boolean isru=udi.isRegistedUser(u);
		return isru;
	}

	@Override
	public boolean login(User u) throws Exception {
		// TODO Auto-generated method stub
		Userdao udi=new Userdaoimpl();
		boolean canLogin=udi.login(u);
		return canLogin;
	}

	@Override
	public HashMap getImage() throws Exception {
		// TODO Auto-generated method stub
		BufferedImage bi=new BufferedImage(88, 22, BufferedImage.TYPE_INT_RGB);
		Graphics g=bi.getGraphics();
		Color c=new Color(175, 238, 238);
		g.setColor(c);
		g.fillRect(0,0, 88, 22);
		
		char ch[]="QAZWSXEDCRFVTGBYHNUJMIKOLP1234567890".toCharArray();
		Random r=new Random();
		int len=ch.length;
		int index;
		StringBuffer sb=new StringBuffer();
		for(int i=0;i<4;i++){
			index=r.nextInt(len);
			g.setColor(new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)));
			g.drawString(ch[index]+"", i*15+3, 18);
			sb.append(ch[index]);
		}
		HashMap map=new HashMap();
		map.put("image", bi);
		map.put("code", sb.toString());
		return map;
	}

}
