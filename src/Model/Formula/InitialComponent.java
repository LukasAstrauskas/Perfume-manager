package Model.Formula;

import javafx.beans.property.*;

public class InitialComponent {

    private StringProperty name;
    private DoubleProperty density;
    private IntegerProperty dropCount;
    private DoubleProperty dilution;

    public InitialComponent() {
    }

    public InitialComponent(String  name, double density) {
        setName(name);
        setDensity(density);
        setDropCount(1);
        setDilution(5);
    }

    public String getName() {
        return nameProperty().get();
    }

    public void setName(String name) {
        nameProperty().set(name);
    }

    public StringProperty nameProperty() {
        if (name == null) {
            name = new SimpleStringProperty(this, "name");
        }
        return name;
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

    public int getDropCount() {
        return dropCountProperty().get();
    }

    public IntegerProperty dropCountProperty() {
        if (dropCount == null) {
            dropCount = new SimpleIntegerProperty(this, "dropCount");
        }
        return dropCount;
    }

    public void setDropCount(int dropCount) {
        this.dropCountProperty().set(dropCount);
    }

    public double getDilution() {
        return dilutionProperty().get();
    }

    public DoubleProperty dilutionProperty() {
        if (dilution == null) {
            dilution = new SimpleDoubleProperty(this, "dilution");
        }
        return dilution;
    }

    public void setDilution(double dilution) {
        this.dilutionProperty().set(dilution);
    }

    @Override
    public String toString() {
        return getName()+" "+getDensity()+" "+getDropCount()+" "+getDilution();
    }
}
