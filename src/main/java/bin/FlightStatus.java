package bin;

public enum FlightStatus {
    checkIn("check-in"),
    gateClosed("gate closed"),
    arrived("arrived"),
    departedAt("departed at"),
    unknown("unknown"),
    canceled("canceled"),
    expectedAt("expected at"),
    delayed("delayed"),
    inFlight("in flight"),
    gate("gate");

    private String name;

    FlightStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static String getCurrentStatus(String currentName) {

        FlightStatus[] flightStatuses = FlightStatus.values();
        for (FlightStatus currentStatus : flightStatuses) {
            if (currentStatus.name.equals(currentName)) {
                return currentStatus.name();
            }
        }

        return "";
    }
}
