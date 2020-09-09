package base_knowledge.annot;

import java.lang.reflect.Method;

/**
 * FirstAnnotation注解的处理类（Annotation Processing Tool, APT）
 */
public class FirstAnnotationAPT {
    public static void main(String[] args) {
        FirstAnnotationUser firstAnnotationUser = new FirstAnnotationUser();
        Method[] declaredMethods = firstAnnotationUser.getClass().getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            FirstAnnotation annotation = declaredMethod.getAnnotation(FirstAnnotation.class);
            if (annotation != null) {
                try {
                    System.out.println(annotation.value());
                    declaredMethod.invoke(firstAnnotationUser, null);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    System.out.println(declaredMethod.getName() + "有bug！");
                }
            }
        }
    }
}
