package lesson08.task2;

public class EnterException extends Exception {
    public void getRussianMessage(){
        System.out.println("Ошибка ввода.\nВведино значение не типа double.");
    }
}
