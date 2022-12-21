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
java CheckRunner <1-1 2-5 card-2>  
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
>TAXABLE TOT: 72,85   
>VAT 7%: 5,1   
>TOTAL: 77,95   
