import java.util.List;

public class Main {
    public static void main(String[] args) {

        CustomList<Integer> myList = new CustomList<>();
        myList.addFirst(1);
        myList.addFirst(2);
        myList.addFirst(3);
        System.out.println(myList.get(0));
    }
}