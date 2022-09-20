package jogadores;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import jogo.Tabuleiro;

public class JogadorCavaleiro extends Jogador{

    public JogadorCavaleiro(String nome) {
        super(nome);
    }

    @Override
    public int[] jogar(int[][] tabuleiro) {
        int[] jogada = new int[2];

        switch(tabuleiro.length){
            case 3:

            break;

            case 10:

            break;

            case 50:
            

            break;
        }


        //buscas cegas para poderem ser implementadas? largura, profundidade e peso;
        //resgatar a ideia da atividade de Eng. Software II: criar os estados para a próxima jogada (no caso da busca cega, apenas)




        //estratégia legado do 3x3
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
    
    //gerar os próximos estados possíveis
    public Stream geradorDeEstados(int[][] tabuleiro){
        List<Tabuleiro> estados = new ArrayList<Tabuleiro>();
        Tabuleiro novoEstado = new Tabuleiro(tabuleiro.length);//é preciso clonar isso




        return estados.stream();
    }



}