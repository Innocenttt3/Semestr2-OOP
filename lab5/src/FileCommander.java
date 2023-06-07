import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class FileCommander {
    private Path directoryPath;
    public int counter = 0;

    public Path getDirectoryPath() {
        return directoryPath;
    }

    public FileCommander() {
        this.directoryPath = Paths.get(System.getProperty("user.home"));
    }

    public String pwd() {
        System.out.println((directoryPath.toString()));
        return directoryPath.toString();
    }

    public void cd(String path) {
        Path newPath = directoryPath.resolve(path);
        if (Files.isDirectory(newPath)) {
            directoryPath = newPath;
        } else {
            System.out.println("Podana ścieżka nie jest katalogiem.");
        }
    }
    public List<String> ls(String parameter) {
        int index = 0;
        List<String> fileList = new ArrayList<>();
        if (parameter.contains("--filter=")) {
            int equalsIndex = parameter.indexOf("=");
            String txtToFind = parameter.substring(equalsIndex + 1);
            File folder = new File(directoryPath.toString());
            if (!folder.isDirectory()) {
                System.out.println("Specified path is not a directory.");
            }
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (!file.isFile()) {
                        if (!file.getName().startsWith(".") && file.getName().contains(txtToFind)) {
                            fileList.add(index, file.getName().toString());
                            index++;
                        }
                    }
                }
                this.counter = index;
                    for (File file2 : files) {
                        if (file2.isFile()) {
                            if (!file2.getName().startsWith(".") && file2.getName().contains(txtToFind)) {
                                fileList.add(file2.getName().toString());
                            }
                        }
                    }
            }
        }
        else {
            File folder = new File(directoryPath.toString());
            if (!folder.isDirectory()) {
                System.out.println("Specified path is not a directory.");
            }
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (!file.isFile()) {
                        if (!file.getName().startsWith(".")) {
                            fileList.add(index, file.getName().toString());
                            index++;
                        }
                    }
                }
                this.counter = index;
                for (File file2 : files) {
                    if (file2.isFile()) {
                        if (!file2.getName().startsWith(".")) {
                            fileList.add(file2.getName().toString());
                        }
                    }
                }

            }
            List<String> firstPart = fileList.subList(0, counter);
            Collections.sort(firstPart);

            List<String> secondPart = fileList.subList(counter, fileList.size());
            Collections.sort(secondPart);

            List<String> mergedList = new ArrayList<>(firstPart);
            mergedList.addAll(secondPart);
            if (parameter.equals("--color")) {
                for (String element : mergedList) {
                    System.out.println(ConsoleColors.BLUE + element + ConsoleColors.RESET);
                }
            } else if (parameter.equals("--brackets")) {
                for (String element : mergedList) {
                    System.out.println("[" + element + "]");
                }
            } else if (parameter.equals("--brackets --color")) {
                for (String element : mergedList) {
                    System.out.println(ConsoleColors.BLUE + "[" + element + "]" + ConsoleColors.RESET);
                }
            } else if (parameter.equals("--color --brackets")) {
                for (String element : mergedList) {
                    System.out.println(ConsoleColors.BLUE + "[" + element + "]" + ConsoleColors.RESET);
                }
            }
            return mergedList;
        }
       return null;
    }
    public List<String> ls() {
        int index = 0;
        File folder = new File(directoryPath.toString());
        if (!folder.isDirectory()) {
            System.out.println("Specified path is not a directory.");
        }
        File[] files = folder.listFiles();
        List<String> fileList = new ArrayList<>();
        if (files != null) {
            for (File file : files) {
                if (!file.isFile()) {
                    if (!file.getName().startsWith(".")) {
                        fileList.add(index, file.getName().toString());
                        index++;
                    }
                }
            }
            this.counter = index;
            for (File file2 : files) {
                if (file2.isFile()) {
                    if (!file2.getName().startsWith(".")) {
                        fileList.add(file2.getName().toString());
                    }
                }
            }

        }
        List<String> firstPart = fileList.subList(0, counter);
        Collections.sort(firstPart);

        List<String> secondPart = fileList.subList(counter , fileList.size());
        Collections.sort(secondPart);

        List<String> mergedList = new ArrayList<>(firstPart);
        mergedList.addAll(secondPart);

        for (String element : mergedList) { System.out.println(element);}

        return fileList;
    }

    public List<String> findBySubString(String subString) {
        File folder = new File(directoryPath.toString());
        File[] files = folder.listFiles();
        List<String> fileHasSubString = new ArrayList<>();
        if (files != null) {
            for (File file : files) {
                String tmp = file.getName().toString();
               if(tmp.contains(subString)) {
                   fileHasSubString.add(directoryPath.toString() + "/" + file.getName().toString());
               }
            }
        }
        return fileHasSubString;
    }

}

