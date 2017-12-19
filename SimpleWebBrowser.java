package simplewebbrowser;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class SimpleWebBrowser {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
	final JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	frame.setSize(600,400);
        final JFXPanel fxpanel = new JFXPanel();
        frame.add(fxpanel);
        Platform.runLater(new Runnable() {
        public void run() {
            WebEngine engine;
            WebView wv = new WebView();
            engine = wv.getEngine();
            fxpanel.setScene(new Scene(wv));
            engine.load("http://www.google.com");
	 } 
    }); 
    frame.setVisible(true);
    }
    
}
