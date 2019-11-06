package com.example.georgevio.sqlinew;

public class Users {
    private String Name;
    private String Phone;
    private String Email;
    private String Age;



    public Users(String name, String phone, String email, String age){
        Name = name;
        Phone = phone;
        Email = email;
        Age= age;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }
}
