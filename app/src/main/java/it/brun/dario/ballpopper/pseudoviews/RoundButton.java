package it.brun.dario.ballpopper.pseudoviews;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.MotionEvent;

import it.brun.dario.ballpopper.interfaces.Eseguibile;
import it.brun.dario.ballpopper.ballpopper.Giocatore;
import it.brun.dario.ballpopper.views.MessageView;

/**
 * Created by dario on 05/05/17.
 */
public class RoundButton
{
    private float diametro;

    private float posX;
    private float posY;
    private float cx;
    private float cy;
    private Eseguibile eseguibile;
    private String text;

    public RoundButton(float x,float y,float r,Eseguibile ese,String t)
    {
        posX=x;
        posY=y;


        diametro=r;
        cx=posX+diametro/2;
        cy=posY+diametro/2;
        eseguibile=ese;
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
               eseguibile.esegui();
                setSelected(false);

            }

        }
    }
    public boolean interno(float x,float y)
    {
        if((x-cx)*(x-cx)+(y-cy)*(y-cy)<=(diametro/2)*(diametro/2))
        {

            return true;

        }
        return false;
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
        MessageView.setDimensione(text,p,diametro/2,diametro/2);
        Rect bounds=new Rect();
        p.getTextBounds(text,0,text.length(),bounds);

        int colore=Color.LTGRAY;

        if(selected)
        {
            colore=Color.YELLOW;
        }
        p.setColor(colore);
        c.drawOval(new RectF(posX,posY,posX+diametro,posY+diametro),p);


        p.setColor(Color.BLACK);
        float kk=bounds.height()/2;
        if(text.equals("-"))
        {
            kk=diametro/2;
        }
        c.drawText(text,cx-bounds.width()/2,cy+kk,p);

        p.setStyle(Paint.Style.STROKE);
        //p.setStrokeWidth(diametro/40);

        c.drawOval(new RectF(posX,posY,posX+diametro,posY+diametro),p);

    }

}
