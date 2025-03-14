package Repository;

import Domain.Department;
import Domain.Patient;

import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RepoPatient extends DataRepo<Integer, Patient> {
    private String URL="jdbc:sqlite:Data/identifiable.sqlite";

    public RepoPatient() {
        ReadDB();
    }

    @Override
    public void UpdateDB(Integer id, Patient t) {
        String query = "UPDATE Patient SET Name=?, Surname=?, CNP=?, Condition=? WHERE ID_Patient=?";
        try{
            Connection con = DriverManager.getConnection(URL);
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, t.getName());
            ps.setString(2, t.getSurname());
            ps.setString(3, t.getCNP());
            ps.setString(4, t.getCondition());
            ps.setInt(5, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void ReadDB() {
        String query = "SELECT * FROM Patient";
        try{
            Connection con=DriverManager.getConnection(URL);
            ResultSet rs=con.createStatement().executeQuery(query);
            while(rs.next()){
                int ID = rs.getInt("ID_Patient");
                String Name = rs.getString("Name");
                String Surname = rs.getString("Surname");
                String CNP = rs.getString("CNP");
                String Condition = rs.getString("Condition");
                Patient p = new Patient(ID, Name, Surname, CNP, Condition);
                map.put(ID, p);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void WriteDB(Patient t) {
        String query = "INSERT INTO Patient VALUES(?,?,?,?,?)";
        try{
            Connection con=DriverManager.getConnection(URL);
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, t.getID());
            ps.setString(2, t.getName());
            ps.setString(3, t.getSurname());
            ps.setString(4, t.getCNP());
            ps.setString(5, t.getCondition());
            ps.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void DeleteDB(Integer id) {
        String query = "DELETE FROM Patient WHERE ID_Patient=?";
        try{
            Connection con=DriverManager.getConnection(URL);
            PreparedStatement ps=con.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public List<Patient> filter(String condition) {
        List<Patient> list = new ArrayList<>();
        list=getlist();
        if(condition.equals("ID_Patient")){
            return list.stream().sorted(Comparator.comparing(Patient::getID)).toList();
        }
        else if(condition.equals("Name")){
            return list.stream().sorted(Comparator.comparing(Patient::getName)).toList();
        }
        else if(condition.equals("Surname")){
            return list.stream().sorted(Comparator.comparing(Patient::getSurname)).toList();
        }
        else if(condition.equals("CNP")){
            return list.stream().sorted(Comparator.comparing(Patient::getCNP)).toList();
        }
        else if(condition.equals("Condition")){
            return list.stream().sorted(Comparator.comparing(Patient::getCondition)).toList();
        }
        return list;
    }

    public List<Patient> find(String condition) {
        List<Patient> list = new ArrayList<>();
        list=getlist();
        return list.stream().filter(t->t.getNameSurname().contains(condition)).toList();
    }


}
