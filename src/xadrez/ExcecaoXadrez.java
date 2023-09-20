package xadrez;

import jogodetabuleiro.ExcecaoTabuleiro;

public class ExcecaoXadrez extends ExcecaoTabuleiro{

	private static final long serialVersionUID = 1L;
	
	public ExcecaoXadrez (String msg) {
		super(msg);
	}
}
