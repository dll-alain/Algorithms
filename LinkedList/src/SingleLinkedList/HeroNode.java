package SingleLinkedList;

public class HeroNode {
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;

    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    //为了显示方便，重写toString
    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no + ","
                + " name='" + name + '\'' + ","
                + " nickname='" + nickname + '\'' + '}';
    }
}
