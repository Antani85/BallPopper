package it.brun.dario.ballpopper.views;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;

import it.brun.dario.ballpopper.interfaces.Eseguibile;
import it.brun.dario.ballpopper.R;
import it.brun.dario.ballpopper.pseudoviews.RoundButton;
import it.brun.dario.ballpopper.pseudoviews.SquareButton;
import it.brun.dario.ballpopper.ballpopper.Giocatore;
import it.brun.dario.ballpopper.ballpopper.shots.Munizioni;
import it.brun.dario.ballpopper.dialogs.FinestraVendita;

/**
 * Created by dario on 05/05/17.
 */
public class PannelloVendita extends View
{
    private static final float POSXBUTTON=0.48951f;
    private static final float POSYBUTTON=0.41958f;
    private static final float WIDTHBUTTON=0.06993f;

    private static final float POSX=0.10489f;
    private static final float POSY=0.20979f;
    private static final float WIDTH=0.79254f;
    private static final float HEIGHT=0.17482f;

    private static final float COSTOWIDTH=0.37296f;
    private static final float COSTOHEIGHT=0.08741f;
    private static final float POSYCOSTOTOTALE=0.6993f;

    private static final float POSYCOSTO=0.34965f;
    private static final float POSYNUMERO=0.5944f;


    private static final float POSYACQUISTA=0.76923f;
    /*
    private static final float ACQUISTAHEIGHT=0.08741f;
    private static final float ACQUISTAWIDTH=0.16317f;
    */
    private static final float ACQUISTAHEIGHT=0.09986f;
    private static final float ACQUISTAWIDTH=0.18648f;

    private static final float POSYPUNTI=0.96153f;




    private static final float POSXIMMAGINE=0.02331f;
    private static final float POSYIMMAGINE=0.31468f;



    private static final String PROVA="999999";
    private static final float IMAGEWIDTH=0.34965f;


    private Munizioni munizioni;

    private int width;
    private int height;

    private float dimNome;
    private float dimCosto;
    private String costo;
    private String costot;
    private String puntit;
    private String acquist;
    private float costowidth;
    private float costoheight;
    private float dimcostot;
    private float dimpuntit;
    private float posxbutton;


    private Bitmap immagine=null;


    private  void assegnaImmagine( )
    {
        if(immagine==null)
        {
            immagine=munizioni.immaginePalla((int)(IMAGEWIDTH*getWidth()));

        }
    }
    private void assegnaDimensioni()
    {

        width=getWidth();
        height=getHeight();
        costowidth=width*COSTOWIDTH;
        costoheight=height*COSTOHEIGHT;
        Paint p=new Paint();
        Giocatore.setTypeface(p);
        MessageView.setDimensione(munizioni.getNome(),p,WIDTH*width,HEIGHT*height);
        dimNome=p.getTextSize();

        Giocatore.setTypefaceCorsivo(p);

        MessageView.setDimensione(costo,p,costowidth,costoheight);
        dimCosto=p.getTextSize();

        MessageView.setDimensione(costot+PROVA,p,costowidth,costoheight);
        dimcostot=p.getTextSize();

        MessageView.setDimensione(puntit+PROVA,p,costowidth,costoheight);
        dimpuntit=p.getTextSize();

        minus=new RoundButton(POSXBUTTON*width,POSYBUTTON*height,WIDTHBUTTON*width,new Eseguibile() {
            @Override
            public void esegui() {
                incrementa(-1);

            }
        },"-");

        plus=new RoundButton(POSXBUTTON*width+2*WIDTHBUTTON*width,POSYBUTTON*height,WIDTHBUTTON*width,new Eseguibile() {
            @Override
            public void esegui() {
                incrementa(1);

            }
        },"+");
        posxbutton=(float)(POSXBUTTON*width+WIDTHBUTTON*width+0.5*WIDTHBUTTON*width);


        acquista=new SquareButton(POSXBUTTON*width,POSYACQUISTA*height,ACQUISTAWIDTH*width,ACQUISTAHEIGHT*height,new Eseguibile()
        {
            @Override
            public void esegui()
            {
                acquista();
            }
        },acquist);

        setOnTouchListener(listener);
        assegnaImmagine();

        assegnato=true;

    }




