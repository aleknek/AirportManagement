package bin;

import java.io.Serializable;

public class Passenger implements Serializable {

    private int id;
    private String firstName;
    private String lastName;
    private String nationality;
    private String passport;
    private String dateOfBirthday;
    private Gender gender;
    private FlightClass flightClass;

    public Passenger() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getDateOfBirthday() {
        return dateOfBirthday;
    }

    public void setDateOfBirthday(String dateOfBirthday) {
        this.dateOfBirthday = dateOfBirthday;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public FlightClass getFlightClass() {
        return flightClass;
    }

    public void setFlightClass(FlightClass flightClass) {
        this.flightClass = flightClass;
    }


    public Passenger(String firstName, String lastName, String nationality, String passport, String dateOfBirthday, Gender gender, FlightClass flightClass) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationality = nationality;
        this.passport = passport;
        this.dateOfBirthday = dateOfBirthday;
        this.gender = gender;
        this.flightClass = flightClass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Passenger)) return false;

        Passenger passenger = (Passenger) o;

        if (getId() != passenger.getId()) return false;
        if (!getFirstName().equals(passenger.getFirstName())) return false;
        if (!getLastName().equals(passenger.getLastName())) return false;
        if (!getNationality().equals(passenger.getNationality())) return false;
        if (!getPassport().equals(passenger.getPassport())) return false;
        if (!getDateOfBirthday().equals(passenger.getDateOfBirthday())) return false;
        if (getGender() != passenger.getGender()) return false;
        return getFlightClass() == passenger.getFlightClass();

    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getFirstName().hashCode();
        result = 31 * result + getLastName().hashCode();
        result = 31 * result + getNationality().hashCode();
        result = 31 * result + getPassport().hashCode();
        result = 31 * result + getDateOfBirthday().hashCode();
        result = 31 * result + getGender().hashCode();
        result = 31 * result + getFlightClass().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", nationality='" + nationality + '\'' +
                ", passport='" + passport + '\'' +
                ", dateOfBirthday='" + dateOfBirthday + '\'' +
                ", gender=" + gender +
                ", flightClass=" + flightClass +
                '}';
    }
}

