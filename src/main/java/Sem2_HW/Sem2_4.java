package Sem2_HW;


import java.util.InputMismatchException;
import java.util.Scanner;

//2. Разработайте программу, которая выбросит Exception, когда пользователь вводит пустую строку.
//   Пользователю должно показаться сообщение, что пустые строки вводить нельзя.
public class Sem2_4 {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter words: ");
        String str = scanner.nextLine();
        System.out.println("You enter: " + str);
        if(str.equals("")){
            throw new Exception("OOOPS, nothing entered. Enter something!");
        }
        scanner.close();
    }
}
