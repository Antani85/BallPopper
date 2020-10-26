package it.brun.dario.ballpopper.views;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import it.brun.dario.ballpopper.ballpopper.balls.Bomba;
import it.brun.dario.ballpopper.ballpopper.balls.BombaBonusUp;
import it.brun.dario.ballpopper.ballpopper.bonus.Bonus;
import it.brun.dario.ballpopper.ballpopper.Cannone;
import it.brun.dario.ballpopper.ballpopper.Combo;
import it.brun.dario.ballpopper.interfaces.Contenitore;
import it.brun.dario.ballpopper.ballpopper.DisegnaPunti;
import it.brun.dario.ballpopper.ballpopper.Giocatore;
import it.brun.dario.ballpopper.ballpopper.InventarioMunizioni;
import it.brun.dario.ballpopper.pseudoviews.InventarioRotellante;
import it.brun.dario.ballpopper.levels.Livello;
import it.brun.dario.ballpopper.ballpopper.bullets.Palla;
import it.brun.dario.ballpopper.pseudoviews.PannelloPotenziamenti;
import it.brun.dario.ballpopper.R;
import it.brun.dario.ballpopper.Riproduttore;
import it.brun.dario.ballpopper.Risultato;
import it.brun.dario.ballpopper.ballpopper.SbloccaTrofeo;
import it.brun.dario.ballpopper.ballpopper.Scoppiante;
import it.brun.dario.ballpopper.ballpopper.Timer;
import it.brun.dario.ballpopper.ballpopper.trophies.Trofeo;
import it.brun.dario.ballpopper.activities.ActivityFineLivello;
import it.brun.dario.ballpopper.activities.MainActivity;

/**
 * Created by dario on 10/04/17.
 */
public class Ambiente extends View implements Contenitore, Runnable
{

    private boolean fermati;
    public void run()
    {
        while(!fermati)
        {
            try
            {
                System.gc();
                Thread.sleep(1000);
            }
            catch (Exception e)
            {
                return;
            }
        }
    }
    private static Bitmap sfondo;
    public static void assegnaSfondo(Activity act)
    {
        sfondo=Giocatore.getResizedBitmap(BitmapFactory.decodeResource(act.getResources(), R.drawable.sfondogioco), Ambiente.getHEIGHT(), Ambiente.getWIDTH());
        //sfondo=Giocatore.getResizedBitmap(Giocatore.decodeResource(act.getResources(), R.drawable.sfondogioco), Ambiente.getHEIGHT(), Ambiente.getWIDTH());

    }
    private Timer timer;

    public void caricaSalvataggio(Bundle b)
    {

        Bomba[] bombe=(Bomba[])b.getParcelableArray(MainActivity.BOMBE);
        if(bombe!=null)
        {
            for(int i=0;i<bombe.length;i++)
            {
                bombe[i].setAmbiente(this);
                bomb.add(bombe[i]);
            }
        }
        risultato=b.getParcelable(ActivityFineLivello.RISULTATO);
        timer.setTempoRimanente(b.getLong(MainActivity.TEMPOTRASCORSO));

    }


    public void salva(Bundle retval)
    {

        Bomba [] bombe=bomb.toArray(new Bomba[bomb.size()]);
        retval.putParcelableArray(MainActivity.BOMBE,bombe);
        retval.putBoolean(MainActivity.LIVELLOCOMPIUTO,terminato);
        retval.putParcelable(ActivityFineLivello.RISULTATO,risultato);
        retval.putLong(MainActivity.TEMPOTRASCORSO,timer.getTempoRimanente());




    }
    private Gestore gestore;
    private GestoreScoppiate gestoreScoppiate;
    private Risultato risultato;
    private InventarioRotellante inventariorotellante;

