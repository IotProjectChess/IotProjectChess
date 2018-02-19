package net.iot.iotprojectchess;

/**
 * Created by user on 2018-02-14.
 */

public class King extends Pieces {
    public King(int teamColor,GridAdapter_ChessPieces piece, int x, int y) { super(teamColor,piece, x, y); }
    @Override
    public int[][] getisMoveblock() {
        int count = 0;
        int x = myPosition_X;
        int y = myPosition_Y;

        //전방향 한칸씩 적블록이거나 blank 일경우
        if(getTeam(x-1,y) == enemyteamColor || getid(x-1,y) == BLANK) setisMoveblock(x-1,y,count++);
        if(getTeam(x-1,y-1) == enemyteamColor || getid(x-1,y-1) == BLANK) setisMoveblock(x-1,y-1,count++);
        if(getTeam(x-1,y+1) == enemyteamColor || getid(x-1,y+1) == BLANK) setisMoveblock(x-1,y+1,count++);
        if(getTeam(x,y-1) == enemyteamColor || getid(x,y-1) == BLANK) setisMoveblock(x,y-1,count++);
        if(getTeam(x,y+1) == enemyteamColor || getid(x,y+1) == BLANK) setisMoveblock(x,y+1,count++);
        if(getTeam(x+1,y) == enemyteamColor || getid(x+1,y) == BLANK) setisMoveblock(x+1,y,count++);
        if(getTeam(x+1,y-1) == enemyteamColor || getid(x+1,y-1) == BLANK) setisMoveblock(x+1,y-1,count++);
        if(getTeam(x+1,y+1) == enemyteamColor || getid(x+1,y+1) == BLANK) setisMoveblock(x+1,y+1,count++);
        return isMoveblock;
    }
}
