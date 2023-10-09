package vinicius.entities;

public class Conductor {
    private String name;
    private String cpf;

    public Conductor() {}

    public Conductor(String name, String cpf) {
        this.name = name;
        this.cpf = cpf;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

}