/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import db.DbException;
import gui.util.Alerts;
import gui.util.Constraints;
import gui.util.Utils;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entites.Departament;
import model.services.DepartamentService;

/**
 *
 * @author Lucas Murilo
 */
public class DepartamentFormController implements Initializable{
    
    private Departament entity;
    
    private DepartamentService service;
    
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
    
    public void setDepartament(Departament dep){
        this.entity = dep;
    }
    
    public void setDepartamentService(DepartamentService service){
        this.service = service;
    }
    
    @FXML
    public void onBtnSaveAction(ActionEvent event){
        if(entity == null){
            throw new IllegalStateException("Entity was null");
        }
        if(service == null){
            throw new IllegalStateException("Service was null");   
        }
        try{
            
            entity = getFormData();
            service.saveOrUpdate(entity);
            Utils.currentStage(event).close();
            
        }
        catch(DbException e){
            Alerts.showAlert("Error saving object", null, e.getMessage(), Alert.AlertType.ERROR);
        }
         
    }
    
    @FXML 
    public void onBtnCancelAction(ActionEvent event){
        Utils.currentStage(event).close();
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        initializeNode();
    }
    
    private void initializeNode(){
        Constraints.setTextFieldInteger(txtId);
        Constraints.setTextFieldMaxLength(txtName, 30);
    }
    
    public void updateFormData(){
        if(entity == null){
            throw new IllegalStateException("entity was null");
        }
        txtId.setText(String.valueOf(entity.getId()));
        txtName.setText(entity.getName());
    }

    private Departament getFormData() {
        Departament obj = new Departament();
        obj.setId(Utils.tryParseToInt(txtId.getText()));
        obj.setName(txtName.getText());
        
        return obj;
    }
}
