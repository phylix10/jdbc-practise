package com.alireza;

import com.alireza.model.User;
import com.alireza.repository.UserRepository;

import java.sql.Date;

public class Main {
    public static void main(String[] args) {
        User user1 = new User();
        user1.setFirstName("hamed");
        user1.setLastName("zandi");
        user1.setAge(56);
        user1.setNationalCode("5189764523");
        user1.setBirthDate(Date.valueOf("2010-09-17"));

        UserRepository.createUser(user1);
    }
}
