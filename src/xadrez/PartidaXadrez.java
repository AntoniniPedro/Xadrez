package xadrez;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
	private int turno;
	private Cor jogadorAtual;
	private boolean cheque;

	private List<Peca> pecasNoTabuleiro = new ArrayList<>();
	private List<Peca> pecasCapturadas = new ArrayList<>();

	public PartidaXadrez() {
		tabuleiro = new Tabuleiro(8, 8);
		turno = 1;
		jogadorAtual = Cor.BRANCO;
		initialSetup();
	}

	public int getTurno() {
		return turno;
	}

	public Cor getJogadorAtual() {
		return jogadorAtual;
	}
	
	public boolean getCheque() {
		return cheque;
	}

	public PecaXadrez[][] getPecas() {
		PecaXadrez[][] mat = new PecaXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
		for (int i = 0; i < tabuleiro.getLinhas(); i++) {
			for (int j = 0; j < tabuleiro.getColunas(); j++) {
				mat[i][j] = (PecaXadrez) tabuleiro.peca(i, j);
			}
		}
		return mat;
	}

	public boolean[][] possiveisMovimentos(PosicaoXadrez posicaoOrigem) {
		Posicao posicao = posicaoOrigem.paraPosicao();
		validarPosicaoOrigem(posicao);
		return tabuleiro.peca(posicao).movimentosPossiveis();
	}

	public PecaXadrez fazerMovimentoXadrez(PosicaoXadrez posicaoOrigem, PosicaoXadrez posicaoDestino) {
		Posicao origem = posicaoOrigem.paraPosicao();
		Posicao destino = posicaoDestino.paraPosicao();
		validarPosicaoOrigem(origem);
		validarPosicaoDestino(origem, destino);
		Peca pecaCapturada = mover(origem, destino);
		
		if(testeCheque(jogadorAtual)) {
			desmover(origem, destino, pecaCapturada);
			throw new ExcecaoXadrez("Você não pode se colocar em Xeque");
		}
		
		cheque = (testeCheque(oponente(jogadorAtual))) ? true : false;
		
		proximoTurno();
		return (PecaXadrez) pecaCapturada;
	}

	private Peca mover(Posicao origem, Posicao destino) {
		Peca p = tabuleiro.removerPeca(origem);
		Peca pecaCapturada = tabuleiro.removerPeca(destino);
		tabuleiro.colocarPeca(p, destino);
		if (pecaCapturada != null) {
			pecasNoTabuleiro.remove(pecaCapturada);
			pecasCapturadas.add(pecaCapturada);
		}

		return pecaCapturada;
	}

	private void desmover(Posicao origem, Posicao destino, Peca pecaCapturada) {
		Peca p = tabuleiro.removerPeca(destino);
		tabuleiro.colocarPeca(p, origem);

		if (pecaCapturada != null) {
			tabuleiro.colocarPeca(pecaCapturada, destino);
			pecasCapturadas.remove(pecaCapturada);
			pecasNoTabuleiro.add(pecaCapturada);
		}
	}

	private void validarPosicaoOrigem(Posicao posicao) {
		if (!tabuleiro.temUmaPeca(posicao)) {
			throw new ExcecaoXadrez("Não existe peça na posição origem");
		}
		if (jogadorAtual != ((PecaXadrez) tabuleiro.peca(posicao)).getCor()) {
			throw new ExcecaoXadrez("A peça escolhida não é sua");
		}
		if (!tabuleiro.peca(posicao).existeMovimentoPossivel()) {
			throw new ExcecaoXadrez("Não existe movimentos possíveis para a peça");
		}
	}

	private void validarPosicaoDestino(Posicao origem, Posicao destino) {
		if (!tabuleiro.peca(origem).movimentoPossivel(destino)) {
			throw new ExcecaoXadrez("A peça escolhida não pode se mover para o destino");
		}
	}

	private void proximoTurno() {
		turno++;
		jogadorAtual = (jogadorAtual == Cor.BRANCO) ? Cor.PRETO : Cor.BRANCO;
	}

	private Cor oponente(Cor cor) {
		return (cor == Cor.BRANCO) ? Cor.PRETO : Cor.BRANCO;
	}

	private PecaXadrez rei(Cor cor) {
		List<Peca> lista = pecasNoTabuleiro.stream().filter(x -> ((PecaXadrez)x).getCor() == cor).collect(Collectors.toList());
		for(Peca p : lista) {
			if(p instanceof Rei) {
				return (PecaXadrez)p;
			}
		}
		throw new IllegalStateException("Não existe rei "+ cor +" no tabuleiro.");
	}
	
	private boolean testeCheque(Cor cor) {
		Posicao posicaoRei = rei(cor).getPosicaoXadrez().paraPosicao();
		List<Peca> pecasOponente = pecasNoTabuleiro.stream().filter(x -> ((PecaXadrez)x).getCor() == oponente(cor)).collect(Collectors.toList());
		for (Peca p: pecasOponente){
			boolean[][] mat = p.movimentosPossiveis();
			if(mat[posicaoRei.getLinha()][posicaoRei.getColuna()]) {
				return true;
			}
		}
		return false;
	}
	
	

	private void colocarNovaPeca(char coluna, int linha, PecaXadrez peca) {
		tabuleiro.colocarPeca(peca, new PosicaoXadrez(coluna, linha).paraPosicao());
		pecasNoTabuleiro.add(peca);
	}

	private void initialSetup() {
		colocarNovaPeca('d', 1, new Rainha(tabuleiro, Cor.BRANCO));
		colocarNovaPeca('e', 1, new Rei(tabuleiro, Cor.BRANCO));
		colocarNovaPeca('a', 1, new Torre(tabuleiro, Cor.BRANCO));
		colocarNovaPeca('h', 1, new Torre(tabuleiro, Cor.BRANCO));
		colocarNovaPeca('c', 1, new Bispo(tabuleiro, Cor.BRANCO));
		colocarNovaPeca('f', 1, new Bispo(tabuleiro, Cor.BRANCO));
		colocarNovaPeca('g', 1, new Cavalo(tabuleiro, Cor.BRANCO));
		colocarNovaPeca('b', 1, new Cavalo(tabuleiro, Cor.BRANCO));
		// for (char i = 'a'; i < 'i'; i++) {
		// colocarNovaPeca(i, 2, new Peao(tabuleiro, Cor.BRANCO));
		// }

		colocarNovaPeca('d', 8, new Rainha(tabuleiro, Cor.PRETO));
		colocarNovaPeca('e', 8, new Rei(tabuleiro, Cor.PRETO));
		colocarNovaPeca('a', 8, new Torre(tabuleiro, Cor.PRETO));
		colocarNovaPeca('h', 8, new Torre(tabuleiro, Cor.PRETO));
		colocarNovaPeca('c', 8, new Bispo(tabuleiro, Cor.PRETO));
		colocarNovaPeca('f', 8, new Bispo(tabuleiro, Cor.PRETO));
		colocarNovaPeca('g', 8, new Cavalo(tabuleiro, Cor.PRETO));
		colocarNovaPeca('b', 8, new Cavalo(tabuleiro, Cor.PRETO));
		// for (char i='a'; i <'i'; i++) {
		// colocarNovaPeca(i, 7, new Peao(tabuleiro, Cor.PRETO));
		// }
	}
}
