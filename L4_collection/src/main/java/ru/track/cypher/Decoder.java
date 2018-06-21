package ru.track.cypher;

import java.util.LinkedHashMap;
import java.util.*;

import org.jetbrains.annotations.NotNull;

public class Decoder {

    // Расстояние между A-Z -> a-z
    public static final int SYMBOL_DIST = 32;

    private Map<Character, Character> cypher;

    /**
     * Конструктор строит гистограммы открытого домена и зашифрованного домена
     * Сортирует буквы в соответствие с их частотой и создает обратный шифр Map<Character, Character>
     *
     * @param domain - текст по кторому строим гистограмму языка
     */
    public Decoder(@NotNull String domain, @NotNull String encryptedDomain) {
        Map<Character, Integer> domainHist = createHist(domain);
        Map<Character, Integer> encryptedDomainHist = createHist(encryptedDomain);

        cypher = new LinkedHashMap<>();

        Iterator<Character> domainIterator = domainHist.keySet().iterator();
        Iterator<Character> encryptedIterator = encryptedDomainHist.keySet().iterator();

        while (encryptedIterator.hasNext()) {
            char encryptedCh = encryptedIterator.next();
            char domainCh = domainIterator.next();
            cypher.put(encryptedCh, domainCh);

        }
    }

    public Map<Character, Character> getCypher() {
        return cypher;
    }

    /**
     * Применяет построенный шифр для расшифровки текста
     *
     * @param encoded зашифрованный текст
     * @return расшифровка
     */
    @NotNull
    public String decode(@NotNull String encoded) {
        StringBuilder decoded = new StringBuilder();
        encoded = encoded.toLowerCase();
        for (int i = 0; i < encoded.length(); i++){
            char c = encoded.charAt(i);
            if (Character.isLetter(c)){
                decoded.append(cypher.get(c));
            }
            else{
                decoded.append(c);
            }
        }
        return decoded.toString();
    }

    /**
     * Считывает входной текст посимвольно, буквы сохраняет в мапу.
     * Большие буквы приводит к маленьким
     *
     *
     * @param text - входной текст
     * @return - мапа с частотой вхождения каждой буквы (Ключ - буква в нижнем регистре)
     * Мапа отсортирована по частоте. При итерировании на первой позиции наиболее частая буква
     */
    @NotNull
    Map<Character, Integer> createHist(@NotNull String text) {
        Map<Character, Integer> hist = new HashMap<>();
        text = text.toLowerCase();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (Character.isLetter(c)) {
                if (hist.containsKey(c)) {
                    int val = hist.get(c);
                    hist.put(c, val + 1);
                } else {
                    hist.put(c, 1);
                }
            }
        }

        List<Map.Entry<Character, Integer>> entries = new ArrayList<>(hist.entrySet());
        entries.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));

        Map<Character, Integer> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<Character, Integer> entry : entries) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }

}
