package it.brun.dario.ballpopper.views;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

import it.brun.dario.ballpopper.Premio;
import it.brun.dario.ballpopper.R;
import it.brun.dario.ballpopper.ballpopper.Giocatore;
import it.brun.dario.ballpopper.dialogs.FinestraDialogoPremio;
import it.brun.dario.ballpopper.views.MessageView;

/**
 * Created by dario on 22/05/17.
 */

public class PannelloPremio extends View
{


    public static final float PROPORZIONE=0.74825f;

    private Premio premio;
    private FinestraDialogoPremio finestra;


    private float posX;
    private float dim;
    private boolean assegnato;
    private String intestauno;
    private String intestadue;


    private Bitmap sfondo;



    private void setDimensioni()
    {

        Paint p=new Paint();
        Giocatore.setTypeface(p);
        MessageView.setDimensione(intestauno,p,getWidth()*8/10,getHeight()/15);
        dim=p.getTextSize();
        MessageView.setDimensione(intestadue,p,getWidth()*8/10,getHeight()/15);

        if(dim>p.getTextSize())
        {
            dim=p.getTextSize();
        }

        posX=p.measureText(intestadue,0,intestadue.length());





        sfondo=Giocatore.getResizedBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.premio),getHeight(),getWidth());
        assegnato=true;


    }
    public PannelloPremio(FinestraDialogoPremio ff,Premio pre)
    {
        super(ff.getActivity());

        finestra=ff;
        premio=pre;

        intestauno=getResources().getString(R.string.ringraziamento);
        intestadue=getResources().getString(R.string.premio)+" ";

        assegnato=false;








    }


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
        c.drawText(intestauno,getWidth()/10,getHeight()/8,p);
        c.drawText(intestadue,getWidth()/10,getHeight()/3,p);
        premio.disegnaPremio(c,p,getWidth()/10+posX,getHeight()/3);



    }

    public void setta()
    {
        setDimensioni();
        postInvalidate();
    }




}
