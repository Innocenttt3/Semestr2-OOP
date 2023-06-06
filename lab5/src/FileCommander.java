import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
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
    public List<String> ls(String path) {
        int index = 0;
        File folder = new File(path);
        if (!folder.isDirectory()) {
            System.out.println("Specified path is not a directory.");
        }
        File[] files = folder.listFiles();
        List<String> fileList = new ArrayList<>();
        if (files != null) {
            for (File file : files) {
                if (!file.isFile()) {
                    if (!file.getName().startsWith(".")) {
                        fileList.add(index, "[" + file.getName().toString() + "]");
                        index++;
                    }
                }
            }
            this.counter = index;
                for (File file2 : files) {
                    if (file2.isFile()) {
                        if (!file2.getName().startsWith(".")) {
                            fileList.add("[" + file2.getName().toString() + "]");
                        }
                    }
                }

            }
        return fileList;
    }

    public List<String> findBySubString(String subString) {
        File folder = new File(directoryPath.toString());
        File[] files = folder.listFiles();
        List<String> fileHasSubString = new ArrayList<>();
        if (files != null) {
            for (File file : files) {
                String tmp = file.getName().toString();
               if(tmp.contains("iterm")) {
                   fileHasSubString.add(directoryPath.toString() + "/" + file.getName().toString());
               }
            }
        }
        return fileHasSubString;
    }
}

