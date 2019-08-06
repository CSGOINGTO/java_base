package base_knowledge.annot;

import java.lang.reflect.Method;

/**
 * FirstAnnotation注解的处理类（Annotation Processing Tool, APT）
 */
public class FirstAnnotationAPT {
    public static void main(String[] args) {
        FirstAnnotationUser firstAnnotationUser = new FirstAnnotationUser();
        Method[] declaredMethods = firstAnnotationUser.getClass().getDeclaredMethods();
        for (int i = 0; i < declaredMethods.length; i++) {
            FirstAnnotation annotation = declaredMethods[i].getAnnotation(FirstAnnotation.class);
            if (annotation != null) {
                try {
                    declaredMethods[i].invoke(firstAnnotationUser, null);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    System.out.println(declaredMethods[i].getName() + "有bug！");
                }
            }
        }
    }
}
