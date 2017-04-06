package service.impl;

import dao.ShoppingCarDao;
import dao.impl.ShoppingCarDaoImp;
import model.Orders;
import model.ShopingCar;
import model.User;
import service.ShoppingCarService;

public class ShoppingCarServiceImp implements ShoppingCarService {

	@Override
	public int toShoppingCar(ShopingCar sc) throws Exception {
		// TODO Auto-generated method stub
		ShoppingCarDao scd=new ShoppingCarDaoImp();
		int result=scd.toShoppingCar(sc);
		return result;
	}

	@Override
	public String showShoppingCarProducts(User u) throws Exception {
		// TODO Auto-generated method stub
		ShoppingCarDao scd=new ShoppingCarDaoImp();
		String json=scd.showShoppingCarProducts(u);
		return json;
	}

	@Override
	public void buyAll(User u, Orders or) throws Exception {
		// TODO Auto-generated method stub
		ShoppingCarDao scd=new ShoppingCarDaoImp();
		scd.buyAll(u, or);
	}

}
