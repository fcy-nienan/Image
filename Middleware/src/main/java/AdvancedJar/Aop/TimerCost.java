package AdvancedJar.Aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.logging.Logger;
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TimerCost {
}
