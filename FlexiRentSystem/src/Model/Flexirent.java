package Model;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Flexirent  extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root=(Parent) FXMLLoader.load(getClass().getResource("/controller/Flexirent.fxml"));
        Scene scene=new Scene(root);
        scene.getStylesheets().add("/view/Master.css");
        primaryStage.setScene(scene);
        primaryStage.setTitle("Flexirent System ");
        primaryStage.centerOnScreen();
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    public static void main(String [] args)
    {
        launch(args);
    }
}
