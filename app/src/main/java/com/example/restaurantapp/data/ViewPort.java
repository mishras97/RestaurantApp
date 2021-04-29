package com.example.restaurantapp.data;

public class ViewPort {
    public NorthEast northeast;

    public NorthEast getNortheast() {
        return northeast;
    }

    public void setNortheast(NorthEast northeast) {
        this.northeast = northeast;
    }

    public SouthWest getSouthwest() {
        return southwest;
    }

    public void setSouthwest(SouthWest southwest) {
        this.southwest = southwest;
    }

    public SouthWest southwest;
}
