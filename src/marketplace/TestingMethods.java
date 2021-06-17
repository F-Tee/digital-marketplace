package marketplace;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TestingMethods {

    @Test
    @DisplayName("Save a post to a csv upon creation")
    void savePost() throws Exception{
        marketplace.Post post = new marketplace.Post("Posttest", "this is a test", "123456", "address",
                "Fishing");
        marketplace.Post post2 = new marketplace.Post("Posttest2", "this is a test 2", "12345689", "address",
                "Fishing");
        File file = new File("./posts/Posttest.csv");
        File file2 = new File("postTitles");
        File file3 = new File("./posts/Posttest2.csv");
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
        marketplace.Post post = new marketplace.Post("load1", "load test 1", "12", "address", "Fishing");
        marketplace.Post post2 = new marketplace.Post("load2", "load test 2","12", "address", "Fishing");
        marketplace.Post post3 = new marketplace.Post("load3", "load test 3", "12", "address", "Fishing");
        File file1 = new File("./posts/load1.csv");
        File file2 = new File("./posts/load2.csv");
        File file3 = new File("./posts/load3.csv");
        File file4 = new File("postTitles");

        ArrayList<marketplace.Post> arrayList = new ArrayList<>();
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
        marketplace.Post post = new marketplace.Post("TestDelete", "Delete test","123", "address",
                "Fishing");
        File file1 = new File("./posts/TestDelete.csv");
        File file2 = new File("postTitles");
        File file3 = new File("./posts/Figure_1.png");
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
        File file = new File("./pics/eco.png");
        assertAll(
                () -> assertEquals("./pics/eco.png", database.uploadImage("/home/ft/Documents/Uni/Synoptic Project/Digital Marketplace/eco.png")),
                () -> assertTrue(file.exists()));
        file.delete();

    }
}