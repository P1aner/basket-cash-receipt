package by.receipt.app;

import by.receipt.controllers.BasketController;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class CheckRunner {
    public static void main(String[] args) {
        //  ConfigurableApplicationContext run = SpringApplication.run(CheckRunner.class, args);

        ApplicationContext context = new AnnotationConfigApplicationContext(JavaConfig.class);
        var bean = context.getBean(BasketController.class);//context.getBean();
        bean.printReceipt(args);
    }
}