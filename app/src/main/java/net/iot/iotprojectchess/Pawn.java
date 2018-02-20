package net.iot.iotprojectchess;

/**
 * Created by user on 2018-02-09.
 */

public class Pawn extends Pieces {

    public Pawn(int teamColor, GridAdapter_ChessPieces piece, int x, int y) {super(teamColor, piece , x, y);}

    public int[][] getisMoveblock() {
        if(myteamColor == WHITE_TEAM)
            isMoveblock = getisMoveW_PAWN(isMoveblock);
        else if(myteamColor == BLACK_TEAM)
            isMoveblock = getisMoveB_PAWN(isMoveblock);


        return isMoveblock;
    }
    public int[][] getisMoveB_PAWN(int[][] mblock) {
        int count = 0;
        int x = myPosition_X;
        int y = myPosition_Y;

        //우하방향(대각선)에 적블록이 있을경우
        if(getTeam(x+1,y+1) == WHITE_TEAM) setisMoveblock(x+1,y+1,count++);
        //좌하방향(대각선)에 적블록이 있을경우
        if(getTeam(x+1,y-1) == WHITE_TEAM) setisMoveblock(x+1,y-1,count++);
        // 처음 위치에 있을땐 2칸 움직이고 그외에는 한칸씩만 가능
        if (x == 1) {
            if(getid(x+1,y)==BLANK) setisMoveblock(x+1,y,count++);
            if(getid(x+2,y)==BLANK) setisMoveblock(x+2,y,count++);
        } else if(getid(x+1,y) == BLANK) setisMoveblock(x+1,y,count++);

        return mblock;
    }
    public int[][] getisMoveW_PAWN(int[][] mblock) {
        int count = 0;
        int x = myPosition_X;
        int y = myPosition_Y;

        //우상방향(대각선)에 적블록이 있을경우
        if (getTeam(x - 1, y + 1) == BLACK_TEAM) setisMoveblock(x - 1, y + 1, count++);

        //좌상방향(대각선)에 적블록이 있을경우
        if(getTeam(x-1,y-1) == BLACK_TEAM) setisMoveblock(x-1,y-1,count++);

        // 처음 위치에 있을땐 2칸 움직이고 그외에는 한칸씩만 가능
        if (x == 6) {
            if(getid(x-1,y) == BLANK) setisMoveblock(x-1,y,count++);
            if(getid(x-2,y) == BLANK) setisMoveblock(x-2,y,count++);
        } else if(getid(x-1,y) == BLANK) setisMoveblock(x-1,y,count++);

        return mblock;
    }
}
