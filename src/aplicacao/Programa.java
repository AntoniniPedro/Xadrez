package aplicacao;

import java.util.InputMismatchException;
import java.util.Scanner;

import jogodetabuleiro.Posicao;
import jogodetabuleiro.Tabuleiro;
import xadrez.ExcecaoXadrez;
import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;
import xadrez.PosicaoXadrez;

public class Programa {
	public static void main(String[] args) {
		
		Scanner leitor = new Scanner(System.in);
		PartidaXadrez partidaXadrez = new PartidaXadrez();
		
		while (true) {
			try {			
				UI.limparTela();
				UI.imprimirTabuleiro(partidaXadrez.getPecas());
				System.out.println();
				System.out.print("Origem: ");
				PosicaoXadrez origem = UI.lerPosicaoXadrez(leitor);
				
				boolean[][] movimentosPossiveis = partidaXadrez.possiveisMovimentos(origem);
				UI.limparTela();
				UI.imprimirTabuleiro(partidaXadrez.getPecas(), movimentosPossiveis);
				System.out.println();
				System.out.print("Destino: ");
				PosicaoXadrez destino = UI.lerPosicaoXadrez(leitor);
				
				PecaXadrez pecaCapturada = partidaXadrez.fazerMovimentoXadrez(origem, destino);
			}catch (ExcecaoXadrez e) {
				System.out.println(e.getMessage());
				leitor.nextLine();
			}catch (InputMismatchException e) {
				System.out.println(e.getMessage());
				leitor.nextLine();
			}
		}
		
	}
}
