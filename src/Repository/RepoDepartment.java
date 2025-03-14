package Repository;

import Domain.Department;

import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RepoDepartment extends DataRepo<Integer, Department> {

    private String URL="jdbc:sqlite:Data/identifiable.sqlite";

    public RepoDepartment() {
        ReadDB();
    }
    @Override
    public void UpdateDB(Integer id, Department t) {
        String query = "UPDATE Department SET Staff=?, Name=? WHERE ID_Department=?";
        try{
            Connection con = DriverManager.getConnection(URL);
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, t.getStaff());
            ps.setString(2, t.getName());
            ps.setInt(3, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void ReadDB() {
        String query = "SELECT * FROM Department";
        try{
            Connection con=DriverManager.getConnection(URL);
            ResultSet rs=con.createStatement().executeQuery(query);
            while(rs.next()){
                int ID = rs.getInt("ID_Department");
                int Staff = rs.getInt("Staff");
                String Name = rs.getString("Name");
                Department d = new Department(ID, Staff ,Name);
                map.put(ID, d);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void WriteDB(Department t) {
        String query = "INSERT INTO Department VALUES(?,?,?)";
        try{
            Connection con=DriverManager.getConnection(URL);
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, t.getID());
            ps.setInt(2, t.getStaff());
            ps.setString(3, t.getName());
            ps.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void DeleteDB(Integer id) {
        String query = "DELETE FROM Department WHERE ID_Department=?";
        try{
            Connection con=DriverManager.getConnection(URL);
            PreparedStatement ps=con.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public List<Department> filter(String option){
        ArrayList<Department> list = new ArrayList<>();
        ArrayList<Department> newList = new ArrayList<>();
        list=getlist();
        if(option.equals("Name")){
            return list.stream().sorted(Comparator.comparing(Department::getName)).toList();
        }
        else if(option.equals("Staff")){
            return list.stream().sorted(Comparator.comparing(Department::getStaff)).toList();
        }
        else if(option.equals("ID_Department")){
            return list.stream().sorted(Comparator.comparing(Department::getID)).toList();
        }
        return list;
    }
}