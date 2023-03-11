package Model.Formula;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class FinalComponent {
    private StringProperty name;
    private DoubleProperty density;
    private DoubleProperty mass;
    private DoubleProperty volume;
    private Double massFraction;
    private Double volumeFraction;
    private DoubleProperty concentration;

    public FinalComponent() {
    }

    public FinalComponent(String name, double density, double massFraction, double volumeFraction) {
        setName(name);
        setDensity(density);
        setMassFraction(massFraction);
        setVolumeFraction(volumeFraction);
        setMass(0);
        setVolume(0);
        setConcentration(100);
    }

    public String getName() {
        return nameProperty().get();
    }

    public StringProperty nameProperty() {
        if (name == null) {
            name = new SimpleStringProperty(this, "name");
        }
        return name;
    }

    public void setName(String name) {
        this.nameProperty().set(name);
    }

    public double getDensity() {
        return densityProperty().get();
    }

    public DoubleProperty densityProperty() {
        if (density == null) {
            density = new SimpleDoubleProperty(this, "density");
        }
        return density;
    }

    public void setDensity(double density) {
        this.densityProperty().set(density);
    }

    public double getMass() {
        return massProperty().get();
    }

    public DoubleProperty massProperty() {
        if (mass == null) {
            mass = new SimpleDoubleProperty(this, "mass");
        }
        return mass;
    }

    public void setMass(double mass) {
        this.massProperty().set(mass);
    }

    public double getVolume() {
        return volumeProperty().get();
    }

    public DoubleProperty volumeProperty() {
        if (volume == null) {
            volume = new SimpleDoubleProperty(this, "volume");
        }
        return volume;
    }

    public void setVolume(double volume) {
        this.volumeProperty().set(volume);
    }


    public Double getMassFraction() {
        return massFraction;
    }

    public void setMassFraction(Double massFraction) {
        this.massFraction = massFraction;
    }

    public Double getVolumeFraction() {
        return volumeFraction;
    }

    public void setVolumeFraction(Double volumeFraction) {
        this.volumeFraction = volumeFraction;
    }

    public double getConcentration() {
        return concentrationProperty().get();
    }

    public DoubleProperty concentrationProperty() {
        if (concentration == null) {
            concentration = new SimpleDoubleProperty(this, "concentration");
        }
        return concentration;
    }

    public void setConcentration(double concentration) {
        this.concentrationProperty().set(concentration);
    }

    @Override
    public String toString() {
        return getName() + ", density: " + getDensity() + ", massFraction: "
                + getMassFraction() + ", volumeFraction: " + getVolumeFraction() + ".";
    }
}
