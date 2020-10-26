package it.brun.dario.ballpopper.ballpopper;

import android.graphics.Canvas;
import android.graphics.Paint;

import it.brun.dario.ballpopper.ballpopper.balls.Bomba;
import it.brun.dario.ballpopper.views.Ambiente;

/**
 * Created by dario on 19/04/17.
 */
public class Combinazione extends Thread
{
    private int numero;
    private float x;
    private float y;
    private int colore;
    private Ambiente ambiente;
    public static final String combo="COMBO X ";
    private Paint p;
    private float dimmax;
    private float dimmay;
    private void setDim()
    {
        dimmax=p.measureText(combo+numero);
        dimmay= p.getTextSize();
    }
    public Combinazione (Ambiente a,float xx,float yy, int num, int col)
    {
        ambiente=a;

        numero=num;
        colore=col;
        p=new Paint();
        setTypeFace(p);
        p.setColor(colore);
        p.setShader(null);
        setDim();
        x=xx-(dimmax/2);
        y=yy+(dimmay/2);

    }

    private void setTypeFace(Paint p)
    {
        Giocatore.setTypeface(p);
        p.setTextSize((Bomba.getDIAMETROBASE()/3));


    }
    private void cresciTypeFace(int s)
    {
        p.setTextSize(p.getTextSize()+s);

    }


    public static final int LIVELLO=5;

    public void disegna(Canvas g)
    {



        g.drawText(combo+numero ,x,y,p);
    }

    public void run()
    {
        while(dimmax<ambiente.getHeight())
        {



            try
            {
                float xk=dimmax;
                float yk=dimmay;
                setDim();
                cresciTypeFace((int)(Bomba.getINCREMENTATORE_RAGGIO()/2));


                x+=(xk-dimmax)/2;
                y-=(yk-dimmay)/2;

                ambiente.postInvalidate();
                Thread.sleep(LIVELLO);


            }
            catch(InterruptedException ex)
            {
                ex.printStackTrace();
            }
        }
        //ambiente.rimuovi(this);
        ambiente.postInvalidate();
    }
}
