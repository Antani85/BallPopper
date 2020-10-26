package it.brun.dario.ballpopper.views;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import it.brun.dario.ballpopper.pseudoviews.PannelloSelezionabile;
import it.brun.dario.ballpopper.R;
import it.brun.dario.ballpopper.activities.InventarioActivity;
import it.brun.dario.ballpopper.ballpopper.Giocatore;
import it.brun.dario.ballpopper.ballpopper.balls.Bomba;
import it.brun.dario.ballpopper.ballpopper.bonus.Bonus;

/**
 * Created by dario on 02/05/17.
 */
public class PannelloInventario extends View
{
    private static final float LENGTHX=0.89510f;
    private static final float POSX=0.05245f;
    private static final float POSY=0.07009f;
    private static final float POSY2=0.53738f;

    private static Bitmap immagine;
    public static void assegnaImmagine(Activity act)
    {
        immagine=Giocatore.getResizedBitmap(BitmapFactory.decodeResource(act.getResources(), R.drawable.inventario), Ambiente.getHEIGHT(), Ambiente.getWIDTH());
        //immagine=Giocatore.getResizedBitmap(Giocatore.decodeResource(act.getResources(), R.drawable.inventario), Ambiente.getHEIGHT(), Ambiente.getWIDTH());


    }
    private String punti;
    private String bonus;
    private Paint p;
    private float lengthLine;
    private float puntiX1;
    private float puntiX2;
    private float puntiY;
    private float bonusX1;
    private float bonusX2;
    private float bonusY;
    private float munizionix;
    private float munizioniy;
    private InventarioActivity parente;


    private List<PannelloSelezionabile> bonuslist;
    public PannelloInventario(Context c)
    {
        super(c);
        parente=(InventarioActivity)c;
        punti=c.getResources().getString(R.string.punti);
        bonus=c.getResources().getString(R.string.bonus);
        bonuslist=new ArrayList<PannelloSelezionabile>();

        lengthLine=LENGTHX*Ambiente.getWIDTH();
        p=new Paint();
        Giocatore.setTypeface(p);
        p.setTextSize(Bomba.getDIAMETROBASE());
        Rect bounds = new Rect();
        p.getTextBounds(punti, 0, punti.length(), bounds);
        puntiX1=POSX*Ambiente.getWIDTH();
        puntiY=bounds.height()+POSY*Ambiente.getHEIGHT();
        puntiX2=bounds.width()+Bonus.getDIAMETROBASE()+puntiX1;
        bonusX1=POSX*Ambiente.getWIDTH();

        p.getTextBounds(bonus, 0, bonus.length(), bounds);
        bonusY=bounds.height()+Bonus.getDIAMETROBASE()+puntiY;

        bonusX2=bounds.width()+Bonus.getDIAMETROBASE()+bonusX1;
        creaBonusList(bonusX2,bonusY);
        munizioniy=POSY2*Ambiente.getHEIGHT();
        munizionix=POSX*Ambiente.getWIDTH();
        setOnTouchListener(listener);

    }


    private View.OnTouchListener listener=new View.OnTouchListener()
    {
        @Override
        public boolean onTouch(View v, MotionEvent event)
        {
            if(event.getAction()==MotionEvent.ACTION_DOWN)
            {
                for(int i=0;i<bonuslist.size();i++)
                {
                    if(bonuslist.get(i).interno(event.getX(),event.getY()))
                    {
                        bonuslist.get(i).setSelected(true);
                        postInvalidate();
                        return true;
                    }
                }
            }
            else if(event.getAction()==MotionEvent.ACTION_UP)
            {
                for(int i=0;i<bonuslist.size();i++)
                {

                    if(bonuslist.get(i).selected())
                    {
                        bonuslist.get(i).setSelected(false);
                        parente.creaDialog(bonuslist.get(i).getBonus());
                    }

                }
                postInvalidate();
                return true;
            }

            return true;
        }
    };


    private float creaBonusList(float x,float y)
    {
        float xx=x;
        float yy=y;
        for(int i=0;i<Giocatore.inventario().getMaxPotenziamenti();i++)
        {
            bonuslist.add(new PannelloSelezionabile(Giocatore.inventario().getPotenziamento(i),xx,yy-Bonus.getDIAMETROBASE(),Bonus.getDIAMETROBASE()));
            xx+=2*Bonus.getDIAMETROBASE();
            if(xx+Bonus.getDIAMETROBASE()>=lengthLine&&i<Giocatore.inventario().numPotenziamenti()-1)
            {
                xx=x;
                yy+=2*Bonus.getDIAMETROBASE();
            }
        }
        return yy+=2*Bonus.getDIAMETROBASE();

    }

    private float disegnaMunizioni(float x,float y,int munizioni,Canvas c,Paint p)
    {
        c.drawBitmap(Giocatore.inventario().immaginePalla(munizioni,(int)Bonus.getDIAMETROBASE()),x,y,p);
        String result=": "+Giocatore.inventario().getNumero(munizioni);
        Rect bounds=new Rect();
        p.getTextBounds(result,0,result.length(),bounds);
        c.drawText(result,x+Bonus.getDIAMETROBASE(),y+Bonus.getDIAMETROBASE(),p);
        return bounds.width()+Bonus.getDIAMETROBASE();


    }
    private void disegnaTutteLeMunizioni(float x,float y,Canvas c,Paint p)
    {
        float xx=x;
        float yy=y;
        for(int i=0;i<Giocatore.inventario().getNumeroMunizioni();i++)
        {
            xx+=disegnaMunizioni(xx,yy,i,c,p)+2*Bonus.getDIAMETROBASE();
            if(xx+2*Bonus.getDIAMETROBASE()>=lengthLine&&i<Giocatore.inventario().getNumeroMunizioni()-1)
            {
                xx=x;
                yy+=2*Bonus.getDIAMETROBASE();
            }
        }
    }


    public void onDraw(Canvas c)
    {
        c.drawBitmap(immagine,0,0,p);
        c.drawText(punti,puntiX1,puntiY,p);
        c.drawText(""+Giocatore.inventario().totPunti(),puntiX2,puntiY,p);
        c.drawText(bonus,bonusX1,bonusY,p);
        for(int i=0;i<bonuslist.size();i++)
        {
            bonuslist.get(i).disegnaPannello(c,p);
        }
        p.setColor(Color.BLACK);
        p.setTextSize(Bomba.getDIAMETROBASE());
        disegnaTutteLeMunizioni(munizionix,munizioniy,c,p);


    }
}
