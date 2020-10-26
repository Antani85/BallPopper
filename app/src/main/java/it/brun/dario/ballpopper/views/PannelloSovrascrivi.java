package it.brun.dario.ballpopper.views;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

import it.brun.dario.ballpopper.interfaces.Eseguibile;
import it.brun.dario.ballpopper.R;
import it.brun.dario.ballpopper.pseudoviews.RoundButton;
import it.brun.dario.ballpopper.ballpopper.Giocatore;
import it.brun.dario.ballpopper.dialogs.FinestraDialogoSalvataggio;

/**
 * Created by dario on 01/05/17.
 */

public class PannelloSovrascrivi extends View
{


    private static final float RAGGIOBUTTON=0.17482f;


    private float dim;

    private boolean assegnato;
    private RoundButton yes;
    private RoundButton no;

    private FinestraDialogoSalvataggio finestra;

    private Bitmap sfondo;
    public static final float PROPORZIONE=0.74825f;



    private float raggio;
    private String intestauno;
    private String intestadue;

    private void setDimensioni()
    {

        raggio=getWidth()*RAGGIOBUTTON;
        Paint p=new Paint();

        Giocatore.setTypeface(p);

        MessageView.setDimensione(intestauno,p,getWidth()*8/10,getHeight()*3/20);


        dim=p.getTextSize();

        MessageView.setDimensione(intestadue,p,getWidth()*8/10,getHeight()*3/20);
        if(p.getTextSize()<dim)
        {
            dim=p.getTextSize();
        }



        yes=new RoundButton(getWidth()/2-raggio*5/4,getHeight()*3/5,raggio,new Eseguibile() {
            @Override
            public void esegui()
            {
                finestra.salvataggio();
            }
        },getResources().getString(R.string.si));

        no=new RoundButton(getWidth()/2+raggio/4,getHeight()*3/5,raggio,new Eseguibile() {
            @Override
            public void esegui()
            {
                finestra.setLayoutUno();
            }
        },getResources().getString(R.string.no));
        sfondo=Giocatore.getResizedBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.sovrascrivere),getHeight(),getWidth());
        assegnato=true;


    }
    public PannelloSovrascrivi(FinestraDialogoSalvataggio ff)
    {
        super(ff.getActivity());

        finestra=ff;
        assegnato=false;



        intestauno=getResources().getString(R.string.fileesiste);
        intestadue=getResources().getString(R.string.richiestasovrascrivi);
        setOnTouchListener(listener);


    }
    private View.OnTouchListener listener=new View.OnTouchListener()
    {
        @Override
        public boolean onTouch(View v, MotionEvent event)
        {

            yes.onTouch(event);
            no.onTouch(event);
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
        c.drawBitmap(sfondo, 0, 0, p);
        c.drawText(intestauno,getWidth()/10,getHeight()/6,p);
        c.drawText(intestadue,getWidth()/10,getHeight()/3,p);
        yes.disegnaBottone(c);
        no.disegnaBottone(c);


    }





}
