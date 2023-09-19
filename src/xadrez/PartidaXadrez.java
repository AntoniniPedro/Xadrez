package xadrez;

import java.awt.Color;
import java.util.Iterator;

import jogodetabuleiro.Posicao;
import jogodetabuleiro.Tabuleiro;
import xadrez.pecas.Bispo;
import xadrez.pecas.Cavalo;
import xadrez.pecas.Peao;
import xadrez.pecas.Rainha;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class PartidaXadrez {
	
	private Tabuleiro tabuleiro;
	
	public PartidaXadrez() {
		tabuleiro = new Tabuleiro(8, 8);
		initialSetup();
	}
	
	public PecaXadrez[][] getPecas(){
		PecaXadrez[][] mat = new PecaXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
		for (int i=0; i<tabuleiro.getLinhas();i++) {
			for (int j=0; j<tabuleiro.getColunas(); j++) {
				mat[i][j] = (PecaXadrez) tabuleiro.peca(i,j);
			}
		}
		return mat;
	}
	
	private void initialSetup() {
		tabuleiro.colocarPeca(new Rainha(tabuleiro, Cor.WHITE), new Posicao(7,3));
		tabuleiro.colocarPeca(new Rei(tabuleiro, Cor.WHITE), new Posicao(7,4));
		tabuleiro.colocarPeca(new Torre(tabuleiro, Cor.WHITE), new Posicao(7,7));
		tabuleiro.colocarPeca(new Torre(tabuleiro, Cor.WHITE), new Posicao(7,0));
		tabuleiro.colocarPeca(new Bispo(tabuleiro, Cor.WHITE), new Posicao(7,2));
		tabuleiro.colocarPeca(new Bispo(tabuleiro, Cor.WHITE), new Posicao(7,5));
		tabuleiro.colocarPeca(new Cavalo(tabuleiro, Cor.WHITE), new Posicao(7,6));
		tabuleiro.colocarPeca(new Cavalo(tabuleiro, Cor.WHITE), new Posicao(7,1));
		for (int i=0; i <8; i++) {
			tabuleiro.colocarPeca(new Peao(tabuleiro, Cor.WHITE), new Posicao(6,i));	
		}
		
		tabuleiro.colocarPeca(new Rainha(tabuleiro, Cor.BLACK), new Posicao(0,3));
		tabuleiro.colocarPeca(new Rei(tabuleiro, Cor.BLACK), new Posicao(0,4));
		tabuleiro.colocarPeca(new Torre(tabuleiro, Cor.BLACK), new Posicao(0,7));
		tabuleiro.colocarPeca(new Torre(tabuleiro, Cor.BLACK), new Posicao(0,0));
		tabuleiro.colocarPeca(new Bispo(tabuleiro, Cor.BLACK), new Posicao(0,2));
		tabuleiro.colocarPeca(new Bispo(tabuleiro, Cor.BLACK), new Posicao(0,5));
		tabuleiro.colocarPeca(new Cavalo(tabuleiro, Cor.BLACK), new Posicao(0,6));
		tabuleiro.colocarPeca(new Cavalo(tabuleiro, Cor.BLACK), new Posicao(0,1));
		for (int i=0; i <8; i++) {
			tabuleiro.colocarPeca(new Peao(tabuleiro, Cor.BLACK), new Posicao(1,i));	
		}
		
	}
}
