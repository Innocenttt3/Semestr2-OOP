import pl.umcs.oop.lec7.shop.Account;
import pl.umcs.oop.lec7.shop.Cart;

import java.math.BigDecimal;

public class FoodCart extends Cart<FoodProduct> {
    public FoodCart(Account owner) {
        super(owner);
    }

    public Integer energy() {
        return products.stream()
                .map(product -> product.getEnergy())
                .reduce(0, Integer::sum);
    }

}
