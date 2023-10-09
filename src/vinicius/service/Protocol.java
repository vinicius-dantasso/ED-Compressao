package vinicius.service;

import vinicius.dao.HashTable;
import vinicius.entities.Conductor;
import vinicius.entities.Vehicle;

public class Protocol {
    public static HashTable hash = new HashTable(47);
    public static Huffman compressor = new Huffman();

    public void insert(Vehicle car){
        String compressed = compressor.compress(car.toString());

        if(hash.inserir(compressed)){
            System.out.println("\nVeículo adicionado com Sucesso!\n");
        }
        else{
            System.out.println("\nVeículo já existente ou nosso espaço acabou!\n");
        }
    }

    public void remove(String plate){
        plate = compressor.compress(plate);

        if(hash.remover(plate)){
            System.out.println("\nVeículo removido com sucesso!\n");
        }
        else{
            System.out.println("\nVeículo não encontrado!\n");
        }
    }

    public void search(String plate){
        // envio para busca
        plate = compressor.compress(plate);

        // retorno da busca
        String data = hash.buscar(plate);

        if(data != null){
            data = compressor.decompress(data);
            String[] datas = data.split("@");
            
            Vehicle car = new Vehicle(datas[0], datas[1], datas[2], datas[3], new Conductor(datas[4], datas[5]));

            System.out.println(
            "// --------------------------------------" +
            "\nPlaca: " + car.getPlate() +
            "\nRenavam: " + car.getRenavam() +
            "\nModelo: " + car.getModelName() +
            "\nData de Fabricação: " + car.getFabricationDate() +
            "\nProprietário: " + car.getConductor().getName() +
            "\nCPF: " + car.getConductor().getCpf() +
            "\n// --------------------------------------\n"
            );
        }
        else{
            System.out.println("Veículo não encontrado!");
        }
    }

    public void update(Vehicle newVehicle){
        String compressed = compressor.compress(newVehicle.toString());

        if(hash.atualizar(compressed)){
            System.out.println("\nVeículo atualizado com sucesso!\n");
        }
        else{
            System.out.println("\nVeículo não encontrado!\n");
        }
    }

    public void list(){
        hash.imprimir();
    }

