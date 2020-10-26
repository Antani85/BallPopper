package it.brun.dario.ballpopper.ballpopper.balls;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import it.brun.dario.ballpopper.ballpopper.Giocatore;
import it.brun.dario.ballpopper.R;
import it.brun.dario.ballpopper.ballpopper.bonus.Bonus;
import it.brun.dario.ballpopper.views.Ambiente;

/**
 * Created by dario on 15/05/17.
 */
public class CyberBomba extends Bomba
{
    private static Bitmap[] immagini;
    public static void setImmagine(Activity act)
    {
        immagini=new Bitmap[24];

        immagini[0]=Giocatore.getResizedBitmap(BitmapFactory.decodeResource(act.getResources(), R.drawable.pallacyber), (int)(Bomba.getDIAMETROBASE()*2f), (int)(Bomba.getDIAMETROBASE()*2f));

        for(int i=23;i>0;i--)
        {
            immagini[i]=Giocatore.getResizedBitmap(BitmapFactory.decodeResource(act.getResources(), R.drawable.pallacyber), (int)(Bomba.getDIAMETROBASE()*2f-Bomba.getDIAMETROBASE()*(24-i)/12), (int)(Bomba.getDIAMETROBASE()*2f-Bomba.getDIAMETROBASE()*(24-i)/12));

        }



    }

    @Override
    public void incrementaInventario()
    {
        Giocatore.inventario().incrementaCyberScoppiate();
    }
    public CyberBomba(Ambiente a, float ix, float iy, int c, float xx, float yy)
    {
        super(a, ix, iy, c, xx, yy);
        setPunti(75);
        impostaHp(50);
        setDiametro(Bomba.getDIAMETROBASE()*2);

        doubleIncrementatori(2);
        i=0;

    }

    @Override
    public void suonaColpitore()
    {
        getAmbiente().suonaElettrificatore();
    }

    public CyberBomba(Ambiente a,float ix,float iy, int c,float xx,float yy,Bonus bon)
    {
        super(a, ix, iy, c, xx, yy, bon);
        impostaHp(50);
        setDiametro(Bomba.getDIAMETROBASE()*2);

        setPunti(75);
        doubleIncrementatori(2);
        i=0;


    }

    public  Bomba getBomba (Ambiente a,float ix,float iy, int c,float xx,float yy)
    {
        return new CyberBomba(a,ix,iy,c,xx,yy,getBonus());

    }


    private int i;
    public void muovi()
    {
        super.muovi();
        if(getRaggio()!=0)
        {
            i++;
            if(i>=immagini.length)
            {
                i=immagini.length-1;
            }
        }

    }

    public void disegnaBomba(Canvas g, Paint p)
    {

        if (getRaggio() == 0)
        {
            g.drawBitmap(immagini[i],getX(),getY(),p);
            disegnaBarraHp(g,p);

        }
        else if (getRaggio() <= getDiametro())
        {


            float x1=getCentroX()-getRaggio()/2;
            float y1=getCentroY()-getRaggio()/2;
            g.drawBitmap(immagini[i],x1,y1,p);

        }


    }




}
