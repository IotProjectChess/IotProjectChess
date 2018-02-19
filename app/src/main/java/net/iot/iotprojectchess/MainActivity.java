package net.iot.iotprojectchess;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements Labels {
    TextView system_Message; // 시스템 메세지 출력
    AlertDialog.Builder alert; // 이겼을때 다이얼로그 출력
    String sys = "System Message: "; // 시스템 메세지 초반 텍스트

    public int checkTurn = WHITE_TEAM; //누구의 턴인지 체크하는 변수, 처음 턴은 화이트 팀
    int[][] save_position = new int[64][2]; // 기존에 표시했던 블록들(클릭상태, 움직임가능상태, 체크상태 등)의 위치값을 순서대로 저장하는 블록
    int save_position_count; // save_position이 순서대로 저장되도록 카운트를 세주는 블록, 매 클릭마다 모든 이벤트를 처리한 후 0으로 초기화된다.

    boolean isClickPiece = false; // 말을 클릭했는지 확인하는 변수
    boolean isClickYelloBlock = false; // 노란블록을 클릭했는지 확인하는 변수
    boolean isCheck = false; // 현재 체크인지 확인하는변수

    // 이미지가 배열식으로 저장된 어댑터클래스
    public static GridAdapter_ChessPieces piece; // 말의 움직임, 조건식을 처리하는데 쓰임 매우중요
    public static GridAdapter_ChessBlock block; // 주로 블록의 상태를 바꾸는데 쓰임
    public static GridAdapter_ChessBoard board; // 표시용이라 쓰이지 않아서 중요하지 않음.

    // 어댑터를 받아 출력하는 인스턴스 변수
    GridView gv_ChessBoard;
    GridView gv_ChessPieces;
    GridView gv_ChessBlock;

    Utiles utile = new Utiles(); //중요 기능들을 모아놓은 tools 개념 클래스
    Pieces pieces;  // 말들의 클래스의 부모 클래스, 다형성을 위한 변수

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window win = getWindow();
        win.setContentView(R.layout.activity_main);
        // 뷰들 초기화
        system_Message = (TextView) findViewById(R.id.sys_message);
        alert = new AlertDialog.Builder(this);
        settingSave_Position(); // save_position의 모든 값을 INT_NULL로 초기화
        save_position[0][0] = INT_NULL;
        setLayout(win); // 어댑터 연결및 클릭이벤트를 처리하는 중요한 메서드 이곳에서 GameStart가 실행됨.

    }

    public void GameStart(int position) {
        String PieceName = utile.getPieceName(position,piece); //샘플 소스 : 선택된 블록의 이름(말 or 블록) 가져오기
        system_Message.setText("클릭위치 : " + position + ", 블록이름 : " + PieceName);
        int[] xy = utile.positionToArray(position);
        int x = xy[0]; int y = xy[1]; //좌표값
        int save_x = save_position[0][0]; int save_y = save_position[0][1]; // 전에 저장된 좌표값

        //처음 클릭한 위치와 같은곳을 클릭하면 모든 체크 블록 지우기
        if (save_x == x && save_y == y)
            removePreBlock();
        else {
            //1. 말이 클릭된 상태일때 저장된 노란색 블록을 클릭할 경우
            if (isClickPiece && (int)block.getItemId(position) == IS_MOVE_BLOCK) {
                int check_team = 0;
                visiblePiece(utile.getid(piece,save_x,save_y),x,y);
                invisiblePiece(save_x,save_y);
                removePreBlock();
                isClickYelloBlock = true;
                check_team = chess_check((int)piece.getItemId(position), x, y);
                isClickYelloBlock = false;
                if(isCheck)check_Mate(check_team);
                isCheck = false;
                checkTurn = checkTurn == BLACK_TEAM ? WHITE_TEAM : BLACK_TEAM;
            }
            //2. 말이 클릭되지 않은 상태일때, 혹은 말은 클릭되도 노란색 말고 다른 블록을 클릭한 경우
            else {
                removePreBlock();
                visibleSelectedBlock(x, y);
                // 현재 팀의 말을 클릭 했을때 움직일 수 있는 블록 표시
                if (piece.getItemId(position) != BLANK) {
                    if (utile.getPieceTeam(position,piece) == checkTurn) {
                        makeIsMoveBlock((int)piece.getItemId(position), position);
                        isClickPiece = true;
                    }
                }
            }
        }
        save_position_count = 0;
    }
    public boolean teamCheck(){
        int check_team = 0;
        for(int i=0; i<64; i++) {
            int[] xy = utile.positionToArray(i);
            int x = xy[0]; int y = xy[1];
            check_team = chess_check((int)piece.getItemId(i), x, y);
            if(check_team == checkTurn) return true; //
        }
        return false;
    }
    public int chess_check(int PieceID, int x,int y){
        int isMoveblock[][];
        int check_team = 0;
        setPieceClass(PieceID,x,y);
        if(pieces != null) {
            isMoveblock = pieces.getisMoveblock();
            for(int i=0; isMoveblock[i][0] != INT_NULL; i++){
                String chk_piece = utile.getPieceName(utile.xyToPosition(isMoveblock[i][0],isMoveblock[i][1]),piece);
                if(chk_piece.substring(2,chk_piece.length()).equals("KING")) {
                    check_team = pieces.enemyteamColor== WHITE_TEAM ? WHITE_TEAM : BLACK_TEAM;
                    if(isClickYelloBlock) {
                        String team_MSG = pieces.enemyteamColor== WHITE_TEAM ? "화이트팀이":"블랙팀이";
                        system_Message.setText(sys + team_MSG+" 체크당했습니다!!");
                        visibleCheckBlock(x,y,isMoveblock[i][0],isMoveblock[i][1]);
                        isCheck = true;
                    }
                    break;
                }
            }
            pieces = null;
        }
        return check_team;
    }
    public void check_Mate(int check_team){
        boolean isCheckMate = true;
        for(int i=0; i<64; i++){
            int PieceID = (int)piece.getItemId(i);
            if(utile.getPieceTeam(i,piece) == check_team && checkIsMoveBlock(PieceID,i)) {
                isCheckMate = false;
            }

        }
        if(isCheckMate == true){
            System.out.println("게임종료해야됨");
            String winner = check_team == WHITE_TEAM ?  "블랙팀" : "화이트팀";
            alert.setTitle("체크메이트!!");
            alert.setMessage(winner + "의 승리입니다!!");
            alert.setPositiveButton("게임종료", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            alert.show();
        }
    }
    public boolean checkIsMoveBlock(int PieceID, int position) {
        int isMoveblock[][];
        int xy[] = utile.positionToArray(position);
        int x = xy[0];
        int y = xy[1];
        boolean isMove = false;


        setPieceClass(PieceID,x,y);

        if(pieces != null) {
            checkTurn = checkTurn == WHITE_TEAM ? BLACK_TEAM : WHITE_TEAM;
            isMoveblock = pieces.getisMoveblock();
            for (int i = 0; isMoveblock[i][0] != INT_NULL; i++) {
                if(checkSafeblock(PieceID, x, y, isMoveblock[i][0], isMoveblock[i][1])) {
                    System.out.println(utile.getPieceName(position,piece) + "가 움직일수 있는 위치 : [" +  isMoveblock[i][0] + "," + isMoveblock[i][1] + "]");
                    isMove = true;
                }
            }
            checkTurn = checkTurn == WHITE_TEAM ? BLACK_TEAM : WHITE_TEAM;
        }
        pieces = null;
        return isMove;
    }
    public boolean makeIsMoveBlock(int PieceID, int position) {
        int isMoveblock[][];
        int xy[] = utile.positionToArray(position);
        int x = xy[0];
        int y = xy[1];
        boolean isMove = false;


        setPieceClass(PieceID,x,y);

        if(pieces != null) {
            isMoveblock = pieces.getisMoveblock();
            for (int i = 0; isMoveblock[i][0] != INT_NULL; i++) {
                if(checkSafeblock(PieceID, x, y, isMoveblock[i][0], isMoveblock[i][1])) {
                    visibleisMovingBlock(isMoveblock[i][0], isMoveblock[i][1]);
                    isMove = true;
                }
            }
        }
        pieces = null;
        return isMove;
    }

    public boolean checkSafeblock(int PieceId, int x,int y , int toX, int toY){
        boolean check_team = false;
        int savePieceID = BLANK;

        if(utile.getid(piece,toX,toY) != BLANK) savePieceID = utile.getid(piece,toX,toY);

        visiblePiece(PieceId,toX,toY);
        invisiblePiece(x,y);

        check_team = teamCheck();

        visiblePiece(PieceId,x,y);
        visiblePiece(savePieceID,toX,toY);

        if(check_team) return false;
        else return true;

    }
    public void setPieceClass(int PieceID, int x,int y){
        switch (PieceID) {
            case W_PAWN:
                pieces = new Pawn(WHITE_TEAM,piece,x,y);
                break;
            case B_PAWN:
                pieces = new Pawn(BLACK_TEAM,piece,x,y);
                break;
            case W_CASTLE:
                pieces = new Castle(WHITE_TEAM,piece,x,y);
                break;
            case B_CASTLE:
                pieces = new Castle(BLACK_TEAM,piece,x,y);
                break;
            case W_KNIGHT:
                pieces = new Knight(WHITE_TEAM,piece,x,y);
                break;
            case B_KNIGHT:
                pieces = new Knight(BLACK_TEAM,piece,x,y);
                break;
            case W_BISHOP:
                pieces = new Bishop(WHITE_TEAM,piece,x,y);
                break;
            case B_BISHOP:
                pieces = new Bishop(BLACK_TEAM,piece,x,y);
                break;
            case W_QUEEN:
                pieces = new Queen(WHITE_TEAM,piece,x,y);
                break;
            case B_QUEEN:
                pieces = new Queen(BLACK_TEAM,piece,x,y);
                break;
            case W_KING:
                pieces = new King(WHITE_TEAM,piece,x,y);
                break;
            case B_KING:
                pieces = new King(BLACK_TEAM,piece,x,y);
                break;
        }
    }
    public void visibleSelectedBlock(int x, int y) {
        block.setImageResource(x,y,R.drawable.block_selected);
        save_Position(x, y);
    }
    public void invisibleSelectedBlock(int x, int y) {
        block.setImageResource(x,y,BLANK);
    }
    public void visibleisMovingBlock(int x, int y) {
        block.setImageResource(x,y,R.drawable.block_ismovie);
        save_Position(x, y);
    }
    public void invisibleisMovingBlock(int x, int y) {
        block.setImageResource(x,y,BLANK);
    }
    public void visibleCheckBlock(int x1, int y1, int x2, int y2) {
        block.setImageResource(x1,y1,CHECK_BLOCK);
        block.setImageResource(x2,y2,CHECK_BLOCK);
        save_Position(x1,y1);
        save_Position(x2,y2);
    }
    public void visiblePiece(int pieces, int x, int y) {
        piece.setImageResource(x,y,pieces);
    }
    public void invisiblePiece(int x, int y) {
        piece.setImageResource(x,y,BLANK);
    }
    public void removePreBlock() {
        for (int i = 0; i < save_position.length; i++) {
            if (save_position[i][0] != INT_NULL) {
                invisibleisMovingBlock(save_position[i][0], save_position[i][1]);
                invisibleSelectedBlock(save_position[i][0], save_position[i][1]);
                save_position[i][0] = INT_NULL;
                save_position[i][1] = INT_NULL;
                isClickPiece = false;
            } else break;
        }
    }
    public void settingSave_Position() {
        if (save_position[0][0] != INT_NULL) {
            for (int i = 0; i < save_position.length; i++) {
                for (int j = 0; j < save_position[i].length; j++)
                    save_position[i][j] = INT_NULL;
            }
        }
    }
    public void save_Position(int x, int y) {
        save_position[save_position_count][0] = x;
        save_position[save_position_count++][1] = y;
    }
    public void setLayout(Window win) {
        // 현재의 윈도우를 받아와 추가하기위한 기본세팅,
        // 레이아웃 xml에 명시한 위젯을 메모리로 가져오는 역할(inflater)
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout.LayoutParams paramlinear = new LinearLayout.LayoutParams(MATCH, MATCH);

        // xml을 inflater로 변수에 저장
        LinearLayout layout_pieces = (LinearLayout) inflater.inflate(R.layout.gridview_pieces, null);
        LinearLayout layout_block = (LinearLayout) inflater.inflate(R.layout.gridview_block, null);

        // 저장한 레이아웃을 window에 추가
        win.addContentView(layout_pieces, paramlinear);
        win.addContentView(layout_block, paramlinear);

        //레이아웃(xml) 안에 있는 그리드뷰 연결
        gv_ChessBoard = (GridView) findViewById(R.id.gv_chessboard);
        gv_ChessPieces = (GridView) findViewById(R.id.gv_pieces);
        gv_ChessBlock = (GridView) findViewById(R.id.gv_block);

        // 어댑터 생성
        board = new GridAdapter_ChessBoard(this);
        piece = new GridAdapter_ChessPieces(this);
        block = new GridAdapter_ChessBlock(this);

        // 그리드뷰에 어댑터를 연결
        gv_ChessBoard.setAdapter(board);
        gv_ChessPieces.setAdapter(piece);
        gv_ChessBlock.setAdapter(block);

        //클릭한 블록에 따라 이벤트를 처리하는 함수, 여기서 GameStart가 실행됨.
        gv_ChessBlock.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                GameStart(position);
            }
        });
    }
}