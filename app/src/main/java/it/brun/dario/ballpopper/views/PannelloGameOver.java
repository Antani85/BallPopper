package it.brun.dario.ballpopper.views;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

import it.brun.dario.ballpopper.interfaces.Eseguibile;
import it.brun.dario.ballpopper.R;
import it.brun.dario.ballpopper.pseudoviews.SquareButton;
import it.brun.dario.ballpopper.activities.ActivityIniziaPartita;
import it.brun.dario.ballpopper.ballpopper.Giocatore;

/**
 * Created by dario on 04/05/17.
 */
public class PannelloGameOver extends View
{
    private ActivityIniziaPartita attivita;
    private boolean gameover;
    private static Bitmap sfondo1;
    private static Bitmap sfondo2;
    public static void assegnaSfondo(Activity act)
    {
        sfondo1=Giocatore.getResizedBitmap(BitmapFactory.decodeResource(act.getResources(), R.drawable.caricamento), Ambiente.getHEIGHT(), Ambiente.getWIDTH());

        sfondo2=Giocatore.getResizedBitmap(BitmapFactory.decodeResource(act.getResources(), R.drawable.gameover), Ambiente.getHEIGHT(), Ambiente.getWIDTH());


    }



    private static final float POSX=0.26585f;
    private static final float POSY=0.49697f;
    private static final float WIDTH=0.4533f;
    private static final float HEIGHT=0.19394f;
    private float x;
    private float y;
    private float width;
    private float height;
    private Paint p;
    private String message;
    private SquareButton nuova;
    private SquareButton carica;
    private String nuovatesto;
    private String caricatesto;

    private Bitmap losfondo;
    private void assegnaLoSfondo()
    {
        if(gameover)
        {
            losfondo=sfondo2;


        }
        else
        {
            losfondo = sfondo1;

        }
    }


    public PannelloGameOver(Context c)
    {
        super(c);
        attivita=(ActivityIniziaPartita)c;
        gameover=attivita.getGameOver();
        p=new Paint();
        Giocatore.setTypeface(p);
        x=POSX*Ambiente.getWIDTH();
        y=POSY*Ambiente.getHEIGHT();
        message=c.getResources().getString(R.string.gameover);
        width=WIDTH * Ambiente.getWIDTH();
        height=HEIGHT * Ambiente.getHEIGHT();
        MessageView.setDimensione(message, p,width ,height );
        assegnaLoSfondo();

        nuovatesto=getResources().getString(R.string.nuova);
        caricatesto=getResources().getString(R.string.carica);

        nuova=new SquareButton(Ambiente.getWIDTH() / 2 - 0.15f * Ambiente.getWIDTH(), y + height / 2, Ambiente.getWIDTH() * 0.3f, Ambiente.getHEIGHT() / 8, new Eseguibile() {
            @Override
            public void esegui() {
                attivita.continua();
            }
        }, nuovatesto, new Eseguibile() {
            @Override
            public void esegui() {
                AmbienteLivelli.suonaBlippatore();
            }
        });
        carica=new SquareButton(Ambiente.getWIDTH() / 2 - 0.15f * Ambiente.getWIDTH(), y + height / 2 + Ambiente.getHEIGHT() / 5, Ambiente.getWIDTH() * 0.3f, Ambiente.getHEIGHT() / 8, new Eseguibile() {
            @Override
            public void esegui() {
                attivita.controllaPermessi();
            }
        }, caricatesto, new Eseguibile() {
            @Override
            public void esegui() {
                AmbienteLivelli.suonaBlippatore();
            }
        });
        setOnTouchListener(listener);
    }
    private View.OnTouchListener listener=new View.OnTouchListener()
    {
        @Override
        public boolean onTouch(View v, MotionEvent event)
        {

            nuova.onTouch(event);
            carica.onTouch(event);
            postInvalidate();
            return true;
        }


    };

    @Override
    protected void onDraw(Canvas c)
    {

        Paint p=new Paint();
        Giocatore.setTypeface(p);
        if(gameover)
        {
            c.drawBitmap(sfondo2,0,0,p);
            MessageView.setDimensione(message, p,width ,height );
            c.drawText(message, x, y, p);
        }
        else
        {
            c.drawBitmap(sfondo1,0,0,p);

        }

        carica.disegnaBottone(c);
        nuova.disegnaBottone(c);



    }




}
