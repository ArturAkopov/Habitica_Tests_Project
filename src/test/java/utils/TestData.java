package utils;

import com.github.javafaker.Faker;

import java.util.UUID;

public class TestData {

        Faker faker = new Faker();

        public String taskName = faker.funnyName().name();
        public String taskId = UUID.randomUUID().toString();

}
