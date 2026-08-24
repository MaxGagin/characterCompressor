package com.maxgagin.symbolcompressing;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class AppController {
    
    @FXML private Button button1;
    
    public void click(ActionEvent event) {
        System.out.println("clicked");
    }    

}
