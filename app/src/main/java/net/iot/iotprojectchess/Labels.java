package net.iot.iotprojectchess;

import android.widget.LinearLayout;

/**
 * Created by user on 2018-02-12.
 */

public interface Labels {
    int MATCH = LinearLayout.LayoutParams.MATCH_PARENT;
    int CHECK = 96;
    int INT_NULL = 99;
    int BLACK_TEAM = 98;
    int WHITE_TEAM = 97;
    int OUT_OF_ARRAY = 100;
    int W_PAWN = R.drawable.w_pawn;
    int W_KNIGHT = R.drawable.w_knight;
    int W_BISHOP = R.drawable.w_bishop;
    int W_QUEEN = R.drawable.w_queen;
    int W_KING = R.drawable.w_king;
    int W_CASTLE = R.drawable.w_castle;
    int B_PAWN = R.drawable.b_pawn;
    int B_KNIGHT = R.drawable.b_knight;
    int B_BISHOP = R.drawable.b_bishop;
    int B_QUEEN = R.drawable.b_queen;
    int B_KING = R.drawable.b_king;
    int B_CASTLE = R.drawable.b_castle;
    int BLANK = R.drawable.block_blank;
    int IS_MOVE_BLOCK = R.drawable.block_ismovie;
    int CHECK_BLOCK = R.drawable.block_check;
}
