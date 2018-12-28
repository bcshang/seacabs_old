// Generic Ingredient Class
package playtest;

public class Ingredient {
    String name;
    String type;
    double amount;

    public Ingredient(String name, double amount) {
        this.name = name;
        this.amount = amount;
    }
    public Ingredient(String name, String type, double amount) {
        this.name = name;
        this.type = type;
        this.amount = amount;
    }


    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setType(String type) {
        this.type = type;
    }

    // Getters
    public String getName() {
        return name; 
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "\tType: " + type +
                "\n\tName: " + name +
                "\n\tAmount: " + amount + "\n";
    }

}
