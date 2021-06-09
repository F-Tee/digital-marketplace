package marketplace;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public class Post implements Serializable {

    private String title;
    private String image_path;
    private String description;
    private String location;
    private Category category;
    private enum Category {
        FOOD,
        FASHION,
        SIGHTSEEING,
        OTHER
    }

    Post(String title, String image_path, String description, String address, String category) {
        this.title = title;
        this.image_path = image_path;
        this.description = description;
        this.location = address;
        this.category = switch (category) {
            case "Food" -> Category.FOOD;
            case "Fashion" -> Category.FASHION;
            case "Sightseeing" -> Category.SIGHTSEEING;
            case "Other" -> Category.OTHER;
            default -> Category.FOOD;
        };
        save();
    }

    Post() {

    }

    public String getAddress() {
        return location;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public void setAddress(String location) {
        this.location = location;
        save();
    }

    public void setDescription(String description) {
        this.description = description;
        save();
    }

    public void setTitle(String title) {
        this.title = title;
        save();
    }

    public Category getCategory() {
        return category;
    }

    public void uploadImage(String path) {
        BufferedImage img = null;
        System.out.println(path);
        //read image


        try {
            File file = new File(path);
            img = ImageIO.read(file);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("SSSSS");
        }
        //write image

        String filename = path.replaceAll(".+\\\\", "");
        System.out.println(filename);

        try {
            ImageIO.write(img, "jpg", new File(".\\pics\\" + filename));
        } catch (Exception e) {
            e.printStackTrace();
        }
        //    save();

    }

    public void save() {

        File f = new File(title + ".csv");
        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(title + ".csv"));
            os.writeObject(this);
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("saved file");

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
