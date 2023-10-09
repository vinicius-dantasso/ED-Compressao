package vinicius.entities;

public class Vehicle {
    private String plate;
    private String renavam;
    private String modelName;
    private String fabricationDate;
    private Conductor conductor;

    public Vehicle(){}

    public Vehicle(String plate, String renavam, String modelName, String frabricationDate, Conductor conductor) {
        this.plate = plate;
        this.renavam = renavam;
        this.modelName = modelName;
        this.fabricationDate = frabricationDate;
        this.conductor = conductor;
    }

    public String getPlate() {
        return this.plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getRenavam() {
        return this.renavam;
    }

    public void setRenavam(String renavam) {
        this.renavam = renavam;
    }

    public String getModelName() {
        return this.modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getFabricationDate() {
        return this.fabricationDate;
    }

    public void setFabricationDate(String frabricationDate) {
        this.fabricationDate = frabricationDate;
    }

    public Conductor getConductor() {
        return this.conductor;
    }

    public void setConductor(Conductor conductor) {
        this.conductor = conductor;
    }

    public String toString(){
        return this.getPlate() + "@" + this.getRenavam() + "@" + this.getModelName() + "@"
            +  this.getFabricationDate() + "@" + this.getConductor().getName() + "@"
            +  this.getConductor().getCpf(); 
    }
}
