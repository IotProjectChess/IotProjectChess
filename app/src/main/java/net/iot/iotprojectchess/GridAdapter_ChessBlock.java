package net.iot.iotprojectchess;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

/**
 * Created by user on 2018-02-06.
 */

public class GridAdapter_ChessBlock extends BaseAdapter implements Labels {
    Context context;
    ImageView[] imageView = new ImageView[64];
    ImageView[][] imageView_Block = new ImageView[8][8];
    int[][] imageView_Block_id = new int[8][8];
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
    public long getItemId(int position) {
        if(position == OUT_OF_ARRAY) return OUT_OF_ARRAY;
        return (long)imageView_Block_id[utile.positionToArray(position)[0]][utile.positionToArray(position)[1]];
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null)
            utile.setImageView(imageView,position,parent,images,context);
        else imageView[position] = (ImageView)convertView;
        setViewPosition(imageView[position],position);
        return imageView[position];
    }
    public void setViewPosition(ImageView imageview, int position){
        int[] xy = utile.positionToArray(position);
        imageView_Block[xy[0]][xy[1]] = imageview;
        imageView_Block_id[xy[0]][xy[1]] = images[position];
    }
    public void setImageResource(int x, int y, int imageResource){
        imageView_Block[x][y].setImageResource(imageResource);
        imageView_Block_id[x][y] = imageResource;
    }
    public void setPiecesid(){
        for(int i=0; i<images.length; i++) images[i] = R.drawable.block_blank;
    }
}
