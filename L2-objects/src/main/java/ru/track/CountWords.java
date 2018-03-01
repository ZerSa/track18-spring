package ru.track;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;


/**
 * Задание 1: Реализовать два метода
 *
 * Формат файла: текстовый, на каждой его строке есть (или/или)
 * - целое число (int)
 * - текстовая строка
 * - пустая строка (пробелы)
 *
 * Числа складываем, строки соединяем через пробел, пустые строки пропускаем
 *
 *
 * Пример файла - words.txt в корне проекта
 *
 * ******************************************************************************************
 *  Пожалуйста, не меняйте сигнатуры методов! (название, аргументы, возвращаемое значение)
 *
 *  Можно дописывать новый код - вспомогательные методы, конструкторы, поля
 *
 * ******************************************************************************************
 *
 */
public class CountWords {

    String skipWord;

    public CountWords(String skipWord) {
        this.skipWord = skipWord;
    }

    /**
     * Метод на вход принимает объект File, изначально сумма = 0
     * Нужно пройти по всем строкам файла, и если в строке стоит целое число,
     * то надо добавить это число к сумме
     * @param file - файл с данными
     * @return - целое число - сумма всех чисел из файла
     */
    public static void main(String[] args) throws Exception{
        CountWords cw = new CountWords("skip");
        File src = new File("/home/zersa/Study/tehnotrek/java/git/track18-spring/L2-objects/words.txt");
        System.out.println(cw.concatWords(src));
        //cw.concatWords(src);
    }

    public long countNumbers(File file) throws Exception {
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        long result = 0;
        while(true){
            String line = bufferedReader.readLine();
            if (line == null){
                break;
            }
            if (line.equals(this.skipWord)) {
                continue;
            }
            if (StringUtils.isNumeric(line)) {
                result = result + Integer.parseInt(line);
            }
        }

        return result;
    }

    public String concatWords(File file) throws Exception {
        StringBuilder result = new StringBuilder("");
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        while(true){
             String line = bufferedReader.readLine();
            if (line == null){
                break;
            }
            if (line.equals(this.skipWord)){
                continue;
            }
            if (StringUtils.isNumeric(line)) {
                continue;
            }
            result = result.append(line);
            result = result.append(" ");

        }
        return result.toString();
    }


    /**
     * Метод на вход принимает объект File, изначально результат= ""
     * Нужно пройти по всем строкам файла, и если в строка не пустая и не число
     * то надо присоединить ее к результату через пробел
     * @param file - файл с данными
     * @return - результирующая строка
     */

}

