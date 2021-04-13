/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entites.Departament;
import model.services.DepartamentService;
import worshop.javafx.jdbc.WorshopJavafxJdbc;

/**
 *
 * @author Lucas Murilo
 */
public class DepartamentListController implements Initializable {
    
    private DepartamentService service;
    
    @FXML
    private TableView<Departament> tableViewDepartament;
    
    @FXML
    private TableColumn<Departament, Integer> tableColumnId;
    
    @FXML
    private TableColumn<Departament, String> tableColumnName;
    
    @FXML
    private Button btNew;
    
    private ObservableList<Departament> obsList;
    
    
    
    @FXML
    public void onBtNewAction(){
        
    }
    
    public void setDepartmentSevice(DepartamentService service){
        this.service = service;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        
        initializeNodes();
        
    }

    private void initializeNodes() {
        tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        
        Stage stage = (Stage) WorshopJavafxJdbc.getMainScene().getWindow();
        tableViewDepartament.prefHeightProperty().bind(stage.heightProperty());
    }
    
    public void updateTableView(){
        if(service == null){
            throw new IllegalStateException("Service was null");
           
        }
        List<Departament> list = service.findAll();
        obsList = FXCollections.observableArrayList(list);
        tableViewDepartament.setItems(obsList);
        
    }
    
}