    public void setTerminato()
    {
        terminato=true;
        if(bomb.size()==0&&trofei.size()==0&&!timer.isTerminato())
        {
            synchronized (scoppiate)
            {
                if(!ended)
                {
                    ended=true;
                    distruggi();
                    contenente.termina(risultato);
                }

            }
        }
    }
    private boolean terminato;
    private static Riproduttore suonatore;
    private static Riproduttore combatore;
    private static Riproduttore colpitore;
    private static Riproduttore sparatore;
    private static Riproduttore scaricatore;
    private static Riproduttore esploditore;
    private static Riproduttore esploditoreuno;
    private static Riproduttore apicultore;
    private static Riproduttore granchiatore;
    private static Riproduttore fuocatore;
    private static Riproduttore dingo;
    private static Riproduttore elettrificatore;
    private static Riproduttore fantasmatore;
    public static void assegnaRiproduttori(Activity act)
    {
        suonatore=new Riproduttore(act,R.raw.sound);
        sparatore=new Riproduttore(act,R.raw.cannone);
        scaricatore=new Riproduttore(act,R.raw.scarico);
        esploditore=new Riproduttore(act,R.raw.esplosione);
        esploditoreuno=new Riproduttore(act,R.raw.esplosioneuno);
        colpitore=new Riproduttore(act,R.raw.colpita);
        combatore=new Riproduttore(act,R.raw.combo);
        dingo=new Riproduttore(act,R.raw.ding);
        apicultore=new Riproduttore(act,R.raw.ape);
        granchiatore=new Riproduttore(act,R.raw.granchio);
        fuocatore=new Riproduttore(act,R.raw.fuoco);
        elettrificatore=new Riproduttore(act,R.raw.elettrico);
        fantasmatore=new Riproduttore(act,R.raw.fantasma);


    }



    public static void suonaGranchiatore()
    {
        granchiatore.play();
    }
    public static void suonaFuocatore()
    {
        fuocatore.play();
    }
    public static void suonaElettrificatore()
    {
        elettrificatore.play();
    }
    public static void suonaFantasmatore()
    {
        fantasmatore.play();
    }

    public Bitmap getImmagine(int res,int dim)
    {
        return Giocatore.getResizedBitmap(BitmapFactory.decodeResource(contenente.getResources(), res), dim, dim);
    }

    public static int getWIDTH()
    {
        return WIDTH;
    }
    public static int getHEIGHT()
    {
        return HEIGHT;
    }
    private Paint p;
    public static final int LANCIOBOMBE=5000;
    private Thread lancio;
    private List<Palla> palla;
    private List<Bomba> bomb;
    private List<DisegnaPunti> scoppiate;
    private List<Combo> combo;
    private List<SbloccaTrofeo> trofei;
    private PannelloPotenziamenti pannellopotenziamenti;
    private InventarioMunizioni inventario;

    private MainActivity contenente;

    private static  int WIDTH;
    private static int HEIGHT;

    public static void setWIDTH(int w)
    {
        WIDTH=w;
    }
    public static void setHEIGHT(int h)
    {
        HEIGHT=h;
    }
    private Cannone cannone;
    public void destroy(Palla p)
    {
        inventario.usaBonus();
        p.incrementaInventario();
        if(p.getNumScoppiate()>1)
        {
            //Combinazione comb=new Combinazione(this, getWIDTH() / 2, getHEIGHT() / 2, p.getNumScoppiate(), Color.argb(255,255,0,0));
            Combo comb=Combo.getCombo(this,p,HEIGHT/4);
            combo.add(comb);
            comb.start();
            combatore.play();
        }
        List<Trofeo> t=Giocatore.controllaTrofei(p,null);
        if(t.size()>0)
        {
            for(int i=0;i<t.size();i++)
            {
                SbloccaTrofeo panne = new SbloccaTrofeo(this, WIDTH - Bomba.getDIAMETROBASE() * 6, 0, t.get(i));
                trofei.add(panne);
                panne.start();
                dingo.play();
            }
        }
        synchronized(palla)
        {
            palla.remove(p);

            postInvalidate();
        }
        if(Giocatore.inventario().getNumeroMunizioni()==0&&palla.size()==0&&(bomb.size()>0||!terminato))
        {
            termina();
        }


    }


    public static void suonaDingo()
    {
        dingo.play();
    }

    public Bomba getBomb(int i)
    {
        return bomb.get(i);
    }




    public void suonaColpitore(int i)
    {
        bomb.get(i).suonaColpitore();
    }
    public static  void suonaColpitore()
    {
        colpitore.play();
    }
    public static void suonaApicultore()
    {
        apicultore.play();
    }
    public static void suonaEsploditore()
    {
        esploditore.play();
    }
    public static void suonaEsploditoreUno()
    {
        esploditoreuno.play();
    }

