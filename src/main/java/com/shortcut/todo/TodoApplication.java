package com.shortcut.todo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;

@SpringBootApplication
public class TodoApplication {

	public static void main(String[] args) {
		try {
			ClassPathResource exists = new ClassPathResource("log4j2.xml");
			System.out.println("Файл log4j2.xml существует: " + exists.exists());
			System.out.println("claasspath: " + exists.getURL());
		} catch (Exception e) {
			System.err.println("Ошибка проверки файла: " + e.getMessage());
		}
		SpringApplication.run(TodoApplication.class, args);
	}

}
