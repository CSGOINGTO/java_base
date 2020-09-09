package base_knowledge.annot;

/**
 * FirstAnnotation注解的使用类
 */
public class FirstAnnotationUser {

    @FirstAnnotation(role = {"BO", "PM"}, value = "2 * 3 = ?")
    public void chengfa() {
        System.out.println(2 * 3);
    }

    @FirstAnnotation(role = {"lalala"}, value = "2 / 0 = ?")
    public void chufa() {
        System.out.println(2 / 0);
    }
}
