import java.util.Objects;

public class User {
    public String fname, lname, email, gender, city, state;
    public int age;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return age == user.age && fname.equals(user.fname) && lname.equals(user.lname) && email.equals(user.email) && gender.equals(user.gender) && city.equals(user.city) && state.equals(user.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fname, lname, email, gender, city, state, age);
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", age=" + age +
                '}';
    }

    public User(String line){
        //"Lola,Grimsdyke,89,lgrimsdyke0@facebook.com,Female,Newport Beach,CA"
        String[] items = line.split(",");

        this.fname = items[0];
        this.lname = items[1];

        try{
            this.age = Integer.valueOf(items[2]);
        } catch (NumberFormatException exception){
            this.age = 29;
            exception.printStackTrace();
        }

        this.email = items[3];
        this.gender = items[4];
        this.city = items[5];
        this.state = items[6];
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
