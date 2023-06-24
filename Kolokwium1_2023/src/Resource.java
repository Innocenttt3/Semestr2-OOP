public class Resource {
    public enum Type {
        Coal, Wood, Fish;
    }

    public final Point centerOnMap;
    public Type whatResource;

    public Resource(Point centerOnMap, Type whatResource) {
        this.centerOnMap = centerOnMap;
        this.whatResource = whatResource;
    }
}
