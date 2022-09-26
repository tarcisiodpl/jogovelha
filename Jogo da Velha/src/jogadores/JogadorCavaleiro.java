package jogadores;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import jogo.Tabuleiro;

public class JogadorCavaleiro extends Jogador{

public JogadorCavaleiro(String nome) {
    super(nome);
}

public int[] jogar(int[][] matrizTabuleiro) {
   int[] jogadaPrincipal = new int[2];
   jogadaPrincipal[0] = -1; jogadaPrincipal[1] = -1;
    int jogadaperfeita = -1000;
   for (int i = 0; i < matrizTabuleiro.length; i++){
       for (int j = 0; j < matrizTabuleiro.length; j++){
            if (matrizTabuleiro[i][j] == -1) {
                matrizTabuleiro[i][j] = this.getSimbolo();
                int jogada = jogadorMiniMax(matrizTabuleiro, 0, false, this.getSimbolo());
                matrizTabuleiro[i][j] = -1;
                    if(jogada>jogadaperfeita){
                    jogadaPrincipal[0] = i;
                    jogadaPrincipal[1] = j;
                    jogadaperfeita= jogada;
                }     
            }
        }
    }
    return jogadaPrincipal;
}

public static int jogadorMiniMax(int matrizTabuleiro[][], int profundidade, Boolean maximo, int simbolo){
    if (matrizTabuleiro.length > 3 && profundidade >0) {
        return 0;
    }
    int valor = verifyTabu(matrizTabuleiro, simbolo);
    if (valor == 10 || valor == -10) {
        return valor;
    }
    if (existeMove(matrizTabuleiro) == false) {
        return 0;
    }
    if (maximo == true) {
        int best = -1000;
        for (int i = 0; i < matrizTabuleiro.length; i++){
            for (int j = 0; j < matrizTabuleiro.length; j++){
                if (matrizTabuleiro[i][j] == -1) {
                    matrizTabuleiro[i][j] = simbolo;
                    best = Math.max(best, jogadorMiniMax(matrizTabuleiro, profundidade + 1, !maximo, simbolo));
                    matrizTabuleiro[i][j] = -1;
                }
            }
        }
        return best;
    }
    else {
        int bestmove = 1000;
        for (int i = 0; i < matrizTabuleiro.length; i++){
            for (int j = 0; j < matrizTabuleiro.length; j++){
                if (matrizTabuleiro[i][j] == -1) {
                    matrizTabuleiro[i][j] = -1*(simbolo - 1);
                    bestmove = Math.min(bestmove, jogadorMiniMax(matrizTabuleiro, profundidade + 1, !maximo, simbolo));
                    matrizTabuleiro[i][j] = -1;
                }
            }
        }
        return bestmove;
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

public static Boolean existeMove(int matrizTabuleiro[][]) {
    for (int i = 0; i < matrizTabuleiro.length; i++)
        for (int j = 0; j < matrizTabuleiro.length; j++)
            if (matrizTabuleiro[i][j] == -1) {
                return true;
            }
    return false;
}

public int alphabeta(JogadorCavaleiro g, int alpha, int beta, char vez ) {
    if(vez=='0'){
        char ganhador = 0;
        if(ganhador == 'x')
            return 1;
        else if(ganhador == 'o'){
            return -1;
        }else{
            return 0;
        }
    }
    if(vez == 'x'){
        int v = Integer.MIN_VALUE;
        for(JogadorCavaleiro next : g.prox()){
            v = Integer.max(v, alphabeta(next, alpha, beta, 'o'));
            alpha = Integer.max(alpha, v);
            if(beta <= alpha){
                break;
            }
        }
        return v;
    }
    else{
        int v = Integer.MAX_VALUE;

        for(JogadorCavaleiro next : g.prox() ){
            v = Integer.min(v, alphabeta(next, alpha, beta, 'x'));
            beta = Integer.min(beta, v);
            if(beta <= alpha){
                break;
            }
        }
        return v;
    }
}

List<JogadorCavaleiro> prox() { 
    List<JogadorCavaleiro> resp = null;
    return  resp;
}
}