package it.brun.dario.ballpopper.pseudoviews;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.view.MotionEvent;

import it.brun.dario.ballpopper.interfaces.Eseguibile;
import it.brun.dario.ballpopper.ballpopper.Giocatore;
import it.brun.dario.ballpopper.views.MessageView;

/**
 * Created by dario on 05/05/17.
 */
public class SquareButton
{
    private float width;
    private float height;
    private float posX;
    private float posY;

    private Eseguibile eseguibile;
    private Eseguibile eseguisuono;

    private String text;
    public SquareButton(float x,float y,float w,float h,Eseguibile ese,String t,Eseguibile eseguis)
    {
        this(x,y,w,h,ese,t);
        eseguisuono=eseguis;


    }
    public SquareButton(float x,float y,float w,float h,Eseguibile ese,String t)
    {
        posX=x;
        posY=y;

        height=h;
        width=w;
        eseguibile=ese;
        eseguisuono=null;
        text=t;
        selected=false;


    }
    public void setSelected(boolean b)
    {
        selected=b;
    }

    public  void onTouch(MotionEvent event)
    {
        if(event.getAction()==MotionEvent.ACTION_DOWN||event.getAction()==MotionEvent.ACTION_MOVE)
        {
            setSelected(interno(event.getX(),event.getY()));


        }
        else if(event.getAction()==MotionEvent.ACTION_UP)
        {
            if(isSelected())
            {
                if(eseguisuono!=null)
                {
                    eseguisuono.esegui();
                }
                eseguibile.esegui();
                setSelected(false);

            }

        }
    }
    public boolean interno(float x,float y)
    {
        return (x>=posX&&x<=posX+width&&y>=posY&&y<=posY+height);
    }
    private boolean selected;
    public boolean isSelected()
    {
        return selected;
    }
    public void disegnaBottone(Canvas c)
    {
        Paint p=new Paint();
        Giocatore.setTypeface(p);
        MessageView.setDimensione(text,p,width*4/5,height*4/5);
        Rect bounds=new Rect();
        p.getTextBounds(text,0,text.length(),bounds);

        p.setShader(new LinearGradient(posX+width/2, posY,posX+width/2, posY+height, Color.LTGRAY,Color.argb(255,150,150,150), Shader.TileMode.MIRROR));


        if(selected)
        {
            p.setShader(null);
            p.setColor(Color.argb(255,255,255,50));
        }
        c.drawRect(new RectF(posX,posY,posX+width,posY+height),p);

        p.setShader(null);
        p.setColor(Color.BLACK);
        c.drawText(text,posX+(width-bounds.width())/2,posY+height-(height-bounds.height())/2,p);

        p.setShader(new LinearGradient(posX+width/2, posY,posX+width/2, posY+height, Color.LTGRAY,Color.DKGRAY, Shader.TileMode.MIRROR));

        p.setStyle(Paint.Style.STROKE);
        c.drawRect(new RectF(posX,posY,posX+width,posY+height),p);

    }

}
