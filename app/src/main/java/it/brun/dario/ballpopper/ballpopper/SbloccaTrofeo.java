package it.brun.dario.ballpopper.ballpopper;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import it.brun.dario.ballpopper.interfaces.Contenitore;
import it.brun.dario.ballpopper.views.PannelloTrofeo;
import it.brun.dario.ballpopper.ballpopper.trophies.Trofeo;
import it.brun.dario.ballpopper.ballpopper.balls.Bomba;

/**
 * Created by dario on 26/04/17.
 */
public class SbloccaTrofeo extends Thread
{
    private Contenitore contenente;
    private Trofeo trofeo;
    private float posX;
    private float posY;
    private float width;
    private int alpha;

    private static final int LIVELLO=10;


    public SbloccaTrofeo(Contenitore a,float x,float y,Trofeo t)
    {
        contenente=a;
        posX=x;
        posY=y;

        width=Bomba.getDIAMETROBASE()*6;
        MAX_LENGTH=(int)Bomba.getDIAMETROBASE();
        trofeo=t;
        alpha=255;

    }

    public void run()
    {

        try
        {
           for(int i=0;i<255;i++)
           {
               alpha--;
               contenente.invalida();
               Thread.sleep(LIVELLO);

           }


        }
        catch (Exception e)
        {

        }
        contenente.rimuovi(this);
        contenente.invalida();
    }
    private int MAX_LENGTH;

    private void setTextSize(Paint p,String stringa)
    {
        int i=1;
        p.setTextSize(i);
        while(p.measureText(stringa)<=width*18/20&&i<MAX_LENGTH)
        {
            i++;
            p.setTextSize(i);
        }
    }
    public void disegnaSbloccaTrofeo(Canvas c)
    {
        Paint p = new Paint();
        Giocatore.setTypeface(p);

        p.setTextSize(Bomba.getDIAMETROBASE());
        String lunga=PannelloTrofeo.piuLunga(trofeo.getNome());
        setTextSize(p,lunga);
        String[] stringhe=PannelloTrofeo.splitString(trofeo.getNome(),p,(int)(width*18/20));

        p.setColor(Color.argb(255,240,240,255));
        p.setAlpha(alpha);
        c.drawRect(new RectF(posX, posY, posX + width, posY+(stringhe.length+1)*p.getTextSize()+Bomba.getDIAMETROBASE()*4 ), p);
        p.setColor(Color.BLACK);
        p.setAlpha(alpha);
        float posYY=posY+p.getTextSize();
        for(int i=0;i<stringhe.length;i++)
        {
            c.drawText(stringhe[i],posX+width/20,posYY, p);
            posYY+=p.getTextSize();
        }


        c.drawBitmap(trofeo.immagineTrofeo(),posX+width/6,posYY,p);
    }

}
