package marketplace;

import javax.xml.crypto.Data;
import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Database {

    Database(){

    }

    public void loadAllPosts(ArrayList<Post> posts){
        try{
            FileReader fr = new FileReader("postTitles");
            BufferedReader br = new BufferedReader(fr);
            String title = br.readLine();
            while(title != null){
                ObjectInputStream is = new ObjectInputStream(new FileInputStream(".\\posts\\"+ title + ".csv"));
                Post post = (Post) is.readObject();
                posts.add(post);
                title = br.readLine();
            }
        } catch(Exception e){
            e.printStackTrace();
        }


    }

    public static void main(String[] args) {
        Database database = new Database();
        ArrayList<Post> posts = new ArrayList<>();
        database.loadAllPosts(posts);
        for(Post post: posts){
            System.out.println(post.getTitle());
        }
    }
}


