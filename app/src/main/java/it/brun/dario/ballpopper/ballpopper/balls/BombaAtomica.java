package it.brun.dario.ballpopper.ballpopper.balls;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;

import it.brun.dario.ballpopper.exceptions.EsplosaException;
import it.brun.dario.ballpopper.ballpopper.Giocatore;
import it.brun.dario.ballpopper.ballpopper.bullets.Palla;
import it.brun.dario.ballpopper.ballpopper.bullets.PallaEsplosiva;
import it.brun.dario.ballpopper.R;
import it.brun.dario.ballpopper.ballpopper.Scoppiante;
import it.brun.dario.ballpopper.views.Ambiente;

/**
 * Created by dario on 10/04/17.
 */
public class BombaAtomica extends Palla implements Scoppiante
{
    private static Bitmap immagine;
    private static Bitmap immagineSmall;
    private static Bitmap immaginecorrente;
    public static void assegnaImmagine(Activity act)
    {
        immagine=BitmapFactory.decodeResource(act.getResources(), R.drawable.atomico);
        immaginecorrente=Giocatore.getResizedBitmap(BitmapFactory.decodeResource(act.getResources(), R.drawable.atomico),(int) ((Bomba.getDIAMETROBASE()*3)/8), (int)((Bomba.getDIAMETROBASE()*3)/8));
        immagineSmall = Giocatore.getResizedBitmap(BitmapFactory.decodeResource(act.getResources(), R.drawable.atomico),(int)Bomba.getDIAMETROBASE(),(int)Bomba.getDIAMETROBASE());

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
        Giocatore.inventario().addAtomiciSparati(isDetonato());
    }


    public BombaAtomica(Ambiente ambiente, float i, float j, double d, int k)
    {
        super(ambiente, i, j, d, k);
        setWidth((Bomba.getDIAMETROBASE()*3)/8);
        setHeight((Bomba.getDIAMETROBASE()*3)/8);
        ondadurto = getWidth();
        basicwidth = ondadurto;
        MAX=Bomba.getDIAMETROBASE()*22;
        setDanno(5);
    }

    public void disegnaPalla(Canvas g,Paint p)
    {
        if(ondadurto == basicwidth)
        {
            p.setColor(Color.GRAY);
            p.setShader(null);



            g.save();
            g.rotate((float)Math.toDegrees(getAngolo()),getX(),getY());
            g.drawRect(new RectF(getX(), getY(), getX()+getWidth(), getY()+getHeight()),p);
            g.drawArc(new RectF(getX()+getWidth()-getWidth()/2,getY(),getX()+getWidth()+getWidth()/2,getY()+getHeight()),270,180,true,p);
            g.drawBitmap(immaginecorrente, getX(), getY(), null);
            g.restore();



        }
        else
        {

            setWidth(ondadurto);
            setHeight(ondadurto);

            float[]ff=new float[]{0.3f,1f};

            int[] cc=new int[]{Color.RED,Color.BLACK};
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
            p.setShader(new RadialGradient(x1+ondadurto/2, y1+ondadurto/2,(float)(ondadurto/2), cc,ff, Shader.TileMode.MIRROR));

            g.drawOval(new RectF(x1, y1,x1+ondadurto, y1+ondadurto),p);

        }

    }

    public void detona()
    {
        if(!isDetonato())
        {
            suonaEsploditore();
            setDanno(30);
            resettaColpite();
            setCentro();
            ondadurto += (Bomba.getINCREMENTATORE_RAGGIO()*Palla.VELOCITAFPS)/PallaEsplosiva.LIVELLOESPLOSIONE;;
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
                double d = Math.random() * Math.PI - (Math.PI/2);
                setAngolo(d);
            }
        }
        else
        {
            setLivello(PallaEsplosiva.LIVELLOESPLOSIONE);
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



            ondadurto +=(Bomba.getINCREMENTATORE_RAGGIO()*Palla.VELOCITAFPS)/PallaEsplosiva.LIVELLOESPLOSIONE;
        }
    }

    private float basicwidth;
    private float MAX;
    private float ondadurto;
}
