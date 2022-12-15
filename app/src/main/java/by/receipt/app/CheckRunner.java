package by.receipt.app;


import by.receipt.model.Basket;
import by.receipt.repository.BasketRepository;
import by.receipt.services.CheckService;
import by.receipt.utils.CheckView;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CheckRunner {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(JavaConfig.class);

        var bean = context.getBean(BasketRepository.class);
        Basket x = bean.findById(1).get();
        System.out.println(x);
        var bean1 = context.getBean(CheckService.class);
        String check = bean1.getCheck(x);

        CheckView checkView = new CheckView();
        checkView.filePrint(check);
        checkView.consolePrint(check);


        //   var bean = context.getBean(ProductService.class);
        //   bean.asdlkfal();
        //   System.out.println(bean);
        //   var bean1 = context.getBean(BasketService.class);bean1.createBasket(List.of(new BasketItem(new Product("asdkfj", 12.2, DiscountStatus.DISCOUNT), 1)), new DiscountCard(1));
        //   var bean3 = context.getBean(BasketRepository.class);
        //   bean3.save(new Basket());


    }
}

