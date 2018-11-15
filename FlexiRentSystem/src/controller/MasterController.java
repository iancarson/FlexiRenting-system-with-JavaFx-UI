package controller;

import Model.Apartment;
import Model.Premiumsuite;
import Model.dbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;


public class MasterController  implements Initializable {
    @FXML
    private TableView<Apartment> ApartmentView;
    @FXML
    private TableColumn<Apartment, String> Name;
    @FXML
    private TableColumn<Apartment, String> Location;
    @FXML
    private TableColumn<Apartment, Integer> RoomNumbers;
    @FXML
    private TableColumn<Apartment, Double> Price;
    @FXML
    private TableColumn<Apartment, String> Status;
    @FXML
    private TableColumn<Apartment, String> Maintainance;
    @FXML
    private TextArea TextArea1;
    @FXML
    private ImageView imageview1;
    @FXML
    private ImageView Imageview2;
    @FXML
    private TextArea TextArea2;
    @FXML
    private TextField Insertname;
    @FXML
    private Button Upload;
    @FXML
    private Button Download;
    @FXML
    private Button BookApartment;
    @FXML
    private TableView<Premiumsuite>premiumsuiteTableView;
    @FXML
    private TableColumn<Premiumsuite,String>SuiteName;
    @FXML
    private TableColumn<Premiumsuite,String>SuiteLocation;
    @FXML
    private TableColumn<Premiumsuite,Integer>roomNumbers;
    @FXML
    private TableColumn<Premiumsuite,Double>price;
    @FXML
    private TableColumn<Premiumsuite,String>status;
    @FXML
    private TableColumn<Premiumsuite,String>Maintain;
    @FXML
    private TextField InsertSuite;
    @FXML
    private dbConnection dc;
    private ObservableList<Apartment> apartments;
    private ObservableList<Premiumsuite>Suite;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.dc = new dbConnection();
    }

    @FXML
    private void LoadApartment(javafx.event.ActionEvent event) throws Exception
    {
        try {
            Connection conn = dbConnection.getConnection();
            this.apartments = FXCollections.observableArrayList();
            String LoadApart = "SELECT * FROM Apartment";
            assert (conn != null);
            ResultSet rs = conn.createStatement().executeQuery(LoadApart);
            while (rs.next()) {
                this.apartments.add(new Apartment(rs.getString(1), rs.getString(2),
                        rs.getInt(3), rs.getDouble(4), rs.getString(5),
                        rs.getString(6)));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error reading from database", null, JOptionPane.ERROR_MESSAGE);
        }
        this.Name.setCellValueFactory(new PropertyValueFactory<>("ApartmentName"));
        this.Location.setCellValueFactory(new PropertyValueFactory<>("ApartmentLocation"));
        this.RoomNumbers.setCellValueFactory(new PropertyValueFactory<>("RoomNumbers"));
        this.Price.setCellValueFactory(new PropertyValueFactory<>("Cost"));
        this.Status.setCellValueFactory(new PropertyValueFactory<>("Status"));
        this.Maintainance.setCellValueFactory(new PropertyValueFactory<>("Maintainance"));
        this.ApartmentView.setItems(null);
        this.ApartmentView.setItems(this.apartments);
    }
    @FXML
    private void LoadSuite(ActionEvent event) throws Exception
    {
        try {
            Connection conn = dbConnection.getConnection();
            this.Suite = FXCollections.observableArrayList();
            String LoadSute = "SELECT * FROM Suite";
            assert (conn != null);
            ResultSet rs = conn.createStatement().executeQuery(LoadSute);
            while (rs.next()) {
                this.Suite.add(new Premiumsuite(rs.getString(1), rs.getString(2),
                        rs.getInt(3), rs.getDouble(4), rs.getString(5),
                        rs.getString(6)));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error reading from database", null, JOptionPane.ERROR_MESSAGE);
        }
        this.SuiteName.setCellValueFactory(new PropertyValueFactory<>("PremiumsuiteName"));
        this.SuiteLocation.setCellValueFactory(new PropertyValueFactory<>("PremiumsuiteLocation"));
        this.roomNumbers.setCellValueFactory(new PropertyValueFactory<>("RoomNumbers"));
        this.price.setCellValueFactory(new PropertyValueFactory<>("Cost"));
        this.status.setCellValueFactory(new PropertyValueFactory<>("Status"));
        this.Maintain.setCellValueFactory(new PropertyValueFactory<>("Maintainance"));
        this.premiumsuiteTableView.setItems(null);
        this.premiumsuiteTableView.setItems(this.Suite);
    }
    @FXML
    private void addApartment(javafx.event.ActionEvent event) throws roomisfullexception
    {
        try{
            String addApart="UPDATE Apartment SET  Rooms=? , Status=? WHERE  Name=?";
            String book="SELECT Rooms ,Maintenance,Image,Description  FROM Apartment WHERE Name=?";
            Connection conn=dbConnection.getConnection();
            assert conn != null;
            PreparedStatement stmt1=conn.prepareStatement(book);
            stmt1.setString(1,Insertname.getText());
            ResultSet rs1=stmt1.executeQuery();
            InputStream is= rs1.getBinaryStream(3);
            OutputStream os=new FileOutputStream(new File("C:\\Users\\15\\Documents\\FlexiRentSystem\\pictures"));
            byte[] contents= new byte[1024];
            int  size=0;
            while((size=is.read(contents) )!= -1)
            {
                os.write(contents,0,size);
                Image image=new Image("file C:\\Users\\15\\Documents\\FlexiRentSystem\\pictures",
                        imageview1.getFitWidth(),imageview1.getFitHeight(),true,true);
                imageview1.setImage(image);
            }
            TextArea1.setText((rs1.getBlob(4)).toString());
            if (rs1.getInt(3)<=4 && rs1.getString(5).equals("TO LET"))
            {
                int Rooms=rs1.getInt(3) + 1;
                PreparedStatement stmt=conn.prepareStatement(addApart);
                ResultSet rs=stmt.executeQuery();
                while(rs.next()) {
                    stmt.setInt(1, Rooms);
                    stmt.setString(3, "occupied");
                    stmt.executeUpdate();
                }
                stmt.close();
                rs.close();
            }
            else {
                throw new roomisfullexception();

            }


        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,"Enter Apartment Name!!",
                    null,JOptionPane.ERROR_MESSAGE);
        }
    }
    @FXML
    private void addSuite(ActionEvent event) throws roomisfullexception
    {
        try{
            String addSuite="UPDATE Suite SET  Rooms=? , Status=? WHERE  NAME=?";
            String suitesel="SELECT Rooms,Status, Image,Description FROM Suite WHERE Name=?";
            Connection conn=dbConnection.getConnection();
            assert conn != null;
            PreparedStatement stmt1=conn.prepareStatement(suitesel);
            stmt1.setString(1,InsertSuite.getText());
            ResultSet rs1=stmt1.executeQuery();
            InputStream is= rs1.getBinaryStream(3);
            OutputStream os=new FileOutputStream(new File("C:\\Users\\15\\Documents\\FlexiRentSystem\\pictures"));
            byte[] contents= new byte[1024];
            int  size=0;
            while((size=is.read(contents) )!= -1)
            {
                os.write(contents,0,size);
                Image image=new Image("file C:\\Users\\15\\Documents\\FlexiRentSystem\\pictures",Imageview2.getFitWidth(),Imageview2.getFitHeight(),true,true);
                Imageview2.setImage(image);
            }
            TextArea2.setText((rs1.getBlob(4).toString()));
            PreparedStatement stmt=conn.prepareStatement(addSuite);
            stmt.setString(3, InsertSuite.getText());
            ResultSet rs=stmt.executeQuery();
            if (rs.getInt(3)<4 && rs.getString(5).equals("TO LET"))
            {
                int rooms=rs.getInt(3) + 1;
                while (rs.next()) {
                    stmt.setInt(1,rooms);
                    stmt.setString(2, "Occupied");
                    stmt.executeUpdate();
                }
                stmt.close();
                rs.close();
            }
            else
            {
                throw new roomisfullexception();
            }

        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,"The suite is Rented.Try another suite or type correct Name!");
           e.printStackTrace();
        }
    }
    @FXML
    public void DownLoadSuite(ActionEvent event)
    {
        try {
            String Suitesel="SELECT * FROM Suite WHERE Name=?";
            Connection conn=dbConnection.getConnection();
            assert conn != null;
            PreparedStatement stmt=conn.prepareStatement(Suitesel);
            stmt.setString(1,InsertSuite.getText());
            ResultSet rs1=stmt.executeQuery();

            File f=new File("c:/documents/FlexirentSystem/export_data.txt");
            PrintWriter writer1 = new PrintWriter("export_data.txt", "UTF-8");
            while(rs1.next()) {
                writer1.write(rs1.getString(1));
                writer1.write(rs1.getString(2));
                writer1.write(rs1.getInt(3));
                writer1.print(rs1.getDouble(4));
                writer1.write(rs1.getString(5));
                writer1.write(rs1.getString(6));
            }
            stmt.close();
            rs1.close();
            DirectoryChooser chooser =new DirectoryChooser();
            File SelectedDirectory=null;
            SelectedDirectory=chooser.showDialog(null);
            File file=new File(SelectedDirectory + "/" + writer1);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Failed to Download file,try again",
                    null, JOptionPane.ERROR_MESSAGE);
        }
    }
    @FXML
    private void importdata(ActionEvent event)
    {
        new JFileChooser("c:", FileSystemView.getFileSystemView());
        File file=new File("C:\\Users\\15\\Documents\\FlexiRentSystem\\export_data.txt");
        BufferedReader reader=null;
        StringBuilder sb=new StringBuilder();
        try{

            reader=new BufferedReader(new FileReader(file));
            String text=null;
            while((text=reader.readLine()) !=null)
            {
                sb.append(text).append(System.getProperty("line.separator"));
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void  DownloadFile(javafx.event.ActionEvent event) throws  Exception {
        try {
            String sel="SELECT * FROM Apartment WHERE Name=?";
            Connection conn=dbConnection.getConnection();
            assert conn != null;
            PreparedStatement stmt=conn.prepareStatement(sel);
            stmt.setString(1,Insertname.getText());
            ResultSet rs=stmt.executeQuery();
            PrintWriter writer = new PrintWriter("export_data.txt", "UTF-8");
                while(rs.next()) {
                    writer.write(rs.getString(1));
                    writer.write(rs.getString(2));
                    writer.write(rs.getInt(3));
                    writer.print(rs.getDouble(4));
                    writer.write(rs.getString(5));
                    writer.write(rs.getString(6));
                }
                stmt.close();
                rs.close();
            DirectoryChooser chooser =new DirectoryChooser();
            File SelectedDirectory=null;
            SelectedDirectory=chooser.showDialog(null);
            File file=new File(SelectedDirectory + "/" + writer);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Failed to Download file,try again",
                    null, JOptionPane.ERROR_MESSAGE);
        }
    }
    @FXML
    public void UploadFile(javafx.event.ActionEvent event) throws FileNotFoundException {
        JFileChooser j=new JFileChooser("C:\\Users\\15\\Documents\\FlexiRentSystem", FileSystemView.getFileSystemView());
            File file=new File("C:\\Users\\15\\Documents\\FlexiRentSystem\\export_data.txt");
        BufferedReader reader=null;
        StringBuffer sb2=new StringBuffer();
        try{

            reader=new BufferedReader(new FileReader(file));
            String text=null;
            while((text=reader.readLine()) !=null)
            {
                sb2.append(text).append(System.getProperty("line.separator"));
            }
            }
         catch (IOException e) {
            e.printStackTrace();
        }

    }
    @FXML
    public void Quit(ActionEvent event)
    {
        JOptionPane.showConfirmDialog(null,"Are you sure ?");
        System.exit(0);
    }
}
