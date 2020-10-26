package it.brun.dario.ballpopper.ballpopper;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import it.brun.dario.ballpopper.views.MessageView;
import it.brun.dario.ballpopper.ballpopper.bullets.Palla;
import it.brun.dario.ballpopper.R;
import it.brun.dario.ballpopper.views.Ambiente;

/**
 * Created by dario on 30/04/17.
 */
public class Combo extends Thread
{
    private int numero;
    private float x;
    private float y;
    private static Bitmap[] immagine;
    public static void caricaImmagine(Activity act)
    {
        immagine=new Bitmap[2];
        immagine[0]=Giocatore.getResizedBitmap(BitmapFactory.decodeResource(act.getResources(), R.drawable.combo1),(int)(Ambiente.getHEIGHT()*PROPORZIONE/4),(int)(Ambiente.getHEIGHT()/4));
        immagine[1]=Giocatore.getResizedBitmap(BitmapFactory.decodeResource(act.getResources(), R.drawable.combo2),(int)(Ambiente.getHEIGHT()*PROPORZIONE/4),(int)(Ambiente.getHEIGHT()/4));


    }
    private Ambiente ambiente;
    public static final String combo="COMBO X ";
    public static final float PROPORZIONE=0.58438f;
    public static final float POSX=0.15113f;
    public static final float POSY=0.60345f;
    public static final float LUNGHEZZA=0.71788f;
    public static final float ALTEZZA=0.34483f;

    private Paint p;

    private float posx;
    private float posy;
    private float lunghezza;
    private float altezza;
    private String message;
    private int j;
    public static Combo getCombo(Ambiente a, Palla p, float length)
    {

        p.setCentro();

        float x=p.getCentroX()-length/2;
        float y=p.getCentroY()-length*PROPORZIONE/2;
        if(x<1)
        {
            x=1;
        }
        if(y<1)
        {
            y=1;
        }
        if(x>a.getWidth()-length)
        {
            x=a.getWidth()-length;
        }
        if(y>a.getHeight()-length*PROPORZIONE)
        {
            y=a.getHeight()-length*PROPORZIONE;
        }
        return new Combo(a, x,y,length, p.getNumScoppiate());
    }
    public Combo (Ambiente a,float xx,float yy,float lenght, int num)
    {
        ambiente=a;

        numero=num;
        p=new Paint();
        Giocatore.setTypeface(p);
        p.setColor(Color.argb(255,190,0,0));
        p.setShader(null);
        message=combo+num;

        x=xx;
        y=yy;
        posx=x+lenght*POSX;
        posy=y+lenght*PROPORZIONE*POSY;
        lunghezza=lenght*LUNGHEZZA;
        altezza=lenght*PROPORZIONE*ALTEZZA;
        p.setShader(null);
        MessageView.setDimensione(message, p, lunghezza, altezza);
        j=1;

    }



    public static final int LIVELLO=100;

    public void disegna(Canvas g)
    {



        g.drawBitmap(immagine[j], x, y, p);

        g.drawText(message ,posx,posy,p);



    }

    public void run()
    {
        for(int i=0;i<10;i++)
        {


            try
            {

                j=i%2;

                ambiente.postInvalidate();
                Thread.sleep(LIVELLO);


            }
            catch(InterruptedException ex)
            {
                ex.printStackTrace();
            }
        }
        ambiente.rimuovi(this);
        ambiente.postInvalidate();
    }
}
