package Service.Formula;

import Model.Formula.*;

import java.util.LinkedList;
import java.util.List;

public class MixtureCalculator {

    private double formulaMass;
    private double formulaVolume;
    private List<FinalComponent> finalComponents;


    public List<FinalComponent> getFinalFormula(List<InitialComponent> list) {
        formulaMass = 0;
        formulaVolume= 0;
        finalComponents = new LinkedList<>();
        calculateComponents(list);
        return  finalComponents;
    }

    private void calculateComponents(List<InitialComponent> initialComponents) {
        calculateMassAndVolume(initialComponents);
        for (FinalComponent component : finalComponents) {
            component.setMassFraction(component.getMassFraction() / formulaMass);
            component.setVolumeFraction(component.getVolumeFraction() / formulaVolume);
        }
    }

    private void calculateMassAndVolume(List<InitialComponent> initialComponents) {
        for (InitialComponent component : initialComponents) {
            double componentMass = component.getDropCount() * Drop.getMass() * component.getDilution() / 100;
            double componentVolume = componentMass / component.getDensity();
            formulaMass += componentMass;
            formulaVolume += componentVolume;
            finalComponents.add(new FinalComponent(component.getName(), component.getDensity(), componentMass, componentVolume));
        }

    }

}
