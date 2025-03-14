package Domain;

import java.time.LocalDate;
import Domain.Errors;

public class Receipt implements Object<Integer> {
    private Integer ID_Patient;
    private int ID_Department;
    private int Price;
    private LocalDate Date;

    public Receipt(Integer ID_Patient, int ID_Department, int Price, LocalDate Date) {
        Errors errors = new Errors();
        if(!(ID_Patient instanceof Integer)){
            throw new NullPointerException("ID is not an integer");
        }
        else if(ID_Patient == null || Date==null){
            throw new NullPointerException("All the fields are required");
        }
        this.ID_Patient = ID_Patient;
        this.ID_Department = ID_Department;
        this.Price = Price;
        this.Date = Date;
    }

    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public Integer getID() {
        return ID_Patient;
    }

    public void setID(Integer ID_Patient) {
        this.ID_Patient = ID_Patient;
    }

    public int getID_Department() {
        return ID_Department;
    }

    public void setID_Department(int ID_Department) {
        this.ID_Department = ID_Department;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public LocalDate getDate() {
        return Date;
    }

    public void setDate(LocalDate date) {
        Date = date;
    }

    @Override
    public String toString() {
        return "Receipt{" +
                "ID_Patient=" + ID_Patient +
                ", ID_Department=" + ID_Department +
                ", Price=" + Price +
                ", Date=" + Date +
                '}';
    }
}
