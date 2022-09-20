package jogadores;

import jogo.Tabuleiro;

public class JogadorCavaleiro extends Jogador{

    public JogadorCavaleiro(String nome) {
        super(nome);
    }

    @Override
    public int[] jogar(int[][] tabuleiro) {
        int[] jogada = new int[2];

        if(tabuleiro[0][0] == -1){
            jogada[0] = 0;
            jogada[1] = 0;
            return jogada;
        }else if(tabuleiro[0][2] == -1){
            jogada[0] = 0;
            jogada[1] = 2;
            return jogada;
        }else if(tabuleiro[2][0] == -1){
            jogada[0] = 2;
            jogada[1] = 0;
            return jogada;
        }else if(tabuleiro[2][2] == -1){
            jogada[0] = 2;
            jogada[1] = 2;
            return jogada;
        }else{
            if(tabuleiro[0][1] == -1){
                jogada[0] = 0;
                jogada[1] = 1;
                return jogada;
            }else if(tabuleiro[1][0] == -1){
                jogada[0] = 1;
                jogada[1] = 0;
                return jogada;
            }else if(tabuleiro[1][1] == -1){
                jogada[0] = 1;
                jogada[1] = 1;
                return jogada;
            }else if(tabuleiro[1][2] == -1){
                jogada[0] = 1;
                jogada[1] = 2;
                return jogada;
        }else if(tabuleiro[2][1] == -1){
            jogada[0] = 2;
            jogada[1] = 1;
            return jogada;
    }
    }
   
        return null;

    }
    
}