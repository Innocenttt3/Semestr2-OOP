import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Person implements Serializable {
    private String name;
    private LocalDate birth, death;
    private Person parents[] = new Person[2];

    public static class AmbigiousCollector {
        public String nameCollector;
        public String pathCollector;

        public AmbigiousCollector(String nameCollector, String pathCollector) {
            this.nameCollector = nameCollector;
            this.pathCollector = pathCollector;
        }


    }

    public Person(String name, LocalDate birth) {
        this(name, birth, null);
    }

    public Person(String name, LocalDate birth, LocalDate death) {
        this.name = name;
        this.birth = birth;
        this.death = death;
        try {
            if (birth.isAfter(death)) {
                throw new NegativeLifespanException(birth, death, "Possible time-space loophole.");
            }
        } catch (NullPointerException e) {
        }
    }

    public Person(String name, LocalDate birth, LocalDate death, Person parent1, Person parent2) throws IncestException {
        this(name, birth, death);
        parents[0] = parent1;
        parents[1] = parent2;

        checkForIncest();
    }

    public Person(String name, LocalDate birth, Person parent1, Person parent2) throws IncestException {
        this(name, birth, null, parent1, parent2);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", birth=" + birth +
                ", death=" + death +
                ", parents=" + Arrays.toString(parents) +
                '}';
    }

    void checkForIncest() throws IncestException {
        if (parents[0] == null || parents[1] == null)
            return;
        for (var leftSideParent : parents[0].parents) {
            if (leftSideParent == null) continue;
            for (var rightSideParent : parents[1].parents) {
                if (rightSideParent == null) continue;
                if (leftSideParent == rightSideParent)
                    throw new IncestException(leftSideParent, this);
            }
        }
    }

    private static List<AmbigiousCollector> names = new ArrayList<>();

    private static boolean isAmbigious(String name, String path) {
        for (AmbigiousCollector collector : names) {
            if (collector.nameCollector.equals(name)) {
                return true;
            }
        }
        AmbigiousCollector tmp = new AmbigiousCollector(name, path);
        names.add(tmp);
        return false;
    }


    public static Person createPerson(String path) throws FileNotFoundException, AmbigiousPersonException {
        File file = new File(path);
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (Exception exception) {
            System.out.println("error otwierania pliku");
        }
        String name = scanner.nextLine();
        String tmpBirth = scanner.nextLine();
        LocalDate birthDate = LocalDate.parse(tmpBirth, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        if (isAmbigious(name, path)) {
            throw new AmbigiousPersonException(name, path);
        }
        if (scanner.hasNextLine()) {
            String tmpDeath = scanner.nextLine();
            LocalDate deathDate = LocalDate.parse(tmpDeath, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            return new Person(name, birthDate, deathDate);
        } else {
            return new Person(name, birthDate);
        }
    }
}
