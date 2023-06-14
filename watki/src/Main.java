public class Main {
    public static void main(String[] args) {
        ImageProcessor image = new ImageProcessor();
        image.loadImageFromPath("/Users/kamilgolawski/Nauka/Programowanie/Semestr2-OOP/watki/image.jpg");
        try {
            image.addBrightnessWithThreads(-100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        image.saveToImage("/Users/kamilgolawski/Nauka/Programowanie/Semestr2-OOP/watki/image2.jpg");


    }
}