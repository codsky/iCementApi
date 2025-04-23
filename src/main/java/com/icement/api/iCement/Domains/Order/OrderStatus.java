package com.icement.api.iCement.Domains.Order;

public enum OrderStatus {
    PENDING("Pending"),
    CONFIRMED("Confirmed"),
    IN_PROGRESS("In Progress"),
    DELIVERED("Delivered"),
    CANCELLED("Cancelled");

    private final String status;

    OrderStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return status;
    }
    public static OrderStatus fromString(String status) {
        for (OrderStatus orderStatus : OrderStatus.values()) {
            if (orderStatus.status.equalsIgnoreCase(status)) {
                return orderStatus;
            }
        }
        throw new IllegalArgumentException("No constant with text " + status + " found");
    }
    public static OrderStatus fromValue(String value) {
        for (OrderStatus orderStatus : OrderStatus.values()) {
            if (orderStatus.name().equalsIgnoreCase(value)) {
                return orderStatus;
            }
        }
        throw new IllegalArgumentException("No constant with text " + value + " found");
    }
}
