package jogadores;

import java.util.List;
import jogo.Tabuleiro;

public class JogadorCavaleiro extends Jogador{

public JogadorCavaleiro(String nome) {
    super(nome);
}

public int[] jogar(int[][] matrizTabuleiro) {
   int[] jogadaPrincipal = new int[2];
   jogadaPrincipal[0] = -1; jogadaPrincipal[1] = -1;
    int jogadaFinal = -1000;

   for (int i = 0; i < matrizTabuleiro.length; i++){
        for (int j = 0; j < matrizTabuleiro.length; j++){
            if (matrizTabuleiro[i][j] == -1) {
                matrizTabuleiro[i][j] = this.getSimbolo();
                int jogada = miniMax(matrizTabuleiro, 0, false, this.getSimbolo());
                matrizTabuleiro[i][j] = -1;
                    if(jogada>jogadaFinal){
                    jogadaPrincipal[0] = i;
                    jogadaPrincipal[1] = j;
                    jogadaFinal= jogada;
                }     
            }
        }
    }
    return jogadaPrincipal;
}

public static int miniMax(int matrizTabuleiro[][], int profundidade, Boolean max, int simbolo){
    if (matrizTabuleiro.length > 3 && profundidade >0) {
        return 0;
    }
    int valor = verifyTabu(matrizTabuleiro, simbolo);
    if (valor == 10 || valor == -10) {
        return valor;
    }
    if (mover(matrizTabuleiro) == false) {
        return 0;
    }
    if (max == true) {
        int melhor = -1000;
        for (int i = 0; i < matrizTabuleiro.length; i++){
            for (int j = 0; j < matrizTabuleiro.length; j++){
                if (matrizTabuleiro[i][j] == -1) {
                    matrizTabuleiro[i][j] = simbolo;
                    melhor = Math.max(melhor, miniMax(matrizTabuleiro, profundidade + 1, !max, simbolo));
                    matrizTabuleiro[i][j] = -1;
                }
            }
        }
        return melhor;
    }
    else {
        int melhorMovimento = 1000;
        for (int i = 0; i < matrizTabuleiro.length; i++){
            for (int j = 0; j < matrizTabuleiro.length; j++){
                if (matrizTabuleiro[i][j] == -1) {
                    matrizTabuleiro[i][j] = -1*(simbolo - 1);
                    melhorMovimento = Math.min(melhorMovimento, miniMax(matrizTabuleiro, profundidade + 1, !max, simbolo));
                    matrizTabuleiro[i][j] = -1;
                }
            }
        }
        return melhorMovimento;
    }
}

public static int verifyTabu(int matrizTabuleiro[][], int simbolo){
    int ganhou = 0;
    int perdeu = 0;
    for (int i = 0; i < matrizTabuleiro.length; i++) {
        for (int j = 0; j < matrizTabuleiro.length; j++) {
            if(matrizTabuleiro[i][j] == simbolo){
                ganhou++;
            }
            else if(matrizTabuleiro[i][j] == -1*(simbolo - 1)){
                perdeu++;
            }
        }
        if (ganhou == matrizTabuleiro.length) {
            return +10;
        } else if (perdeu == matrizTabuleiro.length) {
            return -10;
        }
        ganhou = 0;
        perdeu = 0;
    }
    ganhou = 0;
    perdeu = 0;
    for (int i = 0; i < matrizTabuleiro.length; i++) {
        for (int j = 0; j < matrizTabuleiro.length; j++) {
            if(matrizTabuleiro[j][i] == simbolo){
                ganhou++;
            }
            else if(matrizTabuleiro[j][i] == -1*(simbolo - 1)){
                perdeu++;
            }
        }
        if (ganhou == matrizTabuleiro.length) {
            return +10;
        } else if (perdeu == matrizTabuleiro.length) {
            return -10;
        }
        ganhou = 0;
        perdeu = 0;
    }
    ganhou = 0;
    perdeu = 0;
    for (int i = 0; i < matrizTabuleiro.length; i++) {
        if(matrizTabuleiro[i][i] == simbolo){
            ganhou++;
        }
        else if(matrizTabuleiro[i][i] == -1*(simbolo - 1)){
            perdeu++;
        }
    }
    if (ganhou == matrizTabuleiro.length) {
        return +10;
    } else if (perdeu == matrizTabuleiro.length) {
        return -10;
    }
    ganhou = 0;
    perdeu = 0;
    int indexMax = matrizTabuleiro.length - 1;
    for (int i = 0; i <= indexMax; i++) {
        if(matrizTabuleiro[i][indexMax - i] == simbolo){
            ganhou++;
        }
        else if(matrizTabuleiro[i][indexMax - i] == -1*(simbolo - 1)){
            perdeu++;
        }
    }
    if (ganhou == matrizTabuleiro.length) {
        return +10;
    } else if (perdeu == matrizTabuleiro.length) {
        return -10;
    }
    return 0;
}

public static Boolean mover(int matrizTabuleiro[][]) {
    for (int i = 0; i < matrizTabuleiro.length; i++)
        for (int j = 0; j < matrizTabuleiro.length; j++)
            if (matrizTabuleiro[i][j] == -1) {
                return true;
            }
    return false;
}

List<JogadorCavaleiro> proximo() { 
    List<JogadorCavaleiro> resp = null;
    return  resp;
}
}