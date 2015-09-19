package db; /**
 * Created by Dennis on 2015-08-15.
 */
import org.h2.Driver;
import java.sql.*;

public class db {
    private static Connection connection;



    private static void openConnection(){

        try {
            connection = DriverManager.getConnection("jdbc:h2:~/personalDictionary3;IFEXISTS=TRUE", "sa", "");
        }catch(Exception e){
            try {
                connection = DriverManager.getConnection("jdbc:h2:~/personalDictionary3;IFEXISTS=FALSE", "sa", "");
                createMyDictionary();
            } catch(Exception f){f.printStackTrace();}
        }



    }


    public static ResultSet runQuery(String sqlString){
      ResultSet resultSet = null;
        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlString);
        }catch (Exception e){e.printStackTrace();}

       return resultSet;
    }

    public static void runCommand(String sqlString){
        try {
            Statement statement = connection.createStatement();
            statement.execute(sqlString);
        }catch (Exception e){e.printStackTrace();}


    }


    public static void main(String[] args){
        openConnection();
        try {
            ResultSet resultSet = runQuery("SELECT MyLang FROM MyLangs;");
            while(resultSet.next()){
            System.out.println(resultSet.getString(1));}
        }catch(Exception e){e.printStackTrace();}
    }


    public static void createMyDictionary(){
        String[] sqlArray =  {"CREATE TABLE MyLangs(ID int, MyLang VarChar(35));",
                              "CREATE TABLE MyWords(ID int, MyLangId int, MyWord VarChar(255));",
                              "CREATE TABLE MyDefs(ID int, MyWordId int, MyLangId int, MyDef VarChar(1000));",
                              "INSERT INTO MyLangs(ID, MyLang) VALUES(1,'English');",
                              "INSERT INTO MyLangs(ID, MyLang) VALUES(2,'Korean');",
                              "INSERT INTO MyWords(ID, MyLangId, MyWord) VALUES(1, 1, 'Cheese');",
                              "INSERT INTO MyWords(ID, MyLangId, MyWord) VALUES(1, 1, 'Hello');"
        };

        try{
            int i=0;
            for (; i < sqlArray.length;i++){
                runCommand(sqlArray[i]);
            }



        }catch(Exception e ){e.printStackTrace();}

    }

}
