public class notOnLandException extends Exception{
    private String name;
    public notOnLandException(String message) {
    this.name = message;
    }
}
