/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import gui.util.Constraints;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author Lucas Murilo
 */
public class DepartamentFormController implements Initializable{
    
    @FXML
    private TextField txtId;
    
    @FXML
    private TextField txtName;
    
    @FXML
    private Label errorName;
    
    @FXML
    private Button btnSave;
    
    @FXML
    private Button btnCanel;
    
    @FXML
    public void onBtnSaveAction(){
        
    }
    
    @FXML 
    public void onBtnCancelAction(){
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        initializeNode();
    }
    
    private void initializeNode(){
        Constraints.setTextFieldInteger(txtId);
        Constraints.setTextFieldMaxLength(txtName, 30);
    }
}
