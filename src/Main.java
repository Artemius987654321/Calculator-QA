import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        String expression = scan.nextLine();
        System.out.println(parse(expression));
    }

    public static String parse(String expression) throws Exception {
        int num1;
        int num2;
        String[] operands = expression.split(" ");
        if (operands.length < 3) {
            throw new Exception("Не является математической операцией");
        } else if (operands.length > 3) {
            throw new Exception("Введите только 2 числа");
        }
        Roman roman = new Roman();
        boolean check1 = roman.checkRoman(operands[0]);
        boolean check2 = roman.checkRoman(operands[2]);
        if (check1 ^ check2) {
            throw new Exception("Используются разные системы счисления");
        } else if (check1 && check2) {
            operands[0] = roman.romanToArab(operands[0]);
            operands[2] = roman.romanToArab(operands[2]);
        }

        num1 = Integer.parseInt(operands[0]);
        num2 = Integer.parseInt(operands[2]);
        int result = 0;
        if (num1 < 0) throw new Exception("Число должно быть от 1 до 10");
        else if (num1 > 10) throw new Exception("Число должно быть от 1 до 10");
        else if (num2 < 0) throw new Exception("Число должно быть от 1 до 10");
        else if (num2 > 10) throw new Exception("Число должно быть от 1 до 10");

        switch (operands[1]) {
            case ("+"):
                result = num1 + num2;
                break;
            case ("-"):
                result = num1 - num2;
                break;
            case ("*"):
                result = num1 * num2;
                break;
            case ("/"):
                if (num2 == 0)
                    System.out.println("На ноль делить нельзя");
                else {
                    result = num1 / num2;
                }
                break;
            default:
                throw new Exception("Неверная операция");
        }
        if (check1) {
            if (result <=0){
                throw new Exception("Результатом работы калькулятора с римскими числами могут быть только положительные числа");
            }
            return roman.romanNums[result];
        } else {
            return String.valueOf(result);
        }
    }
}

class Roman {
    String[] romanNums = {"",
            "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X",
            "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
            "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX",
            "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
            "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L",
            "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
            "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
            "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
            "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
            "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"
    };

    public boolean checkRoman(String operand) {
        for (String x : romanNums) {
            if (operand.equals(x)) {
                return true;
            }
        }
        return false;
    }

    String romanToArab(String operand) {
        for (int i = 1; i <= 100; i++) {
            if (operand.equals(romanNums[i])) {
                return Integer.toString(i);
            }
        }
        return "";
    }
}