package Models.Enums;

public enum VehicleTypes {
    CAR,
    MOTORCYCLE,
    HELICOPTER;

    public static VehicleTypes fromString(String dbValue) {
        if (dbValue == null) return null;
        switch (dbValue) {
            case "Car": return CAR;
            case "Motorcycle": return MOTORCYCLE;
            case "Helıcopter": return HELICOPTER;
            default: throw new IllegalArgumentException("Unknown vehicle type: " + dbValue);
        }
    }

}


