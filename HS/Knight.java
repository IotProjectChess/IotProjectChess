/*
 * Name:
 * Section Leader:
 * File: Knight.java
 * ------------------
 * 이 클래스는 Knight이고 ChessPiece를 상속받는다.
 * Knight는 L모양으로 움직일 수 있다.
 * 다른 말을 뛰어넘을 수 있다.
 */

public class Knight extends ChessPiece{

	/**나이트클래스의 생성자 */
	public Knight(int initialRow, int initialCol, int pieceColor)
	{
		this.row = initialRow;
		this.col = initialCol;
		this.color = pieceColor;
	}		
	
	//이 메서드는 나이트가 지정된 장소로 움직일 수 있는지 없는 지 불리언으로 판단하여 반환한다.
	public boolean canMoveTo(int nextRow, int nextCol, ChessBoard board)
	{
		// 여기에 코드를 채우시오.
		return automagicKnightCanMoveTo(nextRow, nextCol, board);	// Eventually this line should not be here
		//특히 이 줄은 여기 있어서는 안된다.(라는 뜻인데 무슨 의미지?)
	}
	
	//나이트클래스의 getType메서드의 구현부이다. 나이트타입을 불러올 수 있다.(이 부분은 HS만의 생각 보충설명필요)
		//완성된 메서드이므로 변경할 필요는 없다.
	public PieceType getType() 
	{
		return PieceType.KNIGHT;
	}
	
}