    public void lanciaBombaCasuale(Bomba b)
    {
        int cc=Color.argb(255,(int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255));
        float ix=(float)(Math.random()*Bomba.getINCREMENTATORE_MAX())+Bomba.getINCREMENTATORE_MIN();
        float iy=(float)(Math.random()*Bomba.getINCREMENTATORE_MAX())+Bomba.getINCREMENTATORE_MIN();

        Bomba bom=b.getBomba(this,ix,iy,cc,WIDTH/2+1,1);
        bomb.add(bom);

    }

    public void lanciaBomba(Bomba b)
    {
        bomb.add(b);
    }

    public int getPunti(Bomba b)
    {
        return getMoltiplicatore(b.getPunti(),b.getMoltiplicatore());
    }
    public void disinnescaBomba(Bomba b)
    {
        synchronized(bomb)
        {

            bomb.remove(b);

        }

        int punti=getMoltiplicatore(b.getPunti(),b.getMoltiplicatore());
        DisegnaPunti ds;
        b.incrementaInventario();
        risultato.addPalle(1);


        Bonus bon=b.getBonus();
        if(b instanceof BombaBonusUp)
        {
            Giocatore.inventario().incrementaPotenziamenti();
            ds=new DisegnaPunti(this,b.getX(),b.getY(),BombaBonusUp.UP,b.getColore());

        }
        else if(bon!=null)
        {
            risultato.addBonus(bon);
            contenente.addPotenziamento(bon);
            ds=new DisegnaPunti(this,b.getX(),b.getY(),bon.getNome(),b.getColore());

        }
        else
        {
            contenente.addPunti(punti);
            risultato.addPunti(punti);
            ds=new DisegnaPunti(this,b.getX(),b.getY(),punti,b.getColore());
        }
        scoppiate.add(ds);




    }
    public void distruggi()
    {
        fermati=true;
        risultato.setTime(timer.getTempoRimanente());
        gestore.stoppa();
        gestoreScoppiate.stoppa();
        timer.stoppa();
        for(int i=0;i<palla.size();i++)
        {
            palla.get(i).stoppa();
        }



    }

    public void invalida()
    {
        postInvalidate();
    }


    public void rimuovi(SbloccaTrofeo b)
    {
        synchronized(trofei)
        {
            trofei.remove(b);

        }
        if(terminato&&bomb.size()==0&&trofei.size()==0&&!timer.isTerminato())
        {

            synchronized (scoppiate)
            {
                if(!ended)
                {
                    ended=true;
                    distruggi();
                    contenente.termina(risultato);
                }
            }
        }

    }

    public void termina()
    {
        if(!ended)
        {
            distruggi();
            contenente.termina();
        }
    }

    private boolean ended;

    public void rimuovi(DisegnaPunti b)
    {
        synchronized(scoppiate)
        {
            scoppiate.remove(b);

        }
        if(terminato&&bomb.size()==0&&trofei.size()==0&&!timer.isTerminato())
        {
            synchronized (scoppiate)
            {
                if(!ended)
                {
                    ended=true;
                    distruggi();
                    contenente.termina(risultato);
                }
            }
        }
    }
    public void rimuovi(Combo b)
    {
        synchronized(combo)
        {
            combo.remove(b);

        }

    }




    public int colpisciBomba(int i, int dan)
    {
        return bomb.get(i).setHp(-1 * dan);

    }


    public void esplodiBomba(int i)
    {

        suonatore.play();
        bomb.get(i).setRaggio(1);


    }

