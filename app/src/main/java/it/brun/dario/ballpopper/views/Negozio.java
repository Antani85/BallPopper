package it.brun.dario.ballpopper.views;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

import it.brun.dario.ballpopper.R;
import it.brun.dario.ballpopper.activities.NegozioActivity;
import it.brun.dario.ballpopper.ballpopper.Giocatore;
import it.brun.dario.ballpopper.ballpopper.shots.ColpiAtomici;
import it.brun.dario.ballpopper.ballpopper.shots.ColpiDetonanti;
import it.brun.dario.ballpopper.ballpopper.shots.ColpiEsplosivi;
import it.brun.dario.ballpopper.ballpopper.shots.ColpiMine;
import it.brun.dario.ballpopper.ballpopper.shots.ColpiNormali;
import it.brun.dario.ballpopper.ballpopper.shots.ColpiPerforanti;
import it.brun.dario.ballpopper.ballpopper.shots.ColpiPietroni;
import it.brun.dario.ballpopper.ballpopper.shots.Munizioni;
import it.brun.dario.ballpopper.views.Ambiente;

/**
 * Created by dario on 18/04/17.
 */
public class Negozio extends View
{
    private double xP;
    private double yP;
    private double w;
    private double h;
    private double xN;
    private double yN;
    private double xD;
    private double yD;
    private double xE;
    private double yE;
    private double xPT;
    private double yPT;
    private double wPT;
    private double hPT;
    private double xM;
    private double yM;
    private double wM;
    private double hM;
    private double xA;
    private double yA;
    private double wA;
    private double hA;


    private NegozioActivity activity;
    public static final String MUNIZIONI="Munizioni";
    public static final String ANIMATION="Animation";


    private static Bitmap immagine;
    public static void assegnaImmagine(Activity act)
    {
        immagine=Giocatore.getResizedBitmap(BitmapFactory.decodeResource(act.getResources(), R.drawable.negozio), Ambiente.getHEIGHT(),Ambiente.getWIDTH());
    }
    private int animation;

    private void assegnaDimensioni()
    {
        xP=0.32106;
        yP=0.11515;
        xN=0.47307;
        yN=0.11515;
        xD=0.67689;
        yD=0.1103;
        w=0.10157;
        h=0.14545;
        xE=0.81049;
        yE=0.11515;
        xPT=0.27607;
        yPT=0.61818;
        hPT=0.2;
        wPT=0.12952;
        xM=0.59782;
        yM=0.67273;
        wM=0.19086;
        hM=0.16606;
        xA=0.82413;
        yA=0.65697;
        wA=0.13769;
        hA=0.17939;
    }

    private Munizioni assegnaMunizioni(int x, int y)
    {
        int xx=Ambiente.getWIDTH();
        int yy=Ambiente.getHEIGHT();
        if(x>xx*xN&&x<xx*xN+xx*w&&y>yy*yN&&y<yy*yN+yy*h)
        {
            animation=R.style.DialogAnimationNormale;
            return new ColpiNormali(1);
        }
        else if(x>xx*xP&&x<xx*xP+xx*w&&y>yy*yP&&y<yy*yP+yy*h)
        {
            animation=R.style.DialogAnimationPerforante;

            return new ColpiPerforanti(1);
        }
        else if(x>xx*xD&&x<xx*xD+xx*w&&y>yy*yD&&y<yy*yD+yy*h)
        {
            animation=R.style.DialogAnimationDetonante;

            return new ColpiDetonanti(1);
        }
        else if(x>xx*xE&&x<xx*xE+xx*w&&y>yy*yE&&y<yy*yE+yy*h)
        {
            animation=R.style.DialogAnimationEsplosivo;

            return new ColpiEsplosivi(1);
        }
        else if(x>xx*xPT&&x<xx*xPT+xx*wPT&&y>yy*yPT&&y<yy*yPT+yy*hPT)
        {
            animation=R.style.DialogAnimationPietrone;

            return new ColpiPietroni(1);
        }
        else if(x>xx*xM&&x<xx*xM+xx*wM&&y>yy*yM&&y<yy*yM+yy*hM)
        {
            animation=R.style.DialogAnimationMina;

            return new ColpiMine(1);
        }
        else if(x>xx*xA&&x<xx*xA+xx*wA&&y>yy*yA&&y<yy*yA+yy*hA)
        {
            animation=R.style.DialogAnimationAtomico;

            return new ColpiAtomici(1);
        }
        else
        {
            return null;
        }

    }
    public Negozio(Context c)
    {
        super(c);
        animation=0;
        activity=(NegozioActivity)c;
        assegnaDimensioni();



        setOnTouchListener(new OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {

                Munizioni m=assegnaMunizioni((int)event.getX(),(int)event.getY());
                if(m!=null)
                {
                    activity.creaDialog(m,animation);
                }
                return false;
            }
        });
    }

    public void onDraw(Canvas cc)
    {
        Paint p=new Paint();
        Bitmap bitmap = Bitmap.createBitmap(Ambiente.getWIDTH(), Ambiente.getHEIGHT(), Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bitmap);
        c.drawBitmap(immagine,0,0,p);
        p.setColor(Color.BLUE);

        //c.drawRect(new RectF((int)(x*Ambiente.getWIDTH()),(int)(y*Ambiente.getWIDTH()),(int)(x*Ambiente.getWIDTH()+w*Ambiente.getWIDTH()),(int)(y*Ambiente.getWIDTH()+h*Ambiente.getWIDTH())),p);

        cc.drawBitmap(bitmap,0,0,p);

    }

}
