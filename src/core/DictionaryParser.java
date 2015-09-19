package core;

import org.jsoup.select.Elements;


/**
 * Functional interface intended to pass functions
 * based on JSoup
 */
public interface DictionaryParser {
    public Elements parse(String html);
}
