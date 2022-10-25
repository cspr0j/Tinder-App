package org.tinder.utils;

import org.apache.commons.lang3.RandomStringUtils;
import org.tinder.entities.User;

import java.util.*;

public class UserGenerator {
    private static final Map<Integer, List<String>> names = new HashMap<>();
    private static final List<String> surnames = new ArrayList<>();
    private static final List<String> urls = new ArrayList<>();

    static {
        names.put(0, Arrays.asList("Stanley", "James", "Bradley", "Michael", "John", "Howard", "Alex")); // for male
        names.put(1, Arrays.asList("Samantha", "Vivian", "Jess", "Barbara", "Jennifer", "Amanda", "Sarah")); // for female
        surnames.addAll(Arrays.asList("Black", "Smith", "Williams", "Miller", "White", "Harris"));
        urls.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSxhcCYW4QDWMOjOuUTxOd50KcJvK-rop9qE9zRltSbVS_bO-cfWA");
    }

    public static User generate() {
        Random random = new Random();
        int rndNum = random.nextInt(2);
        String gender = rndNum == 0 ? "male" : "female";

        int size = names.get(rndNum).size();
        String name = names.get(rndNum).get(random.nextInt(size));
        String surname = surnames.get(random.nextInt(surnames.size()));
        Integer age = random.nextInt(10) + 18;
        String email = RandomStringUtils.randomAlphabetic(9) + "@temp-mail.com";
        String password = RandomStringUtils.randomNumeric(6);
        String url = urls.get(random.nextInt(urls.size()));

        return new User(email, password, name, surname, url, age, gender);
    }
}
