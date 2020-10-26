package it.brun.dario.ballpopper.views;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import it.brun.dario.ballpopper.pseudoviews.Attivatore;
import it.brun.dario.ballpopper.ballpopper.balls.Bomba;
import it.brun.dario.ballpopper.interfaces.Eseguibile;
import it.brun.dario.ballpopper.FloatValore;
import it.brun.dario.ballpopper.ballpopper.Giocatore;
import it.brun.dario.ballpopper.ballpopper.Informazioni;
import it.brun.dario.ballpopper.levels.Livello;
import it.brun.dario.ballpopper.Personaggio;
import it.brun.dario.ballpopper.Punto;
import it.brun.dario.ballpopper.PuntoBlocco;
import it.brun.dario.ballpopper.R;
import it.brun.dario.ballpopper.Riproduttore;
import it.brun.dario.ballpopper.RiproduttoreZonale;
import it.brun.dario.ballpopper.activities.LivelliActivity;
import it.brun.dario.ballpopper.pseudoviews.IconButton;

/**
 * Created by dario on 21/04/17.
 */
public class AmbienteLivelli extends View
{


    public Informazioni getInformazioni()
    {
        return new Informazioni(personaggio.getPartialX(),personaggio.getPartialY(),personaggio.getDirection(), posX.getValore(),bottone.isSelected());
    }
    public Informazioni salva()
    {

        return new Informazioni(personaggio.getPartialX(),personaggio.getPartialY(),personaggio.getDirection(),posX.getValore(),bottone.isSelected());

    }
    private static int MIN_DISTANCE;
    public static void setMIN_DISTANCE(int k)
    {
        MIN_DISTANCE=k;
    }

    private static int MIN_LENGTH;
    private int numPointPerSchermata;

    private static final float PROPORZIONESUONO=0.78064f;
    public static final float COEFFICIENTEX=0.76687f;
    public static final float COEFFICIENTEH=0.12136f;
    public static final float MARGINH=0.04854f;

    //public static final float COEFFICIENTE=0.18204f;
    public static final float COEFFICIENTE=0.12136f;

    private static Riproduttore errorificatore;
    private static Riproduttore cassificatore;
    private static Riproduttore partificatore;
    private static Riproduttore blippatore;

    private static Riproduttore vittorificatore;

    private Attivatore attivaLivello;
    private Attivatore attivaNegozio;
    private Attivatore attivaInventario;
    private Attivatore attivaSalvataggio;
    private Attivatore attivaTrofei;
    private Attivatore attivaVideo;

