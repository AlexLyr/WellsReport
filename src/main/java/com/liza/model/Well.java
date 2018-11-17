package com.liza.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Well {
    private List<Float> vals;
    private String name;
    private LocalDateTime dateTime;
    private double bufferPressure;
    private double outCasingPrassure;
    private double linearPressure;
    private double temperatureGauge;
    private double pressureGauge;

    public Well(){
        vals = new ArrayList<>();
    }

    public List<Float> getVals() {
        return vals;
    }

    public void addVal(Float d) {
        vals.add(d);
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Well{" +
                "name='" + name + '\'' +
                ", dateTime=" + dateTime +
                ", bufferPressure=" + bufferPressure +
                ", outCasingPrassure=" + outCasingPrassure +
                ", linearPressure=" + linearPressure +
                ", temperatureGauge=" + temperatureGauge +
                ", pressureGauge=" + pressureGauge +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Well)) return false;

        Well well = (Well) o;

        if (Double.compare(well.getBufferPressure(), getBufferPressure()) != 0) return false;
        if (Double.compare(well.getOutCasingPrassure(), getOutCasingPrassure()) != 0) return false;
        if (Double.compare(well.getLinearPressure(), getLinearPressure()) != 0) return false;
        if (Double.compare(well.getTemperatureGauge(), getTemperatureGauge()) != 0) return false;
        if (Double.compare(well.getPressureGauge(), getPressureGauge()) != 0) return false;
        if (!getName().equals(well.getName())) return false;
        return getDateTime().equals(well.getDateTime());
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getName().hashCode();
        result = 31 * result + getDateTime().hashCode();
        temp = Double.doubleToLongBits(getBufferPressure());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(getOutCasingPrassure());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(getLinearPressure());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(getTemperatureGauge());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(getPressureGauge());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public double getBufferPressure() {
        return bufferPressure;
    }

    public void setBufferPressure(double bufferPressure) {
        this.bufferPressure = bufferPressure;
    }

    public double getOutCasingPrassure() {
        return outCasingPrassure;
    }

    public void setOutCasingPrassure(double outCasingPrassure) {
        this.outCasingPrassure = outCasingPrassure;
    }

    public double getLinearPressure() {
        return linearPressure;
    }

    public void setLinearPressure(double linearPressure) {
        this.linearPressure = linearPressure;
    }

    public double getTemperatureGauge() {
        return temperatureGauge;
    }

    public void setTemperatureGauge(double temperatureGauge) {
        this.temperatureGauge = temperatureGauge;
    }

    public double getPressureGauge() {
        return pressureGauge;
    }

    public void setPressureGauge(double pressureGauge) {
        this.pressureGauge = pressureGauge;
    }
}
