package marketplace;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Objects;

public class Advertiser extends Application {

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
    private TextField businessInfo;
    @FXML
    private ImageView imagePreview;
    private File selectedImage;
    private static ArrayList<Post> postList;

    @FXML
    private ChoiceBox<String> categoryBox;

    @Override
    public void start(Stage primaryStage) throws Exception {
        postList = new ArrayList<>();
        flowPane = new FlowPane();
        flowPane.prefHeight(641);
        flowPane.prefWidth(321);
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("advertiser_menu.fxml")));
        primaryStage.setTitle("Digital Marketplace");
        primaryStage.setScene(new Scene(root, 335, 600));
        primaryStage.show();
    }

    public void advertiserMenuScreen(ActionEvent event) throws Exception {
        for (Post p : postList) {
            System.out.println(p);
        }
        parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("advertiser_menu.fxml")));
        scene = new Scene(parent);
        // This line gets the stage information
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Digital Marketplace");
        stage.show();
    }

    public void createAdvertScreen(ActionEvent event) throws Exception {
        parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("create_advert.fxml")));
        scene = new Scene(parent);
        // This line gets the stage information
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Digital Marketplace");
        stage.show();
    }

    public void loadCategories() {
        categoryBox.setItems(FXCollections.observableArrayList("Food", "Fashion", "Sightseeing", "Other"));
    }

    public void advertCreationSuccessScreen(ActionEvent event) throws Exception {
        postList.add(new Post(businessName.getText(), selectedImage.getAbsolutePath(),
                businessInfo.getText(), businessLocation.getText(), categoryBox.getValue()));
        parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("advert_creation_success.fxml")));
        scene = new Scene(parent);
        // This line gets the stage information
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Digital Marketplace");
        stage.show();
    }

    public void chooseImage() throws FileNotFoundException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select image");
        selectedImage = fileChooser.showOpenDialog(stage);

        // Image fitting
        Image image = new Image(new FileInputStream(selectedImage));
        imagePreview.setImage(image);
        centerImage();
    }

    public void centerImage() {
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

    public static void main(String[] args) {
        launch(args);
    }
}
