package com.company;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        String surname = "СУРМИН";
        String name = "АЛЕКСЕЙ";
        String together = "СУРМИН АЛЕКСЕЙ";

        System.out.println("Имя: " + name + " Фамилия: " + surname);

        System.out.println("--Простая одинарная перестановка--");
        StringBuilder result = new StringBuilder("");
        result.append(surname.charAt(1));
        result.append(surname.charAt(5));
        result.append(surname.charAt(3));
        result.append(surname.charAt(2));
        result.append(surname.charAt(0));
        result.append(surname.charAt(4));
        System.out.println("Шифрованное сообщение: " + result);
        result = new StringBuilder("");

        System.out.println("\n--Блочная одинарная перестановка--");
        result.append(surname.charAt(2));
        result.append(surname.charAt(0));
        result.append(surname.charAt(1));
        result.append(surname.charAt(5));
        result.append(surname.charAt(3));
        result.append(surname.charAt(4));
        System.out.println("Шифрованное сообщение: " + result);
        result = new StringBuilder("");

        System.out.println("\n--Табличная маршрутная перестановка--");
        System.out.println("Шифрованное сообщение: " + Task3.Encode(together));

        System.out.println("\n--Вертикальной перестановка--");
        System.out.println("Шифрованное сообщение: " + Task4.Encode(together));

        System.out.println("\n--Поворотная решётка--");
        System.out.println("Шифрованное сообщение: " + Task5.Encode(together, 20));

        System.out.println("\n--Магический квадрат --");
        System.out.println("Шифрованное сообщение: " + Task6.Encode(together));

        System.out.println("\n--Двойной перестановки --");
        System.out.println("Шифрованное сообщение: " + Task7.Encode(together));
    }
}

class Task3{
    static String Encode (String input)
    {
        String[][] arr = new String[3][4];
        StringBuilder result = new StringBuilder("");
        int pointer = 0;
        for(int i = 0; i < arr.length; i++) // Формируется таблица слева-направо, сверху-вниз
        {
            for(int j = 0 ; j < arr[0].length; j++)
            {
                if(input.charAt(pointer) != ' ' && input.length()>pointer) {
                    arr[i][j] = String.valueOf(input.charAt(pointer));
                }
                else{
                    arr[i][j] = "_";
                }
                pointer++;
            }
        }
        for(int i = 0; i < arr[0].length; i++)
        {
            for(int j = 0; j < arr.length; j++)
            {
                result.append(arr[j][i]);
            }
        }
        return result.toString();
    }
}

class Task4{
    static String Encode (String input)
    {
        int cols = 0, count = 0;
        char[] skiTestArr = input.replaceAll(" ", "").toCharArray();
        char[] resultArr = new char[input.length()];

        for (int i = 1; i < input.length(); i++) {
            if (input.length() / i == 4) {
                cols = i;
                break;
            }
        }
        for (int i = 0; i < cols; i++) {
            int plus = i;
            for (int j = 0; j < 4; j++) {
                resultArr[count++] += skiTestArr[plus];
                plus += cols;
            }
        }
        return resultArr.toString();
    }
}

class Task5{
    static String Encode(String s, int key){
        StringBuilder res = new StringBuilder();
        for (char c : s.toCharArray()) {
            res.append((char) (c ^ key));
        }
        return res.toString();
    }
}

class Task6{
    static String Encode(String _line){
        String _randomLine = "ЙЦУКЕНГШЩЗХФЪЫВАПРОЛДЖЭЧСМИТЬБЮ";
        Random _rand = new Random();
        int _d = (int)Math.ceil(Math.sqrt(_line.length()));
        if (_d % 2 != 1)
            _d++;
        int[][] _quad = new int[_d][_d];
        for (int j = 0; j < _d; j++)
        {
            for (int i = 0; i < _d; i++)
            {
                _quad[i][j] = _d*(((i + 1) + (j + 1) - 1 + (_d / 2)) % _d)+(((i+1) + 2*(j+1) - 2) % _d) + 1;
            }
        }
        String _cryptedString = "";
        for (int j = 0; j < _d; j++)
        {
            for (int i = 0; i < _d; i++)
            {
                if ((_quad[i][j] - 1) < _line.length())
                {
                    _cryptedString += _line.charAt(_quad[i][j] - 1);
                }
               else
                   {
                       char _randomChar = _randomLine.charAt(_rand.nextInt(_randomLine.length() - 1));
                       _cryptedString += _randomChar;
                   }
            }
        }
        return _cryptedString;
    }
}

class Task7{
    static String Encode (String input)
    {
        String[][] arr = new String[4][4];
        StringBuilder result = new StringBuilder("");
        int pointer = 0;
        String temp;
        for(int i = 0; i < arr.length; i++) // Формируется таблица слева-направо, сверху-вниз
        {
            for(int j = 0 ; j < arr[0].length; j++)
            {
                if(pointer < input.length() && input.charAt(pointer) != ' ') {
                    arr[i][j] = String.valueOf(input.charAt(pointer));
                    pointer++;
                }
                else{
                    arr[i][j] = "_";
                    pointer++;
                }

            }
        }
        // 1 и 3 столбец
        for(int i = 0; i < arr.length; i++)
        {
            temp = arr[i][0];
            arr[i][0] = arr[i][2];
            arr[i][2] = temp;
        }
        // 2 и 4 столбец
        for(int i = 0; i < arr.length; i++)
        {
            temp = arr[i][1];
            arr[i][1] = arr[i][3];
            arr[i][3] = temp;
        }

        //1 и 3 строка
        for(int i = 0;i < arr[0].length; i++)
        {
            temp = arr[0][i];
            arr[0][i] = arr[2][i];
            arr[2][i] = temp;
        }

        for(int i = 0; i < arr[0].length; i++)
        {
            for(int j = 0; j < arr.length; j++)
            {
                result.append(arr[j][i]);
            }
        }
        return result.toString();
    }
}