    public Punto piuVicino(Punto p)
    {
        int indice=0;
        float distanza=(p.getX()-punti[0].getX())*(p.getX()-punti[0].getX())+(p.getY()-punti[0].getY())*(p.getY()-punti[0].getY());
        for(int i=1;i<punti.length;i++)
        {
            float distanzabis=(p.getX()-punti[i].getX())*(p.getX()-punti[i].getX())+(p.getY()-punti[i].getY())*(p.getY()-punti[i].getY());
            if(distanzabis<distanza)
            {
                distanza=distanzabis;
                indice=i;
            }
        }

        if(distanza<MIN_DISTANCE)
        {
            return punti[indice];
        }
        return null;
    }
    private RiproduttoreZonale riproduttoreZonale;
    private int numSchermate;
    private Personaggio personaggio;
    private Punto[] punti;
    private void assegnaPunti()
    {
        numPointPerSchermata=16;
        numSchermate=7;
        punti=new Punto[numPointPerSchermata*numSchermate-3];
        for(int i=0;i<numSchermate;i++)
        {

            punti[i*numPointPerSchermata+0] = new Punto((int)(Personaggio.getALTEZZA() / 2),(int)( Ambiente.getHEIGHT() * 0.21818f), posX, i, null);
            punti[i*numPointPerSchermata+1] = new Punto((int)(0.15337f * Ambiente.getWIDTH()), (int)(Ambiente.getHEIGHT() * 0.21818f), posX, i, null);
            punti[i*numPointPerSchermata+2] = new Punto((int)(0.21472f * Ambiente.getWIDTH()), (int)(0.37621f * Ambiente.getHEIGHT()), posX, i, null);
            punti[i*numPointPerSchermata+3] = new Punto((int)(0.21814f * Ambiente.getWIDTH()), (int)(0.49697 * Ambiente.getHEIGHT()), posX, i, null);
            punti[i*numPointPerSchermata+4] = new Punto((int)(0.241993f * Ambiente.getWIDTH()), (int)(0.80097f * Ambiente.getHEIGHT()), posX, i, Giocatore.getLivello(i*3+0));
            punti[i*numPointPerSchermata+5] = new Punto((int)(0.34424f * Ambiente.getWIDTH()), (int)(0.71515f * Ambiente.getHEIGHT()), posX, i, null);

            punti[i*numPointPerSchermata+6] = new Punto((int)(0.39536f * Ambiente.getWIDTH()), (int)(0.75757f * Ambiente.getHEIGHT()), posX, i, null);

            punti[i*numPointPerSchermata+7] = new Punto((int)(0.47035f * Ambiente.getWIDTH()),(int)( 0.78788f * Ambiente.getHEIGHT()), posX, i, null);
            punti[i*numPointPerSchermata+8] = new Punto((int)(0.4758f * Ambiente.getWIDTH()),(int)( 0.31553f * Ambiente.getHEIGHT()), posX, i, Giocatore.getLivello(i*3+1));
            punti[i*numPointPerSchermata+9] = new Punto((int)(0.57259f * Ambiente.getWIDTH()),(int)( 0.16383f * Ambiente.getHEIGHT()), posX, i, null);
            punti[i*numPointPerSchermata+10] = new Punto((int)(0.65439f * Ambiente.getWIDTH()),(int)( 0.15757f * Ambiente.getHEIGHT()), posX, i, null);

            punti[i*numPointPerSchermata+11] = new Punto((int)(0.75320f * Ambiente.getWIDTH()),(int)( 0.20024f * Ambiente.getHEIGHT()), posX, i, null);
            punti[i*numPointPerSchermata+12] = new Punto((int)(0.75324f * Ambiente.getWIDTH()),(int)( 0.69175f * Ambiente.getHEIGHT()), posX, i, Giocatore.getLivello(i*3+2));
            if(i<numSchermate-1)
            {
                punti[i * numPointPerSchermata + 13] = new Punto((int) (0.86571f * Ambiente.getWIDTH()), (int) (0.51515f * Ambiente.getHEIGHT()), posX, i, null);
                punti[i * numPointPerSchermata + 14] = new Punto((int) (0.92024f * Ambiente.getWIDTH()), (int) (0.21818f * Ambiente.getHEIGHT()), posX, i, null);
                punti[i * numPointPerSchermata + 15] = new PuntoBlocco((int) (1f * Ambiente.getWIDTH()), (int) (0.21818f * Ambiente.getHEIGHT()), posX, i, null);
            }
        }
        MIN_LENGTH=-(numSchermate-1)*Ambiente.getWIDTH();

    }

    public void sbloccaBlocchi()
    {
        for(int i=0;i<punti.length;i++)
        {

            if(punti[i].getLivello()!=null&&!punti[i].getLivello().getCompletato())
            {
                return;
            }
            if(punti[i]instanceof PuntoBlocco)
            {
                ((PuntoBlocco)punti[i]).sblocca();
            }

        }
    }
    private int getIndexOf(Punto p)
    {
        for(int i=0;i<punti.length;i++)
        {
            if(punti[i].equals(p))
            {
                return i;
            }
        }
        return -1;
    }

