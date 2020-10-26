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
 * Created by dario on 15/05/17.
 */
public class BombaApe extends Bomba
{
    private static final float PROPORZIONEBASE = 0.10f;
    private  static Bitmap[] immagini;

    public static void setImmagine(Activity act) {
        immagini = new Bitmap[24];
        immagini[0] = Giocatore.getResizedBitmap(BitmapFactory.decodeResource(act.getResources(), R.drawable.pallaape), (int) (Bomba.getDIAMETROBASE() * 2f), (int) (Bomba.getDIAMETROBASE() * 2f));

        for(int i=23;i>0;i--)
        {
            immagini[i] = Giocatore.getResizedBitmap(BitmapFactory.decodeResource(act.getResources(), R.drawable.pallaape), (int) (Bomba.getDIAMETROBASE() * 2f-Bomba.getDIAMETROBASE()*(24-i)/12), (int) (Bomba.getDIAMETROBASE() * 2f-Bomba.getDIAMETROBASE()*(24-i)/12));

        }

    }

    public BombaApe(Ambiente a, float ix, float iy, int c, float xx, float yy) {
        super(a, ix, iy, c, xx, yy);
        setPunti(50);
        impostaHp(60);
        setDiametro(Bomba.getDIAMETROBASE()*2);

        i = 0;

    }


    public BombaApe(Ambiente a, float ix, float iy, int c, float xx, float yy, Bonus bon) {
        super(a, ix, iy, c, xx, yy, bon);
        impostaHp(60);
        setDiametro(Bomba.getDIAMETROBASE()*2);

        setPunti(50);
        i = 0;


    }

    @Override
    public void incrementaInventario()
    {
        Giocatore.inventario().incrementaApiScoppiate();
    }

    public Bomba getBomba(Ambiente a, float ix, float iy, int c, float xx, float yy) {
        return new BombaApe(a, ix, iy, c, xx, yy, getBonus());

    }


    private int i;

    public void muovi()
    {
        super.muovi();
        if (getRaggio() != 0)
        {
            i++;
            if(i>=immagini.length)
            {
                i=immagini.length-1;
            }
        }

    }

    public void disegnaBomba(Canvas g, Paint p) {


        if (getRaggio() == 0) {
            g.drawBitmap(immagini[i], getX(), getY(), p);
            disegnaBarraHp(g, p);

        } else if (getRaggio() <= getDiametro()) {


            float x1 = getCentroX() - getRaggio() / 2;
            float y1 = getCentroY() - getRaggio() / 2;
            g.drawBitmap(immagini[i], x1, y1, p);

        }


    }



    @Override
    public void suonaColpitore()
    {
        getAmbiente().suonaApicultore();
    }


    @Override
    public boolean interno(float xx, float yy, float r)
    {

        setCentro();
        float x2=xx+r;
        float y2=yy+r;
        float x1=getCentroX();
        float y1=this.getY()+this.getDiametro()*2*PROPORZIONEBASE+this.getDiametro()*(1-PROPORZIONEBASE*2)/2;
        return (x1-x2)*(x1-x2)+(y1-y2)*(y1-y2)<=(r+this.getDiametro()/2)*(r+this.getDiametro()/2);
        /*double x = getX();
        double y = getY();
        double diametro = getDiametro();
        double x2 = (double) (xx) + (double) r;
        double y2 = (double) (yy) + (double) r;
        double xc = (double) x + (double) (diametro) / 2;
        double yc = (double) y + (double) (diametro) / 2;

        double x1 = x2 - xc;
        double y1 = y2 - yc;


        double r1 = (double) (diametro) / 2;
        double r2 = (double) r;


        double b = (x1 * x1 * y1 + y1 * y1 * y1 + y1 * r1 * r1 - y1 * r2 * r2) / (x1 * x1);


        double a = (x1 * x1 + y1 * y1) / (x1 * x1);
        double c1 = (x1 * x1 + y1 * y1 + r1 * r1 - r2 * r2);
        double c = (c1 * c1 / (4 * x1 * x1)) - r1 * r1;


        if ((b * b) - (4 * a * c) >= 0) {
            return true;
        } else if ((x1 * x1 + y1 * y1) <= (diametro / 2) * (diametro / 2)) {
            return true;
        } else if (((xc - x2) * (xc - x2) + (yc - y2) * (yc - y2)) <= r * r) {
            return true;
        }

        return false;*/


    }

}
