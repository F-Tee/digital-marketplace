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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

    @FXML
    private ImageView menuImage;
    @FXML
    private ImageView ecoImage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Run on start of application, initialises lists and other objects
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
        // Opens the list of adverts
        parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("guest_advert_list.fxml")));
        scene = new Scene(parent);
        // This line gets the stage information
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Digital Marketplace");
        stage.show();
    }

    public void guestMenuScreen(ActionEvent event) throws Exception {
        // Opens the guest menu screen
        parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("guest_menu.fxml")));
        scene = new Scene(parent);
        // This line gets the stage information
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Digital Marketplace");
        stage.show();
    }

    public void advertPageScreen(ActionEvent event, Post post) throws Exception {
        // Opens the advert page screen
        currentPost = new Post(post);
        parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("guest_advert_info.fxml")));
        scene = new Scene(parent);
        // This line gets the stage information
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Digital Marketplace");
        stage.show();
    }

    public void ecoScreen(ActionEvent event) throws Exception {
        // Opens the eco message screen
        parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("eco_message.fxml")));
        scene = new Scene(parent);
        // This line gets the stage information
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Digital Marketplace");
        stage.show();
    }

    public void loadLists() {
        // This method assigns Post objects to the relevant category list
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
        listsLoaded = true;
    }

    public void loadCategories() throws FileNotFoundException {
        // This method loads adverts for each category displaying them in the advert list
        loadSportAdverts();
        loadCultureAdverts();
        loadFashionAdverts();
        loadFoodAdverts();
        loadFishingAdverts();
        loadTransportAdverts();
    }

    public void loadSportAdverts() throws FileNotFoundException {
        // Adds the advert to the list on the page
        for (Post p : sportList) {
            loadAdvert(p, sportFlowPane);
        }
    }

    public void loadCultureAdverts() throws FileNotFoundException {
        // Adds the advert to the list on the page
        for (Post p : cultureList) {
            loadAdvert(p, cultureFlowPane);
        }
    }

    public void loadFashionAdverts() throws FileNotFoundException {
        // Adds the advert to the list on the page
        for (Post p : fasionList) {
            loadAdvert(p, fashionFlowPane);
        }
    }

    public void loadFoodAdverts() throws FileNotFoundException {
        // Adds the advert to the list on the page
        for (Post p : foodList) {
            loadAdvert(p, foodFlowpane);
        }
    }

    public void loadFishingAdverts() throws FileNotFoundException {
        // Adds the advert to the list on the page
        for (Post p : fishingList) {
            loadAdvert(p, fishingFlowPane);
        }
    }

    public void loadTransportAdverts() throws FileNotFoundException {

        for (Post p : transportList) {
            loadAdvert(p, transportFlowPane);
        }
    }

    public void loadAdvert(Post post, FlowPane flowPane) throws FileNotFoundException {
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

        // Set positions and sizes
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
        System.out.println("Image path: " + post.getImage_path());
        Image image = new Image(new FileInputStream(post.getImage_path()));
        advertImage.setImage(image);
        centreImage(advertImage);

        // Add elements in
        anchorPane1.getChildren().add(advertImage);
        anchorPane2.getChildren().addAll(text, button);
        splitPane.getItems().addAll(anchorPane1, anchorPane2);
        flowPane.getChildren().add(splitPane);
    }

    public void centreImage(ImageView imageView) {
        // This method resizes and centres the image in the imageview pane
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
        // This method runs on the initialisation of each page

        if (menuImage != null) {
            // Loads menu image
            Image mImage = new Image("@../../pics/menu_image.png");
            menuImage.setImage(mImage);
        }
        if (ecoImage != null) {
            // Loads eco image
            Image mImage = new Image("@../../pics/eco.png");
            ecoImage.setImage(mImage);
        }
        if (!listsLoaded) {
            loadLists();
        }
        if (foodFlowpane != null) {
            // Puts Posts into correct categories
            try {
                loadCategories();
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        }
        if (advertPageImage != null) {
            // Centres the image
            centreImage(advertPageImage);
        }
        if (advertPageImage != null) {
            // Loads the advert information page attributes up
            Image image = new Image(currentPost.getImage_path());
            advertPageImage.setImage(image);
            advertPageText.setText(currentPost.getDescription());
            advertPageTitle.setText(currentPost.getTitle());
            advertPageLocation.setText(currentPost.getAddress());
        }
    }
}
