package org.tinder.utils;

public class User {

    String pic;
    String name;

    public User(String pic, String name) {
        this.pic = pic;
        this.name = name;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "pic='" + pic + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
