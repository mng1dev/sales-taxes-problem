package com.vektor.salestaxes.category.parser;

import java.util.Map;

public interface CategoryParser {
    public Map<String, String> parse(String filePath);
}
