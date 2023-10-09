package vinicius.service;

import java.util.ArrayList;
import java.util.List;

public class Heap {
    List<HuffmanNode> heap;

    Heap(){
        this.heap = new ArrayList<HuffmanNode>();
    }

    public int piso(double num) {
        return (int) num;
    }

    public void subir(int i, List<HuffmanNode> h) {
        int j; // índice do pai de i;
        HuffmanNode temp;

        j = piso((i - 1) / 2); // gera valor do pai i

        if (j >= 0 && h.get(i).freq < h.get(j).freq) { // Comparar com base na frequência
            temp = h.get(i);
            h.set(i, h.get(j));
            h.set(j, temp);

            subir(j, h);
        }
    }

    public void descer(int i, List<HuffmanNode> h, int tamHeap) {
        int j; // índice do filho de i
        HuffmanNode temp;

        j = 2 * i + 1;

        // Verificar se o índice é válido
        if (j < tamHeap) {

            // Verificar se existem índices à frente dele
            if (j < tamHeap - 1) {

                if (h.get(j).freq > h.get(j + 1).freq) // Comparar com base na frequência
                    j++;

            }

            // Caso o filho seja maior que o pai, trocar suas posições
            if (h.get(j).freq < h.get(i).freq) { // Comparar com base na frequência
                temp = h.get(i);
                h.set(i, h.get(j));
                h.set(j, temp);

                // Verifica se o j recém gerado desce
                descer(j, h, tamHeap);
            }

        }
    }

    public void construirHeap() {
        int i;
        int tamHeap = this.heap.size();

        for (i = piso(tamHeap / 2) - 1; i >= 0; i--) {
            descer(i, this.heap, tamHeap);
        }
    }

    public void inserir(HuffmanNode node) {
        int tamHeap = this.heap.size();

        if (tamHeap == 0) {
            heap.add(node);
        } else {
            heap.add(node);
            subir(tamHeap - 1, heap);
        }
    }

    public HuffmanNode remover() {
        int tamHeap = heap.size();
        HuffmanNode retirado;

        if (tamHeap != 0) {
            retirado = heap.get(0);
            heap.set(0, heap.get(tamHeap - 1));
            heap.remove(tamHeap - 1);

            descer(0, heap, --tamHeap);
            return retirado;
        } else {
            return null;
        }
    }

    public int tamanho() {
        return heap.size();
    }

}
