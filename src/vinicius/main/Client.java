package vinicius.main;

import java.util.Scanner;

import vinicius.entities.Conductor;
import vinicius.entities.Vehicle;
import vinicius.service.Protocol;

public class Client {
    public static void main(String[] args) {
        Protocol prot = new Protocol();

        prot.insertFifty();

        int state = 0;
        String placa, renavam, modelo, data, dono, cpf;
        Scanner in = new Scanner(System.in);

        do{
            System.out.println(
                    "Selecione uma opção:" +
                    "\n[1]Cadastrar Veículo" +
                    "\n[2]Atualizar Veículo" +
                    "\n[3]Remover Veículo" +
                    "\n[4]Pesquisar Veículo" +
                    "\n[5]Listar Todos os Veículos" +
                    "\n[6]Total de Veículos Cadastrados" +
                    "\n[0]Sair"
                );

                state = in.nextInt();
                in.nextLine();

                switch(state){
                    case 1:
                        System.out.println("Insira a placa:");
                        placa = in.nextLine();
                        System.out.println("Insira o renavam:");
                        renavam = in.nextLine();
                        System.out.println("Insira o modelo:");
                        modelo = in.nextLine();
                        System.out.println("Insira a data de fabricação:");
                        data = in.nextLine();
                        System.out.println("Insira o nome do condutor:");
                        dono = in.nextLine();
                        System.out.println("Insira o cpf do condutor:");
                        cpf = in.nextLine();
                        prot.insert(new Vehicle(placa, renavam, modelo, data, new Conductor(dono, cpf)));
                    break;

                    case 2:
                        System.out.println("Insira a placa do Veículo que deseja atualizar:");
                        placa = in.nextLine();
                        System.out.println("Insira o renavam:");
                        renavam = in.nextLine();
                        System.out.println("Insira o modelo:");
                        modelo = in.nextLine();
                        System.out.println("Insira a data de fabricação:");
                        data = in.nextLine();
                        System.out.println("Insira o nome do condutor:");
                        dono = in.nextLine();
                        System.out.println("Insira o cpf do condutor:");
                        cpf = in.nextLine();
                        prot.update(new Vehicle(placa, renavam, modelo, data, new Conductor(dono, cpf)));
                    break;

                    case 3:
                        System.out.println("Insira a placa:");
                        placa = in.nextLine();
                        prot.remove(placa);
                    break;

                    case 4:
                        System.out.println("Insira a placa:");
                        placa = in.nextLine();
                        prot.search(placa);
                    break;

                    case 5:
                        prot.list();
                    break;

                    case 6:
                        //prot.getTotal();
                    break;
                }

        }while(state != 0);


        in.close();
    }
}
