package controller;
import Model.*;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Labeled;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
public class SelectionController extends Exception implements Initializable  {
  /* public  roomisfull(String Message)
    {
        super(Message);

    }*/
    @FXML
    private Labeled dbStatus;
    @FXML
    private ComboBox<Option> comboBox;
    @FXML
            private Button BookRoom;
    SelectModel model=new SelectModel();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(model.isDatabaseConnected())
        {
            this.dbStatus.setText("Connected ");
        }
        else
        {
            this.dbStatus.setText("Lost Connection ");
        }
        this.comboBox.setItems(FXCollections.observableArrayList(Option.values()));

    }
    @FXML
    public void BookRoom(ActionEvent event) {

            try { Stage stage = (Stage) this.BookRoom.getScene().getWindow();

                    switch ((Option) this.comboBox.getValue()) {
                        case Apartment:
                           this.BookRoom.setOnAction(new EventHandler<ActionEvent>() {
                               @Override
                               public void handle(ActionEvent event) {
                                   ApartmentProp();
                                   //Stage stage = (Stage) this.BookRoom.getScene().getWindow();
                                  // stage.close();


                               }
                           });

                            break;
                        case Premiumsuite:
                            this.BookRoom.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    PremiumSuiteProp();


                                }
                            });

                            break;
                        default:
                            JOptionPane.showMessageDialog(null,"Try Again!",null,JOptionPane.INFORMATION_MESSAGE);

                    }
                } catch (Exception LocalException){
                JOptionPane.showMessageDialog(null,"Not connected",null,JOptionPane.ERROR_MESSAGE);
                    }
    }
@FXML
    public void  ApartmentProp()
    {
        try
        {
            Stage stage=new Stage();
            FXMLLoader loader=new FXMLLoader();
            Pane root=(Pane)loader.load(getClass().getResource("/view/Apartment.fxml").openStream());
         //Apartment apartment=(Apartment)loader.getController();
            Scene scene=new Scene(root);
            scene.getStylesheets().add("/view/Master.css");
            stage.setScene(scene);
            stage.setTitle("Apartment Property");
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void quit(ActionEvent event)
    {
        System.exit(0);
    }
    @FXML
    public void  PremiumSuiteProp(){
        try{
            Stage stage=new Stage();
            FXMLLoader loader=new FXMLLoader();
            Pane root=(Pane)loader.load(getClass().getResource("/view/Premium.fxml").openStream());
            //Premiumsuite premiumsuite=(Premiumsuite)loader.getController();
            Scene scene=new Scene(root);
            scene.getStylesheets().add("/view/Master.css");
            stage.setScene(scene);
            stage.setTitle("PremiumSuite Property");
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
