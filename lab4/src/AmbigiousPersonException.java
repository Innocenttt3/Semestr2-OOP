public class AmbigiousPersonException extends Exception {
    public AmbigiousPersonException(String name, String path) {
        super("Multiple persons with name: " + name + "from path:" + path);
    }
}