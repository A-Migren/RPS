import java.util.Scanner;

public class two {
    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        if(input.length() > 106){
            System.out.println("Длина строки превышаает доступный предел в 106 символов.");
        }

        for (char c : input.toCharArray()) {
            if (c != '<' && c != '>' && c != '-') {
                System.out.println("Некорректный символ: " + c);
                // Этот цикл создает массив на основе введеного пользователем текста и проверяет каждый символ в этом массиве. Если символ не соответствует доступным нам по задаче - выводит каждый неверный символ на экран.
            }
        }

        int quantity = 0; // создаем переменную, которая будет через цикл считать количество < и >
        for (char c : input.toCharArray()) {
            if (c == '<' || c == '>') {
                quantity++;
            }
        }
        System.out.println("Количество стрелок составляет: " +quantity);


    }
}
