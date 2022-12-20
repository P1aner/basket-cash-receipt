package by.receipt.app;

import by.receipt.api.controllers.IBasketController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class CheckRunner {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(CheckRunner.class, args);
        IBasketController bean = context.getBean(IBasketController.class);
        bean.printReceipt(args);
    }
}