
import java.io.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.Scanner;

public class User implements Serializable {

    // Constructor to initialise User variables and HealthInformation variables

    private String userName;
    private String realName;
    private String email;

    public User(String userName, String realName, String email)
            throws FileNotFoundException {
        this.userName = userName;
        this.realName = realName;
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getRealName() {
        return realName;
    }



    public static void main(String[] args) throws FileNotFoundException {
//        User user = new User("JT93", "Josh Thomson", "JoshT626@hotmail.co.uk", 28,
//                184, 77.6, "M");
//        System.out.println("Username: " + user.userName);
//        System.out.println("Full name: " + user.realName);
//        System.out.println("Email address: " + user.email);
//        System.out.println("Age: " + user.healthInformation.getAge());
//        System.out.println("Height: " + user.healthInformation.getHeight());
//        System.out.println("Weight: " + user.healthInformation.getWeight());
////        System.out.println("BMI: " + user.healthInformation.calculateBMI());
    }

}
