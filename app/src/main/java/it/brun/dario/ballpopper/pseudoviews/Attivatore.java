package it.brun.dario.ballpopper.pseudoviews;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import it.brun.dario.ballpopper.interfaces.Eseguibile;
import it.brun.dario.ballpopper.Personaggio;
import it.brun.dario.ballpopper.ballpopper.Giocatore;

/**
 * Created by dario on 24/04/17.
 */
public  class Attivatore
{
    private Bitmap b;
    private Personaggio personaggio;
    private float width;
    private float heigth;
    private float posX;
    private float posY;
    private Eseguibile eseguibile;

    private String text;
    private boolean visible;

    public Attivatore(float x,float y,float w,float h,Eseguibile ese,Bitmap t)
    {
        posX=x;
        posY=y;
        personaggio=null;

        heigth=h;
        eseguibile=ese;
        text=null;
        b=t;
        visible=false;
        width=w;

    }
    public Attivatore(float x,float y,float w,float h,Eseguibile ese,Bitmap t,Personaggio pers)
    {
        posX=x;
        posY=y;
        personaggio=pers;

        heigth=h;
        eseguibile=ese;
        text=null;
        b=t;
        visible=false;
        width=w;

    }



    public void setVisible(boolean b)
    {
        visible=b;
    }
    public boolean interno(float x,float y)
    {
        if((visible&&personaggio==null||(visible&&!personaggio.inMoto()))&&x>=posX&&x<=posX+width&&y>=posY&&y<=posY+heigth)
        {
            eseguibile.esegui();
            return true;

        }
        return false;
    }
    public void disegnaAttivatore(Canvas c)
    {
        if(visible&&personaggio==null||(visible&&!personaggio.inMoto()))
        {
            if(b==null)
            {

                Paint p = new Paint();
                Giocatore.setTypeface(p);


                p.setTextSize(heigth / 2);
                p.setColor(Color.WHITE);
                c.drawRect(new RectF(posX, posY, posX + width, posY + heigth), p);
                p.setColor(Color.BLACK);
                c.drawText(text, posX + width / 10, posY + heigth - heigth / 4, p);
                p.setStrokeWidth(heigth / 10);
                p.setStyle(Paint.Style.STROKE);
                c.drawRect(new RectF(posX, posY, posX + width, posY + heigth), p);
            }
            else
            {
                c.drawBitmap(b,posX,posY,null);
            }
        }
    }
}

