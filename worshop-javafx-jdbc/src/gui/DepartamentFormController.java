
package gui;

import db.DbException;
import gui.listener.DataChangeListener;
import gui.util.Alerts;
import gui.util.Constraints;
import gui.util.Utils;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entites.Departament;
import model.exceptions.ValidationException;
import model.services.DepartamentService;

/**
 *
 * @author Lucas Murilo
 */
public class DepartamentFormController implements Initializable{
    
    private Departament entity;
    
    private DepartamentService service;
    
    private List<DataChangeListener> dataChangeListeners = new ArrayList<>();
    
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
    
    public void subscribeDataChangeListener(DataChangeListener listener){
        dataChangeListeners.add(listener);
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
            notifyDataChangeListeners();
            Utils.currentStage(event).close();
            
        }
        catch(ValidationException e){
            setErrorMessages(e.getErrors());
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
        
        ValidationException exception = new ValidationException("Validation error!");
        
        obj.setId(Utils.tryParseToInt(txtId.getText()));
        
        if(txtName.getText() == null || txtName.getText().trim().equals("")){
            exception.addError("name", "Filed can´t be empty");
        }
        
        obj.setName(txtName.getText());
        
        if(exception.getErrors().size() > 0){
            throw exception;
        }
        
        return obj;
    }

    private void notifyDataChangeListeners() {
        for(DataChangeListener listener: dataChangeListeners){
            listener.onDataChanged();
        }
    }
    
    private void setErrorMessages(Map<String, String> errors){
        Set<String> fields = errors.keySet();
        if(fields.contains("name")){
            errorName.setText(errors.get(("name")));
        }
    }
}
