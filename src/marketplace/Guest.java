package marketplace;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class Guest extends Application implements Initializable {

    private Parent parent;
    private Scene scene;
    private Stage stage;
    @FXML
    private FlowPane sportFlowPane;
    @FXML
    private FlowPane cultureFlowPane;
    @FXML
    private FlowPane fashionFlowPane;
    @FXML
    private FlowPane foodFlowpane;
    @FXML
    private FlowPane fishingFlowPane;
    @FXML
    private FlowPane transportFlowPane;

    private static Post currentPost;
    private static ArrayList<Post> postList;

    private static ArrayList<Post> sportList;
    private static ArrayList<Post> cultureList;
    private static ArrayList<Post> fasionList;
    private static ArrayList<Post> foodList;
    private static ArrayList<Post> fishingList;
    private static ArrayList<Post> transportList;

    private static boolean listsLoaded = false;

    private Database database;

    @FXML
    private ImageView advertImage;
    @FXML
    private ImageView advertPageImage;
    @FXML
    private TextArea advertPageText;
    @FXML
    private TextArea advertPageLocation;
    @FXML
    private TextArea advertPageTitle;

    @Override
    public void start(Stage primaryStage) throws Exception {
        sportList = new ArrayList<>();
        cultureList = new ArrayList<>();
        fasionList = new ArrayList<>();
        foodList = new ArrayList<>();
        fishingList = new ArrayList<>();
        transportList = new ArrayList<>();


        database = new Database();
        postList = new ArrayList<>();
        database.loadAllPosts(postList);

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

    public void advertPageScreen(ActionEvent event, Post post) throws Exception {
        System.out.println("Post title: " + post.getTitle());
        currentPost = new Post(post);
        parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("guest_advert_info.fxml")));
        scene = new Scene(parent);
        // This line gets the stage information
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Digital Marketplace");
        stage.show();
    }

    public void createTestAdverts() {
        postList = new ArrayList<>();
        postList.add(new Post("Test Business 1",
                "file:///home/ft/Documents/Uni/Synoptic Project/Digital " +
                        "Marketplace/src/marketplace//seller2.png", "Business description here",
                "Business location here", "Food"));
        postList.add(new Post("kuahsiduhsdfsdg",
                "file:///home/ft/Documents/Uni/Synoptic Project/Digital " +
                        "Marketplace/src/marketplace//seller2.png", "Business description here",
                "Business location here", "Food"));
        postList.add(new Post("Test Business 2",
                "file:///home/ft/Documents/Uni/Synoptic Project/Digital " +
                        "Marketplace/src/marketplace//seller1.png", "Business description here",
                "Business location here", "Fashion"));
        postList.add(new Post("Test Business 3",
                "file:///home/ft/Documents/Uni/Synoptic Project/Digital " +
                        "Marketplace/src/marketplace//seller3.png", "Business description here",
                "Business location here", "Sightseeing"));
        postList.add(new Post("Test Business 4",
                "file:///home/ft/Documents/Uni/Synoptic Project/Digital " +
                        "Marketplace/src/marketplace//seller1.png", "Business description here",
                "Business location here", "Other"));
    }

    public void loadLists() {
        if (postList != null) {
            for (Post p : postList) {
                if (p.getCategory() == Post.Category.SPORT) {
                    sportList.add(p);
                } else if (p.getCategory() == Post.Category.CULTURE) {
                    cultureList.add(p);
                } else if (p.getCategory() == Post.Category.FASHION) {
                    fasionList.add(p);
                } else if (p.getCategory() == Post.Category.FOOD) {
                    foodList.add(p);
                } else if (p.getCategory() == Post.Category.FISHING) {
                    fishingList.add(p);
                } else if (p.getCategory() == Post.Category.TRANSPORT) {
                    transportList.add(p);
                }
            }
        }
        System.out.println("Lists loaded");
        listsLoaded = true;
        System.out.println(listsLoaded);
    }

    public void loadCategories() {
        loadSportAdverts();
        loadCultureAdverts();
        loadFashionAdverts();
        loadFoodAdverts();
        loadFishingAdverts();
        loadTransportAdverts();
    }

    public void loadSportAdverts() {
        System.out.println("\nSport posts:");
        for (Post p : sportList) {
            loadAdvert(p, sportFlowPane);
            System.out.println(p.getTitle());
        }
    }

    public void loadCultureAdverts() {
        System.out.println("\nCulture posts:");
        for (Post p : cultureList) {
            loadAdvert(p, cultureFlowPane);
            System.out.println(p.getTitle());
        }
    }

    public void loadFashionAdverts() {
        System.out.println("\nFashion posts:");
        for (Post p : fasionList) {
            loadAdvert(p, fashionFlowPane);
            System.out.println(p.getTitle());
        }
    }

    public void loadFoodAdverts() {
        System.out.println("\nFishing posts:");
        for (Post p : foodList) {
            loadAdvert(p, foodFlowpane);
            System.out.println(p.getTitle());
        }
    }

    public void loadFishingAdverts() {
        System.out.println("\nFishing posts:");
        for (Post p : fishingList) {
            loadAdvert(p, fishingFlowPane);
            System.out.println(p.getTitle());
        }
    }

    public void loadTransportAdverts() {
        System.out.println("\nTransport posts:");
        for (Post p : transportList) {
            loadAdvert(p, transportFlowPane);
            System.out.println(p.getTitle());
        }
    }

    public void loadAdvert(Post post, FlowPane flowPane) {
        // Create elements
        SplitPane splitPane = new SplitPane();
        AnchorPane anchorPane1 = new AnchorPane();
        AnchorPane anchorPane2 = new AnchorPane();
        advertImage = new ImageView();
        Text text = new Text();
        Button button = new Button();
        button.setOnAction((event) -> {
            try {
                advertPageScreen(event, post);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        // Set element positions
        splitPane.prefWidth(300);
        splitPane.prefHeight(160);
        splitPane.prefHeight(160);
        splitPane.setDividerPositions(0.5);
        advertImage.setFitWidth(140);
        advertImage.setFitHeight(200);
//        advertImage.pickOnBoundsProperty().setValue(true);
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

        // Image fitting
        Image image = new Image("file://" + post.getImage_path());
        advertImage.setImage(image);
        centreImage(advertImage);

        // Add elements in
        anchorPane1.getChildren().add(advertImage);
        anchorPane2.getChildren().addAll(text, button);
        splitPane.getItems().addAll(anchorPane1, anchorPane2);
        flowPane.getChildren().add(splitPane);
    }

    public void centreImage(ImageView imageView) {
        Image img = imageView.getImage();
        if (img != null) {
            double w;
            double h;

            double ratioX = imageView.getFitWidth() / img.getWidth();
            double ratioY = imageView.getFitHeight() / img.getHeight();

            double reducCoeff;
            if (ratioX >= ratioY) {
                reducCoeff = ratioY;
            } else {
                reducCoeff = ratioX;
            }

            w = img.getWidth() * reducCoeff;
            h = img.getHeight() * reducCoeff;

            imageView.setX((imageView.getFitWidth() - w) / 2);
            imageView.setY((imageView.getFitHeight() - h) / 2);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Starter listsLoaded: " + listsLoaded);
        if (!listsLoaded) {
            loadLists();
        }
        if (foodFlowpane != null) {
            loadCategories();
        }
        if (advertPageImage != null) {
            centreImage(advertPageImage);
        }

        if (advertPageImage != null) {
            Image image = new Image("file://" + currentPost.getImage_path());
            advertPageImage.setImage(image);
            advertPageText.setText(currentPost.getDescription());
            advertPageTitle.setText(currentPost.getTitle());
            advertPageLocation.setText(currentPost.getAddress());
        }
    }
}