    public void insertFifty(){
        this.insert(new Vehicle("ABC1234", "12345678910", "Fiat Uno", "01/01/2023", new Conductor("Vinicius Dantas", "123.456.789-01")));
        this.insert(new Vehicle("XYZ5678", "98765432109", "Toyota Corolla", "02/02/2023", new Conductor("Maria Silva", "987.654.321-09")));
        this.insert(new Vehicle("DEF4321", "45678901234", "Honda Civic", "03/03/2023", new Conductor("Carlos Gonzalez", "456.789.012-34")));
        this.insert(new Vehicle("GHI9876", "32109876543", "Volkswagen Golf", "04/04/2023", new Conductor("Ana Santos", "321.098.765-43")));
        this.insert(new Vehicle("JKL5432", "65432109876", "Ford Mustang", "05/05/2023", new Conductor("Pedro Ramirez", "654.321.098-76")));
        this.insert(new Vehicle("MNO2109", "21098765432", "Chevrolet Cruze", "06/06/2023", new Conductor("Laura Fernandez", "210.987.654-32")));
        this.insert(new Vehicle("PQR8765", "76543210987", "Nissan Sentra", "07/07/2023", new Conductor("Raul Martinez", "765.432.109-87")));
        this.insert(new Vehicle("STU9012", "90123456789", "Hyundai Elantra", "08/08/2023", new Conductor("Sofia Pereira", "901.234.567-89")));
        this.insert(new Vehicle("VWX3456", "34567890123", "Kia Seltos", "09/09/2023", new Conductor("Gabriel Santos", "345.678.901-23")));
        this.insert(new Vehicle("YZA6789", "67890123456", "Mercedes-Benz C-Class", "10/10/2023", new Conductor("Isabela Ribeiro", "678.901.234-56")));
        this.insert(new Vehicle("BCD9012", "01234567890", "BMW 3 Series", "11/11/2023", new Conductor("Lucas Silva", "012.345.678-90")));
        this.insert(new Vehicle("EFG2345", "23456789012", "Audi A4", "12/12/2023", new Conductor("Mariana Oliveira", "234.567.890-12")));
        this.insert(new Vehicle("HIJ5678", "56789012345", "Tesla Model 3", "13/01/2023", new Conductor("Guilherme Santos", "567.890.123-45")));
        this.insert(new Vehicle("KLM8901", "89012345678", "Mazda CX-5", "14/02/2023", new Conductor("Beatriz Alves", "890.123.456-78")));
        this.insert(new Vehicle("NOP2345", "43210987654", "Subaru Outback", "15/03/2023", new Conductor("Vitor Rodrigues", "432.109.876-54")));
        this.insert(new Vehicle("QRS6789", "10987654321", "Volvo XC60", "16/04/2023", new Conductor("Julia Lima", "109.876.543-21")));
        this.insert(new Vehicle("TUV0123", "98765432109", "Porsche 911", "17/05/2023", new Conductor("Rodrigo Pereira", "987.654.321-09")));
        this.insert(new Vehicle("WXYZ456", "65432109876", "Lexus RX", "18/06/2023", new Conductor("Fernanda Castro", "654.321.098-76")));
        this.insert(new Vehicle("AAA9876", "21098765432", "Acura MDX", "19/07/2023", new Conductor("Ricardo Oliveira", "210.987.654-32")));
        this.insert(new Vehicle("BBB5432", "76543210987", "Infiniti Q50", "20/08/2023", new Conductor("Camila Mendes", "765.432.109-87")));
        this.insert(new Vehicle("CCC2109", "90123456789", "Jaguar F-Type", "21/09/2023", new Conductor("Thiago Costa", "901.234.567-89")));
        this.insert(new Vehicle("DDD8765", "34567890123", "Land Rover Range Rover", "22/10/2023", new Conductor("Larissa Sousa", "345.678.901-23")));
        this.insert(new Vehicle("EEE9012", "67890123456", "Mini Cooper", "23/11/2023", new Conductor("Rafael Lima", "678.901.234-56")));
        this.insert(new Vehicle("FFF2345", "01234567890", "Jeep Wrangler", "24/12/2023", new Conductor("Bianca Almeida", "012.345.678-90")));
        this.insert(new Vehicle("GGG5678", "23456789012", "Ferrari 488", "25/01/2023", new Conductor("Gustavo Souza", "234.567.890-12")));
        this.insert(new Vehicle("HHH8901", "56789012345", "Lamborghini Huracan", "26/02/2023", new Conductor("Eduarda Ferreira", "567.890.123-45")));
        this.insert(new Vehicle("III1234", "89012345678", "Bugatti Chiron", "27/03/2023", new Conductor("Leonardo Santos", "890.123.456-78")));
        this.insert(new Vehicle("JJJ5678", "43210987654", "McLaren 720S", "28/04/2023", new Conductor("Natalia Oliveira", "432.109.876-54")));
        this.insert(new Vehicle("KKK9012", "10987654321", "Aston Martin DB11", "29/05/2023", new Conductor("Henrique Martins", "109.876.543-21")));
        this.insert(new Vehicle("LLL2345", "98765432109", "Rolls-Royce Phantom", "30/06/2023", new Conductor("Manuela Silva", "987.654.321-09")));
        this.insert(new Vehicle("MMM6789", "65432109876", "Bentley Continental GT", "01/07/2023", new Conductor("Raul Oliveira", "654.321.098-76")));
        this.insert(new Vehicle("NNN2109", "21098765432", "Maserati Quattroporte", "02/08/2023", new Conductor("Bianca Alves", "210.987.654-32")));
        this.insert(new Vehicle("OOO8765", "76543210987", "Audi R8", "03/09/2023", new Conductor("Luis Sousa", "765.432.109-87")));
        this.insert(new Vehicle("PPP9012", "90123456789", "Lexus LC", "04/10/2023", new Conductor("Camila Mendes", "901.234.567-89")));
        this.insert(new Vehicle("QQQ2345", "34567890123", "Ferrari SF90", "05/11/2023", new Conductor("Thiago Costa", "345.678.901-23")));
        this.insert(new Vehicle("RRR5678", "67890123456", "Porsche Taycan", "06/12/2023", new Conductor("Larissa Sousa", "678.901.234-56")));
        this.insert(new Vehicle("SSS9012", "01234567890", "Tesla Model S", "07/01/2023", new Conductor("Rafael Lima", "012.345.678-90")));
        this.insert(new Vehicle("TTT2345", "23456789012", "Jaguar I-PACE", "08/02/2023", new Conductor("Bianca Almeida", "234.567.890-12")));
        this.insert(new Vehicle("UUU5678", "56789012345", "Chevrolet Bolt EV", "09/03/2023", new Conductor("Gustavo Souza", "567.890.123-45")));
        this.insert(new Vehicle("VVV8901", "89012345678", "Nissan Leaf", "10/04/2023", new Conductor("Eduarda Ferreira", "890.123.456-78")));
        this.insert(new Vehicle("WWW1234", "43210987654", "Hyundai Kona Electric", "11/05/2023", new Conductor("Leonardo Santos", "432.109.876-54")));
        this.insert(new Vehicle("XXX5678", "10987654321", "Kia Niro EV", "12/06/2023", new Conductor("Natalia Oliveira", "109.876.543-21")));
        this.insert(new Vehicle("YYY9012", "98765432109", "Tesla Model Y", "13/07/2023", new Conductor("Henrique Martins", "987.654.321-09")));
        this.insert(new Vehicle("ZZZ2345", "65432109876", "Ford Mustang Mach-E", "14/08/2023", new Conductor("Manuela Silva", "654.321.098-76")));
        this.insert(new Vehicle("ABC6789", "21098765432", "Audi e-tron", "15/09/2023", new Conductor("Raul Oliveira", "210.987.654-32")));
        this.insert(new Vehicle("DEF8765", "76543210987", "Polestar 2", "16/10/2023", new Conductor("Luis Sousa", "765.432.109-87")));
        this.insert(new Vehicle("GHI9012", "90123456789", "Rivian R1T", "17/11/2023", new Conductor("Camila Mendes", "901.234.567-89")));
        this.insert(new Vehicle("JKL2345", "34567890123", "Lordstown Endurance", "18/12/2023", new Conductor("Thiago Costa", "345.678.901-23")));
        this.insert(new Vehicle("MNO5678", "67890123456", "Rivian R1S", "19/01/2023", new Conductor("Larissa Sousa", "678.901.234-56")));
        this.insert(new Vehicle("PQR9012", "01234567890", "GMC Hummer EV", "20/02/2023", new Conductor("Rafael Lima", "012.345.678-90")));
    }

}
