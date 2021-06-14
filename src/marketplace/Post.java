package marketplace;

import javafx.geometry.Pos;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public class Post implements Serializable {

    private String title;
    private String image_path;
    private String description;
    private String location;
    private Category category;
    public enum Category {
        SPORT,
        CULTURE,
        FASHION,
        FOOD,
        FISHING,
        TRANSPORT
    }

    Post(String title, String image_path, String description, String address, String category) {
        this.title = title;
        this.image_path = image_path;
        this.description = description;
        this.location = address;
        this.category = switch (category) {
            case "Sport" -> Category.SPORT;
            case "Culture" -> Category.CULTURE;
            case "Fashion" -> Category.FASHION;
            case "Food" -> Category.FOOD;
            case "Fishing" -> Category.FISHING;
            case "Transport" -> Category.TRANSPORT;
            default -> throw new IllegalStateException("Unexpected value: " + category);
        };
        save();
    }

    Post(Post post) {
        this.title = post.getTitle();
        this.image_path = post.getImage_path();
        this.description = post.getDescription();
        this.location = post.getAddress();
        this.category = post.getCategory();
    }

    Post() {}

    public String getAddress() {
        return location;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public String getImage_path() {
        return image_path;
    }

    public Category getCategory() {
        return category;
    }

    
        //Unsure if necessary
//    public BufferedImage loadImage(){
//        BufferedImage img = null;
//
//        try{
//            File file = new File(image_path);
//            img = ImageIO.read(file);
//        } catch(Exception e){
//            e.printStackTrace();
//        }
//
//        return img;
//    }

       public void save() { // requires posts folder to work 

        //saves post object
        File f = new File(this.title + ".csv");
        try{
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("./posts/"+ this.title + ".csv"));
            os.writeObject(this);
            os.close();
        } catch (IOException e ){
            e.printStackTrace();
            System.out.println("Error saving");
        }
        System.out.println("saved file");
        //saves post title to csv (used for loading all posts)

        try{
            FileWriter fw = new FileWriter("postTitles", true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(this.title);
            bw.newLine();
            bw.close();
            fw.close();
        } catch(Exception e){
            e.printStackTrace();
        }


    }

    @Override
    public String toString() {
        return "Post{" +
                "title='" + title + '\'' +
                ", image_path='" + image_path + '\'' +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", category='" + category + '\'' +
                '}';
    }

    public static void main(String[] args) {
        Post post = new Post();
        //post.uploadImage("D:\\willi\\Nayeon.jpg");
        post.uploadImage("VM4E3.jpg");
    }


}
