import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args)  {
        Random random = new Random();
        int p = random.nextInt(3);
        int v = random.nextInt(3);
        String[] choices = {"Камень", "Ножницы", "Бумагу"};
        String schoiceNamep = choices[p];
        String schoiceNamev = choices[v];

        System.out.println("Петя выкинул " + schoiceNamep);
        System.out.println("Вася выкинул " + schoiceNamev);

        if (p == v) {
            System.out.println("Ничья");
        } else if ((p == 0 && v == 1)||(p == 1 && v == 2)||(p == 2 && v == 0)){
            System.out.println("Петя победил!");
        } else {
            System.out.println("Вася победил!");
        }
    }
}
