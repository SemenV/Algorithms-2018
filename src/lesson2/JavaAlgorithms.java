package lesson2;

import kotlin.NotImplementedError;
import kotlin.Pair;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

@SuppressWarnings("unused")
public class JavaAlgorithms {
    /**
     * Получение наибольшей прибыли (она же -- поиск максимального подмассива)
     * Простая
     *
     * Во входном файле с именем inputName перечислены цены на акции компании в различные (возрастающие) моменты времени
     * (каждая цена идёт с новой строки). Цена -- это целое положительное число. Пример:
     *
     * 201
     * 196
     * 190
     * 198
     * 187
     * 194
     * 193
     * 185
     *
     * Выбрать два момента времени, первый из них для покупки акций, а второй для продажи, с тем, чтобы разница
     * между ценой продажи и ценой покупки была максимально большой. Второй момент должен быть раньше первого.
     * Вернуть пару из двух моментов.
     * Каждый момент обозначается целым числом -- номер строки во входном файле, нумерация с единицы.
     * Например, для приведённого выше файла результат должен быть Pair(3, 4)
     *
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */
    static public Pair<Integer, Integer> optimizeBuyAndSell(String inputName) {
        throw new NotImplementedError();
    }

    /**
     * Задача Иосифа Флафия.
     * Простая
     *
     * Образовав круг, стоят menNumber человек, пронумерованных от 1 до menNumber.
     *
     * 1 2 3
     * 8   4
     * 7 6 5
     *
     * Мы считаем от 1 до choiceInterval (например, до 5), начиная с 1-го человека по кругу.
     * Человек, на котором остановился счёт, выбывает.
     *
     * 1 2 3
     * 8   4
     * 7 6 х
     *
     * Далее счёт продолжается со следующего человека, также от 1 до choiceInterval.
     * Выбывшие при счёте пропускаются, и человек, на котором остановился счёт, выбывает.
     *
     * 1 х 3
     * 8   4
     * 7 6 Х
     *
     * Процедура повторяется, пока не останется один человек. Требуется вернуть его номер (в данном случае 3).
     *
     * 1 Х 3
     * х   4
     * 7 6 Х
     *
     * 1 Х 3
     * Х   4
     * х 6 Х
     *
     * х Х 3
     * Х   4
     * Х 6 Х
     *
     * Х Х 3
     * Х   х
     * Х 6 Х
     *
     * Х Х 3
     * Х   Х
     * Х х Х
     */
    static public int josephTask(int menNumber, int choiceInterval) {
        throw new NotImplementedError();
    }

    /**
     * Наибольшая общая подстрока.
     * Средняя
     *
     * Дано две строки, например ОБСЕРВАТОРИЯ и КОНСЕРВАТОРЫ.
     * Найти их самую длинную общую подстроку -- в примере это СЕРВАТОР.
     * Если общих подстрок нет, вернуть пустую строку.
     * При сравнении подстрок, регистр символов *имеет* значение.
     * Если имеется несколько самых длинных общих подстрок одной длины,
     * вернуть ту из них, которая встречается раньше в строке first.
     */
    static public String longestCommonSubstring(String first, String second) {
        String result = "";
        for (int i = 0; i < first.length(); i++) {
            for (int j = 0; j < second.length(); j++) {
                if (first.charAt(i) == second.charAt(j)) {
                    StringBuilder pseudoResult = new StringBuilder();
                    //check diagonal
                    for (int k = 0; i + k < first.length() && j + k < second.length() && first.charAt(i + k) == second.charAt(j + k); k++) {
                        pseudoResult.append(first.charAt(i + k));
                    }
                    if (pseudoResult.length() > result.length()) {
                        result = pseudoResult.toString();
                    }
                }
            }
        }
        return result;
    }

    /**
     * Число простых чисел в интервале
     * Простая
     *
     * Рассчитать количество простых чисел в интервале от 1 до limit (включительно).
     * Если limit <= 1, вернуть результат 0.
     *
     * Справка: простым считается число, которое делится нацело только на 1 и на себя.
     * Единица простым числом не считается.
     */
    static public int calcPrimesNumber(int limit) {
        throw new NotImplementedError();
    }

