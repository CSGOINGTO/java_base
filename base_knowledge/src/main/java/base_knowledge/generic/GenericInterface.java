package base_knowledge.generic;

public interface GenericInterface <T> {

    void showKeyValue(GenericInterface<T> genericInterface);
}

class GenericInterfaceImpl<T> implements GenericInterface<T> {

    @Override
    public void showKeyValue(GenericInterface<T> genericInterface) {
        System.out.println("xxxxx");
    }

    private <K> K test(Class<K> tClass) throws IllegalAccessException, InstantiationException {
        return tClass.newInstance();
    }

    public static void main(String[] args) {
        GenericInterfaceImpl<Object> objectGenericInterface = new GenericInterfaceImpl<>();
        objectGenericInterface.showKeyValue(objectGenericInterface);
        GenericInterfaceImpl<System> objectGenericInterface1 = new GenericInterfaceImpl<>();
        objectGenericInterface1.showKeyValue(objectGenericInterface1);
        if (objectGenericInterface instanceof GenericInterfaceImpl) {
            System.out.println("xxxxx");
        }
    }
}

