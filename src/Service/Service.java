package Service;

import Repository.RepoDepartment;
import Repository.RepoPatient;
import Repository.RepoReceipt;
import Domain.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Service {
    private RepoPatient RepoP;
    private RepoReceipt RepoR;
    private RepoDepartment RepoD;

    public Service() {
        RepoP = new RepoPatient();
        RepoR = new RepoReceipt();
        RepoD = new RepoDepartment();
    }

    public void refresh(){
        RepoP.ReadDB();
        RepoR.ReadDB();
        RepoD.ReadDB();
    }

    public void create_department(int ID, int Staff, String Name){
        Department department=new Department(ID,Staff,Name);
        RepoD.add(ID,department);
    }

    public void create_patient(int ID, String Name, String Surname, String CNP, String Condition){
        Patient patient=new Patient(ID,Name,Surname,CNP,Condition);
        RepoP.add(ID,patient);
    }

    public void create_receipt(int ID_P, int ID_D, int Price){
        if(RepoP.get(ID_P)==null){
            throw new NullPointerException("ID of the patient does not exist");
        }
        else if(RepoD.get(ID_D)==null){
            throw new NullPointerException("ID of the department does not exist");
        }
        LocalDate date=LocalDate.now();
        Receipt re=new Receipt(ID_P, ID_D, Price, date);
        RepoR.add(ID_P,re);
    }

    public void delete_receipt(int ID_P){
        RepoR.delete(ID_P);
    }

    public void delete_patient(int ID_P){
        RepoP.delete(ID_P);
    }

    public void delete_department(int ID){
        RepoD.delete(ID);
    }

    public void update_department(int ID, int Staff, String Name){
        Department department=new Department(ID,Staff,Name);
        RepoD.update(ID,department);
    }

    public void update_patient(int ID, String Name, String Surname, String CNP, String Condition){
        Patient patient=new Patient(ID,Name,Surname,CNP,Condition);
        RepoP.update(ID,patient);
    }

    public void update_receipt(int ID_P, int ID_D, int Price){
        Receipt receipt=new Receipt(ID_P, ID_D, Price, LocalDate.now());
        RepoR.update(ID_P,receipt);
    }

    public ArrayList<Patient> getPatients(){
        return RepoP.getlist();
    }

    public ArrayList<Receipt> getReceipts(){
        return RepoR.getlist();
    }

    public ArrayList<Department> getDepartments(){
        return RepoD.getlist();
    }

    public List<Patient> filter_patient(String condition){
        return RepoP.filter(condition);
    }

    public List<Receipt> filter_receipt(String condition){
        return RepoR.filter(condition);
    }

    public List<Department> filter_department(String condition){
        return RepoD.filter(condition);
    }

    public List<Patient> find(String condition){
        return RepoP.find(condition);
    }

    public Patient getPatient(int ID){
        return RepoP.get(ID);
    }

    public List<Receipt> getReceipts(int ID){
        return RepoR.getReceipts(ID);
    }

}
