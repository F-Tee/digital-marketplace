package marketplace;

import java.io.*;
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
                is.close();
            }

            fr.close();
            br.close();
        } catch(Exception e){
            e.printStackTrace();
        }


    }
    
    public void deletePost(Post post){
        //deleting title from postTitles
        String title_remove = post.getTitle();
       // String title_remove = "swim";
        String current_line = "";
        File input = new File("postTitles");
        File output = new File("temp.csv");
        try{
            FileReader fr = new FileReader(input);
            BufferedReader br = new BufferedReader(fr);
            FileWriter fw = new FileWriter(output);
            BufferedWriter bw = new BufferedWriter(fw);
            current_line = br.readLine();
            while(current_line != null){
                String trimmed_line = current_line.trim();
                if(trimmed_line.equals(title_remove)){
                    current_line = br.readLine();
                    continue;
                }
                bw.write(trimmed_line);
                bw.newLine();

                current_line = br.readLine();
            }
            bw.close();
            br.close();
            fr.close();
            fw.close();
            System.out.println(input.delete());
            output.renameTo(input);


        } catch(Exception e){
            e.printStackTrace();
        }
       // deleting from posts
        System.out.println(".\\posts\\plswork.csv");
        File post_remove = new File(".\\posts\\" + title_remove + ".csv");
        boolean test = post_remove.delete();
        System.out.println(test);

        //deleting from pics
        File img_remove = new File(post.getImage_path());
        System.out.println("This is img path: " + post.getImage_path());
        //File img_remove = new File(".\\pics\\test2.jpg");
        img_remove.delete();
    }
    
    public String uploadImage(String path){
        BufferedImage img = null;
        System.out.println(path);
        //read image

        try{
            File file = new File(path);
            img = ImageIO.read(file);
        } catch(Exception e){
            e.printStackTrace();
        }
        //write image

        String filename = path.replaceAll(".+\\\\", "");
        System.out.println(filename);

        try{
            ImageIO.write(img, "jpg", new File(".\\pics\\" + filename));

        } catch(Exception e){
            e.printStackTrace();
        }
        return ".\\pics\\" + filename;

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


