package Faker;

import com.github.javafaker.Faker;

import java.util.Locale;

public class DemoFaker {
    public static void main (String args[]) {
        Faker faker = new Faker(Locale.CHINA);

        String name = faker.name().fullName(); // Miss Samanta Schmidt
        String firstName = faker.name().firstName(); // Emory
        String lastName = faker.name().lastName(); // Barton

        String streetAddress = faker.address().streetAddress(); // 60018 Sawayn Brooks Suite 449
        System.out.println(firstName);
        System.out.println(lastName);
        System.out.println(streetAddress);
        System.out.println(name);
    }
}
