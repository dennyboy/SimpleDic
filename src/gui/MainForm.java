package gui;

import core.Dictionary;
import core.RemoteDictionaryDefsLoader;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import utilities.Misc;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;


public class MainForm extends Application{

    private HashMap<String, Dictionary> dictionaries;
    private Iterator<String> dictionaryKeys;
    private TextField searchBox;
    private Button searchButton;
    private TextArea resultsBox;
    private ComboBox dictionarySelector;


    @Override
    public void start(Stage primaryStage) throws Exception {
        dictionaries = new RemoteDictionaryDefsLoader().getDictionaries();

        primaryStage.setScene(getMainScene());
        primaryStage.setTitle("SimpleDic");
        primaryStage.getIcons().add(new Image("icon.PNG"));
        primaryStage.show();


    }



    private Scene getMainScene(){
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.TOP_LEFT);
        gridPane.setVgap(3);
        gridPane.setHgap(3);
        gridPane.setStyle("-fx-font: 16px \"Times New Roman\";");

        dictionarySelector = getDictionarySelector();
        dictionarySelector.setPrefSize(125,25);

        searchButton = new Button("Search");
        searchButton.setPrefSize(100, 25);
        searchButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (event)->search());

        searchBox = new TextField();
        searchBox.setPrefSize(300,25);
        searchBox.addEventHandler(KeyEvent.KEY_PRESSED,(keyPressed)->
            {if(keyPressed.getCode().equals(KeyCode.ENTER)){Misc.click(searchButton);}});

        resultsBox = new TextArea();
        resultsBox.setPrefSize(525,400);

        gridPane.add(searchBox,0,0);
        gridPane.add(searchButton,1,0);
        gridPane.add(dictionarySelector,2,0);
        gridPane.add(resultsBox, 0, 1);
        gridPane.setColumnSpan(resultsBox,3);

        Scene scene = new Scene(gridPane,525,450);
        return scene;
    }



    public void search(){
        String searchTerm="";
        try{
        searchTerm = URLEncoder.encode(searchBox.getText(),"UTF-8");}
        catch(UnsupportedEncodingException e){e.printStackTrace();}
        Iterator<String> results;
        String result = "";

        results = dictionaries.get(dictionarySelector.getValue()).search(searchTerm).iterator();
        while(results.hasNext()){
        result += results.next() + "\n";}
        resultsBox.setText(result);

    }

    private ComboBox<String> getDictionarySelector(){
        ComboBox<String> comboBox = new ComboBox<>();
        try {
        dictionaryKeys = dictionaries.keySet().iterator();
        while (dictionaryKeys.hasNext()) {
            comboBox.getItems().add(dictionaryKeys.next());
        }
        comboBox.setValue(dictionaries.keySet().iterator().next());
        }catch(Exception e){e.printStackTrace();}

        return comboBox;
    }


}
