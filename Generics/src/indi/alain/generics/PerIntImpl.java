package indi.alain.generics;

/**
 * @author d
 */
public class PerIntImpl implements PerInt<String> {

    /**
     * 1 泛型接口的实现类可以指定具体的泛型接口类型
     * 2 泛型接口的实现类如果没有指定具体的泛型类型，必须要在这个实现类中声明一个泛型类型占位符给接口使用
     * @param name
     */
    @Override
    public void show(String name) {
        System.out.println(name + "有打电动");
    }

    public static void main(String[] args) {
        PerIntImpl perInt = new PerIntImpl();
        perInt.show("godfury");
        //PerIntImpl2 perInt2 = new PerIntImpl2();  也可以不指定
        PerIntImpl2<Integer> perInt2 = new PerIntImpl2<Integer>();
        PerIntImpl2<Number> perInt3 = new PerIntImpl2<>();
        perInt2.setName(777);
        perInt3.show2(perInt2);
        System.out.println(perInt3.getName());


    }
}

class PerIntImpl2<T> implements PerInt<T> {

    private T name;

    public T getName() {
        return name;
    }

    public void setName(T name) {
        this.name = name;
    }

    @Override
    public void show(T name) {
        System.out.println(name + "有打电动");
    }

    /**
     * ? extends T  表示是泛型可以传入T和T的子类 上边界
     * 上边界在读取T这个类型数据时候，但不写入时候，使用上边界, T是父亲，当前数据类型是儿子
     * ? super T 表示， T 和T 的父类 下边界
     *下边界 需要写入数据，但是不读取。 使用下边界，T是儿子,当前数据类型是父亲
     * @param perIntImpl2
     */
    public void show2(PerIntImpl2<? extends T> perIntImpl2) {
        this.setName((T)perIntImpl2.getName());
    }
}
