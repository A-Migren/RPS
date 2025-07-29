import java.util.Scanner;

public class one {
    public static void main(String[] args) {
        symbols symbols = new symbols();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Выберите вашу букву: ");
        String spectatorSymbol = scanner.nextLine();


        if (spectatorSymbol.length() != 1) {
            System.out.println("Пожалуйста, введите одну букву.");
            return;
        }

        char inputChar = spectatorSymbol.charAt(0);
        int index = -1;

        for (int i = 0; i < symbols.mySymbols.length; i++) {
            if (symbols.mySymbols[i] == inputChar) {
                index = i;
                break;
            }
        }


            if (index == -1) {
                // Буква не найдена
                System.out.println("Буква не найдена в массиве.");
            } else {
                // Получение следующей буквы
                int prevIndex = (index - 1 + symbols.mySymbols.length) % symbols.mySymbols.length;
                char prevLetter = symbols.mySymbols[prevIndex];
                System.out.println("Предыдущая буква: " + prevLetter);
            }


        }
    }


    class symbols {
        char[] mySymbols = new char[26];

        public symbols() {

            mySymbols[0] = 'q';
            mySymbols[1] = 'w';
            mySymbols[2] = 'e';
            mySymbols[3] = 'r';
            mySymbols[4] = 't';
            mySymbols[5] = 'y';
            mySymbols[6] = 'u';
            mySymbols[7] = 'i';
            mySymbols[8] = 'o';
            mySymbols[9] = 'p';
            mySymbols[10] = 'a';
            mySymbols[11] = 's';
            mySymbols[12] = 'd';
            mySymbols[13] = 'f';
            mySymbols[14] = 'g';
            mySymbols[15] = 'h';
            mySymbols[16] = 'j';
            mySymbols[17] = 'k';
            mySymbols[18] = 'l';
            mySymbols[19] = 'z';
            mySymbols[20] = 'x';
            mySymbols[21] = 'c';
            mySymbols[22] = 'v';
            mySymbols[23] = 'b';
            mySymbols[24] = 'n';
            mySymbols[25] = 'm';


        }
    }

