package bean;

import java.io.Serializable;

public class Flight implements Serializable {

    private int id;
    private String number;
    private String terminal;
    private String gate;
    private FlightStatus statusOfFlight;
    private String dateOfDeparture;
    private City city;
    private TypeOfFlight typeOfFlight;

    public Flight() {
    }

    public Flight(String number, String terminal, String gate, FlightStatus statusOfFlight, String dateOfDeparture, City city, TypeOfFlight typeOfFlight) {
        this.number = number;
        this.terminal = terminal;
        this.gate = gate;
        this.statusOfFlight = statusOfFlight;
        this.dateOfDeparture = dateOfDeparture;
        this.city = city;
        this.typeOfFlight = typeOfFlight;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public FlightStatus getStatusOfFlight() {
        return statusOfFlight;
    }

    public void setStatusOfFlight(FlightStatus statusOfFlight) {
        this.statusOfFlight = statusOfFlight;
    }

    public String getGate() {
        return gate;
    }

    public void setGate(String gate) {
        this.gate = gate;
    }

    public String getDateOfDeparture() {
        return dateOfDeparture;
    }

    public void setDateOfDeparture(String dateOfDeparture) {
        this.dateOfDeparture = dateOfDeparture;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public TypeOfFlight getTypeOfFlight() {
        return typeOfFlight;
    }

    public void setTypeOfFlight(TypeOfFlight typeOfFlight) {
        this.typeOfFlight = typeOfFlight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Flight)) return false;

        Flight flight = (Flight) o;

        if (getId() != flight.getId()) return false;
        if (!getNumber().equals(flight.getNumber())) return false;
        if (!getTerminal().equals(flight.getTerminal())) return false;
        if (!getGate().equals(flight.getGate())) return false;
        if (getStatusOfFlight() != flight.getStatusOfFlight()) return false;
        if (!getDateOfDeparture().equals(flight.getDateOfDeparture())) return false;
        if (!getCity().equals(flight.getCity())) return false;
        return getTypeOfFlight() == flight.getTypeOfFlight();

    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getNumber().hashCode();
        result = 31 * result + getTerminal().hashCode();
        result = 31 * result + getGate().hashCode();
        result = 31 * result + getStatusOfFlight().hashCode();
        result = 31 * result + getDateOfDeparture().hashCode();
        result = 31 * result + getCity().hashCode();
        result = 31 * result + getTypeOfFlight().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", terminal='" + terminal + '\'' +
                ", gate='" + gate + '\'' +
                ", statusOfFlight=" + statusOfFlight +
                ", dateOfDeparture='" + dateOfDeparture + '\'' +
                ", city=" + city +
                ", typeOfFlight=" + typeOfFlight +
                '}';
    }
}

