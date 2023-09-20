package xadrez;

import jogodetabuleiro.Peca;
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
	
	public PecaXadrez fazerMovimentoXadrez(PosicaoXadrez posicaoOrigem, PosicaoXadrez posicaoDestino ) {
		Posicao origem = posicaoOrigem.paraPosicao();
		Posicao destino = posicaoDestino.paraPosicao();
		validarPosicaoOrigem(origem);
		Peca pecaCapturada = mover(origem, destino);
		return (PecaXadrez)pecaCapturada;
	}
	
	private Peca mover(Posicao origem, Posicao destino) {
		Peca p = tabuleiro.removerPeca(origem);
		Peca pecaCapturada = tabuleiro.removerPeca(destino);
		tabuleiro.colocarPeca(p, destino);
		return pecaCapturada;
	}
	
	private void validarPosicaoOrigem(Posicao posicao) {
		if (!tabuleiro.temUmaPeca(posicao)) {
			throw new ExcecaoXadrez("Não existe peça na posição origem");
		}
	}
	
	private void colocarNovaPeca(char coluna, int linha, PecaXadrez peca) {
		tabuleiro.colocarPeca(peca, new PosicaoXadrez(coluna, linha).paraPosicao());
	}
	
	private void initialSetup() {
		colocarNovaPeca('d', 1, new Rainha(tabuleiro, Cor.WHITE));
		colocarNovaPeca('e', 1, new Rei(tabuleiro, Cor.WHITE));
		colocarNovaPeca('a', 1, new Torre(tabuleiro, Cor.WHITE));
		colocarNovaPeca('h', 1, new Torre(tabuleiro, Cor.WHITE));
		colocarNovaPeca('c', 1, new Bispo(tabuleiro, Cor.WHITE));
		colocarNovaPeca('f', 1, new Bispo(tabuleiro, Cor.WHITE));
		colocarNovaPeca('g', 1, new Cavalo(tabuleiro, Cor.WHITE));
		colocarNovaPeca('b', 1, new Cavalo(tabuleiro, Cor.WHITE));
		for (char i='a'; i <'i'; i++) {
			colocarNovaPeca(i, 2, new Peao(tabuleiro, Cor.WHITE));	
		}
		
		colocarNovaPeca('d', 8, new Rainha(tabuleiro, Cor.BLACK));
		colocarNovaPeca('e', 8, new Rei(tabuleiro, Cor.BLACK));
		colocarNovaPeca('a', 8, new Torre(tabuleiro, Cor.BLACK));
		colocarNovaPeca('h', 8, new Torre(tabuleiro, Cor.BLACK));
		colocarNovaPeca('c', 8, new Bispo(tabuleiro, Cor.BLACK));
		colocarNovaPeca('f', 8, new Bispo(tabuleiro, Cor.BLACK));
		colocarNovaPeca('g', 8, new Cavalo(tabuleiro, Cor.BLACK));
		colocarNovaPeca('b', 8, new Cavalo(tabuleiro, Cor.BLACK));
		for (char i='a'; i <'i'; i++) {
			colocarNovaPeca(i, 7, new Peao(tabuleiro, Cor.BLACK));	
		}
	}
} 
