import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileCommanderCLI {
    private BufferedWriter bufferedWriter;
    private BufferedReader bufferedReader;
    private FileCommander fileCommander;

    public FileCommanderCLI(InputStream dataIN, OutputStream dataOUT) {
        this.fileCommander = new FileCommander();
        InputStreamReader inputStrmRdr = new InputStreamReader(dataIN);
        OutputStreamWriter outputStrmWrtr = new OutputStreamWriter(dataOUT);
        this.bufferedReader = new BufferedReader(inputStrmRdr);
        this.bufferedWriter = new BufferedWriter(outputStrmWrtr);
    }
    public void eventLoop() {
        while(true) {
            String line;
            try {
                line = bufferedReader.readLine();
            }  catch (IOException e) { throw new RuntimeException(e); }
            runCommand(line);
        }
    }
    private void runCommand(String commandToRun) {
        int spaceIndex = commandToRun.indexOf(" ");
        if (spaceIndex != -1 && spaceIndex < commandToRun.length() - 1)  {
            String parameter = commandToRun.substring(spaceIndex + 1);
            String command = commandToRun.substring(0, spaceIndex);
            switch(command) {
                case "cd": fileCommander.cd(parameter); break;
                case "ls": fileCommander.ls(parameter); break;
            }
        } else {
            switch(commandToRun) {
            case "ls": fileCommander.ls(); break;
            case "pwd": fileCommander.pwd(); break;
            case "cd": fileCommander.cd(System.getProperty("user.home")); break;
            }
        }
    }
}

