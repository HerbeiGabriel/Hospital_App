package GUI;
import Domain.Department;
import Service.Service;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.List;

public class Controller2<T> {
    private Service service;
    private GUI gui;

    public Controller2(Service service) {
        this.service = service;
        this.gui = new GUI();
    }

    @FXML
    private TextField first, second, third, forth, fifth;

    @FXML
    private ListView<T> list;

    @FXML
    private DatePicker Date;

    @FXML
    private Button Exit, Add;

    @FXML
    private ChoiceBox<String> Entity, Action;

    public void initialize(){
        service.refresh();
        List<String> _list=new ArrayList<>();

        _list.add("Department");
        _list.add("Patient");
        _list.add("Receipt");

        Entity.getItems().addAll(_list);

        _list.clear();
        _list.add("Add");
        _list.add("Update");
        _list.add("Delete");


        first.setVisible(false);
        second.setVisible(false);
        third.setVisible(false);
        forth.setVisible(false);
        fifth.setVisible(false);

        Exit.setOnAction(this::Exit);

        Entity.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                Action.getItems().addAll(_list);
                _list.clear();
                UpdateList(newValue);
            }
        });

        Action.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                Date.setVisible(false);
                first.setVisible(false);
                second.setVisible(false);
                third.setVisible(false);
                forth.setVisible(false);
                fifth.setVisible(false);
                doAction(newValue);
            }
        });

        Add.setOnAction(this::DoAction);
    }

    public void Exit(ActionEvent actionEvent){
        GUI gui = new GUI();
        gui.changeSceneOriginal();
    }

    public void doAction(String newValue){
        if(newValue.equals("Add")){
            Add();
        }
        else if(newValue.equals("Update")){
            Add();
        }
        else if(newValue.equals("Delete")){
            String entity=Entity.getValue();
            if(entity.equals("Department")){
                list.setItems((ObservableList<T>) FXCollections.observableList(service.getDepartments()));
            }
            else if(entity.equals("Patient")){
                list.setItems((ObservableList<T>) FXCollections.observableList(service.getPatients()));
            }
            else if(entity.equals("Receipt")){
                list.setItems((ObservableList<T>) FXCollections.observableList(service.getReceipts()));
            }
            first.setVisible(true);
            first.setPromptText("Id:");
        }
    }

    public void Add(){
        String entity=Entity.getValue();
        if(entity.equals("Department")){
            list.setItems((ObservableList<T>) FXCollections.observableArrayList(service.getDepartments()));
            first.setVisible(true);
            first.setPromptText("Id:");
            second.setVisible(true);
            second.setPromptText("Staff number:");
            third.setVisible(true);
            third.setPromptText("Name of department:");
        }
        else if(entity.equals("Patient")){
            list.setItems((ObservableList<T>) FXCollections.observableArrayList(service.getPatients()));
            first.setVisible(true);
            first.setPromptText("Id:");
            second.setVisible(true);
            second.setPromptText("Name:");
            third.setVisible(true);
            third.setPromptText("Surname");
            forth.setVisible(true);
            forth.setPromptText("CNP:");
            fifth.setVisible(true);
            fifth.setPromptText("Condition:");

        }
        else if(entity.equals("Receipt")){
            list.setItems((ObservableList<T>) FXCollections.observableArrayList(service.getReceipts()));
            first.setVisible(true);
            first.setPromptText("Id patient:");
            second.setVisible(true);
            second.setPromptText("Id department:");
            third.setVisible(true);
            third.setPromptText("Price:");
        }
    }

    public void DoAction(ActionEvent actionEvent){
        String entity=Entity.getValue();
        String action=Action.getValue();
        if(entity.equals("Department")){
            if(action.equals("Add")){
                try{
                    service.create_department(Integer.parseInt(first.getText()), Integer.parseInt(second.getText()), third.getText());
                    UpdateList(entity);
                }catch(IllegalArgumentException e){
                    System.out.println(e.getMessage());
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }

            }
            else if(action.equals("Delete")){
                try{
                    service.delete_department(Integer.parseInt(first.getText()));
                    UpdateList(entity);
                }catch(Exception e){
                    System.out.println(e.getMessage());
                }
            }
            else if(action.equals("Update")){
                try{
                    service.update_department(Integer.parseInt(first.getText()), Integer.parseInt(second.getText()), third.getText());
                    UpdateList(entity);
                }catch(IllegalArgumentException e){
                    System.out.println(e.getMessage());
                }
            }
        }
        else if(entity.equals("Patient")){
            if(action.equals("Add")){
                try{
                    service.create_patient(Integer.parseInt(first.getText()), second.getText(), third.getText(), forth.getText(), fifth.getText());
                    UpdateList(entity);
                }catch (IllegalArgumentException e){
                    System.out.println(e.getMessage());
                }
            }
            else if(action.equals("Delete")){
                try{
                    service.delete_patient(Integer.parseInt(first.getText()));
                    UpdateList(entity);
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
            else if(action.equals("Update")){
                try{
                    service.update_patient(Integer.parseInt(first.getText()), second.getText(), third.getText(), forth.getText(), fifth.getText());
                    UpdateList(entity);
                }catch (IllegalArgumentException e){
                    System.out.println(e.getMessage());
                }
            }
        }
        else if(entity.equals("Receipt")){
            if(action.equals("Add")){
                try {
                    service.create_receipt(Integer.parseInt(first.getText()), Integer.parseInt(second.getText()), Integer.parseInt(third.getText()));
                    UpdateList(entity);
                }catch (IllegalArgumentException e){
                    System.out.println(e.getMessage());
                }
            }
            else if(action.equals("Delete")){
                try{
                    service.delete_receipt(Integer.parseInt(first.getText()));
                    UpdateList(entity);
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }

            }
            else if(action.equals("Update")){
                try{
                    service.update_receipt(Integer.parseInt(first.getText()), Integer.parseInt(second.getText()), Integer.parseInt(third.getText()));
                    UpdateList(entity);
                }catch (IllegalArgumentException e){
                    System.out.println(e.getMessage());
                }
            }
        }
        clear();
    }

    public void UpdateList(String Entity){
        if(Entity.equals("Department")){
            list.setItems((ObservableList<T>) FXCollections.observableArrayList(service.getDepartments()));
            list.refresh();
        }
        else if(Entity.equals("Patient")){
            list.setItems((ObservableList<T>) FXCollections.observableArrayList(service.getPatients()));
            list.refresh();
        }
        else if(Entity.equals("Receipt")){
            list.setItems((ObservableList<T>) FXCollections.observableArrayList(service.getReceipts()));
            list.refresh();
        }
    }

    public void clear(){
        first.clear();
        second.clear();
        third.clear();
        forth.clear();
        fifth.clear();
    }
}
