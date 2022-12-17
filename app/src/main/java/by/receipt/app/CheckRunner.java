package by.receipt.app;

import by.receipt.api.controllers.IBasketController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class CheckRunner {
    public static void main(String[] args) {
        SpringApplication.run(CheckRunner.class, args);
        ApplicationContext context = new AnnotationConfigApplicationContext(JavaConfig.class);
        var bean = context.getBean(IBasketController.class);
        bean.printReceipt(args);
    }
}