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
import it.brun.dario.ballpopper.dialogs.FinestraDialogoCancella;

/**
 * Created by dario on 04/05/17.
 */
public class PannelloCancellaFile extends View
{

    private static final float POSYBUTTON=0.46729f;
    private static final float POSXBUTTON=0.17301f;
    private static final float RAGGIOBUTTON=0.17482f;

    private float posxyes;
    private float posxno;
    private float posy;
    private float dim;

    private boolean assegnato;
    private RoundButton yes;
    private RoundButton no;

    private FinestraDialogoCancella finestra;

    private Bitmap sfondo;
    public static final float PROPORZIONE=0.74825f;



    private float raggio;
    private String intestauno;
    private String intestadue;

    private void setDimensioni()
    {
        posy=getHeight()*POSYBUTTON;
        posxyes=getWidth()*POSXBUTTON;
        raggio=getWidth()*RAGGIOBUTTON;
        Paint p=new Paint();

        Giocatore.setTypeface(p);

        MessageView.setDimensione(intestauno,p,getWidth()*8/10,getHeight()*39/300);


        dim=p.getTextSize();

        MessageView.setDimensione(intestadue,p,getWidth()*8/10,getHeight()*39/300);
        if(p.getTextSize()<dim)
        {
            dim=p.getTextSize();
        }


        posxno=getWidth()-posxyes-raggio;
        yes=new RoundButton(posxyes,posy,raggio,new Eseguibile() {
            @Override
            public void esegui()
            {
                finestra.cancella();
            }
        },getResources().getString(R.string.si));

        no=new RoundButton(posxno,posy,raggio,new Eseguibile() {
            @Override
            public void esegui()
            {
                finestra.chiudi();
            }
        },getResources().getString(R.string.no));
        sfondo=Giocatore.getResizedBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.cancellasalvataggio),getHeight(),getWidth());

        assegnato=true;


    }
    public PannelloCancellaFile(FinestraDialogoCancella ff)
    {
        super(ff.getActivity());
        finestra=ff;
        assegnato=false;




        intestauno=getResources().getText(R.string.cancellazione).toString();
        intestadue=finestra.getNome()+"?";
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
        c.drawBitmap(sfondo,0,0,p);
        c.drawText(intestauno,getWidth()/10,getHeight()/5,p);
        c.drawText(intestadue,getWidth()/10,getHeight()*2/5,p);
        yes.disegnaBottone(c);
        no.disegnaBottone(c);


    }





}