    /**
     * Балда
     * Сложная
     *
     * В файле с именем inputName задана матрица из букв в следующем формате
     * (отдельные буквы в ряду разделены пробелами):
     *
     * И Т Ы Н
     * К Р А Н
     * А К В А
     *
     * В аргументе words содержится множество слов для поиска, например,
     * ТРАВА, КРАН, АКВА, НАРТЫ, РАК.
     *
     * Попытаться найти каждое из слов в матрице букв, используя правила игры БАЛДА,
     * и вернуть множество найденных слов. В данном случае:
     * ТРАВА, КРАН, АКВА, НАРТЫ
     *
     * И т Ы Н     И т ы Н
     * К р а Н     К р а н
     * А К в а     А К В А
     *
     * Все слова и буквы -- русские или английские, прописные.
     * В файле буквы разделены пробелами, строки -- переносами строк.
     * Остальные символы ни в файле, ни в словах не допускаются.
     */
    static public Set<String> baldaSearcher(String inputName, Set<String> words) {
        try {
            Scanner read = new Scanner(new InputStreamReader(new FileInputStream(new File(inputName))));
            List<List<String>> field = new ArrayList<>();

            while (read.hasNext()) {
                List k = Arrays.asList(read.nextLine().split(" "));
                System.out.println(k);
                field.add(k);
            }

            Set wordsExist = new HashSet();
            for (String word : words) {
                for (int i = 0; i < field.size(); i++) {
                    List<String> currentArr = field.get(i);
                    for (int j = 0; j < currentArr.size(); j++) {
                        if (word.startsWith(field.get(i).get(j))) {
                            if (search(i, j, word, field)) wordsExist.add(word);
                        }
                    }
                }
            }
            return wordsExist;
        } catch (IOException e) {
            System.out.println("IOException");
        }
        return new TreeSet();
    }

    static public boolean search(int i, int j, String word, List<List<String>> field) {
        List<String> wordArr = Arrays.asList(word.split(""));
        HashSet<Pair<Integer, Integer>> positions = new HashSet();
        positions.add(new Pair<>(i, j));

        HashSet<Pair<Integer, Integer>> newPos = new HashSet<>();
        for (int k = 1; k < word.length(); k++) {
            int currK = k;
            newPos.clear();
            for (Pair<Integer, Integer> elem : positions) {
                newPos.addAll(getSteps(elem, field.size(), field.get(0).size()));
            }

            Iterator iter =  newPos.iterator();
            while (iter.hasNext()){
                Pair<Integer, Integer>  elem = (Pair<Integer, Integer>) iter.next();
                String elemInField = field.get(elem.getFirst()).get(elem.getSecond());
                if (!elemInField.equals(word.charAt(currK) + "")) iter.remove();
                if (currK == word.length() - 1 &&
                        elemInField.equals(word.charAt(word.length() - 1) + "")) return true;
            }
            positions.clear();
            positions.addAll(newPos);
        }
        return false;
    }


    static public Set<Pair<Integer, Integer>> getSteps(Pair<Integer, Integer> e, Integer limitH, Integer limitW) {
        Set<Pair<Integer, Integer>> end = new HashSet<>();
        end.add(new Pair(e.getFirst() + 1, e.getSecond()));
        end.add(new Pair(e.getFirst() - 1, e.getSecond()));
        end.add(new Pair(e.getFirst(), e.getSecond() + 1));
        end.add(new Pair(e.getFirst(), e.getSecond() - 1));
        end.removeIf(k -> !isInside(k, limitH, limitW));
        return end;
    }

    static public boolean isInside(Pair<Integer, Integer> e, Integer limitH, Integer limitW) {
        return e.getFirst() < limitH   && e.getSecond() < limitW  && e.getFirst() >= 0 && e.getSecond() >= 0;
    }
}