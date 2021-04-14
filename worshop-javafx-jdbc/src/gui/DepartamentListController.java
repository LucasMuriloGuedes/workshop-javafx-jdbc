package gui;

import gui.listener.DataChangeListener;
import gui.util.Alerts;
import gui.util.Utils;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.entites.Departament;
import model.services.DepartamentService;
import worshop.javafx.jdbc.WorshopJavafxJdbc;

public class DepartamentListController implements Initializable, DataChangeListener {
    
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
    public void onBtNewAction(ActionEvent event){ 
        Stage parentStage = Utils.currentStage(event);
        Departament obj = new Departament();
        createDialogForm(obj, "/gui/DepartamentForm.fxml", parentStage);    
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
    
    private void createDialogForm(Departament obj,String absoluteName, Stage parentStage){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
            Pane pane = loader.load();
            
            DepartamentFormController controller = loader.getController();
            controller.setDepartament(obj);
            controller.setDepartamentService(new DepartamentService());
            controller.subscribeDataChangeListener(this);
            controller.updateFormData();
            
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Enter Departament data");
            dialogStage.setScene(new Scene(pane));
            dialogStage.setResizable(false);
            dialogStage.initOwner(parentStage);
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.showAndWait();
            
        }
        catch(IOException e){
            Alerts.showAlert("Io Exception", "Error loading view", e.getMessage(), Alert.AlertType.ERROR);
        }
        
    }

    @Override
    public void onDataChanged() {
        updateTableView();
    }
}
