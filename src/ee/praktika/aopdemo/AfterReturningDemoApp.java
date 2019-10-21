package ee.praktika.aopdemo;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import ee.praktika.aopdemo.dao.AccountDAO;

public class AfterReturningDemoApp {

    public static void main( String[] args ){

        //read Spring Configuration Java Class
        AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext( DemoConfig.class );

        //Get the bean from Spring container
        AccountDAO theAccountDAO = context.getBean( "accountDAO", AccountDAO.class );

        //call the method to find accoutns
        List<Account> theAccounts = theAccountDAO.findAccounts( false );

        //display the accounts
        System.out.println( "\n\nMain Program:AfterReturningDemoApp" );
        System.out.println( "-----------------" );

        System.out.println( theAccounts );

        System.out.println( "\n\n" );

        //Close the context
        context.close();

    }

}
