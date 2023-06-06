import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        FileCommander test1 = new FileCommander();
        test1.cd("/Users/kamilgolawski/Nauka/sandbox");
        List<String> filesWithSubStringList = test1.findBySubString("Nauka");
        for (String element : filesWithSubStringList) {
            System.out.println(element);
        }

//        List<String> myList = test1.ls("/Users/kamilgolawski/Nauka/sandbox");
//
//        List<String> firstPart = myList.subList(0, test1.counter);
//        Collections.sort(firstPart);
//
//        List<String> secondPart = myList.subList(test1.counter, myList.size());
//        Collections.sort(secondPart);
//
//        List<String> mergedList = new ArrayList<>(firstPart);
//        mergedList.addAll(secondPart);
//
//        for (String element : mergedList) {
//            System.out.println(element);
//        }

    }
}