import pl.umcs.oop.lec7.shop.Account;
import pl.umcs.oop.lec7.shop.Cart;
import pl.umcs.oop.lec7.shop.Warehouse;

import java.math.BigDecimal;

public class FoodCart extends Cart {
    public FoodCart(Account owner, Warehouse warehouse) {
        super(owner, warehouse);
    }

    public Integer energy() {
        return products.stream()
                .map(product -> ((FoodProduct)product).getEnergy())
                .reduce(0, Integer::sum);
    }

}
