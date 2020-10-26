package it.brun.dario.ballpopper.ballpopper.bullets;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;

import it.brun.dario.ballpopper.exceptions.EsplosaException;
import it.brun.dario.ballpopper.R;
import it.brun.dario.ballpopper.ballpopper.Scoppiante;
import it.brun.dario.ballpopper.ballpopper.Giocatore;
import it.brun.dario.ballpopper.ballpopper.balls.Bomba;
import it.brun.dario.ballpopper.views.Ambiente;

/**
 * Created by dario on 10/04/17.
 */
public class PallaDetonante extends Palla implements Scoppiante
{

    private static Bitmap immagine;
    private static Bitmap immagineSmall;

    public static void assegnaImmagine(Activity act)
    {
        immagine=BitmapFactory.decodeResource(act.getResources(), R.drawable.detonante);
        immagineSmall = Giocatore.getResizedBitmap(BitmapFactory.decodeResource(act.getResources(), R.drawable.detonante),(int)Bomba.getDIAMETROBASE(),(int)Bomba.getDIAMETROBASE());

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
        Giocatore.inventario().addDetonantiSparati();
    }



    public PallaDetonante(Ambiente ambiente, float i, float j, double d, int k)
    {
        super(ambiente, i, j, d, k);

        ondadurto = getWidth();
        basicwidth = ondadurto;
        MAX= Bomba.getDIAMETROBASE()*6;
        setDanno(18);
    }

    public void disegnaPalla(Canvas g,Paint p)
    {

        if(ondadurto == basicwidth)
        {
            p.setShader(new LinearGradient(getX(), getY(), getX()+getWidth(), getY()+getHeight(), Color.BLACK, Color.BLUE, Shader.TileMode.MIRROR));
            g.save();
            g.rotate((float)Math.toDegrees(getAngolo()),getX(),getY());            g.drawRect(new RectF(getX(), getY(), getX()+getWidth(), getY()+getHeight()),p);
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
            disegnaStella(g,p,ondadurto,ondadurto/2,11,Color.argb(255,130,130,255),Color.argb(255,0,0,120));
        }
        p.setColor(Color.BLACK);
    }

    public void detona()
    {
        if(!isDetonato())
        {
            suonaEsploditoreUno();
            setCentro();
            ondadurto += (Bomba.getINCREMENTATORE_RAGGIO()*Palla.VELOCITAFPS)/PallaEsplosiva.LIVELLOESPLOSIONE;
        }

    }
    public boolean isDetonato()
    {
        return ondadurto!=basicwidth;
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
                detona();
            }
        }
        else
        {
            if(ondadurto > MAX)
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


            ondadurto += (Bomba.getINCREMENTATORE_RAGGIO()*Palla.VELOCITAFPS)/PallaEsplosiva.LIVELLOESPLOSIONE;
        }
    }

    private float basicwidth;
    private float MAX;
    private float ondadurto;
}
