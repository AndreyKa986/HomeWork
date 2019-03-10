package lesson14.task1;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Reflet {
    public static void main(String[] args) {
        Clazz clazz=new Clazz();
        Class myClass=clazz.getClass();
        try {
            Field stringField=myClass.getDeclaredField("string");
            stringField.setAccessible(true);
            stringField.set(clazz,"some string");
            Field intField=myClass.getDeclaredField("intNumber");
            intField.setAccessible(true);
            intField.set(clazz,13);
            Field floatField=myClass.getDeclaredField("floatNumber");
            floatField.setAccessible(true);
            floatField.set(clazz,46.6f);
            Method getString=myClass.getMethod("getString");
            Method getInt=myClass.getMethod("getIntNumber");
            Method getFloat=myClass.getMethod("getFloatNumber");
            System.out.println(getString.invoke(clazz));
            System.out.println(getInt.invoke(clazz));
            System.out.println(getFloat.invoke(clazz));
        } catch (NoSuchFieldException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
