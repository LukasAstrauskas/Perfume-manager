package Model.Formula;

import java.util.List;

public class FinalFormula {
    private String name;
    private String comment;
    private List<FinalComponent> finalComponents;

    public FinalFormula() {
    }

    public FinalFormula(String name, List<FinalComponent> finalComponents) {
        this.name = name;
        this.finalComponents = finalComponents;
    }

    public String getName() {
        return name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<FinalComponent> getFinalComponents() {
        return finalComponents;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (FinalComponent comp:finalComponents) {
            builder.append(comp.toString());
           builder.append("\n");
        }
        return "Formula: " + name + "\n" +
                "Components:\n"
                + builder;
    }
}
