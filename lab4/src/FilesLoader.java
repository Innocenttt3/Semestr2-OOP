import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FilesLoader {
    public static List<String> readFilesFromPath(String path) {

        List<String> fileList = new ArrayList<>();

        File directory = new File(path);
        if (directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        fileList.add(file.toString());
                    }
                }
            }
        }

        return fileList;
    }
}
