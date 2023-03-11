package Model.Formula;

import java.util.List;

public class InitialFormula {

    private String name;
    private String comments;
    private List<InitialComponent> initialComponents;


    public InitialFormula() {
    }

    public InitialFormula(String name, List<InitialComponent> initialComponents) {
        this.name = name;
        this.initialComponents = initialComponents;
    }

    public String getName() {
        return name;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public List<InitialComponent> getInitialComponents() {
        return initialComponents;
    }
}
