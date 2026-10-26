package com.maxgagin.charactercompressor;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class App extends Application {
    

    
    @Override
    public void start(Stage stage) throws Exception {
        
        HashMap<Integer, Integer> map = new HashMap<>();
        
        InputStream stream = App.class.getClassLoader().getResourceAsStream("config.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8));
        
        int v, w;
        String[] x;
        String z;
        while (true) {
            z = reader.readLine();
            try {x = z.split(" ");} catch(Exception ex) {break;}
            v = Integer.parseInt(x[0]);
            w = Integer.parseInt(x[1]);
            System.out.println(v); System.out.println(w);
            map.put(v, w);
        }
        
//        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/Fxmltest.fxml")));
//        Parent root = loader.load();
        TextArea area = new TextArea();
        TextArea field = new TextArea();

        GridPane pane = new GridPane();
        
        Button button = new Button();
        VBox btnBox = new VBox();
        
        ToggleButton toggle = new ToggleButton();
        HBox togBox = new HBox();
        
        Label label = new Label();
        label.setText(" -- uncompress characters");
        label.setAlignment(Pos.CENTER_RIGHT);
        label.setPadding(new Insets(5, 0, 0, 5));
        
        btnBox.getChildren().add(button);
        btnBox.setAlignment(Pos.TOP_LEFT);
        btnBox.setPadding(new Insets(10, 10, 0, 10));
        
//        togBox.setMinWidth(100);
        togBox.setMaxHeight(20);
        togBox.getChildren().add(toggle);
        togBox.getChildren().add(label);
        togBox.setAlignment(Pos.TOP_LEFT);
        togBox.setPadding(new Insets(10, 0, 0, 0));
        
        
        field.setMaxWidth(250); field.setMaxHeight(15);
        area.setMaxWidth(250); area.setMaxHeight(15);

        pane.add(field, 0, 0);
        pane.add(btnBox, 260, 0);
        pane.add(area, 280, 0);
        pane.add(togBox, 0, 280);
//        pane.add(labelBox, 25, 300);
        
        
        Scene scene = new Scene(pane);

        stage.setScene(scene);
        
        stage.setTitle("Character compressing!");
        stage.setWidth(536);
        stage.setHeight(111);
        stage.setResizable(false);
        stage.show();

        button.setOnAction((ActionEvent event) -> {
            try {if(toggle.isSelected()) {unCore(area, field);} else{core(area, field, map);}}
            catch (Exception ex) {ex.printStackTrace();}
        });
        
    }

    public static void main(String[] args) {
        launch();
    }
    
    private static void core(TextArea area, TextArea field, HashMap<Integer, Integer> map) throws Exception {
        
        boolean k = false;
        List raw_list = new ArrayList();
        short[] the_list = new short[128];
        char[] char_list = new char[128];
        short i = 0;
        short j = 0;
        short m = 0;
        short n;

        String raw_s = field.getText();
        System.out.println("the string: " + raw_s);
        byte[] megaraw_list = raw_s.getBytes("UTF-8");
        while (m < megaraw_list.length) {
            raw_list.add(m, megaraw_list[m]);
            
            m++;
        }
        
//        while (i < raw_list.length) {
//            System.out.println(raw_list[i]);
//            i++;
//        }

        n = (short)megaraw_list.length;
        System.out.println("n = " + n);
        
        while (j < n){
            try {raw_list.get(j+1);} catch(Exception ex){raw_list.add(j+1, (byte)0);}
            
            
            System.out.println("   " + raw_list.get(j) + "   " + raw_list.get(j+1));
            if((byte)raw_list.get(j) == 10) {raw_list.set(j+1, (byte)97); k = true;} 
            System.out.println("flag0");
            if((byte)raw_list.get(j) == 32) {raw_list.set(j, (byte)123);} //Space to alphabet
            System.out.println("flag1");
            if((byte)raw_list.get(j+1) == 32) {raw_list.set(j+1, (byte)123);}
            System.out.println("flag2");
            if((byte)raw_list.get(j) == 63) {raw_list.set(j, (byte)124);} //? to alphabet
            System.out.println("flag3");
            if((byte)raw_list.get(j+1) == 63) {raw_list.set(j+1, (byte)124);}
            
            System.out.println("FLAG1, j: " + j + " i: " + i);
            
            raw_list.set(j, (byte)((byte)raw_list.get(j) - 96)); raw_list.set(j+1, (byte)((byte)raw_list.get(j+1) - 96));
            
            the_list[i] = (short)((byte)raw_list.get(j) * 28 + ((byte)raw_list.get(j+1)));
            
            System.out.println(the_list[i]);
            char_list[i] = (char)(int)map.get((int)the_list[i]);
            
            System.out.println("FLAG2, j: " + j + " i: " + i);
            System.out.print(char_list[i]);
            
            i++;
            j = (short)(j + 2);
//            if((byte)raw_list.get(j) == 10) {i = n;}
            if(k == true) {i = n;}
            
            System.out.println("FLAG3, j: " + j + " i: " + i);
            System.out.println("postFLAG3");
        }
        System.out.print("\n FLAG4 \n");
        
        String s = "";
        i = 0;
        while(i < n){
            s = s + char_list[i];
            i++;
        }
        
        area.setText(s);
    }
    
    private static void unCore(TextArea area, TextArea field) throws Exception {
        
        List raw_list = new ArrayList();
        short[] the_list = new short[128];
        char[] char_list = new char[128];
        short i = 0;
        short j = 0;
        short m = 0;
        short n;
        
        String raw_s = field.getText();
        char[] megaraw_list = raw_s.toCharArray();
        
        System.out.println("the string: " + raw_s);

        while (m < megaraw_list.length) {
            
            System.out.println(megaraw_list[m]);
            System.out.println(i);
            System.out.println(m);
            System.out.println("flag0");
            
            raw_list.add(m, (short)megaraw_list[m]);
            System.out.println("flag1");
            
            System.out.println(raw_list.get(m));
            System.out.println("flag2");
            
            raw_list.set(m, (short)raw_list.get(m) - 162);
            System.out.println("flag3");
            
            the_list[i] = (short)((int)raw_list.get(m) / 28);
            the_list[i+1] = (short)((int)raw_list.get(m) % 28);
            
            System.out.println("flag4");
            System.out.println(the_list[i]);
            System.out.println(the_list[i+1]);
            
            the_list[i] = (short)(the_list[i] + 97);
            the_list[i+1] = (short)(the_list[i+1] + 97);
            
            if((int)the_list[i] == 125) {the_list[i] = 63;} //? from alphabet
            if((int)the_list[i+1] == 125) {the_list[i+1] = 63;}            
            
            if((int)the_list[i] == 124) {the_list[i] = 32;} //space fron alphabet
            if((int)the_list[i+1] == 124) {the_list[i+1] = 32;}
            
            System.out.println("flag5");
            char_list[i] = (char)the_list[i+1];
            char_list[i+1] = (char)the_list[i];
            
            System.out.println(i);
            System.out.println(m);
            
            i = (short)(i + 2);
            m++;
        }
        System.out.println("FLAG1");
        String s = "";
        i = 0;
        while(i < (short)the_list.length){
            s = s + char_list[i];
            i++;
        }
        
        area.setText(s);
        System.out.println("FLAG2");
        
    }
}