package Domain;
import Domain.Errors;
public class Patient implements Object<Integer> {
    private Integer ID_Patient;
    private String Name;
    private String Surname;
    private String CNP;
    private String Condition;

    public Patient(Integer ID_Patient, String Name, String Surname, String CNP, String Condition) {
        Errors errors = new Errors();
        if(!(ID_Patient instanceof Integer)){
            throw new IllegalArgumentException("ID must be an integer");
        }
        else if(errors.isNotNumber(Name)){
            throw new IllegalArgumentException("Name must be a string");
        }
        else if(errors.isNotNumber(Surname)){
            throw new IllegalArgumentException("Surname must be a string");
        }
        else if(errors.isNotNumber(CNP)){
            throw new IllegalArgumentException("CNP must be a string");
        }
        else if(errors.isNotNumber(Condition)){
            throw new IllegalArgumentException("Condition must be a string");
        }
        else if(ID_Patient == null || Name==null || Surname==null || CNP==null || Condition==null){
            throw new IllegalArgumentException("All the fields are required");
        }
        this.ID_Patient = ID_Patient;
        this.Name = Name;
        this.Surname = Surname;
        this.CNP = CNP;
        this.Condition = Condition;
    }

    public Integer getID() {
        return ID_Patient;
    }

    public void setID(Integer ID_Patient) {
        this.ID_Patient = ID_Patient;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    public String getCNP() {
        return CNP;
    }

    public void setCNP(String CNP) {
        this.CNP = CNP;
    }

    public String getCondition() {
        return Condition;
    }

    public void setCondition(String condition) {
        Condition = condition;
    }

    public String getNameSurname(){
        return Name + ' ' + Surname;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "ID_Patient=" + ID_Patient +
                ", Name='" + Name + '\'' +
                ", Surname='" + Surname + '\'' +
                ", CNP='" + CNP + '\'' +
                ", Condition='" + Condition + '\'' +
                '}';
    }
}
