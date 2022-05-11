package indi.alain.greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @author dll
 * @date 20220507
 */
public class GreedyAlgorithm {


    public static void main(String[] args) {
        //map管理所有的电台
        HashMap<String, HashSet<String>> broadcasts = new HashMap<>();
        //具体电台
        HashSet<String> hashSet1 = new HashSet<>();
        hashSet1.add("北京");
        hashSet1.add("上海");
        hashSet1.add("天津");

        HashSet<String> hashSet2 = new HashSet<>();
        hashSet2.add("广州");
        hashSet2.add("北京");
        hashSet2.add("深圳");

        HashSet<String> hashSet3 = new HashSet<>();
        hashSet3.add("成都");
        hashSet3.add("上海");
        hashSet3.add("杭州");

        HashSet<String> hashSet4 = new HashSet<>();
        hashSet4.add("上海");
        hashSet4.add("天津");

        HashSet<String> hashSet5 = new HashSet<>();
        hashSet5.add("杭州");
        hashSet5.add("大连");

        broadcasts.put("K1", hashSet1);
        broadcasts.put("K2", hashSet2);
        broadcasts.put("K3", hashSet3);
        broadcasts.put("K4", hashSet4);
        broadcasts.put("K5", hashSet5);

        HashSet<String> allAreas = new HashSet<>();
        for (HashSet<String> value : broadcasts.values()) {
            allAreas.addAll(value);
        }

        //创建ArrayList, 存放选择的电台集合
        ArrayList<String> selects = new ArrayList<>();
        //定义一个临时的集合，在遍历过程中，存放遍历过程中的电台覆盖地区和当前没有覆盖地区的交集
        HashSet<String> tempSet1 = new HashSet<>();
        HashSet<String> tempSet2 = new HashSet<>();
        //定义一个maxKey，保存在一次遍历过程中，能够覆盖最大未覆盖地区对于的电台的key
        String maxKey = null;
        //如果maxKey 不为null，则会加入到selects
        while (allAreas.size() != 0) {
            //每一个while 需要置空maxKey
            maxKey = null;
            //没覆盖到未
            for (String key : broadcasts.keySet()) {
                //每一个for需要清空tempSet
                tempSet1.clear();
                HashSet<String> areas = broadcasts.get(key);
                tempSet1.addAll(areas);
                //把两个集合的交集赋给tempSet
                tempSet1.retainAll(allAreas);
                //如果当前集合包含未覆盖地区数量。比maxKey指向的集合地区还多
                if (tempSet1.size() > 0 && (maxKey == null || tempSet1.size() > tempSet2.size())) {
                    //第一次选最大，第二次选更大，这里体现greedy
                    maxKey = key;
                    tempSet2 = broadcasts.get(maxKey);
                    tempSet2.retainAll(allAreas);
                }
            }
            //maxKey != null
            if (maxKey != null) {
                selects.add(maxKey);
                allAreas.removeAll(broadcasts.get(maxKey));
            }
        }
        System.out.println("选择结果为: " + selects);
    }
}
