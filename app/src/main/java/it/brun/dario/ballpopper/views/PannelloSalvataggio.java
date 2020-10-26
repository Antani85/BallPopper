package it.brun.dario.ballpopper.views;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

import it.brun.dario.ballpopper.interfaces.Eseguibile;
import it.brun.dario.ballpopper.R;
import it.brun.dario.ballpopper.pseudoviews.SquareButton;
import it.brun.dario.ballpopper.ballpopper.Giocatore;
import it.brun.dario.ballpopper.dialogs.FinestraDialogoSalvataggio;
import it.brun.dario.ballpopper.pseudoviews.InputTextView;

/**
 * Created by dario on 30/04/17.
 */
public class PannelloSalvataggio extends View
{


    public static final float PROPORZIONE=0.74825f;
    private SquareButton salva;
    private InputTextView edita;

    private FinestraDialogoSalvataggio finestra;
    private int salvawidth;
    private int salvaheight;
    private int editaheight;


    private float dim;
    private boolean assegnato;
    private String save;
    private String intesta;

    private String nomefile;
    private Bitmap sfondo;



    private void setDimensioni()
    {

        Paint p=new Paint();
        Giocatore.setTypeface(p);
        MessageView.setDimensione(intesta,p,getWidth()*8/10,getHeight()/15);
        dim=p.getTextSize();
        salva=new SquareButton(getWidth()*15/16-getWidth()/6,getHeight()*15/16-getWidth()/12,getWidth()/6,getWidth()/12,new Eseguibile() {
            @Override
            public void esegui()
            {
                finestra.salva(edita.getText());

            }
        },save);
        edita=new InputTextView(this,getWidth()/10,getHeight()/4,getWidth()*4/5,getHeight()/8,nomefile);



        sfondo=Giocatore.getResizedBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.salva),getHeight(),getWidth());
        assegnato=true;


    }
    public PannelloSalvataggio(FinestraDialogoSalvataggio ff)
    {
        super(ff.getActivity());

        finestra=ff;


        save=getResources().getString(R.string.salva);
        intesta=getResources().getString(R.string.insert_name);
        nomefile=getResources().getText(R.string.nomefile).toString();
        setFocusableInTouchMode(true);
        assegnato=false;





        setOnTouchListener(listener);
        setOnKeyListener(listenerdue);



    }
    private View.OnTouchListener listener=new View.OnTouchListener()
    {
        @Override
        public boolean onTouch(View v, MotionEvent event)
        {

            salva.onTouch(event);
            edita.onTouch(event);
            postInvalidate();
            return true;
        }


    };
    public void disalloca()
    {
        edita.stoppa();
    }
    private OnKeyListener listenerdue=new OnKeyListener()
    {
        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event)
        {
            if (event.getAction() == KeyEvent.ACTION_DOWN)
            {


                if (event.getKeyCode() == KeyEvent.KEYCODE_BACK)
                {
                    finestra.onPause();
                }
            }
            edita.onKey(v, keyCode, event);
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

        edita.riparti();

        Paint p=new Paint();
        Giocatore.setTypeface(p);
        p.setTextSize(dim);
        c.drawBitmap(sfondo, 0, 0, p);
        c.drawText(intesta,getWidth()/10,getHeight()/8,p);
        salva.disegnaBottone(c);
        edita.disegnaInputTextView(c);


    }





}
