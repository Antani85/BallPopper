package it.brun.dario.ballpopper.ballpopper.balls;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import it.brun.dario.ballpopper.ballpopper.bonus.Bonus;
import it.brun.dario.ballpopper.ballpopper.Giocatore;
import it.brun.dario.ballpopper.R;
import it.brun.dario.ballpopper.views.Ambiente;

/**
 * Created by dario on 16/05/17.
 */
public class BombaFantasma extends Bomba
{
    private static Bitmap[] immagini;
    public static void setImmagine(Activity act)
    {
        immagini=new Bitmap[24];

        immagini[0]=Giocatore.getResizedBitmap(BitmapFactory.decodeResource(act.getResources(), R.drawable.pallafantasma), (int)(Bomba.getDIAMETROBASE()*4), (int)(Bomba.getDIAMETROBASE()*4));

        for(int i=23;i>0;i--)
        {
            immagini[i] = Giocatore.getResizedBitmap(BitmapFactory.decodeResource(act.getResources(), R.drawable.pallafantasma), (int) (Bomba.getDIAMETROBASE() * 4-Bomba.getDIAMETROBASE()*(24-i)/6), (int) (Bomba.getDIAMETROBASE() * 4-Bomba.getDIAMETROBASE()*(24-i)/6));
        }




    }
    @Override
    public void incrementaInventario()
    {
        Giocatore.inventario().incrementaFantasmiScoppiati();
    }
    public BombaFantasma(Ambiente a, float ix, float iy, int c, float xx, float yy)
    {
        super(a, ix, iy, c, xx, yy);
        setPunti(1000);
        impostaHp(250);
        setDiametro(Bomba.getDIAMETROBASE()*4);
        doubleIncrementatori(4);
        i=0;

    }


    public BombaFantasma(Ambiente a,float ix,float iy, int c,float xx,float yy,Bonus bon)
    {
        super(a, ix, iy, c, xx, yy, bon);
        impostaHp(250);
        setPunti(1000);
        setDiametro(Bomba.getDIAMETROBASE()*4);
        doubleIncrementatori(4);
        i=0;


    }

    public  Bomba getBomba (Ambiente a,float ix,float iy, int c,float xx,float yy)
    {
        return new BombaFantasma(a,ix,iy,c,xx,yy,getBonus());

    }

    @Override
    public void suonaColpitore()
    {
        getAmbiente().suonaFantasmatore();
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
