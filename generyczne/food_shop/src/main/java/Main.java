import pl.umcs.oop.lec7.auth.AccountManager;
import pl.umcs.oop.lec7.shop.Account;
import pl.umcs.oop.lec7.shop.Cart;
import pl.umcs.oop.lec7.shop.Product;
import pl.umcs.oop.lec7.shop.Warehouse;

import java.util.List;
import java.math.BigDecimal;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        DatabaseConnection connection = new DatabaseConnection();
        connection.connect("shop.db");
        AccountManager accountManager = new AccountManager(connection);

        //accountManager.register("alice", "secret");
        System.out.println(accountManager.login("alice", "secret"));

        Warehouse warehouse = new Warehouse(connection);
        //warehouse.addProduct(1, 5);
        //warehouse.addProduct(2, 10);

        Account account = new Account("alice");
        Cart cart = new Cart(account, warehouse);

        List<Warehouse.ProductAmount> productList;
        productList = warehouse.findProduct("ziem");
        if(!productList.isEmpty()) {
            cart.add(productList.get(0).product());
            cart.add(productList.get(0).product());
        }
        productList = warehouse.findProduct("bur");
        if(!productList.isEmpty()) {
            cart.add(productList.get(0).product());
        }

        System.out.println(cart.buy());
        connection.disconnect();
    }
}