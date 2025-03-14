package Domain;
import Domain.Errors;

public class Department implements Object<Integer> {
    private Integer ID_Department;
    private int Staff;
    private String Name;

    public Department(Integer ID_Department, int Staff, String Name) {
        Errors errors = new Errors();
        if(!(ID_Department instanceof Integer)){
            throw new IllegalArgumentException("ID must be an integer");
        }
        else if(errors.isNotNumber(Name)){
            throw new IllegalArgumentException("Name must be a string");
        }
        else if(ID_Department == null || Name==null){
            throw new IllegalArgumentException("All the fields are required");
        }
        this.ID_Department = ID_Department;
        this.Staff = Staff;
        this.Name = Name;
    }


    public Integer getID() {
        return ID_Department;
    }

    public void setID(Integer ID_Department) {
        this.ID_Department = ID_Department;
    }

    public int getStaff() {
        return Staff;
    }

    public void setStaff(int staff) {
        Staff = staff;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    @Override
    public String toString() {
        return "Department{" +
                "ID_Department=" + ID_Department +
                ", Staff=" + Staff +
                ", Name='" + Name +
                '}';
    }
}
