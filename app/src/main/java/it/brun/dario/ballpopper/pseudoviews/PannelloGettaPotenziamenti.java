package it.brun.dario.ballpopper.pseudoviews;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;

import it.brun.dario.ballpopper.views.PannelloGettatore;
import it.brun.dario.ballpopper.R;
import it.brun.dario.ballpopper.ballpopper.Giocatore;
import it.brun.dario.ballpopper.ballpopper.InventarioMunizioni;
import it.brun.dario.ballpopper.ballpopper.bonus.Bonus;

/**
 * Created by dario on 09/05/17.
 */
public class PannelloGettaPotenziamenti
{
    private Bitmap cestinochiuso;
    private Bitmap cestinoaperto;

    private static final float PROPORZIONECHIUSO=0.98084f;
    private static final float PROPORZIONEAPERTO=1.10728f;


    private  void assegnaCestino(Context act)
    {
        cestinochiuso=Giocatore.getResizedBitmap(BitmapFactory.decodeResource(act.getResources(), R.drawable.cestinochiuso),(int)(width/4*PROPORZIONECHIUSO),(int)(width/4));
        cestinoaperto=Giocatore.getResizedBitmap(BitmapFactory.decodeResource(act.getResources(), R.drawable.cestinoaperto),(int)(width/4*PROPORZIONEAPERTO),(int)(width/4));


    }
    private void disegnaCestino(Canvas c)
    {
        if(selectedBonus==null)
        {
            float k=width/4*PROPORZIONEAPERTO-width/4*PROPORZIONECHIUSO;
            c.drawBitmap(cestinochiuso,width*2/3,baseY+k,null);
        }
        else
        {
            c.drawBitmap(cestinoaperto,width*2/3,baseY,null);
        }
    }
    private float BASE;
    private Bonus selectedBonus;

    public void setSelectedBonus()
    {

        int m=inventario.numPotenziamenti();

        float base=0;
        float basedue=0;
        float dimmax=baseX;

        if(selectedBonus!=null&&posizioneX>=width*2/3&&posizioneY>baseY&&posizioneX<=width&&posizioneY<=height)
        {

            gettaBonus(selectedBonus);
            selectedBonus=null;
            return;

        }

        for(int i=0;i<m;i++)
        {

            if(dimmax+BASE>width*2/3)
            {
                base=0;
                dimmax=baseX;
                basedue+=BASE;

            }

            if (posizioneX >= baseX +base && posizioneX <= baseX+base+Bonus.getDIAMETROBASE()&&posizioneY>=baseY+basedue&&posizioneY<=baseY+basedue+Bonus.getDIAMETROBASE())
            {

                if(selectedBonus!=null)
                {
                    inventario.scambia(selectedBonus,inventario.getPotenziamento(i));
                    selectedBonus=null;
                    return;
                }
                else
                {
                    selectedBonus=inventario.getPotenziamento(i);
                    return;
                }

            }
            base+=BASE;
            dimmax+=BASE;
        }
        selectedBonus=null;
        return;
    }

    public void onTouch(View v, MotionEvent event)
    {


        if (event.getAction() == MotionEvent.ACTION_DOWN)
        {
            if(setPosizioni(event.getX(),event.getY()))
            {
                setSelectedBonus();
            }

        }
        else   if (event.getAction() == MotionEvent.ACTION_MOVE)
        {
            setPosizioni(event.getX(), event.getY());
        }
        else if (event.getAction() == MotionEvent.ACTION_UP)
        {


            setSelectedBonus();
        }

    }

    public boolean setPosizioni(float px,float py)
    {
        if(px>=baseX&&px<=width&&py>=baseY&&py<=height)
        {
            posizioneX=px;
            posizioneY=py;

            return true;
        }
        return false;
    }
    private InventarioMunizioni inventario;
    private PannelloGettatore parent;
    public PannelloGettaPotenziamenti(PannelloGettatore c,InventarioMunizioni inv,float bx,float by,float w,float h)
    {
        BASE=(Bonus.getDIAMETROBASE()*5)/3;
        inventario=inv;


        baseX=bx;
        baseY=by;
        width=w;
        height=h;
        parent=c;
        assegnaCestino(parent.getContext());


    }




    private float posizioneX;
    private float posizioneY;
    private float baseX;
    private float baseY;
    private float width;
    private float height;



    public float[] getDimensioni()
    {
        return new float[]{baseX,baseY};
    }
    public void disegnaPannelloGettaPotenziamenti(Canvas g,Paint p)
    {

        int m=inventario.numPotenziamenti();

        p.setColor(Color.BLACK);
        p.setShader(null);
        float base=0;
        float basedue=0;
        float dimmax=baseX;
        Bonus selezion=null;

        for(int i=0;i<m;i++)
        {

            if(dimmax+BASE>width*2/3)
            {
                base=0;
                dimmax=baseX;
                basedue+=BASE;

            }
            p.setShader(null);
            p.setColor(Color.BLACK);
            g.drawOval(new RectF(baseX + base, baseY+basedue, baseX +base+ Bonus.getDIAMETROBASE(), baseY+basedue+Bonus.getDIAMETROBASE()), p);
            if(!inventario.getPotenziamento(i).equals(selectedBonus))
            {

                inventario.getPotenziamento(i).disegnaBonusBig(g, p, baseX + base, baseY+basedue);
            }

            else
            {
                selezion=inventario.getPotenziamento(i);
            }
            base+=BASE;
            dimmax+=BASE;
        }
        disegnaCestino(g);
        if(selezion!=null)
        {
            selezion.disegnaBonusBig(g, p, posizioneX - Bonus.getDIAMETROBASE() / 2, posizioneY - Bonus.getDIAMETROBASE() / 2);
        }


    }
    public void gettaBonus(Bonus b)
    {
        inventario.gettaBonus(b);
        if(inventario.getMaxPotenziamenti()>=inventario.numPotenziamenti())
        {
            parent.concludi();
        }
    }
    public void gettaBonus()
    {
        int n=inventario.getMaxPotenziamenti();
        int m=inventario.numPotenziamenti();

        for(int i=n;i<m;i++)
        {
            inventario.gettaBonus(i);
        }

    }



}
