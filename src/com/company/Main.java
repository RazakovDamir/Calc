package com.company;
import java.util.*;

public class Main {

    public static void main(String[] args) {
	    Scanner sc = new Scanner(System.in);
        String decimals = "012345678910";
        String operators = "-+/*";
        String roma = "IIIVIIIX";

        while (true) {
            String[] input = sc.nextLine().toUpperCase().split("\\s+");
            //Если можно избежать использования Java Class Exception, то лучше не использовать исключения, а учесть все граничные условия. Но если они необходимы, могу добавить их в код.
            if (input.length != 3) {
                System.out.println("Error: Wrong input. Try Again.");
                System.exit(0);
            }
            //Проверка ввода: арабские или римские числа. Перенаправление на соответствующий метод Calc.
            if (decimals.contains(input[0]) && operators.contains(input[1]) && decimals.contains(input[2])) {
                Decimal d = new Decimal(input[0], input[1], input[2]);
                d.calc(input[0], input[1], input[2]);
            } else if (roma.contains(input[0]) && operators.contains(input[1]) && roma.contains(input[2])) {
                Roma r = new Roma(input[0], input[1], input[2]);
                r.calc(input[0], input[1], input[2]);
            } else {
                System.out.println("Error: Wrong input. Try Again.");
                System.exit(0);
            }
        }
    }
}


class Decimal extends Main {
    String first;
    String second;
    String operator;

    public Decimal(String first, String operator, String second) {
        this.first = first;
        this.operator = operator;
        this.second = second;
    }
    protected void calc (String first, String operator, String second) {
        switch (operator) {
            case "+" -> System.out.println(Integer.parseInt(first) + Integer.parseInt(second));
            case "-" -> System.out.println(Integer.parseInt(first) - Integer.parseInt(second));
            case "*" -> System.out.println(Integer.parseInt(first) * Integer.parseInt(second));
            case "/" -> {
                //Так как ответ может быть любым, то я учел случаи, где число в ответе будет дробным или целым.
                if (Integer.parseInt(first) % Integer.parseInt(second) > 0) {
                    System.out.println(Float.parseFloat(first) / Float.parseFloat(second));
                } else {
                    System.out.println((Integer.parseInt(first) / Integer.parseInt(second)));
                }
            }
        }

    }

}

class Roma extends Main {
    String first;
    String second;
    String operator;
    int f;
    int s;
    int ans;

    public Roma (String first, String second, String operator) {
        this.first = first;
        this.operator = operator;
        this.second = second;
    }

    protected void calc (String first, String operator, String second) {

        //Переводим в арабские.
        switch (first) {
            case "I" -> f = 1;
            case "II" -> f = 2;
            case "III" -> f = 3;
            case "IV" -> f = 4;
            case "V" -> f = 5;
            case "VI" -> f = 6;
            case "VII" -> f = 7;
            case "VIII" -> f = 8;
            case "IX" -> f = 9;
            case "X" -> f = 10;
        }
        switch (second) {
            case "I" -> s = 1;
            case "II" -> s = 2;
            case "III" -> s = 3;
            case "IV" -> s = 4;
            case "V" -> s = 5;
            case "VI" -> s = 6;
            case "VII" -> s = 7;
            case "VIII" -> s = 8;
            case "IX" -> s = 9;
            case "X" -> s = 10;
        }
        //Вычисляем ответ в арабских числах.
        switch (operator) {
            case "+" -> ans = f + s;
            case "-" -> ans = f - s;
            case "*" -> ans = f * s;
            case "/" -> ans = f / s;
        }

        //Переводим ответ обратно в римские.
        if (ans < 1) {
            System.out.println("Error: Roman numbers haven't zero and negative numbers");
            System.exit(0);
        }

        else if (ans <= 10) {
            switch (ans) {
                case 1 -> System.out.println("I");
                case 2 -> System.out.println("II");
                case 3 -> System.out.println("III");
                case 4 -> System.out.println("IV");
                case 5 -> System.out.println("V");
                case 6 -> System.out.println("VI");
                case 7 -> System.out.println("VII");
                case 8 -> System.out.println("VIII");
                case 9 -> System.out.println("IX");
                case 10 -> System.out.println("X");
            }
        } else if (ans < 50) {
            StringBuilder output = new StringBuilder();
            switch (ans / 10) {
                case 1 -> output.append("X");
                case 2 -> output.append("XX");
                case 3 -> output.append("XXX");
                case 4 -> output.append("XL");
            }
            int m = ans % 10;
            switch (m) {
                case 1 -> output.append("I");
                case 2 -> output.append("II");
                case 3 -> output.append("III");
                case 4 -> output.append("IV");
                case 5 -> output.append("V");
                case 6 -> output.append("VI");
                case 7 -> output.append("VII");
                case 8 -> output.append("VIII");
                case 9 -> output.append("IX");
            }
            System.out.println(output);
        } else if (ans <= 100) {
            StringBuilder output = new StringBuilder();
            switch (ans / 10) {
                case 5 -> output.append("L");
                case 6 -> output.append("LX");
                case 7 -> output.append("LXX");
                case 8 -> output.append("LXXX");
                case 9 -> output.append("CX");
                case 10 -> output.append("C");
            }
            int m = ans % 10;
            switch (m) {
                case 1 -> output.append("I");
                case 2 -> output.append("II");
                case 3 -> output.append("III");
                case 4 -> output.append("IV");
                case 5 -> output.append("V");
                case 6 -> output.append("VI");
                case 7 -> output.append("VII");
                case 8 -> output.append("VIII");
                case 9 -> output.append("IX");
            }
            System.out.println(output);
        }
    }
}