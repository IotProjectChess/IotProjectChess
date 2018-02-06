package net.iot.iotprojectchess;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

/**
 * Created by user on 2018-02-06.
 */

public class GridAdapter_ChessPieces extends BaseAdapter {
    Context context;
    ImageView[] imageView = new ImageView[64];
    ImageView[][] imageView_Pieces = new ImageView[8][8];
    int[] images = new int[64];
    Utiles utile = new Utiles();

    public GridAdapter_ChessPieces(Context c){
        setPiecesid();
        context = c;
    }

    @Override
    public int getCount() {return images.length;}

    @Override
    public Object getItem(int position) {return null;}

    @Override
    public long getItemId(int position) {return images[position];}

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        utile.setImageView(imageView,position,parent,images,context);
        setViewPosition(imageView[position],position);
        return imageView[position];
    }

    public void setViewPosition(ImageView imageview, int position){
        int[] xy = utile.positionToArray(position);
        imageView_Pieces[xy[0]][xy[1]] = imageview;
        if(position == 0) imageView_Pieces[0][0] = imageview;
    }

    public void setPiecesid(){
        for(int i=0; i<images.length; i++){
            switch(i){
                case 0: images[i] = R.drawable.b_castle; break;
                case 1: images[i] = R.drawable.b_knight; break;
                case 2: images[i] = R.drawable.b_bishop; break;
                case 3: images[i] = R.drawable.b_queen; break;
                case 4: images[i] = R.drawable.b_king; break;
                case 5: images[i] = R.drawable.b_bishop; break;
                case 6: images[i] = R.drawable.b_knight; break;
                case 7: images[i] = R.drawable.b_castle; break;
                case 56: images[i] = R.drawable.w_castle; break;
                case 57: images[i] = R.drawable.w_knight; break;
                case 58: images[i] = R.drawable.w_bishop; break;
                case 59: images[i] = R.drawable.w_queen; break;
                case 60: images[i] = R.drawable.w_king; break;
                case 61: images[i] = R.drawable.w_bishop; break;
                case 62: images[i] = R.drawable.w_knight; break;
                case 63: images[i] = R.drawable.w_castle; break;
                default: break;
            }
            if(i>=8 && i<=15) images[i] = R.drawable.b_pawn;
            else if(i>=16 && i<=47) images[i] = R.drawable.block_blank;
            else if(i>=48 && i<=55) images[i] = R.drawable.w_pawn;
        }
    }
}
