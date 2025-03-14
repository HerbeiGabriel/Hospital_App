package GUI;

import Domain.Department;
import Domain.Object;
import Domain.Patient;
import Domain.Receipt;
import Service.Service;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javax.management.RuntimeErrorException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Controller<T>{
    private Service service;
    private ObservableList<Patient> listPatients;
    private ObservableList<Department> listDepartments;
    private ObservableList<Receipt> listReceipts;
    private GUI gui;

    public Controller(Service service) {
        this.service = service;
        this.gui = new GUI();
    }

    @FXML
    private ListView<T> List;

    @FXML
    private Button Get, Edit;

    @FXML
    private ChoiceBox Filter, Display;

    @FXML
    private TextField Search;

    public void initialize(){
        List.setItems((ObservableList<T>) FXCollections.observableList(service.getDepartments()));
        List.refresh();
        ArrayList<String> Options = new ArrayList<>();
        Options.add("Patients");
        Options.add("Departments");
        Options.add("Receipts");
        Display.getItems().setAll(Options);

        Display.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                changeList(newValue);
                addFilters(newValue);
            }
        });

        Filter.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                filter(newValue);
            }
        });

        Search.setOnAction(this::find);

        Get.setOnAction(this::find2);
        
        try{
            Edit.setOnAction(this::chooseTab);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void changeList(String newValue){
        if(newValue.equals("Patients")){
            ArrayList<Patient> Patient = service.getPatients();
            listPatients = FXCollections.observableArrayList(Patient);
            List.setItems((ObservableList<T>) listPatients);
            List.refresh();
        }
        else if(newValue.equals("Departments")){
            ArrayList<Department> Department = service.getDepartments();
            listDepartments = FXCollections.observableArrayList(Department);
            List.setItems((ObservableList<T>) listDepartments);
            List.refresh();
        }
        else if(newValue.equals("Receipts")){
            ArrayList<Receipt> Receipt = service.getReceipts();
            listReceipts = FXCollections.observableArrayList(Receipt);
            List.setItems((ObservableList<T>) listReceipts);
            List.refresh();
        }
    }

    public void chooseTab(ActionEvent actionEvent){
        GUI gui = new GUI();
        gui.changeScene(service);
    }

    public void addFilters(String newValue){
        ArrayList<String> Options = new ArrayList<>();
        if(newValue.equals("Patients")){
            Options.add("ID_Patient");
            Options.add("Name");
            Options.add("Surname");
            Options.add("CNP");
            Options.add("Condition");
            Filter.getItems().setAll(Options);
        }
        else if(newValue.equals("Departments")){
            Options.add("ID_Patient");
            Options.add("Name");
            Options.add("Staff");
            Filter.getItems().setAll(Options);
        }
        else if(newValue.equals("Receipts")){
            Options.add("ID_Patient");
            Options.add("ID_Department");
            Options.add("Price");
            Options.add("Date");
            Filter.getItems().setAll(Options);
        }
    }

    public void filter(String newValue){
        String object=Display.getValue().toString();
        if(object.equals("Patients")){
            List.setItems((ObservableList<T>) FXCollections.observableArrayList(service.filter_patient(newValue)));
            List.refresh();
        }
        else if(object.equals("Departments")){
            List.setItems((ObservableList<T>) FXCollections.observableArrayList(service.filter_department(newValue)));
            List.refresh();
        }
        else if(object.equals("Receipts")){
            List.setItems((ObservableList<T>) FXCollections.observableArrayList(service.filter_receipt(newValue)));
            List.refresh();
        }
    }

    public void find(ActionEvent event){
        List.setItems((ObservableList<T>) FXCollections.observableList(service.find(Search.getText())));
        List.refresh();
    }

    public void find2(ActionEvent event){
        if(Search.getText().isEmpty()){
            throw new IllegalArgumentException();
        }
        else if(List.getSelectionModel().getSelectedItem() != null){
            List<Receipt> list=new ArrayList<>();
            Patient selected=(Patient) List.getSelectionModel().getSelectedItem();
            int index=selected.getID();
            List.setItems((ObservableList<T>) FXCollections.observableList(service.getReceipts(index)));
            List.refresh();
        }
        else if(List.getSelectionModel().getSelectedItem() == null){
            System.out.println("Item not selected");
        }
    }

    public void refresh(){
        List.setItems((ObservableList<T>) FXCollections.observableList(service.find(Search.getText())));
        List.refresh();
    }


}