    private OnTouchListener listener1=new OnTouchListener()
    {

        private long startTime;
        private long sparato;

        private static final int MAX_DURATION = 150;
        private static final int MIN_DURATION = 500;

        private float x1;
        private float y1;

        public boolean onTouch(View v, MotionEvent event)
        {




            if (event.getAction() == MotionEvent.ACTION_DOWN)
            {

                x1=event.getX();
                y1=event.getY();
                if(pannellopotenziamenti.setPosizioni(event.getX(),event.getY()))
                {
                    pannellopotenziamenti.setSelectedBonus();
                }





                if(System.currentTimeMillis() - startTime <= MAX_DURATION&&System.currentTimeMillis() - sparato >= MIN_DURATION)
                {


                    //cannone.posiziona((int)event.getX(),(int) event.getY());

                    int contatore=0;
                    for(int i=0;i<palla.size();i++)
                    {


                        if(palla.get(i)instanceof Scoppiante)
                        {
                            Scoppiante bb=(Scoppiante)(palla.get(i));
                            bb.detona();
                            contatore++;
                        }
                    }
                    if(contatore==0)
                    {
                        if(cannone.spara())

                        {
                            sparatore.play();
                        }
                        else
                        {
                            scaricatore.play();
                        }
                    }
                    sparato=System.currentTimeMillis();
                }



            }
            else   if (event.getAction() == MotionEvent.ACTION_MOVE)
           {
               pannellopotenziamenti.setPosizioni(event.getX(),event.getY());
               if(inventariorotellante.interno(event.getX(),event.getY()))
               {


                   inventariorotellante.assegnaPosizione((x1-event.getX()));
                   x1=event.getX();
                   y1=event.getY();
               }

              /* else if(pannellopotenziamenti.setPosizioni((int)event.getX(),(int)event.getY()))
               {

                   pannellopotenziamenti.setBonusCorrente();
               }
               */
               else if((x1-event.getX())*(x1-event.getX())>=Bomba.getDIAMETROBASE()* Bomba.getDIAMETROBASE()||(y1-event.getY())*(y1-event.getY())>=Bomba.getDIAMETROBASE()*Bomba.getDIAMETROBASE())
               {
                   cannone.posiziona( event.getX(),  event.getY());
               }



           }

            else if (event.getAction() == MotionEvent.ACTION_UP)
            {

                pannellopotenziamenti.setBonusCorrente();
                pannellopotenziamenti.azzeraSelectedBonus();

                startTime = System.currentTimeMillis();
                inventariorotellante.setSelectedIndex();
                //pannellopotenziamenti.setSelectedBonus();


            }


            invalidate();
            return true;
        }
    };





    public static float[] getPuntoUscita(int i)
    {
        switch (i)
        {
            case 0:
                return new float[]{Ambiente.getWIDTH()/3,1,1,1};
            case 1:
                return new float[]{Ambiente.getWIDTH()/2,1,1,1};
            case 2:
                return new float[]{Ambiente.getWIDTH()*2/3,1,-1,1};
            case 3:
                return new float[]{Ambiente.getWIDTH(),Ambiente.getHEIGHT()/3,-1,1};
            case 4:
                return new float[]{Ambiente.getWIDTH(),Ambiente.getHEIGHT()*2/3,-1,-1};
            case 5:
                return new float[]{Ambiente.getWIDTH()/3,Ambiente.getHEIGHT(),1,-1};
            case 6:
                return new float[]{Ambiente.getWIDTH()/2,Ambiente.getHEIGHT(),1,-1};
            case 7:
                return new float[]{Ambiente.getWIDTH()*2/3,Ambiente.getHEIGHT(),-1,-1};
            default:
                return new float[]{Ambiente.getWIDTH()/3,1,1,1};
        }

    }

    public boolean creaPalla(float x,float y,double ang,int liv)
    {
        //Palla p=contenitoreinventario.usaMunizioni(this, x, y, ang, liv);
        Palla p=inventariorotellante.usaMunizioni(this, x, y, ang, liv);


        if(p!=null)
        {
            p.centraSuCannone();
            palla.add(p);
            risultato.addProiettili(1);
            p.start();
            return true;

        }
        return false;

    }

    public int getMoltiplicatore(int punt,int num)
    {
        if(inventario.getBonusCorrente()!=null)
        {
            return inventario.getBonusCorrente().getEffetto(punt,num);
        }
        return punt;
    }