    private List<Punto> getLista(Punto origine,Punto destinazione)
    {
        List<Punto> lista=new ArrayList<Punto>();
        lista.add(origine);
        int i=getIndexOf(origine);
        int j=getIndexOf(destinazione);
        int kk=1;
        if(i>j)
        {
            kk=-1;
        }
        int hh=i;
        while(hh!=j)
        {
            hh+=kk;
            lista.add(punti[hh]);
        }
        return lista;
    }
    private static Bitmap[] sfondo;
    private static Bitmap immagineSalva;
    private static Bitmap immagineTrofei;
    private static Bitmap immagineInventario;
    private static Bitmap icona1;
    private static Bitmap icona2;


    public static void suonaBlippatore()
    {
        blippatore.play();
    }

    public static void assemblaSfondo(Activity act)
    {
        errorificatore=new Riproduttore(act,R.raw.errore);
        partificatore=new Riproduttore(act,R.raw.inizio);

        cassificatore=new Riproduttore(act,R.raw.cassa);

        vittorificatore=new Riproduttore(act,R.raw.vittoria);
        blippatore= new Riproduttore(act,R.raw.premi);

        sfondo=new Bitmap[7];

        sfondo[0]=Giocatore.getResizedBitmap(BitmapFactory.decodeResource(act.getResources(), R.drawable.livelli1), Ambiente.getHEIGHT(), Ambiente.getWIDTH());


        sfondo[1]=Giocatore.getResizedBitmap(BitmapFactory.decodeResource(act.getResources(), R.drawable.livelli2), Ambiente.getHEIGHT(), Ambiente.getWIDTH());


        sfondo[2]=Giocatore.getResizedBitmap(BitmapFactory.decodeResource(act.getResources(), R.drawable.livelli3), Ambiente.getHEIGHT(), Ambiente.getWIDTH());


        sfondo[3]=Giocatore.getResizedBitmap(BitmapFactory.decodeResource(act.getResources(), R.drawable.livelli4), Ambiente.getHEIGHT(), Ambiente.getWIDTH());

        sfondo[4]=Giocatore.getResizedBitmap(BitmapFactory.decodeResource(act.getResources(), R.drawable.livelli5), Ambiente.getHEIGHT(), Ambiente.getWIDTH());

        sfondo[5]=Giocatore.getResizedBitmap(BitmapFactory.decodeResource(act.getResources(), R.drawable.livelli6), Ambiente.getHEIGHT(), Ambiente.getWIDTH());

        sfondo[6]=Giocatore.getResizedBitmap(BitmapFactory.decodeResource(act.getResources(), R.drawable.livelli7), Ambiente.getHEIGHT(), Ambiente.getWIDTH());

        immagineNegozio=Giocatore.getResizedBitmap(BitmapFactory.decodeResource(act.getResources(), R.drawable.iconanegozio),(int)(Ambiente.getHEIGHT()*COEFFICIENTEH),(int)(Ambiente.getHEIGHT()*COEFFICIENTEH));
        immagineSalva=Giocatore.getResizedBitmap(BitmapFactory.decodeResource(act.getResources(), R.drawable.iconasalva),(int)(Ambiente.getHEIGHT()*COEFFICIENTEH),(int)(Ambiente.getHEIGHT()*COEFFICIENTEH));
        immagineTrofei=Giocatore.getResizedBitmap(BitmapFactory.decodeResource(act.getResources(), R.drawable.iconatrofei),(int)(Ambiente.getHEIGHT()*COEFFICIENTEH),(int)(Ambiente.getHEIGHT()*COEFFICIENTEH));
        immagineInventario=Giocatore.getResizedBitmap(BitmapFactory.decodeResource(act.getResources(), R.drawable.iconainventario),(int)(Ambiente.getHEIGHT()*COEFFICIENTEH),(int)(Ambiente.getHEIGHT()*COEFFICIENTEH));

        immagineGioca=Giocatore.getResizedBitmap(BitmapFactory.decodeResource(act.getResources(), R.drawable.iconagioca),(int)(Ambiente.getHEIGHT()*COEFFICIENTEH),(int)(Ambiente.getHEIGHT()*COEFFICIENTEH*2));
        immaginePremio=Giocatore.getResizedBitmap(BitmapFactory.decodeResource(act.getResources(), R.drawable.iconapremio),(int)(Ambiente.getHEIGHT()*COEFFICIENTEH),(int)(Ambiente.getHEIGHT()*COEFFICIENTEH));
        icona1=Giocatore.getResizedBitmap(BitmapFactory.decodeResource(act.getResources(), R.drawable.audio1),(int)(Bomba.getDIAMETROBASE()*1.5f*PROPORZIONESUONO),(int)(Bomba.getDIAMETROBASE()*1.5f));
        icona2=Giocatore.getResizedBitmap(BitmapFactory.decodeResource(act.getResources(), R.drawable.audio2),(int)(Bomba.getDIAMETROBASE()*1.5f*PROPORZIONESUONO),(int)(Bomba.getDIAMETROBASE()*1.5f));


    }
    private static Bitmap immagineNegozio;
    private static Bitmap immagineGioca;
    private static Bitmap immaginePremio;
    private IconButton bottone;

