package aplicacao;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import xadrez.ExcecaoXadrez;
import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;
import xadrez.PosicaoXadrez;

public class Programa {
	public static void main(String[] args) {
		
		Scanner leitor = new Scanner(System.in);
		PartidaXadrez partidaXadrez = new PartidaXadrez();
		List<PecaXadrez> capturado = new ArrayList<>();
		
		while (true) {
			try {			
				UI.limparTela();
				UI.imprimirPartida(partidaXadrez, capturado);
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
				
				if(pecaCapturada != null) {
					capturado.add(pecaCapturada);
				}
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