    public Ambiente(Context f, InventarioMunizioni inv, Livello l, boolean ter)
    {
        super(f);
        contenente =(MainActivity) f;
        terminato=ter;

        ended=false;
        /*
        bitmap = Bitmap.createBitmap(WIDTH, HEIGHT, Bitmap.Config.ARGB_8888);
        c = new Canvas(bitmap);
        */
        bounds=new RectF(0,0,WIDTH,HEIGHT);
        risultato=new Risultato(l.getNumero());
        //timer=new Timer(this,l.getTempo(),Bomba.getDIAMETROBASE());
        timer=new Timer(this,l.getTempo());

        inventario=inv;
        inventariorotellante=new InventarioRotellante(inventario,Bomba.getDIAMETROBASE()/2,Bomba.getDIAMETROBASE()/2,HEIGHT/6);
        pannellopotenziamenti=new PannelloPotenziamenti(inventario,WIDTH,HEIGHT);
        p=new Paint();

        palla=new ArrayList<Palla>();
        bomb=new ArrayList<Bomba>();
        scoppiate=new ArrayList<DisegnaPunti>();
        combo=new ArrayList<Combo>();
        trofei=new ArrayList<SbloccaTrofeo>();
        cannone= new Cannone(this,HEIGHT/2);
        setOnTouchListener(listener1);
        l.creaLanciate(this);
        timer.start();

        gestore=new Gestore();
        gestore.start();
        gestoreScoppiate=new GestoreScoppiate();
        gestoreScoppiate.start();
        new Thread(this).start();
        if(!terminato)
        {
            lancio = l.getLancio();
            lancio.start();
        }

    }

    public boolean esplosa(int i)
    {
        return bomb.get(i).esplosa();
    }


    public boolean interno(int i,float xx,float yy,float r)
    {
        return bomb.get(i).interno(xx,yy,r);
    }

    public int numBombe()
    {

        return bomb.size();
    }
    public int totBombe()
    {
        int aux=0;
        for(int i=0;i<bomb.size();i++)
        {
            if(!bomb.get(i).esplosa())
            {
                aux++;
            }
        }
        return aux;
    }
    public int numPalle()
    {
        return palla.size();
    }
    /*
    private Bitmap bitmap;
    private Canvas c;
    */
    private RectF bounds;
    public void onDraw(Canvas c)
    {

            /*p.setColor(Color.WHITE);
            p.setShader(null);
            c.drawRect(bounds,p);
            */

            c.drawBitmap(sfondo,0,0,p);
            inventariorotellante.disegnaInventario(c,p);

            pannellopotenziamenti.disegnaPannelloPotenziamenti(c,p);

            cannone.disegnaCannone(c,p);

            for(int i=0;i<palla.size();i++)
            {

                palla.get(i).disegnaPalla(c,p);
            }
            p.setShader(null);
            for(int i=0;i<bomb.size();i++)
            {

                try
                {
                    bomb.get(i).disegnaBomba(c, p);
                }
                catch (Exception e)
                {
                    continue;
                }
            }
            for(int i=0;i<scoppiate.size();i++)
            {

                try
                {
                    scoppiate.get(i).disegna(c, p);
                }
                catch (Exception e)
                {
                    continue;
                }
            }
            timer.disegnaTimer(c,p);

            for(int i=0;i<combo.size();i++)
            {
                combo.get(i).disegna(c);
            }


            for(int i=0;i<trofei.size();i++)
            {
                trofei.get(i).disegnaSbloccaTrofeo(c);
            }
            //cc.drawBitmap(bitmap,0,0,null);


    }

    private class GestoreScoppiate extends Thread
    {
        private boolean continua;
        public GestoreScoppiate()
        {
            continua=true;
        }
        public void stoppa()
        {
            continua=false;
        }
        public void run()
        {
            try
            {


                while (continua)
                {
                    long l=System.currentTimeMillis();
                    for (int i = 0; i < scoppiate.size(); i++)
                    {
                        scoppiate.get(i).runna();


                    }
                    postInvalidate();

                    l=System.currentTimeMillis()-l;

                    if(DisegnaPunti.LIVELLO-l>0)
                    {

                        Thread.sleep(DisegnaPunti.LIVELLO - l);
                    }


                }
            }
            catch (Exception e)
            {
                return;
            }
        }

    }


    private class Gestore extends Thread
    {
        private boolean continua;
        public Gestore()
        {
            continua=true;
        }
        public void stoppa()
        {
            continua=false;
        }
        public void run()
        {
            try
            {


                while (continua)
                {
                    long l=System.currentTimeMillis();
                    for (int i = 0; i < bomb.size(); i++)
                    {
                        bomb.get(i).runna();


                    }
                    postInvalidate();
                    l=System.currentTimeMillis()-l;
                    if(Bomba.getLEVEL()-l>0)
                    {

                        Thread.sleep(Bomba.getLEVEL() - l);
                    }
                }
            }
            catch (Exception e)
            {
                return;
            }
        }

    }







    }
