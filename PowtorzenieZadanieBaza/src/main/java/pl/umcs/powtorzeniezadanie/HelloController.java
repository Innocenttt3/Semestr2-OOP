package pl.umcs.powtorzeniezadanie;

import javafx.event.ActionEvent;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import pl.umcs.powtorzeniezadanie.client.ServerThread;
import pl.umcs.powtorzeniezadanie.server.Server;

import java.io.PrintWriter;

public class HelloController {
    public TextField addressField;
    public TextField portField;
    public ColorPicker colorPicker;
    public Slider radiusSlider;
    public Canvas canvas;
    private PrintWriter writer;

    Server server;
    ServerThread serverThread;

    public void onMouseClicked(MouseEvent event) {
        double x = event.getX();
        double y = event.getY();
        double radius = radiusSlider.getValue();
        Color fillColor = colorPicker.getValue();

        canvas.getGraphicsContext2D().setFill(fillColor);
        canvas.getGraphicsContext2D().fillOval(x - radius, y - radius, radius * 2, radius * 2);

        serverThread.send(x, y, radius, fillColor);
    }

    public void setOnAction(MouseEvent mouseEvent) {
    }

    public void onConnectClicked(ActionEvent actionEvent) {
        serverThread = new ServerThread(addressField.getText(), Integer.parseInt(portField.getText()));
        serverThread.start();
    }

    public void onStartServerClicked(ActionEvent actionEvent) {
        server = new Server(Integer.parseInt(portField.getText()));
        server.listen();

        onConnectClicked(actionEvent);
    }
}