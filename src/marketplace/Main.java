package marketplace;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {

    private Parent parent;
    private Scene scene;
    private Stage stage;
//    private ArrayList<Post> arrayList;

    @Override
    public void start(Stage primaryStage) throws Exception {
//        arrayList = new ArrayList<Post>();
        Parent root = FXMLLoader.load(getClass().getResource("guest_menu.fxml"));
        primaryStage.setTitle("Digital Marketplace");
        primaryStage.setScene(new Scene(root, 335, 600));
        primaryStage.show();
    }


    public void guestAdvertList(ActionEvent event) throws Exception {
        parent = FXMLLoader.load(getClass().getResource("guest_advert_list.fxml"));
        scene = new Scene(parent);
        // This line gets the stage information
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Digital Marketplace");
        stage.show();
    }

    public void createAdvertScreen(ActionEvent event) throws Exception {
        parent = FXMLLoader.load(getClass().getResource("create_advert.fxml"));
        scene = new Scene(parent);
        // This line gets the stage information
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Digital Marketplace");
        stage.show();
    }

    public void advertPageScreen(ActionEvent event) throws Exception {
        parent = FXMLLoader.load(getClass().getResource("advert_page.fxml"));
        scene = new Scene(parent);
        // This line gets the stage information
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Digital Marketplace");
        stage.show();
    }

    public void advertLocationScreen(ActionEvent event) throws Exception {
        parent = FXMLLoader.load(getClass().getResource("advert_location.fxml"));
        scene = new Scene(parent);
        // This line gets the stage information
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Digital Marketplace");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
