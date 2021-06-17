import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.regex.*;

public class Post implements Serializable {

    String title;
    String image_path;
    String description;
    String email;
    String phone_no;
    String address;

    Post(String title, String description, String email, String phone_no, String address){
        this.title = title;
        this.description = description;
        this.email = email;
        this.phone_no = phone_no;
        this.address = address;
        save();
    }

    Post(){

    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getDescription() {
        return description;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public String getTitle() {
        return title;
    }

    public void setAddress(String address) {
        this.address = address;
        save();
    }

    public void setDescription(String description) {
        this.description = description;
        save();
    }

    public void setEmail(String email) {
        this.email = email;
        save();
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
        save();
    }

    public void setTitle(String title) {
        this.title = title;
        save();
    }


    public void uploadImage(String path){
        BufferedImage img = null;
        System.out.println(path);
        //read image



        try{
            File file = new File(path);
            img = ImageIO.read(file);
        } catch(Exception e){
            e.printStackTrace();
            System.out.println("SSSSS");
        }
        //write image

        String filename = path.replaceAll(".+\\\\", "");
        System.out.println(filename);

        try{
            ImageIO.write(img, "jpg", new File(".\\pics\\" + filename));
        } catch(Exception e){
            e.printStackTrace();
        }
    //    save();

    }

    public void save() {

        File f = new File(title + ".csv");
        try{
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(title + ".csv"));
            os.writeObject(this);
            os.close();
        } catch (IOException e ){
            e.printStackTrace();
        }

        System.out.println("saved file");

    }

    public static void main(String[] args) {
        Post post = new Post();
        //post.uploadImage("D:\\willi\\Nayeon.jpg");
        post.uploadImage("VM4E3.jpg");
    }


}
