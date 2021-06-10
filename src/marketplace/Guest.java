package marketplace;

import javafx.application.Application;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.ResourceBundle;

public class Guest extends Application implements Initializable {

    private Parent parent;
    private Scene scene;
    private Stage stage;
    @FXML
    private FlowPane foodFlowpane;
    @FXML
    private FlowPane fashionFlowPane;
    @FXML
    private FlowPane sightseeingFlowpane;
    @FXML
    private FlowPane otherFlowPane;

    private Post currentPost;
    private static HashMap<String, Post> postList;
    private static HashMap<String, Post> foodList;
    private static HashMap<String, Post> fasionList;
    private static HashMap<String, Post> sightseeingList;
    private static HashMap<String, Post> otherList;

    @FXML
    private ImageView advertImage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        foodList = new HashMap<>();
        fasionList = new HashMap<>();
        sightseeingList = new HashMap<>();
        otherList = new HashMap<>();
        createTestAdverts();

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

    public void createTestAdverts() {
        postList = new HashMap<>();
        postList.put("Test Business 1", new Post("Test Business 1",
                "file:///home/ft/Documents/Uni/Synoptic Project/Digital " +
                        "Marketplace/src/marketplace//seller2.png", "Lobitos",
                "Business description here", "Food"));
        postList.put("Test Business 2", new Post("Test Business 2",
                "file:///home/ft/Documents/Uni/Synoptic Project/Digital " +
                        "Marketplace/src/marketplace//seller1.png", "Norwich",
                "Business description here", "Fashion"));
        postList.put("Test Business 3", new Post("Test Business 3",
                "file:///home/ft/Documents/Uni/Synoptic Project/Digital " +
                        "Marketplace/src/marketplace//seller3.png", "Pierditas",
                "Business description here", "Sightseeing"));
        postList.put("Test Business 4", new Post("Test Business 4",
                "file:///home/ft/Documents/Uni/Synoptic Project/Digital " +
                        "Marketplace/src/marketplace//seller1.png", "Lobitos",
                "Business description here", "Other"));
        for (Post p : postList.values()) {
            System.out.println(postList.get(p.getTitle()));
        }
    }

    public void loadLists() {
        System.out.println("Loading lists");
        if (postList != null) {
            for (Post p : postList.values()) {
                if (p.getCategory() == Post.Category.FOOD) {
                    foodList.put(p.getTitle(), p);
                    System.out.println(foodList.get(p.getTitle()));
                } else if (p.getCategory() == Post.Category.FASHION) {
                    fasionList.put(p.getTitle(), p);
                } else if (p.getCategory() == Post.Category.SIGHTSEEING) {
                    sightseeingList.put(p.getTitle(), p);
                } else if (p.getCategory() == Post.Category.OTHER) {
                    otherList.put(p.getTitle(), p);
                }
            }
        }
        System.out.println("Lists loaded");
    }

    public void loadFoodAdverts() {
        foodFlowpane.getChildren().removeAll();
        for (Post p : foodList.values()) {
            loadAdvert(p, foodFlowpane, foodList.size());
        }
    }

    public void loadFashionAdverts() {
        fashionFlowPane.getChildren().removeAll();
        for (Post p : fasionList.values()) {
            loadAdvert(p, fashionFlowPane, fasionList.size());
        }
    }

    public void loadSightseeingAdverts() {
        sightseeingFlowpane.getChildren().removeAll();
        for (Post p : sightseeingList.values()) {
            loadAdvert(p, sightseeingFlowpane, sightseeingList.size());
        }
    }

    public void loadOtherAdverts() {
        otherFlowPane.getChildren().removeAll();
        for (Post p : otherList.values()) {
            loadAdvert(p, otherFlowPane, otherList.size());
        }
    }

    public void loadAdvert(Post post, FlowPane flowPane, int size) {
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
            text.setText(post.getTitle());
            button.setLayoutX(51);
            button.setLayoutY(89);
            AnchorPane.setBottomAnchor(button, 20.0);
            AnchorPane.setLeftAnchor(button, 20.0);
            AnchorPane.setRightAnchor(button, 20.0);
            button.setText("View");
            button.setOnMouseClicked((e) -> viewAdvert(button));

            // Image fitting
            Image image = new Image(post.getImage_path());
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Initialised");
        loadLists();
    }
}
