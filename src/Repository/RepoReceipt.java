package Repository;

import Domain.Patient;
import Domain.Receipt;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RepoReceipt extends DataRepo<Integer, Receipt> {
    private String URL="jdbc:sqlite:Data/identifiable.sqlite";

    public RepoReceipt() {
        ReadDB();
    }

    @Override
    public void UpdateDB(Integer id, Receipt t) {
        String query = "UPDATE Receipt SET ID_Patient=?, ID_Department=?, Price=?, Date=? WHERE ID_Patient=?";
        try{
            Connection con = DriverManager.getConnection(URL);
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, t.getID());
            ps.setInt(2, t.getID_Department());
            ps.setInt(3, t.getPrice());
            ps.setString(4, t.getDate().toString());
            ps.setInt(5, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void ReadDB() {
        String query = "SELECT * FROM Receipt";
        try{
            Connection con=DriverManager.getConnection(URL);
            ResultSet rs=con.createStatement().executeQuery(query);
            while(rs.next()){
                int ID = rs.getInt("ID_Patient");
                int ID_Department = rs.getInt("ID_Department");
                int Price = rs.getInt("Price");
                LocalDate Date = LocalDate.parse(rs.getString("Date"));
                Receipt receipt = new Receipt(ID, ID_Department, Price, Date);
                map.put(ID, receipt);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void WriteDB(Receipt t) {
        String query = "INSERT INTO Receipt VALUES(?,?,?,?)";
        try{
            Connection con=DriverManager.getConnection(URL);
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, t.getID());
            ps.setInt(2, t.getID_Department());
            ps.setInt(3, t.getPrice());
            ps.setString(4, t.getDate().toString());
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

    public List<Receipt> filter(String option){
        List<Receipt> list = new ArrayList<>();
        list=getlist();
        if(option.equals("ID_Patient")){
            return list.stream().sorted(Comparator.comparing(Receipt::getID)).toList();
        }
        else if(option.equals("ID_Department")){
            return list.stream().sorted(Comparator.comparing(Receipt::getID_Department)).toList();
        }
        else if(option.equals("Price")){
            return list.stream().sorted(Comparator.comparing(Receipt::getPrice)).toList();
        }
        else if(option.equals("Date")){
            return list.stream().sorted(Comparator.comparing(Receipt::getDate)).toList();
        }
        return list;
    }

    public List<Receipt> getReceipts(int ID){
        List<Receipt> list = new ArrayList<>();
        list=getlist();
        return list.stream().filter(t->t.getID()==ID).toList();
    }
}
