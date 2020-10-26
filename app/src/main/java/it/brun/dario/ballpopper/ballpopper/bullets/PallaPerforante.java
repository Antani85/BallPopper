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

import it.brun.dario.ballpopper.ballpopper.balls.Bomba;
import it.brun.dario.ballpopper.views.Ambiente;
import it.brun.dario.ballpopper.ballpopper.Giocatore;
import it.brun.dario.ballpopper.exceptions.EsplosaException;
import it.brun.dario.ballpopper.R;

/**
 * Created by dario on 10/04/17.
 */
public class PallaPerforante extends Palla
{

    private static Bitmap immagine;
    private static Bitmap immagineSmall;
    public static void assegnaImmagine(Activity act)
    {
        immagine=BitmapFactory.decodeResource(act.getResources(), R.drawable.perforante);
        immagineSmall = Giocatore.getResizedBitmap(BitmapFactory.decodeResource(act.getResources(), R.drawable.perforante),(int)Bomba.getDIAMETROBASE(),(int)Bomba.getDIAMETROBASE());

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
        Giocatore.inventario().addPerforantiSparati();
    }


    public PallaPerforante (Ambiente a, float xx, float yy, double ang, int liv)
    {
        super(a,xx,yy,ang,liv);
        setDanno(12);


    }
    public void disegnaPalla(Canvas g, Paint p)
    {
        g.save();

        g.rotate((float)Math.toDegrees(getAngolo()),getX(),getY());

        p.setShader(new LinearGradient(getX(), getY(), getX()+getWidth(), getY()+getHeight(), Color.BLACK, Color.BLUE, Shader.TileMode.MIRROR));

        g.drawRect(new RectF(getX(), getY(), getX()+getWidth(), getY()+getHeight()),p);
        g.drawArc(new RectF(getX()+getWidth()-getWidth()/2,getY(),getX()+getWidth()+getWidth()/2,getY()+getHeight()),270,180,true,p);
        g.restore();



    }


    public void muovi()throws InterruptedException
    {




        try
        {

            super.muovi();
        }
        catch(EsplosaException e)
        {
            if(!e.esplosa())
            {
                throw new InterruptedException();
            }

        }





    }

}
