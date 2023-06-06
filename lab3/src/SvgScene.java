import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class SvgScene {
    private List<String> def = new ArrayList<>();
    public void addStrings(String newString){
        this.def.add(newString);
    }
    private static SvgScene instance = null;
    public static SvgScene getInstance() {
        if(instance == null) {
            instance = new SvgScene();
        }
        return instance;
    }
    private List<Shape> shapes = new ArrayList<>();

    public void add(Shape shape){
        this.shapes.add(shape);
    }

    public void saveHtml(String path){
        FileWriter file;
        try{
            file = new FileWriter(path);
            file.write("<defs> \n<html> <body> <svg width=\"1000\" height=\"1000\">\n");
            for (Shape p: this.shapes) {
                file.write(p.toSvg("") + "\n");
            }
            file.write("</svg>\n</body>\n</html>\n </defs>");
            file.close();
        } catch(Exception exception) {
            throw new RuntimeException(exception);
        }

    }
}