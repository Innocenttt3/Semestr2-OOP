import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        FileCommanderCLI fc = new FileCommanderCLI(System.in, System.out);
        fc.eventLoop();

//        FileCommander test1 = new FileCommander();
//        test1.cd("/Users/kamilgolawski/Nauka/sandbox");
//        test1.ls();

//        List<String> filesWithSubStringList = test1.findBySubString("Nauka");
//        for (String element : filesWithSubStringList) {
//            System.out.println(element);
//        }



    }
}