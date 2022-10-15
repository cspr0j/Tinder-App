package org.tinder.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    private Integer id;
    private String email; // login
    private String password;
    private String name;
    private String surname;
//    private String birthDate;
    private Integer age;
    private String gender;

    private boolean isActive;

    public User(String email, String password, String name, String surname, Integer age, String gender) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
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
                ", gender='" + (gender.equals("0") ? "male" : "female")  + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
