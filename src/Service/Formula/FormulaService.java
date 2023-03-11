package Service.Formula;

import Controller.JavaFxWindows.FinalMixtureWindow;
import Controller.JavaFxWindows.SceneClass;
import Model.Formula.FinalComponent;
import Model.Formula.InitialComponent;

import java.util.ArrayList;
import java.util.List;


public class FormulaService {

    private final MixtureCalculator calculator;
    private static ArrayList<FinalComponent> arrayList = new ArrayList<>();

    private List<FinalComponent> getList() {
        return FormulaService.arrayList;
    }

    private void setList(List<FinalComponent> finalList) {
        FormulaService.arrayList = new ArrayList<>();
        FormulaService.arrayList.addAll(finalList);
    }

    public FormulaService() {

        this.calculator = new MixtureCalculator();
    }

    public void setFinalFormula(List<InitialComponent> list) {
        List<FinalComponent> finalFormula = calculator.getFinalFormula(list);
        setList(finalFormula);
        FinalMixtureWindow.INSTANCE.setDefaultAmountAndConcentrationValues();
        showFinalMixture(1000, 100);
        SceneClass.INSTANCE.setFinalMixtureWindowToFront();
    }

    public void showFinalMixture(int amount, int mixConcentration) {
        if (arrayList.size() > 0) {
            double ethanolVolume = amount;
            for (FinalComponent c : getList()) {
                double vol = c.getVolumeFraction() * amount * mixConcentration / c.getConcentration();
                double mass = vol * c.getDensity();
                vol = Math.round(vol * 10);
                vol = vol / 10;
                mass = Math.round(mass * 10);
                mass = mass / 10;
                c.setVolume(vol);
                c.setMass(mass);
            }

            for (FinalComponent c : getList()) {
                ethanolVolume = ethanolVolume - c.getVolume();
            }
            setSolventVolume(ethanolVolume);
            FinalMixtureWindow.INSTANCE.addComponentsToObsList(arrayList);
        } else {
            setMessage("Add components to formula.");
        }

    }

    public void setSolventVolume(double volume) {
        FinalMixtureWindow.INSTANCE.setSolventField(Double.toString(volume));
    }

    public void setMessage(String message) {
        FinalMixtureWindow.INSTANCE.setMessageText(message);
    }
}
