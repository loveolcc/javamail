package service;

import model.Orders;
import model.ShopingCar;
import model.User;

public interface ShoppingCarService {

	public int toShoppingCar(ShopingCar sc) throws Exception;
	public String showShoppingCarProducts(User u) throws Exception;
	public void buyAll(User u,Orders or) throws Exception;
}
