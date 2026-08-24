package com.maxgagin.charactercompressor;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        
//        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/Fxmltest.fxml")));
//        Parent root = loader.load();
        
        String s = "";
        s = core(s);


        GridPane pane = new GridPane();
        TextArea field = new TextArea();
        field.setText(s);
        pane.add(field, 0, 0);

        Scene scene = new Scene(pane);

        stage.setScene(scene);
        
        stage.setTitle("Symbol compressing!");
        stage.setWidth(250);
        stage.setHeight(200);
        
        stage.show();
        
        
    }

    public static void main(String[] args) {
        launch();
    }
    
    private static String core(String in) throws Exception {
        
        boolean k = false;
        byte[] raw_list = new byte[256];
        short[] the_list = new short[32];
        char[] char_list = new char[32];
        System.in.read(raw_list);
        short i = 0;
        short j = 0;
        
        while (i < 32){
            
            if(raw_list[j+1] == 10) {raw_list[j+1] = 97; k = true;} 
        
            if(raw_list[j] == 32) {raw_list[j] = 124;} //Space to alphabet
            if(raw_list[j+1] == 32) {raw_list[j+1] = 124;}
            
            if(raw_list[j] == 63) {raw_list[j] = 125;} //? to alphabet
            if(raw_list[j+1] == 63) {raw_list[j+1] = 125;}
            
            raw_list[j] = (byte)(raw_list[j] - 97); raw_list[j + 1] = (byte)(raw_list[j + 1] - 97);
            
            the_list[i] = (short)(raw_list[j] + (raw_list[j + 1] * 28));
            
            the_list[i] = (short)(the_list[i] + 162);
            char_list[i] = (char)the_list[i];
            
            System.out.print(char_list[i]);
            
            i++;
            j = (short)(j + 2);
            if(raw_list[j] == 10) {i = 32;}
            if(k == true) {i = 32;}
        }
        System.out.print("\n");
        
        String s = "";
        i = 0;
        while(i < 32){
            s = s + char_list[i];
            i++;
        }
        
        return s;
    }
}