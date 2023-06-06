import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class SvgScene {

    private List<Polygon> polygons;
    public SvgScene(){
        polygons = new ArrayList<Polygon>();
    }

    public void addPolygon (Polygon polygon){
        polygons.add(polygon);
    }

    public void save(String path){
        String stringFilePath = "/temp.html";
        path += stringFilePath;
        FileWriter file;
        try{

            file = new FileWriter(path);
            for (Polygon polygon: this.polygons){
                file.write(polygon.toSVG());
            }
            file.close();
        }
        catch(Exception e){System.out.println("error");}
    }


}
