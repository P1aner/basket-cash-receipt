# Cash receipt  
Application created on Java 11.  

>## Main stack:  
>- Spring  
>- JPA  
>- Hibernate  
>- PostgreSQL  
>- Logback  
>- Slf4j  
>- JUnit 5  
>- Mockito  
## Example of use:  
```
java CheckRunner 1-1 2-5 card-2  
```
The set of parameters is specified in the format: __productId-quantity__   
- __productId__ - Product ID.  
- __quantity__ - Quantity of product.    
  After that, the generated check is displayed in the console and in the file.  

>## Example of cash receipt:   
>CASH RECEIPT    
>QTY | DESCRIPTION | PRICE | TOTAL   
>-----------------------------------   
>1 | Cherry | 21.1 | 21.1   
>5 | Coconut | 11.5 | 51.75 discount 10%   
>-----------------------------------   
>DISCOUNT CARD №2, PERCENT:1.0%  
>TAXABLE TOT: 72,12  
>VAT 7%: 5,05  
>TOTAL: 77,17

## Example of use REST:
```
http://localhost:8080//check?discountCardId=1&itemId=1&itemId=1
```
The set of parameters is specified in the format: __itemId=quantity__
- __itemId__ - Product ID.
- __quantity__ - Quantity of product.    
- __discountCardId__ - Discount Card ID.
- __id__ - Card ID. 

After that, the generated check is displayed in the browser.

```
http://localhost:8080//jsoncheck?discountCardId=1&itemId=1&itemId=1
```
Generated json check is displayed in the browser.