package net.iot.iotprojectchess;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * Created by user on 2018-02-02.
 */

public class Utiles {
    int[][] arr_position = new int[8][8];

    public Utiles(){
        setPosition();
    }
    // 받아온 position 값을 2차원배열 위치로 변환후 리턴
    public  int[] positionToArray(int position){
        int[] result_Position = new int[2];
        for(int i=0; i<arr_position.length; i++){
            for(int j=0; j<arr_position[i].length; j++){
                if(arr_position[i][j] == position) {
                    result_Position[0] = i;
                    result_Position[1] = j;
                }
            }
        }
        return result_Position;
    }
    // 2차원 배열에 순서대로 1부터 64까지 값 넣어두기
    public void setPosition(){
        int count=0;
        for(int i=0; i<arr_position.length; i++){
            for(int j=0; j< arr_position.length; j++){
                arr_position[i][j] = count++;
            }
        }
    }
    // getView의 ImageView 세팅
    public void setImageView(ImageView[] imageView, int position, ViewGroup parent, int[] images, Context context){
        int width = parent.getResources().getDisplayMetrics().widthPixels/8;
        imageView[position] = new ImageView(context);
        imageView[position].setLayoutParams(new GridView.LayoutParams(width,width));
        imageView[position].setScaleType(ImageView.ScaleType.FIT_CENTER);
        imageView[position].setPadding(0,0,0,0);
        imageView[position].setImageResource(images[position]);
    }
}
