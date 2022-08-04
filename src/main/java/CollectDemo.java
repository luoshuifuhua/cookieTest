import java.util.*;

/**
 * @author H_H
 * @date 2022/08/02 20:13
 **/
public class CollectDemo {
    public static void main(String[] args) {
        String s  = "s";
        String s1  = "s2";
        String s2  = "s3";
        String s3  = "s4";
        String s4  = "s5";
        String s5  = "s6";
        String s6  = "s7";
        List<String> list = new ArrayList<>();
        list.add(s);
        list.add(s1);
        list.add(s2);
        list.add(s3);
        list.add(s4);
        list.add(s5);
        list.add(s6);
        //迭代器
        /*Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()){
            String s7 = iterator.next();
            System.out.println(s7);
        }*/
        //for each 会自己调用迭代器
        /*for (String s7 : list) {
            System.out.println(s7);
        }*/
        //TreeSet 重写hashcode compareTo,实现comparetor
        String[] strings = list.toArray(new String[ list.size()]);
        System.out.println(strings[1]);

    }
}
