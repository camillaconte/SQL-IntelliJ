package DevEx;

public class Student {

    String firstName;
    String lastName;
    int id;
    String country;
    String fiscalCode;

    public Student(){
        System.out.println("A new student has been created");
    }

    public Student(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Student(int id, String firstName, String lastName, String country) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        this.country = country;
    }

    @Override
    public String toString() {
        return "Student data: " +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", id=" + id +
                ", country='" + country + "'";
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lasttName){
        this.lastName = lasttName;
    }

    public int getId() {
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country){
        this.country = country;
    }
}
