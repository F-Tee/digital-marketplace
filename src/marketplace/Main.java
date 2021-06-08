package marketplace;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application{

    private Parent parent;
    private Scene scene;
    private Stage stage;
    @FXML
    private FlowPane flowPane;
//    private ArrayList<Post> arrayList;

    @Override
    public void start(Stage primaryStage) throws Exception {
//        arrayList = new ArrayList<Post>();
        flowPane = new FlowPane();
        flowPane.prefHeight(641);
        flowPane.prefWidth(321);
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

    public void blah() {
        // Create elements
        SplitPane splitPane = new SplitPane();
        AnchorPane anchorPane1 = new AnchorPane();
        AnchorPane anchorPane2 = new AnchorPane();
        ImageView imageView = new ImageView();
        Text text = new Text();
        Button button = new Button();
        // Set element positions
        splitPane.prefWidth(300);
        splitPane.prefHeight(160);
        splitPane.setDividerPositions(0.5);
        imageView.setFitWidth(308);
        imageView.setFitWidth(200);
        imageView.setLayoutX(-28);
        imageView.setLayoutY(-24);
        imageView.pickOnBoundsProperty().setValue(true);
        imageView.preserveRatioProperty().setValue(true);

        anchorPane1.prefWidth(100);
        anchorPane1.prefHeight(160);
        anchorPane2.prefWidth(249);
        anchorPane2.prefHeight(158);
        text.setLayoutX(24);
        text.setLayoutY(60);
        AnchorPane.setTopAnchor(text, 40.0);
        AnchorPane.setLeftAnchor(text, 20.0);
        AnchorPane.setRightAnchor(text, 20.0);
        text.setText("Business Name");
        button.setLayoutX(51);
        button.setLayoutY(89);
        AnchorPane.setBottomAnchor(button, 20.0);
        AnchorPane.setLeftAnchor(button, 20.0);
        AnchorPane.setRightAnchor(button, 20.0);
        button.setText("View");


        Image image = new Image("file:///home/ft/Documents/Uni/Synoptic Project/Digital Marketplace/src/marketplace//seller2.png");
        imageView.setImage(image);

        // Add elements in
        anchorPane1.getChildren().add(imageView);
        anchorPane2.getChildren().addAll(text, button);
        splitPane.getItems().addAll(anchorPane1, anchorPane2);
        flowPane.getChildren().add(splitPane);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
