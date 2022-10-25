package org.tinder.utils;

import org.apache.commons.lang3.RandomStringUtils;
import org.tinder.entities.User;

import java.util.*;

public class UserGenerator {
    private static final Map<Integer, List<String>> names = new HashMap<>();
    private static final List<String> surnames = new ArrayList<>();
    private static final Map<Integer, List<String>> urls = new HashMap<>();

    static {
        names.put(0, Arrays.asList("Stanley", "James", "Bradley", "Michael", "John", "Howard", "Alex")); // for male
        names.put(1, Arrays.asList("Samantha", "Vivian", "Jess", "Barbara", "Jennifer", "Amanda", "Sarah")); // for female
        surnames.addAll(Arrays.asList("Black", "Smith", "Williams", "Miller", "White", "Harris"));

        urls.put(0, Arrays.asList(
                "https://media.istockphoto.com/photos/portrait-of-young-happy-indian-business-man-executive-looking-at-picture-id1309489745?b=1&k=20&m=1309489745&s=170667a&w=0&h=Wo_7nESC_ePyEYfEsnOm-rP6ElkxbWqIB3Ga4W3nw8M=",
                "https://media.gannett-cdn.com/29906170001/29906170001_5858202637001_5858201755001-vs.jpg?pubId=29906170001&width=660&height=371&format=pjpg&auto=webp",
                "https://upload.wikimedia.org/wikipedia/commons/2/25/Leonardo_DiCaprio_2014.jpg",
                "https://static.wikia.nocookie.net/avatar/images/e/e5/Rami_Malek.png"
        ));
        urls.put(1, Arrays.asList(
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSxhcCYW4QDWMOjOuUTxOd50KcJvK-rop9qE9zRltSbVS_bO-cfWA",
                "https://pbs.twimg.com/profile_images/1258426992841461760/BofEq3C-_400x400.jpg",
                "https://images.wallpapersden.com/image/download/naomi-scott-actress-2022_bWZqZmWUmZqaraWkpJRoa2WtaGtl.jpg",
                "https://images.wallpapersden.com/image/download/emmy-rossum-hd-actress-2022_bWloZWyUmZqaraWkpJRoa2WtaGtl.jpg"
        ));
    }

    public static User generate() {
        Random random = new Random();
        int rndNum = random.nextInt(2);
        String gender = rndNum == 0 ? "male" : "female";

        int size = names.get(rndNum).size();
        int sizeUrl = urls.get(rndNum).size();
        String name = names.get(rndNum).get(random.nextInt(size));
        String surname = surnames.get(random.nextInt(surnames.size()));
        Integer age = random.nextInt(10) + 18;
        String email = RandomStringUtils.randomAlphabetic(9) + "@temp-mail.com";
        String password = RandomStringUtils.randomNumeric(6);
        String url = urls.get(rndNum).get(random.nextInt(sizeUrl));

        return new User(email, password, name, surname, url, age, gender);
    }
}
