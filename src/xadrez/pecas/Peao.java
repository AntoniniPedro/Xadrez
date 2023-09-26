package xadrez.pecas;

import jogodetabuleiro.Posicao;
import jogodetabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Peao extends PecaXadrez{

	public Peao(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}
	
	@Override
	public String toString() {
		return "P";
	}
	
	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		Posicao p = new Posicao(0,0);
		
		if(getCor() == Cor.BRANCO) {
			p.setValores(posicao.getLinha() - 1, getContadorMovimento());
			if(getTabuleiro().existePosicao(p) && !getTabuleiro().temUmaPeca(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValores(posicao.getLinha() - 2, getContadorMovimento());
			Posicao p2 = new Posicao(posicao.getLinha() - 1, posicao.getColuna());
			if(getTabuleiro().existePosicao(p) && !getTabuleiro().temUmaPeca(p) && getTabuleiro().existePosicao(p2) && !getTabuleiro().temUmaPeca(p2) && getContadorMovimento() == 0) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValores(posicao.getLinha() - 1, getContadorMovimento() - 1);
			if(getTabuleiro().existePosicao(p) && existePecaOponente(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValores(posicao.getLinha() - 1, getContadorMovimento() + 1);
			if(getTabuleiro().existePosicao(p) && existePecaOponente(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			
		}else {
			p.setValores(posicao.getLinha() + 1, getContadorMovimento());
			if(getTabuleiro().existePosicao(p) && !getTabuleiro().temUmaPeca(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValores(posicao.getLinha() + 2, getContadorMovimento());
			Posicao p2 = new Posicao(posicao.getLinha() + 1, posicao.getColuna());
			if(getTabuleiro().existePosicao(p) && !getTabuleiro().temUmaPeca(p) && getTabuleiro().existePosicao(p2) && !getTabuleiro().temUmaPeca(p2) && getContadorMovimento() == 0) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValores(posicao.getLinha() + 1, getContadorMovimento() - 1);
			if(getTabuleiro().existePosicao(p) && existePecaOponente(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValores(posicao.getLinha() + 1, getContadorMovimento() + 1);
			if(getTabuleiro().existePosicao(p) && existePecaOponente(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
		}
		
		
		
		return mat;
	}
	
}
