import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class TaskForPlus {
    public static void main(String[] args) {
        int size=1000;
        ArrayList[] arrayList = new ArrayList[size];
        for (int i = 0; i < size; i++) {
            arrayList[i] = new ArrayList<Integer>();
            for (int j = 0; j < size; j++) {
                arrayList[i].add(100);
            }
        }
        LinkedList[] linkedList = new LinkedList[size];
        for (int i = 0; i < size; i++) {
            linkedList[i] = new LinkedList<Integer>();
            for (int j = 0; j < size; j++) {
                linkedList[i].add(100);
            }
        }
        long a = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            deleteFromArrayList(arrayList[i]);
        }
        System.out.printf("ArrayList - %d\n", System.currentTimeMillis() - a);
        long l = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            deleteFromLinkedList(linkedList[i]);
        }
        System.out.printf("LinkedList - %d", System.currentTimeMillis() - l);
    }

    private static void deleteFromArrayList(ArrayList arrayList) {
        for (int i = 0; i * 2 + 1 < arrayList.size(); i++) {
            arrayList.set(i, arrayList.get(i * 2 + 1));
        }
        int i = arrayList.size();
        arrayList.subList(i / 2, i).clear();
//        arrayList.trimToSize();
    }

    public static void deleteFromLinkedList(LinkedList linkedList) {
        Iterator iterator = linkedList.iterator();
        while (iterator.hasNext()) {
            iterator.next();
            if (iterator.hasNext()) {
                iterator.remove();
            }
            iterator.next();
        }
    }
}
