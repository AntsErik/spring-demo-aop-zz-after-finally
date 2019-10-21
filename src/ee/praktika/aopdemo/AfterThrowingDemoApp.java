package ee.praktika.aopdemo;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import ee.praktika.aopdemo.dao.AccountDAO;

public class AfterThrowingDemoApp {

    public static void main( String[] args ){

        //read Spring Configuration Java Class
        AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext( DemoConfig.class );

        //Get the bean from Spring container
        AccountDAO theAccountDAO = context.getBean( "accountDAO", AccountDAO.class );

        //call the method to find accoutns
        List<Account> theAccounts = null;

        try {
            //add a boolean flag to simulate the exception
            boolean tripWire = true;
            theAccounts = theAccountDAO.findAccounts( tripWire );
        }
        catch( Exception exc ) {
            System.out.println( "\n\nMain Program .. caugth exception: " + exc );
        }

        //display the accounts
        System.out.println( "\n\nMain Program:AfterThrowingDemoApp" );
        System.out.println( "-----------------" );

        System.out.println( theAccounts );

        System.out.println( "\n\n" );

        //Close the context
        context.close();

    }

}
