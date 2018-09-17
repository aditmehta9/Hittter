package com.example.android.hittter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class flyingfishActivity extends View {

    private Bitmap background;
    private Bitmap mainobject[] = new Bitmap[2];
    private int mainobjectX = 10;
    private int mainobjectY;
    private int mainobjectspeed;
    private int yellox,yellowy,yellowspeed=16;
    private Paint yellopaint = new Paint();
    private int greenx,greeny,greenspeed=20;
    private Paint greenpaint = new Paint();
    private int redx,redy,redspeed=20;
    private Paint redpaint = new Paint();
    private boolean touch = false;
    private int canvaswidth,canvasheight;
    private Paint scorepaint = new Paint();
    private int score,lifecounter;
    private Bitmap life[] = new Bitmap[2];
    public flyingfishActivity(Context context)
    {
        super(context);
        mainobject[0] = BitmapFactory.decodeResource(getResources(),R.drawable.fish1);
        mainobject[1] = BitmapFactory.decodeResource(getResources(),R.drawable.fish2);
        background = BitmapFactory.decodeResource(getResources(),R.drawable.cab1);
        yellopaint.setColor(Color.YELLOW);
        yellopaint.setAntiAlias(false);
        greenpaint.setColor(Color.GREEN);
        greenpaint.setAntiAlias(false);
        redpaint.setColor(Color.RED);
        redpaint.setAntiAlias(false);
        scorepaint.setColor(Color.YELLOW);
        scorepaint.setTextSize(70);
        scorepaint.setTypeface(Typeface.DEFAULT_BOLD);
        scorepaint.setAntiAlias(true);
        life[0] = BitmapFactory.decodeResource(getResources(),R.drawable.hearts);
        life[1] = BitmapFactory.decodeResource(getResources(),R.drawable.heart_grey);
        mainobjectY = 550;
        score = 0;
        lifecounter = 3;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvasheight = canvas.getHeight();
        canvaswidth = canvas.getWidth();
        canvas.drawBitmap(background,0,0,null);
        int minmainobjecty = mainobject[0].getHeight();
        int maxmainobjecty = canvasheight-mainobject[0].getHeight()*3;
        mainobjectY = mainobjectY+mainobjectspeed;
        if(mainobjectY<minmainobjecty)
        {
            mainobjectY=minmainobjecty;
        }
        if(mainobjectY>maxmainobjecty)
        {
            mainobjectY=maxmainobjecty;
        }
        mainobjectspeed = mainobjectspeed+2;
        if (touch)
        {
            canvas.drawBitmap(mainobject[1],mainobjectX,mainobjectY,null);
            touch=false;
        }
        else
        {
            canvas.drawBitmap(mainobject[0],mainobjectX,mainobjectY,null);
        }

        yellox = yellox - yellowspeed;
        if(hitballcheck(yellox,yellowy))
        {
            score = score+9;
            yellox = yellox-100;
        }
        if(yellox<0)
        {
            yellox = canvaswidth + 21;
            yellowy = (int)Math.floor(Math.random()*(maxmainobjecty-minmainobjecty))+minmainobjecty;

        }
            canvas.drawCircle(yellox,yellowy,25,yellopaint);

        greenx = greenx - greenspeed;
        if(hitballcheck(greenx,greeny))
        {
            score = score+18;
            greenx = greenx-100;
        }
        if(greenx<0)
        {
            greenx = canvaswidth + 21;
            greeny = (int)Math.floor(Math.random()*(maxmainobjecty-minmainobjecty))+minmainobjecty;

        }
        canvas.drawCircle(greenx,greeny,25,greenpaint);

        redx = redx - redspeed;
        if(hitballcheck(redx,redy))
        {
            redx = redx-100;
            lifecounter--;
            if (lifecounter==0)
            {
                Toast.makeText(getContext(), "Game Over", Toast.LENGTH_SHORT).show();
                Intent gameoverintent = new Intent(getContext(),GameoverActivity.class);
                gameoverintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                gameoverintent.putExtra("score",score);
                getContext().startActivity(gameoverintent);
            }
        }
        if(redx<0)
        {
            redx = canvaswidth + 21;
            redy = (int)Math.floor(Math.random()*(maxmainobjecty-minmainobjecty))+minmainobjecty;

        }
        canvas.drawCircle(redx,redy,30,redpaint);
        canvas.drawText("Score : "+score,20,60,scorepaint);
        for(int i=0;i<3;i++)
        {
            int x = (int)(580+life[0].getWidth()*1.5*i);
            int y=30;
            if(i<lifecounter)
            {
                canvas.drawBitmap(life[0],x,y,null);
            }
            else
            {
                canvas.drawBitmap(life[1],x,y,null);
            }
        }

    }

        public boolean hitballcheck(int x,int y)
        {
            if(mainobjectX<x&&x<(mainobjectX+mainobject[0].getWidth())&&mainobjectY<y&&y<(mainobjectY+mainobject[0].getHeight()))
            {
                return true;
            }
            return false;
        }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction()==MotionEvent.ACTION_DOWN)
        {
            touch = true;
            mainobjectspeed = -22;
        }
        return true;
    }
}
