/*
 * Name:
 * Section Leader:
 * File: Pawn.java
 * ------------------
 * 
 * 이 클래스는 폰클래스이다. ChecePiece를 상속받는다.
 * 이건 오직 앞으로만 움직일 수 있다.
 * 처음에는 두칸이동이 가능하며 capture(잡아먹기)를 할 때에는 대각선으로 잡아먹을 수 있다.
 * 앙파상의 기능이 추가될 수 있다.
 */

public class Pawn extends ChessPiece{

	/** 폰의 생성자 */
	public Pawn(int initialRow, int initialCol, int pieceColor)
	{
		this.row = initialRow;
		this.col = initialCol;
		this.color = pieceColor;
	}	

	//이 메서드는 폰이 지정된 장소로 움직일 수 있는지 없는 지 불리언으로 판단하여 반환한다.
	public boolean canMoveTo(int nextRow, int nextCol, ChessBoard board)
	{
		// 여기에 코드를 채우시오.
		return automagicPawnCanMoveTo(nextRow, nextCol, board);	// Eventually this line should not be here
		//특히 이 줄은 여기 있어서는 안된다.(라는 뜻인데 무슨 의미지?)
	}
	
	//폰클래스의 getType메서드의 구현부이다. 폰타입을 불러올 수 있다.(이 부분은 HS만의 생각 보충설명필요)
	//완성된 메서드이므로 변경할 필요는 없다.
	public PieceType getType() 
	{
		return PieceType.PAWN;
	}
	
}
