package Sem2_HW;

import java.util.InputMismatchException;
import java.util.Scanner;


//1. Реализуйте метод, который запрашивает у пользователя ввод дробного числа (типа float),
//   и возвращает введенное значение. Ввод текста вместо числа не должно приводить к падению приложения,
//   вместо этого, необходимо повторно запросить у пользователя ввод данных.
public class Sem2_1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Enter float number: ");
            float num = scanner.nextFloat();
            System.out.println("You enter: " + num);
        }catch(InputMismatchException e){
            System.out.println("Mistake. Enter FLOAT number!");
        }
        scanner.close();
    }

}