    public void attivaLivelloCorrente()
    {

        Punto p=personaggio.getContenitore();
        Livello l=p.getLivello();
        if(l!=null)
        {
            suonaInizio();
            contenente.dispiega(l);
        }
    }
    public void attivaNegozio()
    {
        contenente.apriNegozio();
    }
    public void attivaSalvataggio()
    {
        contenente.controllaPermessi();
    }
    public void attivaTrofei()
    {
        contenente.apriTrofei();
    }
    public void attivaInventario()
    {
        contenente.apriInventario();
    }


    private LivelliActivity contenente;
    public static void suonaErrore()
    {
        errorificatore.play();
    }
    public static void suonaInizio()
    {
        partificatore.play();
    }
    public static void suonaCassa()
    {
        cassificatore.play();
    }
    public static void suonaVittoria()
    {
        vittorificatore.play();
    }

    public void rilascia()
    {
        riproduttoreZonale.rilascia();
    }
    public void pausa()
    {
        riproduttoreZonale.pausa();
    }
    public void riparti()
    {
        if(bottone.isSelected()&&contenente.isVisibile())
        {
            riproduttoreZonale.riparti();
        }
    }
    private FloatValore posX;

    public void attivaVideo()
    {
        contenente.mostraFinestraVideo();

    }
    public void settaVideo(boolean b)
    {
        attivaVideo.setVisible(b);
        postInvalidate();
    }

