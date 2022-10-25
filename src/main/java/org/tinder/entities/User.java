package org.tinder.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;
    private String email;
    private String password;
    private String name;
    private String surname;
    private String photo_url;
    private Integer age;
    private String gender;
    private boolean isActive;

    public User(String email, String password, String name,
                String surname, String photo_url, Integer age, String gender) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.photo_url = photo_url;
        this.age = age;
        this.gender = gender;
    }

    public Integer getGender() {
        if (this.gender.equals("male")) return 0;
        else return 1;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", gender='" + (gender.equals("0") ? "male" : "female") + '\'' +
                ", isActive=" + isActive +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(email, user.email) &&
                Objects.equals(name, user.name) &&
                Objects.equals(surname, user.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, name, surname);
    }
}
