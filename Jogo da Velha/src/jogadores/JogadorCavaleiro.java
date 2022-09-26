package jogadores;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import jogo.Tabuleiro;

public class JogadorCavaleiro extends Jogador{

    // public JogadorCavaleiro(String nome) {
    //     super(nome);
    // }

    // @Override
    // public int[] jogar(int[][] tabuleiro) {
    //     int[] jogada = new int[2];

        // switch(tabuleiro.length){
        //     case 3:

        //     break;

        //     case 10:

        //     break;

        //     case 50:
            

        //     break;
        // }
        // buscas cegas para poderem ser implementadas? largura, profundidade e peso;
        // resgatar a ideia da atividade de Eng. Software II: criar os estados para a próxima jogada (no caso da busca cega, apenas)
        // estratégia legado do 3x3
    //     if(tabuleiro[0][0] == -1){
    //         jogada[0] = 0;
    //         jogada[1] = 0;
    //         return jogada;
    //     }else if(tabuleiro[0][2] == -1){
    //         jogada[0] = 0;
    //         jogada[1] = 2;
    //         return jogada;
    //     }else if(tabuleiro[2][0] == -1){
    //         jogada[0] = 2;
    //         jogada[1] = 0;
    //         return jogada;
    //     }else if(tabuleiro[2][2] == -1){
    //         jogada[0] = 2;
    //         jogada[1] = 2;
    //         return jogada;
    //     }else{
    //         if(tabuleiro[0][1] == -1){
    //             jogada[0] = 0;
    //             jogada[1] = 1;
    //             return jogada;
    //         }else if(tabuleiro[1][0] == -1){
    //             jogada[0] = 1;
    //             jogada[1] = 0;
    //             return jogada;
    //         }else if(tabuleiro[1][1] == -1){
    //             jogada[0] = 1;
    //             jogada[1] = 1;
    //             return jogada;
    //         }else if(tabuleiro[1][2] == -1){
    //             jogada[0] = 1;
    //             jogada[1] = 2;
    //             return jogada;
    //     }else if(tabuleiro[2][1] == -1){
    //         jogada[0] = 2;
    //         jogada[1] = 1;
    //         return jogada;
    //     }
    // }

