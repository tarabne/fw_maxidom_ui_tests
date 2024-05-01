package ru.tarabne.testdata;

import com.github.javafaker.Faker;

public class RegistrationTestData {
    static Faker faker = new Faker();
    public String firstName = faker.name().firstName(),
    lastName = faker.name().lastName(),
    userEmail = faker.internet().emailAddress(),
    userPhone = faker.phoneNumber().subscriberNumber(11),
    userPassword = "123456";
}
