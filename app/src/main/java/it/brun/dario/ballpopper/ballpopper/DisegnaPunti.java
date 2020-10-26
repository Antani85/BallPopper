package it.brun.dario.ballpopper.ballpopper;

import android.graphics.Canvas;
import android.graphics.Paint;

import it.brun.dario.ballpopper.ballpopper.balls.Bomba;
import it.brun.dario.ballpopper.views.Ambiente;

/**
 * Created by dario on 10/04/17.
 */
public class DisegnaPunti
{
    private String punti;
    private float x;
    private float y;
    private int colore;
    private Ambiente ambiente;

    public DisegnaPunti (Ambiente a,float xx,float yy, int p, int col)
    {
        ambiente=a;
        x=xx;
        y=yy;
        punti="+"+p;
        colore=col;
        i=0;

    }
    public DisegnaPunti (Ambiente a,float xx,float yy, String pot, int col)
    {
        ambiente=a;
        x=xx;
        y=yy;
        punti=pot;
        colore=col;
        i=0;

    }
    private void setTypeFace(Paint p)
    {
        p.setTextSize((Bomba.getDIAMETROBASE()/3)*2);
        Giocatore.setTypeface(p);
    }


    public static final int LIVELLO=10;
    private static final int MAXRUNNATA=(int)(40/Bomba.PROPFPS);
    private int i;

    public void disegna(Canvas g,Paint p)
    {
        setTypeFace(p);
        p.setColor(colore);
        p.setShader(null);
        g.drawText(punti ,x,y,p);

    }

    public void runna()
    {
        if(i<MAXRUNNATA)
        {

           y-=(Bomba.getINCREMENTATORE_RAGGIO()*Bomba.PROPFPS/8);



           i++;
        }
        else
        {
            ambiente.rimuovi(this);

        }

    }
}
