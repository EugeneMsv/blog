package com.nast.domain.filters;

/**
 * Фильтр для поиска тегов
 */
public class TagFilter implements Filter {

    private String code;

    /**
     * Код
     */
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}