import javax.xml.crypto.Data;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class Testing {


    public static boolean testIMGUpload(String image_path){
        boolean works = true;
        Database database = new Database();
        String path = database.uploadImage(image_path);
        File img_file = new File(path);
        if(!img_file.exists()){
            works = false;
        }

        return works;
    }

    public static boolean testSavePost(String title){
        boolean works = false;
        Post post = new Post(title);
        File post_file = new File(".\\posts\\" + title + ".csv");
        if(!post_file.exists()){
            return false;
        }
        File postTitles = new File("postTitles");
        if(!postTitles.exists()){
            return false;
        }
        try{
            FileReader reader = new FileReader(postTitles);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String current_line = bufferedReader.readLine();
            while(current_line!=null){
                if(title.equals(current_line)){
                    works = true;
                    break;
                }
                current_line = bufferedReader.readLine();
            }
            bufferedReader.close();
            reader.close();

        } catch(Exception e){
            e.printStackTrace();
        }

        return works;
    }

    public static boolean testDelete(Post post){
        File file = new File(".\\posts\\" + post.getTitle() + ".csv");
        File img_file = new File(post.getImage_path());
        File post_titles = new File("postTitles");

        Database database = new Database();
        database.deletePost(post);

        if(file.exists()){
            return false;
        }
        if(img_file.exists()){
            return false;
        }

        try{
            FileReader reader = new FileReader(post_titles);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String current_line = bufferedReader.readLine();
            while(current_line!=null){
                if(post.getTitle().equals(current_line)){
                    return false;
                }
                current_line = bufferedReader.readLine();
            }

            bufferedReader.close();
            reader.close();
        } catch(Exception e){

        }

        return true;
    }

    public static boolean testLoadAll(ArrayList<Post> posts){
        boolean works = true;
        Database database = new Database();
        Post post1 = new Post("test1");
        Post post2 = new Post("test2");
        database.loadAllPosts(posts);
        try{
            for(Post post : posts){
                System.out.println(post.getTitle());
            }
        } catch (Exception e){
            e.printStackTrace();
            works = false;
        }

        return works;
    }


    public static void testHarness(String image_path, String title, ArrayList<Post> posts, Post delete){
        boolean test1 = testIMGUpload(image_path);
        boolean test2 = testSavePost(title);
        boolean test3 = testLoadAll(posts);
        boolean test4 = testDelete(delete);
        System.out.println("Test 1: " + test1);
        System.out.println("Test 2: " + test2);
        System.out.println("Test 3: " + test3);
        System.out.println("Test 4: " + test4);

    }


    public static void main(String[] args) {
        ArrayList<Post> posts = new ArrayList<>();
        Post test4 = new Post("deletetest", "this is a test", "delete.com", "0101", "address",
                "C:\\Users\\willi\\Desktop\\milenioum.jpeg");
        testHarness("D:\\Downloadsasdasd\\20130916_170411.jpg", "just work", posts, test4);

    }
}
