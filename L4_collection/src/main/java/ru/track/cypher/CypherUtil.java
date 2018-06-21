package ru.track.cypher;

import java.util.*;

import org.jetbrains.annotations.NotNull;

/**
 * Вспомогательные методы шифрования/дешифрования
 */
public class CypherUtil {

    public static final String SYMBOLS = "abcdefghijklmnopqrstuvwxyz";

    /**
     * Генерирует таблицу подстановки - то есть каждой буква алфавита ставится в соответствие другая буква
     * Не должно быть пересечений (a -> x, b -> x). Маппинг уникальный
     *
     * @return таблицу подстановки шифра
     */
    @NotNull
    public static Map<Character, Character> generateCypher() {
        Map<Character, Character> cypher = new HashMap<>();

        char[] symbolsArray = SYMBOLS.toCharArray();

        List<Character> tmpList = new ArrayList<>();
        for (int i = 0 ; i < symbolsArray.length; i++){
            tmpList.add(symbolsArray[i]);
        }

        Collections.shuffle(tmpList);

        for (int i = 0; i < symbolsArray.length; i++)
        {
            cypher.put(symbolsArray[i], tmpList.get(i));
        }

        return cypher;
    }

}
