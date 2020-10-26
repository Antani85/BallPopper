package it.brun.dario.ballpopper.views;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;

import it.brun.dario.ballpopper.R;
import it.brun.dario.ballpopper.ballpopper.Giocatore;
import it.brun.dario.ballpopper.dialogs.FinestraDialogoGettatrice;
import it.brun.dario.ballpopper.pseudoviews.PannelloGettaPotenziamenti;
import it.brun.dario.ballpopper.views.MessageView;

/**
 * Created by dario on 09/05/17.
 */
public class PannelloGettatore extends View
{




    private float dim;

    private boolean assegnato;
    private String intestauno;
    private String intestadue;

    private void setDimensioni()
    {


        Paint p=new Paint();

        Giocatore.setTypeface(p);

        MessageView.setDimensione(intestauno,p,getWidth()*8/10,getHeight()*3/20);


        dim=p.getTextSize();

        MessageView.setDimensione(intestadue,p,getWidth()*8/10,getHeight()*3/20);
        if(p.getTextSize()<dim)
        {
            dim=p.getTextSize();
        }


        pannellogetta=new PannelloGettaPotenziamenti(this,Giocatore.inventario(),getWidth()/10,getHeight()/2,getWidth(),getHeight());


        assegnato=true;


    }
    private FinestraDialogoGettatrice parente;
    public PannelloGettatore(FinestraDialogoGettatrice p)
    {
        super(p.getActivity());
        parente=p;

        assegnato=false;



        intestauno=getResources().getString(R.string.troppibonus);
        intestadue=getResources().getString(R.string.qualeeliminare);
        setOnTouchListener(listener);


    }
    private PannelloGettaPotenziamenti pannellogetta;
    private View.OnTouchListener listener=new View.OnTouchListener()
    {
        @Override
        public boolean onTouch(View v, MotionEvent event)
        {

            pannellogetta.onTouch(v,event);
            postInvalidate();
            return true;
        }


    };

    @Override
    protected void onDraw(Canvas c)
    {
        if(!assegnato)
        {
            setDimensioni();
        }

        Paint p=new Paint();
        Giocatore.setTypeface(p);
        p.setTextSize(dim);

        p.setColor(Color.argb(255,239,222,239));
        c.drawRect(new RectF(0,0,getWidth(),getHeight()),p);
        p.setColor(Color.BLACK);
        c.drawText(intestauno,getWidth()/10,getHeight()/6,p);
        c.drawText(intestadue, getWidth() / 10, getHeight() / 3, p);
        pannellogetta.disegnaPannelloGettaPotenziamenti(c,p);


    }
    public void concludi()
    {
        parente.dismiss();
    }



}
