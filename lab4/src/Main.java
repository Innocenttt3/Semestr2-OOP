import java.io.*;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class Main {


    public static void main(String[] args) throws FileNotFoundException, IncestException, AmbigiousPersonException {

        List<String> listOfFile = FilesLoader.readFilesFromPath("/Users/kamilgolawski/Nauka/Programowanie/Semestr2-OOP/lab4/test/test_rodzice");
        Collections.sort(listOfFile);
        List<Person> listOfPeople = new ArrayList<>();
        List<Person> listOfPeopleWithAtLeastOneParent = new ArrayList<>();
        for (String tmp : listOfFile) {
            listOfPeople.add(Person.createPerson(tmp));
        }

        List<Person> relatives = Person.findRelatives(listOfPeople);
        for (Person tmp2 : relatives) {
            System.out.println(tmp2.toString());
        }

    }

}
