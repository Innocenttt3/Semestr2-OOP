import java.io.*;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Main {


    public static void main(String[] args) throws FileNotFoundException, AmbigiousPersonException {
        try {
            Person person1 = Person.createPerson("/Users/kamilgolawski/Nauka/Programowanie/Semestr2-OOP/lab4/test/test_same_osoby/Alicja Stefanek.txt");
            Person person2 = Person.createPerson("/Users/kamilgolawski/Nauka/Programowanie/Semestr2-OOP/lab4/test/test_same_osoby/Alicja Stefanek copy.txt");
        } catch (AmbigiousPersonException e) {
            e.printStackTrace();
        }
    }

}
