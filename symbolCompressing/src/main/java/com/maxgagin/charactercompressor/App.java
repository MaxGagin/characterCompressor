package com.maxgagin.charactercompressor;

import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {
    
    static TextArea area = new TextArea();
    static TextArea field = new TextArea();
    
    @Override
    public void start(Stage stage) throws Exception {
        
//        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/Fxmltest.fxml")));
//        Parent root = loader.load();
        

        GridPane pane = new GridPane();
//        TextArea field = new TextArea();
        
        Button button = new Button();
        VBox btnBox = new VBox();
        
        btnBox.getChildren().add(button);
        btnBox.setAlignment(Pos.TOP_LEFT);
        btnBox.setPadding(new Insets(10, 10, 0, 10));
        
        field.setMaxWidth(250); field.setMaxHeight(200);
        area.setMaxWidth(250); area.setMaxHeight(200);
        
        pane.add(field, 0, 0);
        pane.add(btnBox, 260, 0);
        pane.add(area, 280, 0);
        
        
        
        Scene scene = new Scene(pane);

        stage.setScene(scene);
        
        stage.setTitle("Character compressing!");
        stage.setWidth(600);
        stage.setHeight(220);
        
        stage.show();
        
//        area.setText("hello privet");
        button.setOnAction((ActionEvent event) -> {
            try {core();}
            catch (Exception ex) {ex.printStackTrace();}
        });
        
    }

    public static void main(String[] args) {
        launch();
    }
    
    private static void core() throws Exception {
        
        boolean k = false;
        List raw_list = new ArrayList();
        short[] the_list = new short[32];
        char[] char_list = new char[32];
        short i = 0;
        short j = 0;
        short m = 0;
        short n;
        
        String raw_s = area.getText();
        System.out.println("строка: " + raw_s);
        byte[] megaraw_list = raw_s.getBytes();
        while (m < megaraw_list.length) {
            raw_list.add(m, megaraw_list[m]);
            
            m++;
        }
        
//        while (i < raw_list.length) {
//            System.out.println(raw_list[i]);
//            i++;
//        }

        n = (short)megaraw_list.length;
        System.out.println((byte)raw_list.get(0));
        raw_list.set(0, (byte)65);
        System.out.println((byte)raw_list.get(0));
        System.out.print("\n");
        System.out.println("hello world!" + n);
        
        while (j < n){
            try {raw_list.get(j+1);} catch(Exception ex){raw_list.add(j+1, (byte)0);}
            
            
            System.out.println("   " + raw_list.get(j) + "   " + raw_list.get(j+1));
            if((byte)raw_list.get(j) == 10) {raw_list.set(j+1, (byte)97); k = true;} 
            System.out.println("flag0");
            if((byte)raw_list.get(j) == 32) {raw_list.set(j, (byte)124);} //Space to alphabet
            System.out.println("flag1");
            if((byte)raw_list.get(j+1) == 32) {raw_list.set(j+1, (byte)124);}
            System.out.println("flag2");
            if((byte)raw_list.get(j) == 63) {raw_list.set(j, (byte)125);} //? to alphabet
            System.out.println("flag3");
            if((byte)raw_list.get(j+1) == 63) {raw_list.set(j+1, (byte)125);}
            
            System.out.println("FLAG1, j: " + j + " i: " + i);
            
            raw_list.set(j, (byte)((byte)raw_list.get(j) - 97)); raw_list.set(j+1, (byte)((byte)raw_list.get(j+1) - 97));
            
            the_list[i] = (short)((byte)raw_list.get(j) + ((byte)raw_list.get(j+1) * 28));
            
            the_list[i] = (short)(the_list[i] + 162);
            char_list[i] = (char)the_list[i];
            
            System.out.println("FLAG2, j: " + j + " i: " + i);
            System.out.print(char_list[i]);
            
            i++;
            j = (short)(j + 2);
//            if((byte)raw_list.get(j) == 10) {i = n;}
            if(k == true) {i = n;}
            
            System.out.println("FLAG3, j: " + j + " i: " + i);
            System.out.println("postFLAG");
        }
        System.out.print("\n FLAG4 \n");
        
        String s = "";
        i = 0;
        while(i < n){
            s = s + char_list[i];
            i++;
        }
        
        field.setText(s);
    }
}