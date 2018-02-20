package net.iot.iotprojectchess;

/**
 * Created by user on 2018-02-14.
 */

public class Knight extends Pieces {
    public Knight(int teamColor, GridAdapter_ChessPieces piece, int x, int y) {super(teamColor, piece , x, y);}
    @Override
    public int[][] getisMoveblock() {
        int count = 0;
        int x = myPosition_X;
        int y = myPosition_Y;

        //앞으로 두번의 좌우에 적이 있거나 BLANK일경우
        if(getTeam(x-2,y+1) == enemyteamColor || getid(x-2,y+1) == BLANK) setisMoveblock(x-2,y+1,count++);
        if(getTeam(x-2,y-1) == enemyteamColor || getid(x-2,y-1) == BLANK) setisMoveblock(x-2,y-1,count++);
        //아래로 두번의 좌우에 적이 있거나 blank 일경우
        if(getTeam(x+2,y+1) == enemyteamColor || getid(x+2,y+1) == BLANK) setisMoveblock(x+2,y+1,count++);
        if(getTeam(x+2,y-1) == enemyteamColor || getid(x+2,y-1) == BLANK) setisMoveblock(x+2,y-1,count++);
        //좌로 두번의 위아래가 적이 있거나 blank 일경우
        if(getTeam(x-1,y-2) == enemyteamColor || getid(x-1,y-2) == BLANK) setisMoveblock(x-1,y-2,count++);
        if(getTeam(x+1,y-2) == enemyteamColor || getid(x+1,y-2) == BLANK) setisMoveblock(x+1,y-2,count++);
        //우로 두번의 위아래가 적이 있거나 blank 일경우
        if(getTeam(x-1,y+2) == enemyteamColor || getid(x-1,y+2) == BLANK) setisMoveblock(x-1,y+2,count++);
        if(getTeam(x+1,y+2) == enemyteamColor || getid(x+1,y+2) == BLANK) setisMoveblock(x+1,y+2,count++);

        return isMoveblock;
    }
}
