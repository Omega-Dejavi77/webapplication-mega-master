package cat.tecnocampus.omega.AOP;


import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class InsertAdvice {
    @Pointcut("execution(* *.cat.tecnocampus.omega.*.insert*(..))")
    public void pointcutInsert() {
    }

    @After(("pointcutInsert()"))
    public void afterpointcutInsert() {
        System.out.print("Heeeey");
    }

}
