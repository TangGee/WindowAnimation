package com.rizhi.windowanimation;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.util.Random;

/**
 * Created by tangtang on 15/5/22.
 */
public class MyWindowManager {

    private static WindowManager.LayoutParams myLayoutParams;
    private static FrameLayout myWindowView;
    private static WindowManager windowManager;
    private static ImageView iv;

    private int x;
    private  int y;

    private  static int [] anims={
            R.mipmap.icon1,
            R.mipmap.icon2,
            R.mipmap.icon3,
            R.mipmap.icon4,
            R.mipmap.icon5,
            R.mipmap.icon6,

    };


    public static void showViewOnWindow(Context context)
    {
        if(windowManager==null)
            windowManager= (WindowManager) context.getSystemService(context.WINDOW_SERVICE);

        int screenWidth=windowManager.getDefaultDisplay().getWidth();
        int screenHeight=windowManager.getDefaultDisplay().getHeight();

        if(myWindowView==null){
            myWindowView= (FrameLayout) View.inflate(context,R.layout.layout_window,null);
            iv= (ImageView) myWindowView.findViewById(R.id.icon);

            if(myLayoutParams==null)
            {
                myLayoutParams=new WindowManager.LayoutParams();
                myLayoutParams.type= WindowManager.LayoutParams.TYPE_PHONE;
                myLayoutParams.flags= WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL|WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
                myLayoutParams.gravity= Gravity.TOP;
                myLayoutParams.y=screenHeight/2;
                myLayoutParams.x=screenWidth/2;
                myLayoutParams.width=200;
                myLayoutParams.height=200;

            }

            iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   int x= new Random().nextInt(anims.length);
                    iv.setImageResource(anims[x]);
//                    AnimationDrawable animationDrawable= (AnimationDrawable) iv.getBackground();
//                    animationDrawable.stop();
//                    animationDrawable.start();
                }
            });

            myWindowView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    int action=event.getAction();
                    switch (action){
                        case MotionEvent.ACTION_DOWN:

                            return true;

                        case MotionEvent.ACTION_MOVE:

                            myLayoutParams.x= (int) event.getRawX()-myLayoutParams.width/2;
                            myLayoutParams.y= (int) event.getRawY()-myLayoutParams.height/2;
                            windowManager.updateViewLayout(myWindowView,myLayoutParams);


                            return true;
                        case MotionEvent.ACTION_UP:
                        case MotionEvent.ACTION_CANCEL:

                            break;


                    }
                    return false;
                }
            });

            windowManager.addView(myWindowView,myLayoutParams);
        }



    }
}
