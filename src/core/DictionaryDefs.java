package core;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.HashMap;

/**
 * Created by Dennis on 9/18/2015.
 */
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

        dictionaries = new HashMap<String, Dictionary>();
        dictionaries.put("Naver", naver);
        dictionaries.put("Daum-Kor",daumKor);
        dictionaries.put("Daum-Eng",daumEng);
    }

    @Override
    public HashMap<String, Dictionary> getDictionaries(){
        return dictionaries;
    }

}
