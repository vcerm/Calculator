import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Main {
    public static void main(String[] args) throws ScanerException {
        Scanner input = new Scanner(System.in);
        String newString = input.nextLine();
        newString = newString.replaceAll("\\s", "");
        System.out.println(calc(newString));
    }
    public static String calc(String input) throws ScanerException {
        int answer = 0;
        int operation_index;
        int count = 0;
        String[] numbers = input.split("[+-/*]");

        for (int i = 0; i < input.length(); i++){
            if(input.charAt(i) == '+'){
                count++;
            }
            if(input.charAt(i) == '-'){
                count++;
            }
            if(input.charAt(i) == '/'){
                count++;
            }
            if(input.charAt(i) == '*'){
                count++;
            }
        }
        if(count > 1 || input.length() < 3){
            throw new ScanerException("Пользователь ввёл не верное выражение");
        }

        if(input.contains("+")){
            operation_index = input.indexOf('+');
        }
        else if(input.contains("-")){
            operation_index = input.indexOf('-');
        }
        else if(input.contains("*")){
            operation_index = input.indexOf('*');
        }
        else if(input.contains("/")){
            operation_index = input.indexOf('/');
        }
        else {
            throw new ScanerException("Пользователь ввёл неверное арифмитическое действие");
        }

        char operation = input.charAt(operation_index);



        if(!isRomanian(numbers[0]) && !isRomanian(numbers[1])){
            int a = parseInt(numbers[0]);
            int b = parseInt(numbers[1]);

            if(a > 10 || b > 10){
                throw new ScanerException("Пользователь ввёл число(а) больше 10");
            }

            switch (operation) {
                case '+' -> answer = a + b;
                case '-' -> answer = a - b;
                case '*' -> answer = a * b;
                case '/' -> answer = a / b;
                default -> {
                    return null;
                }
            }
            return Integer.toString(answer);
        }
        else if(isRomanian(numbers[0].toUpperCase()) && isRomanian(numbers[1].toUpperCase())){
            int a = RomanianToNumber(numbers[0]);
            int b = RomanianToNumber(numbers[1]);

            switch (operation) {
                case '+' -> answer = a + b;
                case '-' -> answer = a - b;
                case '*' -> answer = a * b;
                case '/' -> answer = a / b;
                default -> {
                    return null;
                }
            }

            if(answer < 1){
                throw new ScanerException("Результат арифмитической операции с римскими цифрами меньше 1");
            }

            return NumToRomanian(answer);
        }
        throw new ScanerException("Пользователь ввёл арабские и римские числа");
    }

    public static String NumToRomanian (int numArabian) {
        String[] roman = {"O", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
                "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
                "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
                "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
                "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
                "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
                "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"
        };
        return roman[numArabian];
    }
    public static boolean isRomanian(String input) {
        return input.contains("I") || input.contains("V") || input.contains("X") || input.contains("L") || input.contains("C") || input.contains("D") || input.contains("M") || input.contains("Х");
    }
    public static int RomanianToNumber(String num) throws ScanerException {
        return switch (num.toUpperCase()) {
            case "I" -> 1;
            case "II" -> 2;
            case "III" -> 3;
            case "IV" -> 4;
            case "V" -> 5;
            case "VI" -> 6;
            case "VII" -> 7;
            case "VIII" -> 8;
            case "IX", "IХ" -> 9;
            case "X", "Х" -> 10;
            default -> throw new ScanerException("Пользователь ввёл число(а) больше X или неверное римское число");

        };
    }
}