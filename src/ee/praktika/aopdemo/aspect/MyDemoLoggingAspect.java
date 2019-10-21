package ee.praktika.aopdemo.aspect;

import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import ee.praktika.aopdemo.Account;

@Aspect
@Component
@Order( 4 )
public class MyDemoLoggingAspect {

    @AfterThrowing( pointcut = "execution(* ee.praktika.aopdemo.dao.AccountDAO.findAccounts(..))", throwing = "theExc" )
    public void afterThrowingFindAccountsAdvice( JoinPoint theJoinPoint, Throwable theExc ){

        //print out which method we are advising on
        String method = theJoinPoint.getSignature().toShortString();
        System.out.println( "\n======>>> Executing @AfterThrowing on method: " + method );

        //log the exception
        System.out.println( "\n======>>> Exceptiong is: " + theExc );
    }

    @AfterReturning( pointcut = "execution(* ee.praktika.aopdemo.dao.AccountDAO.findAccounts(..))", returning = "result" )
    public void afterReturningFindAccountsAdvice(
        JoinPoint theJoinPoint, List<Account> result ){

        //print out which method are we advising on
        String method = theJoinPoint.getSignature().toShortString();
        System.out.println( "\n======>>> Executing @AfterReturning on method: " + method );

        //print out the result of the method call
        System.out.println( "\n======>>> result is: " + result );

        //watn to post-process the data and modify it, before it makes back to caller

        //convert the account name to all uppercase
        convertAccountNamesToUpperCase( result );

        System.out.println( "\n======>>> result is: " + result );

    }

    private void convertAccountNamesToUpperCase( List<Account> result ){

        //loop through accounts
        for( Account tempAccount : result ) {

            //get uppercase version of names
            String theUpperName = tempAccount.getName().toUpperCase();

            //update the name on the account object
            tempAccount.setName( theUpperName );

        }
    }

    @Before( "ee.praktika.aopdemo.aspect.AopExpressions.referencePointcutIgnoreGetSet()" )
    public void beforeAddAccountAdvice( JoinPoint theJoinPoint ){
        System.out.println( "\n======>>> Executing @Before advice on addAccount() in the DAO package" );

        //display the method signature
        MethodSignature methodSignature = (MethodSignature)theJoinPoint.getSignature();

        System.out.println( "Method: " + methodSignature );

        //display the method arguments that are being passed in

        //get the arguments
        Object[] args = theJoinPoint.getArgs();

        //looping through the arguments and printing them out
        for( Object tempArg : args ) {
            System.out.println( tempArg );

            if( tempArg instanceof Account ) {
                //downcast and print Account specific stuff
                Account theAccount = (Account)tempArg;

                System.out.println( "Account name: " + theAccount.getName() );
                System.out.println( "Level name: " + theAccount.getLevel() );
            }
        }
    }
}
