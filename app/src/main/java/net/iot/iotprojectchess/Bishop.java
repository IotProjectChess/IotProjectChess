package net.iot.iotprojectchess;

/**
 * Created by user on 2018-02-14.
 */

public class Bishop extends Pieces {
    public Bishop(int teamColor,GridAdapter_ChessPieces piece, int x, int y) { super(teamColor,piece, x, y); }
    @Override
    public int[][] getisMoveblock() {
        int count = 0;

        count = getisMoveCastle(-1,-1,count); //좌상방향
        count = getisMoveCastle(-1,1,count); //우상방향
        count = getisMoveCastle(1,-1,count); //좌하방향
        getisMoveCastle(1,1,count); //우하방향

        return isMoveblock;
    }

    public int getisMoveCastle(int px, int py, int count){
        int x = myPosition_X;
        int y = myPosition_Y;

        for(int i=0; i<8; i++){
            x += px; y+= py;
            if(getid(x,y) == BLANK) setisMoveblock(x,y,count++);
            else if(getTeam(x,y) == enemyteamColor) {
                setisMoveblock(x,y,count++);
                break;
            }
            else if(getTeam(x,y) == myteamColor) break;

        }
        return count;
    }
}
