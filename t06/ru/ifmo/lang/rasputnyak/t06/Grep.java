package ru.ifmo.lang.rasputnyak.t06;

import java.util.List;

/**
 * Created by Владелец on 22.03.2015.
 */
public interface Grep {

    /**
     * Аналог {@code grep -E regex}
     * @param regex шаблон для поиска
     * @return список строк, которые содержат указанный шаблон
     */
    List<String> findLines(String regex);

    /**
     * Аналог {@code grep -Eo regex}
     * @param regex шаблон для поиска
     * @return список частей строк, которые полностью удовлетворяют шаблону
     */
    List<String> findParts(String regex);

    /**
     * Аналог {@code grep -Ev regex}
     * @param regex шаблон для поиска
     * @return список строк, которые не содержат указанный шаблон
     */
    List<String> findInvertMatch(String regex);

}