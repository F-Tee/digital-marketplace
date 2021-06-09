package marketplace;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Objects;

public class Guest extends Application {

    private Parent parent;
    private Scene scene;
    private Stage stage;
    @FXML
    private FlowPane flowPane;

    private Post currentPost;
    private ArrayList<Post> postList;
    private ArrayList<Post> foodList;
    private ArrayList<Post> fasionList;
    private ArrayList<Post> sightseeingList;
    private ArrayList<Post> otherList;

    @FXML
    private ImageView advertImage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        postList = new ArrayList<>();
        foodList = new ArrayList<>();
        fasionList = new ArrayList<>();
        sightseeingList = new ArrayList<>();
        otherList = new ArrayList<>();

        flowPane = new FlowPane();
        flowPane.prefHeight(641);
        flowPane.prefWidth(321);
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("guest_menu.fxml")));
        primaryStage.setTitle("Digital Marketplace");
        primaryStage.setScene(new Scene(root, 335, 600));
        primaryStage.show();
    }

    public void guestAdvertList(ActionEvent event) throws Exception {
        parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("guest_advert_list.fxml")));
        scene = new Scene(parent);
        // This line gets the stage information
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Digital Marketplace");
        stage.show();
    }

    public void guestMenuScreen(ActionEvent event) throws Exception {
        parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("guest_menu.fxml")));
        scene = new Scene(parent);
        // This line gets the stage information
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Digital Marketplace");
        stage.show();
    }

    public void advertPageScreen(ActionEvent event) throws Exception {
        parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("guest_advert_info.fxml")));
        scene = new Scene(parent);
        // This line gets the stage information
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Digital Marketplace");
        stage.show();
    }

    public void loadLists() {
//        for (Post p : postList) {
//            switch (p.getCategory()) {
//
//            }
//        }
    }

    public void loadAdverts() {
        // Create elements
        SplitPane splitPane = new SplitPane();
        AnchorPane anchorPane1 = new AnchorPane();
        AnchorPane anchorPane2 = new AnchorPane();
        advertImage = new ImageView();
        Text text = new Text();
        Button button = new Button();
        // Set element positions
        splitPane.prefWidth(300);
        splitPane.prefHeight(160);
        splitPane.setDividerPositions(0.5);
        advertImage.setFitWidth(140);
        advertImage.setFitHeight(200);
        advertImage.pickOnBoundsProperty().setValue(true);
        advertImage.preserveRatioProperty().setValue(true);

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

        // Image fitting
        Image image = new Image("file:///home/ft/Documents/Uni/Synoptic Project/Digital Marketplace/src/marketplace//seller2.png");
        advertImage.setImage(image);
        centerImage();

        // Add elements in
        anchorPane1.getChildren().add(advertImage);
        anchorPane2.getChildren().addAll(text, button);
        splitPane.getItems().addAll(anchorPane1, anchorPane2);
        flowPane.getChildren().add(splitPane);
    }

    public void centerImage() {
        Image img = advertImage.getImage();
        if (img != null) {
            double w;
            double h;

            double ratioX = advertImage.getFitWidth() / img.getWidth();
            double ratioY = advertImage.getFitHeight() / img.getHeight();

            double reducCoeff;
            if (ratioX >= ratioY) {
                reducCoeff = ratioY;
            } else {
                reducCoeff = ratioX;
            }

            w = img.getWidth() * reducCoeff;
            h = img.getHeight() * reducCoeff;

            advertImage.setX((advertImage.getFitWidth() - w) / 2);
            advertImage.setY((advertImage.getFitHeight() - h) / 2);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
