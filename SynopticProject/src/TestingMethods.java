import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.xml.crypto.Data;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TestingMethods {

    @Test
    @DisplayName("Save a post to a csv upon creation")
    void savePost() throws Exception{
        Post post = new Post("Posttest", "this is a test", "test@gmail.com", "123456", "address",
                "path");
        Post post2 = new Post("Posttest2", "this is a test 2", "test2@gmail.com", "12345689", "address",
                "path2");
        File file = new File(".\\posts\\Posttest.csv");
        File file2 = new File("postTitles");
        File file3 = new File(".\\posts\\Posttest2.csv");
        FileReader fileReader = new FileReader(file2);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String first_post = bufferedReader.readLine();
        String second_post = bufferedReader.readLine();
        bufferedReader.close();
        fileReader.close();
        assertAll(
                () -> assertTrue(file.exists()),
                () -> assertTrue(file2.exists()),
                () -> assertTrue(file3.exists()),
                () -> assertEquals("Posttest", first_post),
                () -> assertEquals("Posttest2", second_post)
        );
        file.delete();
        file2.delete();
        file3.delete();
    }

    @Test
    @DisplayName("load all known posts to an arrayList")
    void loadAllPosts() {
        Database database = new Database();
        Post post = new Post("load1", "load test 1", "load1.com", "12", "address", "path");
        Post post2 = new Post("load2", "load test 2", "load2.com", "12", "address", "path");
        Post post3 = new Post("load3", "load test 3", "load3.com", "12", "address", "path");
        File file1 = new File(".\\posts\\load1.csv");
        File file2 = new File(".\\posts\\load2.csv");
        File file3 = new File(".\\posts\\load3.csv");
        File file4 = new File("postTitles");

        ArrayList<Post> arrayList = new ArrayList<>();
        database.loadAllPosts(arrayList);
        assertAll(
                () -> assertEquals("load1", arrayList.get(0).getTitle()),
                () -> assertEquals("load2", arrayList.get(1).getTitle()),
                () -> assertEquals("load3", arrayList.get(2).getTitle())
        );
        file1.delete();
        file2.delete();
        file3.delete();
        file4.delete();
    }

    @Test
    @DisplayName("Delete selected post using title")
    void deletePost() throws Exception {
        Database database = new Database();
        Post post = new Post("TestDelete", "Delete test", "delete.com", "123", "address",
                "C:\\Users\\willi\\Desktop\\Figure_1.png");
        File file1 = new File(".\\posts\\TestDelete.csv");
        File file2 = new File("postTitles");
        File file3 = new File(".\\posts\\Figure_1.png");
        boolean success = database.deletePost(post.getTitle());

        FileReader fileReader = new FileReader(file2);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String is_null = bufferedReader.readLine();
        bufferedReader.close();
        fileReader.close();

        assertAll(
                () -> assertTrue(success),
                () -> assertNull(is_null),
                () -> assertFalse(file1.exists()),
                () -> assertFalse(file3.exists())
        );
        file2.delete();



    }

    @Test
    @DisplayName("Upload selected image to file")
    void uploadImage() {
        Database database = new Database();
        File file = new File(".\\pics\\834e9e8e01f1b36bc9d892d3df0dbf94.jpg");
        assertAll(
                () -> assertEquals(".\\pics\\834e9e8e01f1b36bc9d892d3df0dbf94.jpg", database.uploadImage("C:\\Users\\willi\\Desktop\\834e9e8e01f1b36bc9d892d3df0dbf94.jpg")),
                () -> assertTrue(file.exists()));
        file.delete();

    }
}