    private int numero;
    private View.OnTouchListener listener=new View.OnTouchListener()
    {
        @Override
        public boolean onTouch(View v, MotionEvent event)
        {

            plus.onTouch(event);
            minus.onTouch(event);
            acquista.onTouch(event);
            postInvalidate();
            return true;
        }


    };


    private RoundButton plus;
    private RoundButton minus;
    private SquareButton acquista;

    private void incrementa(int n)
    {

        munizioni.aggiungi(n);
        if(munizioni.getNumero()==0)
        {
            munizioni.aggiungi(1);
        }
        numero=munizioni.getNumero();
        setCostoTotale();

    }

    private int costotot;
    private int puntitot;
    private void setCostoTotale()
    {
        costotot=munizioni.getNumero()*munizioni.getCosto();
    }

    private FinestraVendita contentente;
    private boolean assegnato;
    private void setPuntiDisponibili()
    {
        puntitot=Giocatore.inventario().totPunti();
    }
    protected void onDraw(Canvas c)
    {
        if(!assegnato)
        {
            assegnaDimensioni();
        }

        Paint p=new Paint();

        p.setColor(Color.argb(255,255,190,125));
        c.drawRect(new RectF(0, 0, getWidth(), getHeight()), p);
        p.setColor(Color.BLACK);
        p.setTextSize(dimNome);
        Giocatore.setTypeface(p);
        c.drawText(munizioni.getNome(), POSX*getWidth(), POSY*getHeight(), p);
        c.drawBitmap(immagine,POSXIMMAGINE*getWidth(),POSYIMMAGINE*getHeight(),p);
        Giocatore.setTypefaceCorsivo(p);
        p.setTextSize(dimCosto);
        c.drawText(costo,POSXBUTTON*getWidth(),POSYCOSTO*getHeight(),p);
        String mm=""+numero;
        float dim=p.measureText(mm,0,mm.length());
        c.drawText(""+numero,posxbutton-dim/2,POSYNUMERO*getHeight(),p);

        plus.disegnaBottone(c);
        minus.disegnaBottone(c);
        p.setTextSize(dimcostot);
        c.drawText(costot + costotot, POSXBUTTON * getWidth(), POSYCOSTOTOTALE * getHeight(), p);
        acquista.disegnaBottone(c);
        p.setTextSize(dimpuntit);
        c.drawText(puntit+puntitot,POSXBUTTON*getWidth(),POSYPUNTI*getHeight(),p);


    }

    private void acquista()
    {

        int costi = munizioni.getNumero() * munizioni.getCosto();
        if (Giocatore.inventario().getPunti(costi))
        {




            Giocatore.inventario().addMunizioni(munizioni);
            setPuntiDisponibili();
            contentente.lanciaRisultato(MessageView.OK_TYPE);
            AmbienteLivelli.suonaCassa();



        }
        else
        {
            contentente.lanciaRisultato(MessageView.ERROR_TYPE);
            AmbienteLivelli.suonaErrore();
        }



    }

    public PannelloVendita(FinestraVendita f,Munizioni m)
    {
        super(f.getActivity());
        contentente=f;
        Activity c=f.getActivity();
        munizioni=m;

        costo=c.getResources().getString(R.string.costo)+": "+munizioni.getCosto();
        costot=c.getResources().getString(R.string.costototale)+": ";
        puntit=c.getResources().getString(R.string.punti_disponibili)+": ";
        acquist=c.getResources().getString(R.string.acquista);
        numero=munizioni.getNumero();
        setCostoTotale();
        setPuntiDisponibili();
        assegnato=false;





    }



}
