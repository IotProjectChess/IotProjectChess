/*
 * Name:
 * Section Leader:
 * File: Chess.java
 * ------------------
 * 이 프로그램은 체스를 플레이한다.
 */

import java.awt.Color;
import java.awt.event.*;

/** 체스게임을 관리하는 주요클래스 */
public class Chess extends GraphicsProgram{

	/** 화면에 그래픽표시를 처리하는 객체 */
	ChessDisplay display;
	
	/* 모든 조각의 위치를 추적하는 객체 */
	ChessBoard board;

	/* 실행전에 호출되어 ChessDisplay를 초기화하는 메서드이다. ChessBoard 객체이다. */
	public void init()
	{
		display = ChessDisplay.getInstance(this);			// 이 줄은 필요한 줄이다. 바꿔서는 안된다.
		board = new ChessBoard();
		
		display.useRealChessLabels(false);					// 이 메서드를 사용하면 보드가 화면에 어떻게
															//레이블링되는 지 변경하는 것이 가능하다.
															//사실이라는 것이 확인되면 공식 체스판처럼 보드에
															//레이블을 붙일 것이다.false를 전달하면
															//배열과 ChessDisplay에서 검색되는
															//것처럼 보드에 레이블이 지정된다.
	}														
	
	/* 메인 메서드가 run을 이용해서 프로그램을 실행 */
	public void run()
	{
		// 이곳을 채우시오.
	}
	
}

