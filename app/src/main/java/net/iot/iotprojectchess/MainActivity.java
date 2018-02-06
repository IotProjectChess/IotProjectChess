package net.iot.iotprojectchess;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView system_Message;
    GridAdapter_ChessPieces piece;
    GridAdapter_ChessBlock block;
    GridAdapter_ChessBoard board;

    GridView gv_ChessBoard;
    GridView gv_ChessPieces;
    GridView gv_ChessBlock;

    Utiles utile = new Utiles();

    final static int MATCH = LinearLayout.LayoutParams.MATCH_PARENT;
    final static int W_PAWN = R.drawable.w_pawn;
    final static int W_KINGHT = R.drawable.w_knight;
    final static int W_BISHOP = R.drawable.w_bishop;
    final static int W_QUEEN = R.drawable.w_queen;
    final static int W_KING = R.drawable.w_king;
    final static int W_CASTLE = R.drawable.w_castle;
    final static int B_PAWN = R.drawable.b_pawn;
    final static int B_KINGHT = R.drawable.b_knight;
    final static int B_BISHOP = R.drawable.b_bishop;
    final static int B_QUEEN = R.drawable.b_queen;
    final static int B_KING = R.drawable.b_king;
    final static int B_CASTLE = R.drawable.b_castle;
    final static int BLANK = R.drawable.block_blank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window win = getWindow();
        win.setContentView(R.layout.activity_main);
        system_Message = (TextView)findViewById(R.id.sys_message);
        setLayout(win);

    }

    public void GameStart(AdapterView<?> parent, View view, int position, long id){
        String PieceName = getPieceName(position); //샘플 소스 : 선택된 블록의 이름(말 or 블록) 가져오기
        system_Message.setText("클릭위치 : " + position + ", 블록이름 : " + PieceName);
        int[] xy = utile.positionToArray(position);
        int x = xy[0]; int y = xy[1]; //좌표값

        /*
        //1. 선택된(빨강) 블록 표시하기 & 선택된(빨강) 블록 지우기
        visibleSelectedBlock(x,y);
        invisibleSelectedBlock(x,y);
        //2. 움직임가능한(노랑) 블록 표시하기 & 움직임가능한(노랑) 블록 지우기
        visibleisMovingBlock(x,y);
        invisibleisMovingBlock(x,y);
        //3. 말 지우기 & 말 표시하기
        visiblePiece(W_BISHOP,x,y);
        invisiblePiece(x,y);
        */

    }

    public void setLayout(Window win){
        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout.LayoutParams paramlinear = new LinearLayout.LayoutParams(MATCH,MATCH);

        LinearLayout layout_pieces = (LinearLayout)inflater.inflate(R.layout.gridview_pieces,null);
        LinearLayout layout_block = (LinearLayout)inflater.inflate(R.layout.gridview_block,null);

        win.addContentView(layout_pieces,paramlinear);
        win.addContentView(layout_block,paramlinear);

        gv_ChessBoard = (GridView)findViewById(R.id.gv_chessboard);
        gv_ChessPieces = (GridView)findViewById(R.id.gv_pieces);
        gv_ChessBlock = (GridView)findViewById(R.id.gv_block);

        board = new GridAdapter_ChessBoard(this);
        piece = new GridAdapter_ChessPieces(this);
        block = new GridAdapter_ChessBlock(this);

        gv_ChessBoard.setAdapter(board);
        gv_ChessPieces.setAdapter(piece);
        gv_ChessBlock.setAdapter(block);

        gv_ChessBlock.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                GameStart(parent, view, position, id);
            }
        });
    }

    public String getPieceName(int position){
        if(piece.getItemId(position) != BLANK)
        {
            switch ((int)piece.getItemId(position)) {
                case W_PAWN:return "W_PAWN";
                case W_KINGHT:return "W_KINGHT";
                case W_BISHOP:return "W_BISHOP";
                case W_QUEEN:return "W_QUEEN";
                case W_KING:return "W_KING";
                case W_CASTLE:return "W_CASTLE";
                case B_PAWN:return "B_PAWN";
                case B_KINGHT:return "B_KINGHT";
                case B_BISHOP:return "B_BISHOP";
                case B_QUEEN:return "B_QUEEN";
                case B_KING:return "B_KING";
                case B_CASTLE:return "B_CASTLE";
                default:break;
            }
        }
        return null;
    }
    public void visibleSelectedBlock(int x, int y){
        block.imageView_blankBlock[x][y].setImageResource(R.drawable.block_selected);
    }
    public void invisibleSelectedBlock(int x, int y){
        block.imageView_blankBlock[x][y].setImageResource(R.drawable.block_blank);
    }
    public void visibleisMovingBlock(int x, int y){
        block.imageView_blankBlock[x][y].setImageResource(R.drawable.block_ismovie);
    }
    public void invisibleisMovingBlock(int x, int y){
        block.imageView_blankBlock[x][y].setImageResource(R.drawable.block_blank);
    }
    public void visiblePiece(int pieces, int x, int y){
        piece.imageView_Pieces[x][y].setImageResource(pieces);
    }
    public void invisiblePiece(int x, int y){
        piece.imageView_Pieces[x][y].setImageResource(R.drawable.block_blank);
    }
}