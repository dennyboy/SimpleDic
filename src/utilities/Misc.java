package utilities;

import java.awt.event.InputEvent;
import java.net.URLEncoder;

/**
 * Created by Dennis on 9/18/2015.
 */
public class Misc {


    public static Boolean isEnglish(String searchTerm){
        Boolean b = true;
        try{
            String encodedTerm = URLEncoder.encode(searchTerm, "utf-8");
            if(encodedTerm != URLEncoder.encode(searchTerm,"ISO-8859-1")){b=false;}}
        catch(Exception e){e.printStackTrace();}

        return b;
    }

    public static void click(javafx.scene.control.Control control) {
        java.awt.Point originalLocation = java.awt.MouseInfo.getPointerInfo().getLocation();
        javafx.geometry.Point2D buttonLocation = control.localToScreen(control.getLayoutBounds().getMinX(), control.getLayoutBounds().getMinY());
        try {
            java.awt.Robot robot = new java.awt.Robot();
            robot.mouseMove((int)buttonLocation.getX() + 2, (int)buttonLocation.getY() + 2);
            robot.mousePress(InputEvent.BUTTON1_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_MASK);
            robot.mouseMove((int) originalLocation.getX(), (int)originalLocation.getY());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
