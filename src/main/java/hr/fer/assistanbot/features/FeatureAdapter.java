package hr.fer.assistanbot.features;

public abstract class FeatureAdapter implements Feature {

    private String literal;

    public FeatureAdapter(String literal){
        this.literal = literal;
    }

    public String getLiteral() {
        return literal;
    }
}
