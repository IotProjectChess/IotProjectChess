package net.iot.iotprojectchess;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * Created by user on 2018-02-02.
 */

public class Utiles implements Labels {
    int[][] arr_position = new int[8][8];

    public Utiles(){
        setPosition();
    }
    // 받아온 position 값을 2차원배열 위치로 변환후 리턴
    public  int[] positionToArray(int position){
        int[] result_Position = new int[2];
        for(int i=0; i<arr_position.length; i++){
            for(int j=0; j<arr_position[i].length; j++){
                if(arr_position[i][j] == position) {
                    result_Position[0] = i;
                    result_Position[1] = j;
                }
            }
        }
        return result_Position;
    }
    public int xyToPosition(int x, int y){
        if(x>=0 && x<=7 && y>=0 && y<=7) return arr_position[x][y];
        else return OUT_OF_ARRAY;
    }
    // 2차원 배열에 순서대로 1부터 64까지 값 넣어두기
    public void setPosition(){
        int count=0;
        for(int i=0; i<arr_position.length; i++){
            for(int j=0; j< arr_position.length; j++){
                arr_position[i][j] = count++;
            }
        }
    }

    public int getid(GridAdapter_ChessPieces piece,int x, int y){
        return (int)piece.getItemId(xyToPosition(x , y));
    }
    public int getTeam(GridAdapter_ChessPieces piece, int x, int y){
        return getPieceTeam(xyToPosition(x , y), piece);
    }

    public int getPieceTeam(int position, GridAdapter_ChessPieces piece) {
        if (position == OUT_OF_ARRAY) return OUT_OF_ARRAY;
        if (piece.getItemId(position) != BLANK) {
            switch ((int) piece.getItemId(position)) {
                case W_PAWN:
                case W_KNIGHT:
                case W_BISHOP:
                case W_QUEEN:
                case W_KING:
                case W_CASTLE:
                    return WHITE_TEAM;
                case B_PAWN:
                case B_KNIGHT:
                case B_BISHOP:
                case B_QUEEN:
                case B_KING:
                case B_CASTLE:
                    return BLACK_TEAM;
                default:
                    break;
            }
        }
        return BLANK;
    }

    public String getPieceName(int position, GridAdapter_ChessPieces piece) {
        if(position == OUT_OF_ARRAY) return null;
        if (piece.getItemId(position) != BLANK) {
            switch ((int) piece.getItemId(position)) {
                case W_PAWN:
                    return "W_PAWN";
                case W_KNIGHT:
                    return "W_KINGHT";
                case W_BISHOP:
                    return "W_BISHOP";
                case W_QUEEN:
                    return "W_QUEEN";
                case W_KING:
                    return "W_KING";
                case W_CASTLE:
                    return "W_CASTLE";
                case B_PAWN:
                    return "B_PAWN";
                case B_KNIGHT:
                    return "B_KINGHT";
                case B_BISHOP:
                    return "B_BISHOP";
                case B_QUEEN:
                    return "B_QUEEN";
                case B_CASTLE:
                    return "B_CASTLE";
                case B_KING:
                    return "B_KING";
                case BLANK:
                    return "BLANK";
                default:
                    break;
            }
        }
        return "BLANK";
    }

    // getView의 ImageView 세팅
    public void setImageView(ImageView[] imageView, int position, ViewGroup parent, int[] images, Context context){
        int width = parent.getResources().getDisplayMetrics().widthPixels/8;
        imageView[position] = new ImageView(context);
        imageView[position].setLayoutParams(new GridView.LayoutParams(width,width));
        imageView[position].setScaleType(ImageView.ScaleType.FIT_CENTER);
        imageView[position].setPadding(0,0,0,0);
        imageView[position].setImageResource(images[position]);
    }
}