    // if(tabuleiro[0][0] == -1){
    //     jogada[0] = 0;
    //     jogada[1] = 0;
    //     return jogada;
    // }else if(tabuleiro[0][2] == -1){
    //     jogada[0] = 0;
    //     jogada[1] = 2;
    //     return jogada;
    // }else if(tabuleiro[2][0] == -1){
    //     jogada[0] = 2;
    //     jogada[1] = 0;
    //     return jogada;
    // }else if(tabuleiro[2][2] == -1){
    //     jogada[0] = 2;
    //     jogada[1] = 2;
    //     return jogada;
    // }else{
    //     if(tabuleiro[0][1] == -1){
    //         jogada[0] = 0;
    //         jogada[1] = 1;
    //         return jogada;
    //     }else if(tabuleiro[1][0] == -1){
    //         jogada[0] = 1;
    //         jogada[1] = 0;
    //         return jogada;
    //     }else if(tabuleiro[1][1] == -1){
    //         jogada[0] = 1;
    //         jogada[1] = 1;
    //         return jogada;
    //     }else if(tabuleiro[1][2] == -1){
    //         jogada[0] = 1;
    //         jogada[1] = 2;
    //         return jogada;
    // }else if(tabuleiro[2][1] == -1){
    //     jogada[0] = 2;
    //     jogada[1] = 1;
    //     return jogada;
    // }
// }

public JogadorCavaleiro(String nome) {
    super(nome);
}


public int[] jogar(int[][] tabuleiro) {
   int meuSimbolo = super.getSimbolo();
   int[] minhaJogada = new int[2];
   minhaJogada[0] = -1; minhaJogada[1] = -1;
    int jogadaperfeita = -1000;
   for (int i = 0; i < tabuleiro.length; i++){
       for (int j = 0; j < tabuleiro.length; j++){
            if (tabuleiro[i][j] == -1) {
                tabuleiro[i][j] = this.getSimbolo();
                int jogada = jogadorMiniMax(tabuleiro, 0, false, this.getSimbolo());
                tabuleiro[i][j] = -1;
                    if(jogada>jogadaperfeita){
                   minhaJogada[0] = i;
                    minhaJogada[1] = j;
                    jogadaperfeita= jogada;
                } 
                
               
            }
                }}
    return minhaJogada;
}

public static int jogadorMiniMax(int tabuleiro[][], int profundidade, Boolean maximo, int simbolo){
    if (tabuleiro.length > 3 && profundidade >0) {
        return 0;
    }
    int valor = verifyTabu(tabuleiro, simbolo);
    if (valor == 10 || valor == -10) {
        return valor;
    }
    if (existeMove(tabuleiro) == false) {
        return 0;
    }
    if (maximo == true) {
        int best = -1000;
        for (int i = 0; i < tabuleiro.length; i++){
            for (int j = 0; j < tabuleiro.length; j++){
                if (tabuleiro[i][j] == -1) {
                    tabuleiro[i][j] = simbolo;
                    best = Math.max(best, jogadorMiniMax(tabuleiro, profundidade + 1, !maximo, simbolo));
                    tabuleiro[i][j] = -1;
                }
            }
        }
        return best;
    }
    else {
        int bestmove = 1000;
        for (int i = 0; i < tabuleiro.length; i++){
            for (int j = 0; j < tabuleiro.length; j++){
                if (tabuleiro[i][j] == -1) {
                    tabuleiro[i][j] = -1*(simbolo - 1);
                    bestmove = Math.min(bestmove, jogadorMiniMax(tabuleiro, profundidade + 1, !maximo, simbolo));
                    tabuleiro[i][j] = -1;
                }
            }
        }
        return bestmove;
    }
}

public static int verifyTabu(int tabuleiro[][], int simbolo){
    int ganhou = 0;
    int perdeu = 0;
    for (int i = 0; i < tabuleiro.length; i++) {
        for (int j = 0; j < tabuleiro.length; j++) {
            if(tabuleiro[i][j] == simbolo){
                ganhou++;
            }
            else if(tabuleiro[i][j] == -1*(simbolo - 1)){
                perdeu++;
            }
        }
        if (ganhou == tabuleiro.length) {
            return +10;
        } else if (perdeu == tabuleiro.length) {
            return -10;
        }
        ganhou = 0;
        perdeu = 0;
    }
    ganhou = 0;
    perdeu = 0;
    for (int i = 0; i < tabuleiro.length; i++) {
        for (int j = 0; j < tabuleiro.length; j++) {
            if(tabuleiro[j][i] == simbolo){
                ganhou++;
            }
            else if(tabuleiro[j][i] == -1*(simbolo - 1)){
                perdeu++;
            }
        }
        if (ganhou == tabuleiro.length) {
            return +10;
        } else if (perdeu == tabuleiro.length) {
            return -10;
        }
        ganhou = 0;
        perdeu = 0;
    }
    ganhou = 0;
    perdeu = 0;
    for (int i = 0; i < tabuleiro.length; i++) {
        if(tabuleiro[i][i] == simbolo){
            ganhou++;
        }
        else if(tabuleiro[i][i] == -1*(simbolo - 1)){
            perdeu++;
        }
    }
    if (ganhou == tabuleiro.length) {
        return +10;
    } else if (perdeu == tabuleiro.length) {
        return -10;
    }
    ganhou = 0;
    perdeu = 0;
    int indexMax = tabuleiro.length - 1;
    for (int i = 0; i <= indexMax; i++) {
        if(tabuleiro[i][indexMax - i] == simbolo){
            ganhou++;
        }
        else if(tabuleiro[i][indexMax - i] == -1*(simbolo - 1)){
            perdeu++;
        }
    }
    if (ganhou == tabuleiro.length) {
        return +10;
    } else if (perdeu == tabuleiro.length) {
        return -10;
    }
    return 0;
}

public static Boolean existeMove(int tabuleiro[][]) {
    for (int i = 0; i < tabuleiro.length; i++)
        for (int j = 0; j < tabuleiro.length; j++)
            if (tabuleiro[i][j] == -1) {
                return true;
            }
    return false;
}

public int alphabeta(JogadorCavaleiro g, int alpha, int beta, char vez ){
    // Se g for terminal, retona a heuristica
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
    // Se for a vez de MAX
    if(vez == 'x'){
        // v = -inf
        int v = Integer.MIN_VALUE;
        // Para cada no filho de g
        for(JogadorCavaleiro next : g.prox()){
            // v = max(v, alphabeta(filho, alpha, beta, min)
            v = Integer.max(v, alphabeta(next, alpha, beta, 'o'));
            // alpha = max(alpha, v)
            alpha = Integer.max(alpha, v);
            // se beta <= alpha => poda em beta
            if(beta <= alpha){
                break;
            }
        }
        return v;
    }
    else{
        // v = inf
        int v = Integer.MAX_VALUE;
        // Para cada filho de g
        for(JogadorCavaleiro next : g.prox() ){
            // v = min (v, alphabeta(filho,alpha,beta,max))
            v = Integer.min(v, alphabeta(next, alpha, beta, 'x'));
            // beta = min(beta,v)
            beta = Integer.min(beta, v);
            // se beta <= alpha => poda em alpha
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
    //gerar os próximos estados possíveis
    public Stream geradorDeEstados(int[][] tabuleiro) {
        List<Tabuleiro> estados = new ArrayList<Tabuleiro>();
        Tabuleiro novoEstado = new Tabuleiro(tabuleiro.length);//é preciso clonar isso
        return estados.stream();
    }
}