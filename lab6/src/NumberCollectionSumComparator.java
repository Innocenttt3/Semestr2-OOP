import java.util.Comparator;
import java.util.Collection;

public class NumberCollectionSumComparator implements Comparator<Collection<? extends Number>> {
    @Override
    public int compare(Collection<? extends Number> c1, Collection<? extends Number> c2) {
        double sum1 = c1.stream().mapToDouble(Number::doubleValue).sum();
        double sum2 = c2.stream().mapToDouble(Number::doubleValue).sum();
        return Double.compare(sum1, sum2);
    }
}
