package core;

import core.Dictionary;
import core.DictionaryDefs;

import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by Dennis on 9/19/2015.
 */
public class RemoteDictionariesLoader extends ClassLoader {

    final public static String REMOTE_CLASS = "https://github.com/dennyboy/SimpleDic/blob/master/remote/DictionaryDefs.class?raw=true";


    public HashMap<String, Dictionary> getDictionaries() {
        HashMap<String, Dictionary> dictionaries;
        try {
            URL url = new URL(REMOTE_CLASS);
            InputStream inputStream = url.openStream();
            byte rawBytes[] = new byte[inputStream.available()];

            inputStream.read(rawBytes);
            Class<?> regeneratedClass = this.defineClass("core.DictionaryDefs",rawBytes, 0, rawBytes.length);

             dictionaries = (HashMap) regeneratedClass.getMethod("getDictionaries",null).invoke(regeneratedClass.newInstance(),null);

        } catch (Exception e) {
            DictionaryDefs dictionaryDefs= new DictionaryDefs();
            dictionaries = dictionaryDefs.getDictionaries();
            e.printStackTrace();
        }

        return dictionaries;
    }

}
