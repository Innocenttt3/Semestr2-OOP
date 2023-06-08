import java.io.*;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class Main {


    public static void main(String[] args) throws FileNotFoundException, IncestException, AmbigiousPersonException {

//        List<String> listOfFile = FilesLoader.readFilesFromPath("/Users/kamilgolawski/Nauka/Programowanie/Semestr2-OOP/lab4/test/test_rodzice");
//        Collections.sort(listOfFile);
//        for (String tmp: listOfFile) {
//            System.out.println(tmp);
//        }
        Person testPerson = Person.createPerson("/Users/kamilgolawski/Nauka/Programowanie/Semestr2-OOP/lab4/test/test_rodzice/p3.txt");
        System.out.println(testPerson.toString());
    }

}
