package it.brun.dario.ballpopper.ballpopper.bullets;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import java.util.ArrayList;
import java.util.List;

import it.brun.dario.ballpopper.exceptions.EsplosaException;
import it.brun.dario.ballpopper.ballpopper.Moltiplicante;
import it.brun.dario.ballpopper.R;
import it.brun.dario.ballpopper.ballpopper.Scoppiante;
import it.brun.dario.ballpopper.ballpopper.Cannone;
import it.brun.dario.ballpopper.ballpopper.Giocatore;
import it.brun.dario.ballpopper.ballpopper.balls.Bomba;
import it.brun.dario.ballpopper.views.Ambiente;

/**
 * Created by dario on 10/04/17.
 */
public class MinaAntipalla extends Palla implements Scoppiante,Moltiplicante
{
    private static final int NUM_MOLTIPLICATE=10;
    private int rimosse;
    private List<SottoPalla> sottopalle;
    private static Bitmap immagine;
    private static Bitmap immagineSmall;
    public static void assegnaImmagine(Activity act)
    {
        immagine=BitmapFactory.decodeResource(act.getResources(), R.drawable.mineantipalla);
        immagineSmall = Giocatore.getResizedBitmap(BitmapFactory.decodeResource(act.getResources(), R.drawable.mineantipalla),(int)Bomba.getDIAMETROBASE(),(int)Bomba.getDIAMETROBASE());

    }
    public static Bitmap getImmagineSmall()
    {
        return immagineSmall;
    }
    private boolean scoppiato;
    public static Bitmap getImmagine()
    {
        return immagine;
    }

    public void incrementaInventario()
    {
        Giocatore.inventario().addMineSparate(isDetonato());
    }


    public MinaAntipalla(Ambiente ambiente, float i, float j, double d, int k)
    {
        super(ambiente, i, j, d, k);
        setWidth((Bomba.getDIAMETROBASE() * 3) / 8);
        setHeight((Bomba.getDIAMETROBASE() * 3) / 8);
        scoppiato=false;
        rimosse=0;
        setDanno(5);
        sottopalle=new ArrayList<SottoPalla>();

    }

    public void disegnaPalla(Canvas g,Paint p)
    {
        if(!scoppiato)
        {
            p.setColor(Color.GRAY);
            p.setShader(null);
            g.drawOval(new RectF(getX(), getY(), getX() + getWidth(), getY() + getHeight()), p);
        }
        else
        {


            for(int i=0;i<sottopalle.size();i++)
            {
                try
                {
                    sottopalle.get(i).disegnaPalla(g, p);
                }
                catch (IndexOutOfBoundsException e)
                {
                    continue;
                }
                catch (NullPointerException e)
                {
                    continue;
                }
            }
        }


    }

    public void detona()
    {

        if(!isDetonato())
        {
            suonaEsploditore();
            moltiplica(NUM_MOLTIPLICATE);
            scoppiato=true;
        }

    }

    public boolean isDetonato()
    {
        return scoppiato;
    }
    public void muovi() throws InterruptedException
    {
        if(!scoppiato)
        {
            try
            {
                super.muovi();
            }
            catch(EsplosaException esplosaexception)
            {
                double d = Math.random() * Math.PI - (Math.PI/2);
                setAngolo(d);
            }
        }
        else if(rimosse==NUM_MOLTIPLICATE)
        {
          throw new InterruptedException();
        }

    }

    public void aggiungi(SottoPalla p)
    {
        sottopalle.add(p);
        p.start();
    }
    public void rimuovi(SottoPalla p)
    {
        sottopalle.remove(p);
        rimosse++;
    }

    public void moltiplica(int i)
    {
        for(int j=0;j<i;j++)
        {
            setCentro();
            aggiungi(new SottoPalla(getAmbiente(), this, getCentroX(), getCentroY(), (2 * Math.PI / i) * j, Cannone.getPotenzamax()));


        }

    }

}
