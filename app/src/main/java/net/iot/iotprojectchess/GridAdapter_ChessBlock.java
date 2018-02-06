package net.iot.iotprojectchess;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

/**
 * Created by user on 2018-02-06.
 */

public class GridAdapter_ChessBlock extends BaseAdapter {
    Context context;
    ImageView[] imageView = new ImageView[64];
    ImageView[][] imageView_blankBlock = new ImageView[8][8];
    int[] images = new int[64];
    Utiles utile = new Utiles();

    public GridAdapter_ChessBlock(Context c){
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
        imageView_blankBlock[xy[0]][xy[1]] = imageview;
        if(position==0) imageView_blankBlock[0][0] = imageview;
    }
    public void setPiecesid(){
        for(int i=0; i<images.length; i++) images[i] = R.drawable.block_blank;
    }
}
