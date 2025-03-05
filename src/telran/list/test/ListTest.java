package telran.list.test;

import org.junit.jupiter.api.Test;
import telran.list.intefaces.IList;
import telran.list.model.MyLinkedList;

import java.util.Iterator;

public class ListTest {

    @Test
    void test(){
        IList<Integer> list = new MyLinkedList<>();
        System.out.println(list.size());
        System.out.println(list.isEmpty());
        list.add(2);
        list.add(7);
        list.add(5);
        list.add(3);
        System.out.println(list.size());
        System.out.println(list.isEmpty());
        list.add(7);
        list.add(4, null);
        System.out.println(list.size());
        System.out.println("=== get ===");
        System.out.println(list.get(2));
        System.out.println(list.get(4));
        try {
            System.out.println(list.get(6));
        } catch (Exception e) {
            System.out.println("6 out of bound");
        }
        System.out.println("=== indexOf ===");
        System.out.println(list.indexOf(7));
        System.out.println(list.indexOf(10));
        System.out.println(list.indexOf(null));
        System.out.println(list.contains(1));
        System.out.println(list.contains(7));
        System.out.println(list.contains(null));
        System.out.println("=== lastIndexOf ===");
        System.out.println(list.lastIndexOf(7));
        System.out.println("===== iterator =====");
        for (Integer integer : list) {
            System.out.println(integer);
        }
        System.out.println("===== set =====");
        Integer num = list.set(4, 11);
        System.out.println(num);
        System.out.println(list.get(4));
        System.out.println("===== remove =====");
        num = list.remove(2);
        System.out.println(num);
        System.out.println(list.size());
        System.out.println(list.remove((Integer) 11));
        System.out.println(list.size());
//        System.out.println("===== clear =====");
//        list.clear();
//        System.out.println(list.isEmpty());
//        System.out.println("===== List of String =====");
//        IList<String> myList = new MyLinkedList<>();
//        myList.add("Boston");
//        myList.add("Atlanta");
//        myList.add("Chicago");
//        myList.add("Boston");
//        myList.add("New York");
//        Iterator<String> iterator = myList.iterator();
//        while(iterator.hasNext()) {
//            System.out.println(iterator.next());
//        }
    }
}
