/*
 * Name:
 * Section Leader:
 * File: ChessBoard.java
 * ------------------
 * 이클래스는 체스보드를 나타낸다.
 * 이 클래스가 하는 일은 체스조각이 어디있는 지 추적하는 용도로 사용되어진다. 2차원배열을 사용할 수 있다.
 */

public class ChessBoard extends DrawableObject{

	/** 체스보드의 크기를 설정하는 상수 */
	public static final int BOARD_SIZE = 8;
	
	/** 체스보드클래스의 생성자 */
	public ChessBoard()
	{
		// 이곳을 채우시오.
	}
	
	/* 현재 지정된 행과 열에있는 ChessPiece를 반환한다. 지정된 위치에 조각이 없다면 null을 반환한다.
	 */
	public ChessPiece pieceAt(int row, int col)
	{
		return null;	// 여기는 null이 아니라 다른 값을 반환해주어야한다.
	}
	
	/* 지정딘 ChessPiece를 ChessBoard에 추가한다. (힌트 : ChessPiece는 자신의 행과 열을 알고있다. 
	 * 이것을 이용하여 조각을 배치할 위치를 아는 것이 가능하다.
	 */
	public void addPiece(ChessPiece piece)
	{
		// 이곳을 채우시오.
	}
	
	/* 보드에서 지정된 위치의 조각을 제거한다.
	 */
	public void removePiece(int row, int col)
	{
		//이곳을 채우시오.
	}
	
}
