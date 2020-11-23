package it.unipi.dii.lsmsd.pokeMongo.bean;

public class Pokemon {
    private String name;
    private String[] type;
    private int generation;
    private int pokedexIndex;
    private double catchRate;
    private int points;
    private double height;
    private double weight;

    public Pokemon(String name, String[] type, int generation, int pokedexIndex, double catchRate,
                   int points, double height, double weight){
        this.name=name;
        this.type=type;
        this.generation=generation;
        this.pokedexIndex=pokedexIndex;
        this.catchRate=catchRate;
        this.points=points;
        this.height=height;
        this.weight=weight;
    }

    public String getName() {
        return name;
    }

    public double getCatchRate() {
        return catchRate;
    }

    public double getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }

    public int getGeneration() {
        return generation;
    }

    public int getPoints() {
        return points;
    }

    public int getPokedexIndex() {
        return pokedexIndex;
    }

    public String[] getType() {
        return type;
    }

    public void setCatchRate(double catchRate) {
        this.catchRate = catchRate;
    }

    public void setPoints(int points) {
        this.points = points;
    }

}
