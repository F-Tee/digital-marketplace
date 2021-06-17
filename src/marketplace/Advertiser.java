package marketplace;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class Advertiser extends Application implements Initializable {

    private Parent parent;
    private Scene scene;
    private Stage stage;
    @FXML
    private FlowPane flowPane;
    @FXML
    private TextField businessName;
    @FXML
    private TextField businessLocation;
    @FXML
    private TextArea businessInfo;
    @FXML
    private ImageView imagePreview;
    private File selectedImage;
    private static ArrayList<Post> postList;

    private static Database database;

    @FXML
    private ChoiceBox<String> categoryBox;

    @FXML
    private Text deleteAdvertText;
    @FXML
    private TextField deleteAdvertTextfield;

    @FXML
    private ImageView menuImage;

    @FXML
    private Text businessNameText;

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Run on start of application, initialises lists and other objects
        postList = new ArrayList<>();
        flowPane = new FlowPane();
        flowPane.prefHeight(641);
        flowPane.prefWidth(321);
        database = new Database();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("advertiser_menu.fxml")));
        primaryStage.setTitle("Digital Marketplace");
        primaryStage.setScene(new Scene(root, 335, 600));
        primaryStage.show();
    }

    public void advertiserMenuScreen(ActionEvent event) throws Exception {
        // Opens the advertiser menu screen
        parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("advertiser_menu.fxml")));
        scene = new Scene(parent);
        // This line gets the stage information
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Digital Marketplace");
        stage.show();
    }

    public void createAdvertScreen(ActionEvent event) throws Exception {
        // Opens the create advert screen
        parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("create_advert.fxml")));
        scene = new Scene(parent);
        // This line gets the stage information
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Digital Marketplace");
        stage.show();
    }

    public void deleteAdvertScreen(ActionEvent event) throws Exception {
        // Opens the delete advert screen
        parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("delete_advert.fxml")));
        scene = new Scene(parent);
        // This line gets the stage information
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Digital Marketplace");
        stage.show();
    }

    public void loadCategories() {
        // Initialises the drop down category box to the available categories
        categoryBox.setItems(FXCollections.observableArrayList("Sports & Adventure", "History & Culture",
                "Fashion & Craft", "Lodging & Food", "Fishing", "Transport"));
    }

    public void advertCreationSuccessScreen(ActionEvent event) throws Exception {
        // Opens the advert creation success screen
        if (database.postExists(businessName.getText())) {
            // If the post already exists the user is informed
            businessNameText.setText("Duplicate name");
            businessName.clear();
            businessName.setOnMouseClicked((e) -> {
                businessNameText.setText("Business name");
            });
        } else {
            // Otherwise the post is created, the image is uploaded and the post saved as a csv file
            String imagePath = database.uploadImage(selectedImage.getAbsolutePath());
            System.out.println(imagePath);
            Post p = new Post(businessName.getText(), imagePath,
                    businessInfo.getText(), businessLocation.getText(), categoryBox.getValue());
            postList.add(p);
            parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("advert_creation_success.fxml")));
            scene = new Scene(parent);
            // This line gets the stage information
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Digital Marketplace");
            stage.show();
        }
    }

    public void chooseImage() throws FileNotFoundException {
        // This method allows the user to open a file browser to select the image and it is preview
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select image");
        selectedImage = fileChooser.showOpenDialog(stage);
        System.out.println("Image: " + selectedImage);

        // Image fitting
        Image image = new Image(new FileInputStream(selectedImage));
        imagePreview.setImage(image);
        centerImage();
    }

    public void centerImage() {
        // Centres and resizes the image
        Image img = imagePreview.getImage();
        if (img != null) {
            double w;
            double h;

            double ratioX = imagePreview.getFitWidth() / img.getWidth();
            double ratioY = imagePreview.getFitHeight() / img.getHeight();

            double reducCoeff = Math.min(ratioX, ratioY);

            w = img.getWidth() * reducCoeff;
            h = img.getHeight() * reducCoeff;

            imagePreview.setX((imagePreview.getFitWidth() - w) / 2);
            imagePreview.setY((imagePreview.getFitHeight() - h) / 2);
        }
    }

    public void deleteAdvert() {
        // Deletes the advert if it exists, otherwise the user is informed
        System.out.println("Deleting advert");
        String postName = deleteAdvertTextfield.getText();
        if (database.deletePost(postName)) {
            System.out.println("Advert deleted");
            deleteAdvertText.setText("Advert deleted");
        } else {
            System.out.println("Advert does not exist");
            deleteAdvertText.setText("Advert does not exist");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Run on initilisation of each page
        if (menuImage != null) {
            // Sets the menu image
            Image mImage = new Image("@../../pics/menu_image.png");
            menuImage.setImage(mImage);
        }
    }
}
