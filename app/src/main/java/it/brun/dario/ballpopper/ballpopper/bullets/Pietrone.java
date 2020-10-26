package it.brun.dario.ballpopper.ballpopper.bullets;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import it.brun.dario.ballpopper.exceptions.EsplosaException;
import it.brun.dario.ballpopper.R;
import it.brun.dario.ballpopper.ballpopper.Giocatore;
import it.brun.dario.ballpopper.ballpopper.balls.Bomba;
import it.brun.dario.ballpopper.views.Ambiente;

/**
 * Created by dario on 10/04/17.
 */
public class Pietrone extends Palla
{


    private static final double DECREMENTATORE_ALPHA=-0.2;
    private int alpha;
    private static Bitmap immagine;
    private static Bitmap immagineSmall;
    private static Bitmap immagineCorrente;
    public static void assegnaImmagine(Activity act)
    {
        immagine=BitmapFactory.decodeResource(act.getResources(), R.drawable.pietrone);
        immagineCorrente=Giocatore.getResizedBitmap(BitmapFactory.decodeResource(act.getResources(), R.drawable.pietrone),(int) (Bomba.getDIAMETROBASE()*3/2), (int)(Bomba.getDIAMETROBASE()*3/2));
        immagineSmall = Giocatore.getResizedBitmap(BitmapFactory.decodeResource(act.getResources(), R.drawable.pietrone),(int)Bomba.getDIAMETROBASE(),(int)Bomba.getDIAMETROBASE());

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
        Giocatore.inventario().addPietroniSparati();
    }


    public Pietrone (Ambiente a, float xx, float yy, double ang, int liv)
    {
        super(a,xx,yy,ang,liv*2);
        setWidth((Bomba.getDIAMETROBASE()*3)/2);
        setHeight((Bomba.getDIAMETROBASE()*3)/2);
        setLivello(getLivello()/1.5f);
        setDanno(24);
        alpha=255;


    }
    public void disegnaPalla(Canvas g, Paint p)
    {

        p.setAlpha(alpha);
        g.drawBitmap(immagineCorrente,getX(),getY(),p);
        p.setAlpha(255);
    }


    public void setAlpha(int a)
    {
        alpha += a;
        if (alpha < 0) {
            alpha = 0;
        }
        if (alpha > 255) {
            alpha = 255;
        }
    }

    public void muovi()throws InterruptedException
    {


        if(alpha==255)
        {

            try
            {

                super.muovi();
            }
            catch (EsplosaException e)
            {

                if(e.esplosa())
                {
                    double k = (Math.random() * Math.PI) - (Math.PI / 2);
                    setAngolo(k);
                }
                else
                {
                    setAlpha((int)(DECREMENTATORE_ALPHA*Palla.VELOCITAFPS));
                }

            }
        }
        else
        {
            setAlpha((int)(DECREMENTATORE_ALPHA*Palla.VELOCITAFPS));
            if(alpha==0)
            {
                throw new InterruptedException();
            }
        }




    }

}
