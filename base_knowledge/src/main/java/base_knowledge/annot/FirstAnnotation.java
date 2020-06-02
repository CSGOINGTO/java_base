package base_knowledge.annot;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 该注解用于测试，用法是测试某个方法有没有运行时异常
 */
// 运行时该注解依然在
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FirstAnnotation {

    String value() default "";

    String[] role();

}
