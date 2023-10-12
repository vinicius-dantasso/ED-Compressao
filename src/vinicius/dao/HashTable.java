package vinicius.dao;

import java.io.FileWriter;
import java.io.IOException;

import vinicius.entities.Conductor;
import vinicius.entities.Vehicle;
import vinicius.service.Protocol;

class No {
    Vehicle vehicle;
    No next;

    No() {
    }

    No(Vehicle v, No n) {
        this.vehicle = v;
        this.next = n;
    }
}

public class HashTable {
    // ---------------------------------------------------------------------
    // Inicialização dos atributos e métodos iniciais
    int size;
    int inserts;
    No tabela[];

    public HashTable(int tam) {
        size = tam;
        tabela = new No[size];
        inserts = 0;
    }

    private int hash(String plate) {
        int hash = 0;
        for (int i = 0; i < plate.length(); i++) {
            hash += plate.charAt(i);
        }
        return hash % size;
    }

    // ---------------------------------------------------------------------

    // ---------------------------------------------------------------------
    // Método de inserção na tabela
    public boolean inserir(String compressed) {
        // Descompressão dos dados do veículo enviado para inserção
        String decompressed = Protocol.compressor.decompress(compressed);
        String[] datas = decompressed.split("@");
        Vehicle vehicle = new Vehicle(datas[0], datas[1], datas[2], datas[3], new Conductor(datas[4], datas[5]));

        String plate = vehicle.getPlate();
        int c = hash(plate);
        
        No currentNode = tabela[c];
        No previousNode = null;
        
        if (currentNode == null) {
            tabela[c] = new No(vehicle, null);
            inserts++;
            fileWriter(
                "Veículo Inserido no Endereço: " + c +
                "\n Fator de Carga Atual: " + (inserts / size) + "\n"
            );
            return true;
        } 
        else {
            while (currentNode != null) {
                if (currentNode.vehicle.getPlate().equals(plate)) {
                    break;
                }
                previousNode = currentNode;
                currentNode = currentNode.next;
            }
    
            if (currentNode == null) {
                No newNode = new No(vehicle, null);
                previousNode.next = newNode;
                inserts++;

                double tam = (double) inserts;
                double tamHash = (double) size; 
                double fatorCarga = tam / tamHash;
                fileWriter(
                    "Veículo Inserido no Endereço: " + c +
                    "\n Fator de Carga Atual: " + String.format("%.2f", fatorCarga) + "\n"
                );

                return true;
            }
        }
        return false;
    }

    // ---------------------------------------------------------------------
    // Método de busca na tabela
    // Retorna os dados comprimidos do veículo desejado
    public String buscar(String plate) {
        // Descompressão do dado recebido
        plate = Protocol.compressor.decompress(plate);
        int h = hash(plate);
        No node = tabela[h];

        No atual, anterior = null;
        Vehicle temp;
        int cont = 0;

        // Método da Transposição
        for(atual = node; atual != null; atual = atual.next){

            if(atual.vehicle.getPlate().equals(plate) && cont != 0){
                System.out.println("\nEndereço -> " + h);
                temp = anterior.vehicle;
                anterior.vehicle = atual.vehicle;
                atual.vehicle = temp;
                return Protocol.compressor.compress(anterior.vehicle.toString());
            }
            else if(atual.vehicle.getPlate().equals(plate)){
                return Protocol.compressor.compress(atual.vehicle.toString());
            }

            anterior = atual;
            cont++;

        }

        return null;
    }

    // ---------------------------------------------------------------------

    // ---------------------------------------------------------------------
    // Método de remoção na tabela
    public boolean remover(String plate) {
        // Descompressão do dado recebido
        plate = Protocol.compressor.decompress(plate);
        int h = hash(plate);
        No currentNode = tabela[h];
        No previousNode = null;
        
        // Busca e remoção do veículo desejado
        while (currentNode != null) {
            if (currentNode.vehicle.getPlate().equals(plate)) {
                if (previousNode == null) {
                    // O nó a ser removido é o primeiro da lista
                    tabela[h] = currentNode.next;
                } else {
                    // O nó a ser removido não é o primeiro da lista
                    previousNode.next = currentNode.next;
                }
                inserts--;

                double tam = (double) inserts;
                double tamHash = (double) size; 
                double fatorCarga = tam / tamHash;
                fileWriter(
                    "Veículo Inserido no Endereço: " + h +
                    "\n Fator de Carga Atual: " + String.format("%.2f", fatorCarga) + "\n"
                );


                return true; // Remoção bem-sucedida
            }
            previousNode = currentNode;
            currentNode = currentNode.next;
        }
    
        return false; // O nó com a placa especificada não foi encontrado
    }

    // ---------------------------------------------------------------------

    // ---------------------------------------------------------------------
    // Método de atualização de um veículo na árvore
    public boolean atualizar(String compressed) {
        // Descompressão dos dados recebidos
        String decompressed = Protocol.compressor.decompress(compressed);
        String[] datas = decompressed.split("@");
        Vehicle updatedVehicle = new Vehicle(datas[0], datas[1], datas[2], datas[3], new Conductor(datas[4], datas[5]));

        String plate = updatedVehicle.getPlate();
        int h = hash(plate);
        No currentNode = tabela[h];
        
        // Busca pelo veículo desejado para atualizar
        while (currentNode != null) {
            if (currentNode.vehicle.getPlate().equals(plate)) {
                currentNode.vehicle = updatedVehicle; // Atualiza o veículo no nó
                return true;
            }
            currentNode = currentNode.next;
        }
    
        return false; // O veículo com a placa especificada não foi encontrado
    }
    
    // ---------------------------------------------------------------------

    // ---------------------------------------------------------------------
    // Método para listar os itens da tabela
    public void imprimir() {
        No node;

        for (int i = 0; i < size; i++) {
            node = tabela[i];

            System.out.print("Endereço -> " + i);

            while (node != null) {
                
                System.out.println(
                "\n\t|\n\t|\n\t|\n\tv" +
                "\n// --------------------------------------" +
                "\nPlaca: " + node.vehicle.getPlate() +
                "\nRenavam: " + node.vehicle.getRenavam() +
                "\nModelo: " + node.vehicle.getModelName() +
                "\nData de Fabricação: " + node.vehicle.getFabricationDate() +
                "\nProprietário: " + node.vehicle.getConductor().getName() +
                "\nCPF: " + node.vehicle.getConductor().getCpf() +
                "\n// --------------------------------------\n"
                );

                node = node.next;
            }

            System.out.println();
        }
    }

    // ---------------------------------------------------------------------

    // ---------------------------------------------------------------------
    // Método para escrever no arquivo .txt o fator de carga da tabela
    private void fileWriter(String content){
        try{
            FileWriter writer = new FileWriter("ExteriorLinkLog.txt",true);
            writer.write(content);
            writer.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    // ---------------------------------------------------------------------
}
