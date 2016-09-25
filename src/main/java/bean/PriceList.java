package bean;

import java.io.Serializable;

public class PriceList implements Serializable {

    private Flight flight;
    private FlightClass flightClass;
    private int price;

    public PriceList() {
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public FlightClass getFlightClass() {
        return flightClass;
    }

    public void setFlightClass(FlightClass flightClass) {
        this.flightClass = flightClass;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PriceList)) return false;

        PriceList priceList = (PriceList) o;

        if (getPrice() != priceList.getPrice()) return false;
        if (!getFlight().equals(priceList.getFlight())) return false;
        return getFlightClass() == priceList.getFlightClass();

    }

    @Override
    public int hashCode() {
        int result = getFlight().hashCode();
        result = 31 * result + getFlightClass().hashCode();
        result = 31 * result + getPrice();
        return result;
    }

    @Override
    public String toString() {
        return "PriceList{" +
                "flight=" + flight +
                ", flightClass=" + flightClass +
                ", price=" + price +
                '}';
    }
}
