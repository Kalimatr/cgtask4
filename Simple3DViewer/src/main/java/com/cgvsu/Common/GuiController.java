package com.cgvsu.Common;

import com.cgvsu.math.Vector3f;
import com.cgvsu.model.Model;
import com.cgvsu.objreader.ObjReader;
import com.cgvsu.render_engine.Camera;
import com.cgvsu.render_engine.RenderEngine;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.input.MouseEvent;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


public class GuiController {

    final private float TRANSLATION = 0.5F;

    @FXML
    AnchorPane anchorPane;

    @FXML
    private Canvas canvas;

    private Model mesh = null;

    private Camera camera = new Camera(
            new Vector3f(0, 0, 100),
            new Vector3f(0, 0, 0),
            1.0F, 1, 0.01F, 100);

    private Timeline timeline;
    private double startX, startY;

    @FXML
    private void initialize() {
        anchorPane.prefWidthProperty().addListener((ov, oldValue, newValue) -> canvas.setWidth(newValue.doubleValue()));
        anchorPane.prefHeightProperty().addListener((ov, oldValue, newValue) -> canvas.setHeight(newValue.doubleValue()));

        timeline = new Timeline();
        timeline.setCycleCount(Animation.INDEFINITE);

        KeyFrame frame = new KeyFrame(Duration.millis(15), event -> {
            double width = canvas.getWidth();
            double height = canvas.getHeight();

            canvas.getGraphicsContext2D().clearRect(0, 0, width, height);
            camera.setAspectRatio((float) (width / height));

            if (mesh != null) {
                RenderEngine.render(canvas.getGraphicsContext2D(), camera, mesh, (int) width, (int) height);
            }
        });

        timeline.getKeyFrames().add(frame);
        timeline.play();
        anchorPane.setOnMouseClicked(this::handleMouseClick);
        anchorPane.setOnMouseDragged(this::handleMouseMove);
        anchorPane.setOnScroll(this::handleScroll);

    }


    @FXML
    private void onOpenModelMenuItemClick() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Model (*.obj)", "*.obj"));
        fileChooser.setTitle("Load Model");

        File file = fileChooser.showOpenDialog((Stage) canvas.getScene().getWindow());
        if (file == null) {
            return;
        }

        Path fileName = Path.of(file.getAbsolutePath());

        try {
            String fileContent = Files.readString(fileName);
            mesh = ObjReader.read(fileContent);
            // todo: обработка ошибок
        } catch (IOException exception) {

        }
    }

    @FXML
    public void handleCameraForward(ActionEvent actionEvent) {
        camera.movePosition(new Vector3f(0, 0, -TRANSLATION));
    }

    @FXML
    public void handleCameraBackward(ActionEvent actionEvent) {
        camera.movePosition(new Vector3f(0, 0, TRANSLATION));
    }

    @FXML
    public void handleCameraLeft(ActionEvent actionEvent) {
        camera.movePosition(new Vector3f(TRANSLATION, 0, 0));
    }

    @FXML
    public void handleCameraRight(ActionEvent actionEvent) {
        camera.movePosition(new Vector3f(-TRANSLATION, 0, 0));
    }

    @FXML
    public void handleCameraUp(ActionEvent actionEvent) {
        camera.movePosition(new Vector3f(0, TRANSLATION, 0));
    }

    @FXML
    public void handleCameraDown(ActionEvent actionEvent) {
        //camera.movePosition(new Vector3f(0, -TRANSLATION, 0));
        camera.Rotate(0, 180, 0);
    }

    public void handleScroll(ScrollEvent scrollEvent) {
        double deltaY = scrollEvent.getDeltaY();

        if (deltaY > 0) {
            camera.Scale(0.90F, 0.90f, 0.90f);

        } else if (deltaY < 0) {
            camera.Scale(1.1F, 1.1f, 1.1f);
        }
    }


    public void handleMouseClick(MouseEvent mouseEvent) {
        startX = mouseEvent.getX();
        startY = mouseEvent.getY();
    }
    float angle = 0;
    public void handleMouseMove(MouseEvent mouseEvent) {
        float motionValueX = 0.01F;
        float motionValueY = Math.abs(angle) > 90 && Math.abs(angle) < 270 ? -0.01F : 0.01F;
        System.out.println(angle);
        if (Math.abs(angle) > 360) {
            angle = 0;
        }
        System.out.println(angle);
        if (mouseEvent.getButton() == MouseButton.PRIMARY) {
            if (mouseEvent.getX() > startX) {
                camera.Rotate(0, -motionValueX, 0);
                angle += Math.abs(Math.toDegrees(motionValueX));
            }
            if (mouseEvent.getX() < startX) {
                camera.Rotate(0, motionValueX, 0);
                angle -= Math.abs(Math.toDegrees(motionValueX));
            }

            if (mouseEvent.getY() > startY) {
                camera.Rotate(motionValueY, 0, 0);
            }
            if (mouseEvent.getY() < startY) {
                camera.Rotate(-motionValueY, 0, 0);
            }
        }

        if (mouseEvent.getButton() == MouseButton.SECONDARY) {
            if (mouseEvent.getX() > startX) {
                camera.Rotate(0, 0, 0.01F);
            }
            if (mouseEvent.getX() < startX) {
                camera.Rotate(0, 0, -0.01F);
            }
        }
        startX = mouseEvent.getX();
        startY = mouseEvent.getY();
    }
}