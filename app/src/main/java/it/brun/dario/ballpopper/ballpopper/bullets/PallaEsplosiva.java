package it.brun.dario.ballpopper.ballpopper.bullets;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import it.brun.dario.ballpopper.exceptions.EsplosaException;
import it.brun.dario.ballpopper.R;
import it.brun.dario.ballpopper.ballpopper.Giocatore;
import it.brun.dario.ballpopper.ballpopper.balls.Bomba;
import it.brun.dario.ballpopper.views.Ambiente;

/**
 * Created by dario on 10/04/17.
 */
public class PallaEsplosiva extends Palla
{

    public static final int LIVELLOESPLOSIONE=24;
    private static Bitmap immagine;
    private static Bitmap immagineSmall;
    public static void assegnaImmagine(Activity act)
    {
        immagine=BitmapFactory.decodeResource(act.getResources(), R.drawable.esplosivo);
        immagineSmall = Giocatore.getResizedBitmap(BitmapFactory.decodeResource(act.getResources(), R.drawable.esplosivo),(int)Bomba.getDIAMETROBASE(),(int)Bomba.getDIAMETROBASE());

    }
    public static Bitmap getImmagineSmall()
    {
        return immagineSmall;
    }
    public static Bitmap getImmagine()
    {
        return immagine;
    }

    public void incrementaInventario()
    {
        Giocatore.inventario().addEsplosiviSparati();
    }


    public PallaEsplosiva(Ambiente ambiente, float i, float j, double d, int k)
    {
        super(ambiente, i, j, d, k);

        ondadurto = getWidth();
        basicwidth = ondadurto;
        MAX=Bomba.getDIAMETROBASE()*4;
        setDanno(15);
    }

    public void disegnaPalla(Canvas g,Paint p)
    {
        if(ondadurto == basicwidth)
        {
            p.setColor(Color.RED);
            p.setShader(null);
            g.save();
            g.rotate((float)Math.toDegrees(getAngolo()),getX(),getY());
            g.drawRect(new RectF(getX(), getY(), getX()+getWidth(), getY()+getHeight()),p);
            g.drawArc(new RectF(getX()+getWidth()-getWidth()/2,getY(),getX()+getWidth()+getWidth()/2,getY()+getHeight()),270,180,true,p);
            g.restore();
        }
        else
        {

            setWidth(ondadurto);
            setHeight(ondadurto);

            float x1=getCentroX()-getWidth()/2;
            float y1=getCentroY()-getHeight()/2;
            try
            {
                setX(x1);

            }
            catch(InterruptedException interruptedexception)
            {
            }
            try
            {
                setY(y1);
            }
            catch(InterruptedException interruptedexception)
            {
            }
            disegnaStella(g,p,ondadurto,ondadurto/2,11, Color.YELLOW,Color.RED);
        }
        p.setColor(Color.BLACK);
    }

    public void muovi() throws InterruptedException
    {
        if(ondadurto == basicwidth)
        {
            try
            {
                super.muovi();
            }
            catch(EsplosaException esplosaexception)
            {
                setCentro();
                suonaEsploditoreUno();
                ondadurto += (Bomba.getINCREMENTATORE_RAGGIO()*Palla.VELOCITAFPS)/LIVELLOESPLOSIONE;
            }
        }
        else
        {

            if(ondadurto >MAX)
            {
                throw new InterruptedException();
            }

                for(int i = 0; i < numBombe(); i++)
                {
                    try
                    {
                        scoppia(i);
                    }
                    catch (EsplosaException esplosaexception1)
                    {
                    }

                 }



            ondadurto += (Bomba.getINCREMENTATORE_RAGGIO()*Palla.VELOCITAFPS)/LIVELLOESPLOSIONE;
        }
    }

    private float basicwidth;
    private   float MAX;
    private float ondadurto;


}
