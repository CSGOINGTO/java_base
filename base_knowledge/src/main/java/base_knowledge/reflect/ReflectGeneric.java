package base_knowledge.reflect;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class ReflectGeneric {
    public void test01(Map<String, Book> map, List<Book> bookList) {
        System.out.println("test01 method...");
    }

    public Map<Integer, Book> test02() {
        System.out.println("test02 method...");
        return null;
    }

    public static void main(String[] args) {
        try {
            Class<ReflectGeneric> reflectGenericClass = ReflectGeneric.class;
            Method method01 = reflectGenericClass.getMethod("test01", Map.class, List.class);
            // 获取指定方法参数泛型信息
            Type[] method01GenericParameterTypes = method01.getGenericParameterTypes();
            for (Type method01GenericParameterType : method01GenericParameterTypes) {
                System.out.println("##" + method01GenericParameterType);
                if (method01GenericParameterType instanceof ParameterizedType) {
                    // 获取泛型中的具体信息
                    Type[] actualTypeArguments = ((ParameterizedType) method01GenericParameterType).getActualTypeArguments();
                    for (Type actualTypeArgument : actualTypeArguments) {
                        System.out.println("泛型类型：" + actualTypeArgument);
                    }
                }
            }
            System.out.println("---------------------------------------------------------------");
            Method test02 = reflectGenericClass.getMethod("test02", null);
            Type test02GenericReturnType = test02.getGenericReturnType();
            if (test02GenericReturnType instanceof ParameterizedType) {
                Type[] actualTypeArguments = ((ParameterizedType) test02GenericReturnType).getActualTypeArguments();
                for (Type actualTypeArgument : actualTypeArguments) {
                    System.out.println("返回值，泛型类型：" + actualTypeArgument);
                }
            }

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

}
