package com.lxy.demo;

import lombok.Data;

@Data
public class Person {

    public Person() {

    }

    public  Person(String userName, Integer age) {
        this.userName = userName;
        this.age = age;
    }

    private String userName;
    private Integer age;
}
