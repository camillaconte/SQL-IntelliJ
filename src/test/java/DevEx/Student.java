package DevEx;

public class Student {

    String firstName;
    String lastName;

    int id;

    String country;

    public Student(){
        System.out.println("A new student has been created");
    }

    public Student(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
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
