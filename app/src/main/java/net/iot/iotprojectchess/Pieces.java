package net.iot.iotprojectchess;

/**
 * Created by user on 2018-02-12.
 */

public abstract class Pieces implements Labels{
    Utiles utile = new Utiles();
    GridAdapter_ChessPieces piece;

    public int isMoveblock[][] = new int[100][2];
    public int myteamColor;
    public int enemyteamColor;
    int myPosition_X;
    int myPosition_Y;

    public Pieces(){
    }
    public Pieces(int teamColor,GridAdapter_ChessPieces piece, int x, int y){
        myteamColor = teamColor;
        this.piece = piece;
        myPosition_X = x;
        myPosition_Y = y;
        enemyteamColor = myteamColor == BLACK_TEAM ? WHITE_TEAM : BLACK_TEAM;

        for(int i=0; i<isMoveblock.length; i++){
            for(int j=0; j<isMoveblock[i].length; j++)
                isMoveblock[i][j] = INT_NULL;
        }
    }



    public int getid(int x, int y){
        return (int)piece.getItemId(utile.xyToPosition(x , y));
    }
    public int getTeam(int x, int y){
        return utile.getPieceTeam(utile.xyToPosition(x , y), piece);
    }
    public void setisMoveblock(int x, int y, int count){
        if(utile.getPieceName((int)piece.getItemId(utile.xyToPosition(x,y)),piece).substring(2,5).equals("KING"))
            isMoveblock[count][0] = CHECK;
        else {
            isMoveblock[count][0] = x;
            isMoveblock[count][1] = y;
        }
    }

    public abstract int[][] getisMoveblock();

}
