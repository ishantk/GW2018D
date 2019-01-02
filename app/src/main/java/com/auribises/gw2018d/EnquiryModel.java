package com.auribises.gw2018d;

public class EnquiryModel {

    public String name;
    public String email;
    public boolean cpp;
    public boolean java;
    public boolean python;
    public String gender;
    public String city;

    public EnquiryModel(){

    }

    public EnquiryModel(String name, String email, boolean cpp, boolean java, boolean python, String gender, String city) {
        this.name = name;
        this.email = email;
        this.cpp = cpp;
        this.java = java;
        this.python = python;
        this.gender = gender;
        this.city = city;
    }

    @Override
    public String toString() {
        return "EnquiryModel{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", cpp=" + cpp +
                ", java=" + java +
                ", python=" + python +
                ", gender='" + gender + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
