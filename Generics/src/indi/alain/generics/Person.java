package indi.alain.generics;

/**
 * @author d
 */

public class Person<S, N> {
    /**
     * 如果说不知道未来name和address的类型时，可以用泛型占位符进行占位
     * 定义一个泛型类
     * 占位符随便写 T W E
     */
    private S name;
    private N address;

    public <K> void show(K name) {
        System.out.println(name + "正在演讲");
    }

    //静态方法中的类型占位符和类中的泛型占位符没有关系
    public static <W> W attack(W name) {
        System.out.println(name + "又在打电动哦，休息一下好不好");
        return name;
    }

    public S getName() {
        return name;
    }

    public void setName(S name) {
        this.name = name;
    }

    public N getAddress() {
        return address;
    }

    public void setAddress(N address) {
        this.address = address;
    }

    public static void main(String[] args) {
        Person<String, Integer> person = new Person<>();
        person.setName("alain");
        person.setAddress(11111);
        person.show(333);
        System.out.println(person.getName());
        System.out.println(person.getAddress());
        System.out.println(attack(7777));

        //Person<String, Integer> person = new Person<>();
        Person<Integer, String> person1 = new Person<>();
        //java中的泛型只存在于编码编译阶段，在运行期间泛型的类型是会被去除擦除
        System.out.println(person.getClass() == person1.getClass());
    }
}
