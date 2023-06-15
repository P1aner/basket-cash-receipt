package by.receipt.cash_receipt;

import by.receipt.api.controllers.IBasketController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableTransactionManagement
@ComponentScan("by.receipt")
@EnableJpaRepositories("by.receipt.repository")
@EnableWebMvc
public class CashReceiptApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(CashReceiptApplication.class, args);

		IBasketController bean = context.getBean(IBasketController.class);
		bean.printReceipt(args);
	}

}
