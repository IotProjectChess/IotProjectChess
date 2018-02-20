package net.iot.iotprojectchess;

import android.widget.LinearLayout;

/**
 * Created by user on 2018-02-08.
 */

public class VirtualChessGame {
    int[][] virtualChees = new int[8][8];
    final static int VR_W_CASTLE_1 = 81;
    final static int VR_W_KINGHT_1 = 82;
    final static int VR_W_BISHOP_1 = 83;
    final static int VR_W_QUEEN = 84;
    final static int VR_W_KING = 85;
    final static int VR_W_BISHOP_2 = 86;
    final static int VR_W_KINGHT_2 = 87;
    final static int VR_W_CASTLE_2 = 88;
    final static int VR_W_PAWN_1 = 71;
    final static int VR_W_PAWN_2 = 72;
    final static int VR_W_PAWN_3 = 73;
    final static int VR_W_PAWN_4 = 74;
    final static int VR_W_PAWN_5 = 75;
    final static int VR_W_PAWN_6 = 76;
    final static int VR_W_PAWN_7 = 77;
    final static int VR_W_PAWN_8 = 78;

    final static int VR_B_CASTLE_1 = 11;
    final static int VR_B_KINGHT_1 = 12;
    final static int VR_B_BISHOP_1 = 13;
    final static int VR_B_QUEEN = 14;
    final static int VR_B_KING = 15;
    final static int VR_B_BISHOP_2 = 16;
    final static int VR_B_KINGHT_2 = 17;
    final static int VR_B_CASTLE_2 = 18;
    final static int VR_B_PAWN_1 = 21;
    final static int VR_B_PAWN_2 = 22;
    final static int VR_B_PAWN_3 = 23;
    final static int VR_B_PAWN_4 = 24;
    final static int VR_B_PAWN_5 = 25;
    final static int VR_B_PAWN_6 = 26;
    final static int VR_B_PAWN_7 = 27;
    final static int VR_B_PAWN_8 = 28;


    final static int VR_SELECTED_BLOCK = 50;
    final static int VR_ISMOVE_BLOCK = 51;
    final static int VR_BLANK_BLOCK = 52;


    public VirtualChessGame() {
        settingPiecesToBoard();
    }
    public void settingPiecesToBoard(){
        for(int i=0; i<virtualChees.length; i++) {
            for (int j = 0; j < virtualChees[i].length; j++) {
                virtualChees[i][j] = (i * 10) + 11 + j;
            }
        }
    }
    public int[] getPiecePosition(int Piece_Name){
        int[] position = new int[2];
        for(int i=0; i<virtualChees.length; i++){
            for(int j=0; j<virtualChees[i].length; j++){
                if(virtualChees[i][j] == Piece_Name){
                    position[0] = i; position[1] = j;
                }
            }
        }
        return position;
    }
    public void movingPiece(int Piece, int x, int y){
        int[] position = getPiecePosition(Piece);
        int p_x = position[0]; int p_y = position[1];
        virtualChees[p_x][p_y] = VR_BLANK_BLOCK;
        virtualChees[x][y] = Piece;
    }
}
