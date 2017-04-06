package dao;

import java.util.ArrayList;

import model.Book;
import model.Orders;
import model.ShopingCar;
import model.User;

public interface ShoppingCarDao {

	public int toShoppingCar(ShopingCar sc) throws Exception;
	public void buyAll(User u,Orders or) throws Exception;
	public ArrayList<Book> findBookById(ArrayList<Integer> bid) throws Exception;
	public void clearShoppingCar(User u) throws Exception;
	public void createOrder(Orders or) throws Exception;
	public String showShoppingCarProducts(User u) throws Exception;
	public void resetPnum(User u) throws Exception;
}