    public AmbienteLivelli(Context c,Informazioni saved)
    {
        super(c);
        riproduttoreZonale=new RiproduttoreZonale(getContext());

        contenente=(LivelliActivity)c;
        posX=new FloatValore(0);
        assegnaPunti();
        Punto pp=punti[0];
        int dir=1;
        bottone=new IconButton(Ambiente.getWIDTH()-icona1.getWidth(),0,icona1,icona2,new Eseguibile() {
            @Override
            public void esegui()
            {

                riparti();
            }
        },new Eseguibile() {
            @Override
            public void esegui() {
                pausa();
            }
        });
        if(saved!=null)
        {
            bottone.setSelected(saved.getSuonabile());
            posX.setValore(saved.getPos());
            dir=saved.getDirezione();
            pp=new Punto(saved.getX(),saved.getY(),posX,0);
            pp=piuVicino(pp);


        }



        personaggio=new Personaggio(this,pp,dir);

        attivaVideo=new Attivatore(Ambiente.getHEIGHT()*MARGINH,Ambiente.getHEIGHT()-Ambiente.getHEIGHT()*COEFFICIENTEH-Ambiente.getHEIGHT()*MARGINH,Ambiente.getHEIGHT()*COEFFICIENTEH,Ambiente.getHEIGHT()*COEFFICIENTEH,new Eseguibile() {
            @Override
            public void esegui()
            {
                suonaBlippatore();
                attivaVideo();
            }
        },immaginePremio);
        attivaNegozio=new Attivatore(Ambiente.getWIDTH()-Ambiente.getHEIGHT()*COEFFICIENTEH-Ambiente.getHEIGHT()*MARGINH,Ambiente.getHEIGHT()-Ambiente.getHEIGHT()*COEFFICIENTEH-Ambiente.getHEIGHT()*MARGINH,Ambiente.getHEIGHT()*COEFFICIENTEH,Ambiente.getHEIGHT()*COEFFICIENTEH,new Eseguibile() {
            @Override
            public void esegui()
            {
                suonaBlippatore();
                attivaNegozio();
            }
        },immagineNegozio);
        attivaSalvataggio=new Attivatore(Ambiente.getWIDTH()-2*Ambiente.getHEIGHT()*COEFFICIENTEH-2*Ambiente.getHEIGHT()*MARGINH,Ambiente.getHEIGHT()-Ambiente.getHEIGHT()*COEFFICIENTEH-Ambiente.getHEIGHT()*MARGINH,Ambiente.getHEIGHT()*COEFFICIENTEH,Ambiente.getHEIGHT()*COEFFICIENTEH,new Eseguibile() {
            @Override
            public void esegui()
            {
                suonaBlippatore();
                attivaSalvataggio();
            }
        },immagineSalva);

        attivaInventario=new Attivatore(Ambiente.getWIDTH()-3*Ambiente.getHEIGHT()*COEFFICIENTEH-3*Ambiente.getHEIGHT()*MARGINH,Ambiente.getHEIGHT()-Ambiente.getHEIGHT()*COEFFICIENTEH-Ambiente.getHEIGHT()*MARGINH,Ambiente.getHEIGHT()*COEFFICIENTEH,Ambiente.getHEIGHT()*COEFFICIENTEH,new Eseguibile() {
            @Override
            public void esegui()
            {
                suonaBlippatore();
                attivaInventario();
            }
        },immagineInventario);
        attivaTrofei=new Attivatore(Ambiente.getWIDTH()-4*Ambiente.getHEIGHT()*COEFFICIENTEH-4*Ambiente.getHEIGHT()*MARGINH,Ambiente.getHEIGHT()-Ambiente.getHEIGHT()*COEFFICIENTEH-Ambiente.getHEIGHT()*MARGINH,Ambiente.getHEIGHT()*COEFFICIENTEH,Ambiente.getHEIGHT()*COEFFICIENTEH,new Eseguibile() {
            @Override
            public void esegui()
            {
                suonaBlippatore();
                attivaTrofei();
            }
        },immagineTrofei);


        attivaLivello=new Attivatore(Ambiente.getWIDTH()-6*Ambiente.getHEIGHT()*COEFFICIENTEH-6*Ambiente.getHEIGHT()*MARGINH,Ambiente.getHEIGHT()-Ambiente.getHEIGHT()*COEFFICIENTEH-Ambiente.getHEIGHT()*MARGINH,Ambiente.getHEIGHT()*2*COEFFICIENTEH,Ambiente.getHEIGHT()*COEFFICIENTEH,new Eseguibile() {
            @Override
            public void esegui()
            {
                attivaLivelloCorrente();
            }
        },immagineGioca,personaggio);
        /*attivaLivello=new Attivatore(Ambiente.getWIDTH()-5*Ambiente.getHEIGHT()*COEFFICIENTEH-5*Ambiente.getHEIGHT()*MARGINH,Ambiente.getHEIGHT()-Ambiente.getHEIGHT()*COEFFICIENTEH-Ambiente.getHEIGHT()*MARGINH,Ambiente.getHEIGHT()*COEFFICIENTEH,new Eseguibile() {
            @Override
            public void esegui()
            {
                attivaLivelloCorrente();
            }
        },contenente.getResources().getString(R.string.play),personaggio);*/
        attivaNegozio.setVisible(true);
        attivaSalvataggio.setVisible(true);
        attivaTrofei.setVisible(true);
        attivaInventario.setVisible(true);


        pp.setContenuto(personaggio);


        setOnTouchListener(listener);
    }
    public void caricaSuono(int i)
    {
       if( riproduttoreZonale.caricaSuono(getContext(),i))
       {
           riparti();
       }
    }
    public void setVisibilityLivello(boolean b)
    {
        attivaLivello.setVisible(b);
    }
    public void setPosX(float k)
    {
        posX.setValore(posX.getValore()+k);
        int min_length=0;
        for(int i=numPointPerSchermata-1;i<punti.length;i+=numPointPerSchermata)
        {

            if(!((PuntoBlocco)punti[i]).getSbloccato())
            {
               break;
            }
            min_length-=Ambiente.getWIDTH();
        }

        if(min_length<MIN_LENGTH)
        {
            min_length=MIN_LENGTH;
        }
        if(posX.getValore()<min_length)
        {
            posX.setValore(min_length);
        }
        if(posX.getValore()>0)
        {
            posX.setValore(0);
        }

        invalidate();
    }

