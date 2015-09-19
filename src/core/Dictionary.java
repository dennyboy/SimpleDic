package core;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import utilities.HttpHandler;
import java.util.ArrayList;
import java.util.List;


public class Dictionary{
    protected String URLsubString1;
    protected String URLsubString2;
    protected DictionaryParser dictionaryParser;

    public void setDictionaryParser(DictionaryParser dictionaryParser){
        this.dictionaryParser = dictionaryParser;
    }

    public Dictionary(String URLsubString1, String URLsubString2){
        this.URLsubString1 = URLsubString1;
        this.URLsubString2 = URLsubString2;
    }

    public List<String> search(String searchTerm){
        List<String> list = new ArrayList<String>();
        String url = URLsubString1 + searchTerm + URLsubString2;
        System.out.println(url);
        String html = HttpHandler.executeGet(url);
        Elements elements = dictionaryParser.parse(html);
        for(Element element: elements){
            list.add(element.text());
        }
        return list;
    }
}
