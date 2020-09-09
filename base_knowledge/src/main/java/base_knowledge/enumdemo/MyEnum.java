package base_knowledge.enumdemo;

public enum MyEnum {
    test("test", 1),
    test1("test1", 2);

    int weight;

    MyEnum(String name, int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public static void main(String[] args) {
        System.out.println(MyEnum.test);
        System.out.println(MyEnum.test.name());
        System.out.println(MyEnum.test.getWeight());
    }
}
