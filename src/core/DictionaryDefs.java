package core;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.util.HashMap;

public class DictionaryDefs extends RemoteDictionaryDefs {
    private HashMap<String,Dictionary> dictionaries;

    public DictionaryDefs() {
        Dictionary naver = new Dictionary("http://m.endic.naver.com/search.nhn?query=", "&searchOption=entryIdiom&preQuery=&forceRedirect=");
        naver.setDictionaryParser((html) ->
        {Document document = Jsoup.parse(html);
         return document.getElementsByClass("desc");});

        Dictionary daumKor = new Dictionary("http://m.dic.daum.net/search.do?q=", "&search_first=Y&t=word&dic=eng");
        daumKor.setDictionaryParser((html) ->
        {Document document = Jsoup.parse(html);
         return document.getElementsByClass("txt_means_KUEK");});

        Dictionary daumEng = new Dictionary("http://m.dic.daum.net/search.do?q=", "&search_first=Y&t=word&dic=eng");
        daumEng.setDictionaryParser((html) ->
        {Document document = Jsoup.parse(html);
            return document.getElementsByClass("txt_means_KUKE");});

        Dictionary cambKor = new Dictionary("http://dictionary.cambridge.org/dictionary/english-korean/","");
        cambKor.setDictionaryParser((html)->
        {Document document = Jsoup.parse(html);
            return document.getElementsByClass("trans");});


        dictionaries = new HashMap<String, Dictionary>();
        dictionaries.put("Naver", naver);
        dictionaries.put("Daum-Kor",daumKor);
        dictionaries.put("Daum-Eng",daumEng);
        dictionaries.put("Camb-Kor",cambKor);
    }

    @Override
    public HashMap<String, Dictionary> getDictionaries(){
        return dictionaries;
    }

}
