package model;

public class ApiResponse {

    private float occupancyRate;

    public ApiResponse(float occupancyRate) {
        this.occupancyRate = occupancyRate;
    }

    public float getOccupancyRate() {
        return occupancyRate;
    }

    public void setOccupancyRate(float occupancyRate) {
        this.occupancyRate = occupancyRate;
    }
}
