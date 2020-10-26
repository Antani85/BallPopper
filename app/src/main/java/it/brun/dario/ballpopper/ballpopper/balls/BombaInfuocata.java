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
 * Created by dario on 28/04/17.
 */
public class BombaInfuocata extends Bomba
{
    private float angolo;
    private static Bitmap[] immagini;
    public static void setImmagine(Activity act)
    {
        immagini=new Bitmap[24];

        immagini[0]=Giocatore.getResizedBitmap(BitmapFactory.decodeResource(act.getResources(), R.drawable.pallainfuocata), (int)(Bomba.getDIAMETROBASE()*7), (int)(Bomba.getDIAMETROBASE()*7));
        for(int i=23;i>0;i--)
        {
            immagini[i]=Giocatore.getResizedBitmap(BitmapFactory.decodeResource(act.getResources(), R.drawable.pallainfuocata), (int)(Bomba.getDIAMETROBASE()*7-Bomba.getDIAMETROBASE()*(24-i)*7/24), (int)(Bomba.getDIAMETROBASE()*7-Bomba.getDIAMETROBASE()*(24-i)*7/24));

        }





    }
    private static final int ANGOLOBASE=(int)(Bomba.PROPFPS*36);
    public BombaInfuocata(Ambiente a, float ix, float iy, int c, float xx, float yy)
    {
        super(a,ix,iy,c,xx,yy);
        setPunti(1000);
        impostaHp(150);
        setDiametro(Bomba.getDIAMETROBASE()*7);
        i=0;
        angolo=0;



    }

    private void aumentaAngolo()
    {
        angolo+=ANGOLOBASE;
        if(angolo>360)
        {
            angolo=angolo-360;
        }
    }
    public BombaInfuocata(Ambiente a,float ix,float iy, int c,float xx,float yy,Bonus bon)
    {
        super(a,ix,iy,c,xx,yy,bon);
        impostaHp(150);
        setPunti(1000);
        setDiametro(Bomba.getDIAMETROBASE()*7);
        i=0;
        angolo=0;


    }

    public  Bomba getBomba (Ambiente a,float ix,float iy, int c,float xx,float yy)
    {
        return new BombaInfuocata(a,ix,iy,c,xx,yy,getBonus());

    }

    private int i;

    @Override
    public void incrementaInventario()
    {
        Giocatore.inventario().incrementaInfuocateScoppiate();
    }

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
        aumentaAngolo();
    }
    @Override
    public void suonaColpitore()
    {
        getAmbiente().suonaFuocatore();
    }

    public void disegnaBomba(Canvas g, Paint p)
    {

        if (getRaggio() == 0)
        {
            setCentro();


            g.save();

            g.rotate(angolo,getCentroX(),getCentroY());

            g.drawBitmap(immagini[i],getX(),getY(),p);
            g.restore();
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
