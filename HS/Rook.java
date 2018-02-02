/*
 * Name:
 * Section Leader:
 * File: Rook.java
 * ------------------
 * 이 클래스는 Rook이고 ChessPiece를 상속받는다.
 * 룩은 일직선으로 움직인다.
 * 캐슬링기능을 추가할 수 있다.
 */

public class Rook extends ChessPiece{

	/**룩의 생성자*/
	public Rook(int initialRow, int initialCol, int pieceColor)
	{
		this.row = initialRow;
		this.col = initialCol;
		this.color = pieceColor;
	}	

	//이 메서드는 퀸이 지정된 장소로 움직일 수 있는지 없는 지 불리언으로 판단하여 반환한다.
	public boolean canMoveTo(int nextRow, int nextCol, ChessBoard board)
	{
		// 여기에 코드를 채우시오.
		return automagicRookCanMoveTo(nextRow, nextCol, board);	// Eventually this line should not be here
		//특히 이 줄은 여기 있어서는 안된다.(라는 뜻인데 무슨 의미지?)
	}

	//룩클래스의 getType메서드의 구현부이다. 룩타입을 불러올 수 있다.(이 부분은 HS만의 생각 보충설명필요)
	//완성된 메서드이므로 변경할 필요는 없다.
	public PieceType getType() 
	{
		return PieceType.ROOK;
	}

}