    public OnTouchListener listener=new OnTouchListener()
    {
        private float x1;
        private float y1;
        private long startTime;
        private static final int MAX_DURATION = 150;
        @Override
        public boolean onTouch(View v, MotionEvent event)
        {



            if(event.getAction()==MotionEvent.ACTION_MOVE)
            {


                setPosX((int)((event.getX()-x1)));
                x1=event.getX();
                y1=event.getY();


            }
            else if(event.getAction()==MotionEvent.ACTION_DOWN)
            {
                x1 = event.getX();
                y1 = event.getY();
                if(bottone.onTouch(event))
                {
                    postInvalidate();
                    return true;
                }
                if (attivaLivello.interno(event.getX(), event.getY()))
                {
                    return false;
                }
                if (attivaSalvataggio.interno(event.getX(), event.getY()))
                {
                    return false;
                }
                if (attivaNegozio.interno(event.getX(), event.getY()))
                {
                    return false;
                }
                if (attivaTrofei.interno(event.getX(), event.getY()))
                {
                    return false;
                }
                if (attivaInventario.interno(event.getX(), event.getY()))
                {
                    return false;
                }
                if (attivaVideo.interno(event.getX(), event.getY()))
                {
                    return false;
                }
                if(System.currentTimeMillis() - startTime <= MAX_DURATION)
                {
                    Punto p=piuVicino(new Punto((int) event.getX(), (int) event.getY()));
                    if(p!=null)
                    {


                        if(personaggio.setDestinazione(getLista(personaggio.getPosizione(),p ))&&bottone.isSelected() )
                        {
                            Ambiente.suonaColpitore();
                        }
                        else if(bottone.isSelected())
                        {
                            suonaErrore();
                        }

                    }


                }


            }
            else if(event.getAction()==MotionEvent.ACTION_UP)
            {
                startTime = System.currentTimeMillis();
            }


            return true;
        }
    };
    public void onDraw(Canvas c)
    {


        sbloccaBlocchi();
        for(int i=0;i<sfondo.length;i++)
        {
            c.drawBitmap(sfondo[i], posX.getValore()+Ambiente.getWIDTH()*i, 0, null);

        }
        //c.drawBitmap(sfondo, posX.getValore(), 0, null);

        Paint pp=new Paint();
        pp.setColor(Color.RED);

        for(int i=0;i<punti.length;i++)
        {
            Drawable d=punti[i].getImmagine();
            if(d!=null)
            {

                d.draw(c);
            }
        }
        personaggio.disegnaPersonaggio(c);


        attivaLivello.disegnaAttivatore(c);
        attivaNegozio.disegnaAttivatore(c);
        attivaSalvataggio.disegnaAttivatore(c);
        attivaTrofei.disegnaAttivatore(c);
        attivaInventario.disegnaAttivatore(c);
        attivaVideo.disegnaAttivatore(c);
        bottone.disegnaBottone(c);






    }


}
