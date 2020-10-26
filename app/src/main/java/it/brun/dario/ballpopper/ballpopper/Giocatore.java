package it.brun.dario.ballpopper.ballpopper;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Message;
import android.view.Display;
import android.view.WindowManager;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import it.brun.dario.ballpopper.exceptions.CaricamentoException;
import it.brun.dario.ballpopper.views.MessageView;
import it.brun.dario.ballpopper.ballpopper.bullets.MinaAntipalla;
import it.brun.dario.ballpopper.views.Negozio;
import it.brun.dario.ballpopper.ballpopper.bullets.Palla;
import it.brun.dario.ballpopper.ballpopper.bullets.PallaDetonante;
import it.brun.dario.ballpopper.ballpopper.bullets.PallaEsplosiva;
import it.brun.dario.ballpopper.ballpopper.bullets.PallaPerforante;
import it.brun.dario.ballpopper.views.PannelloFineLivello;
import it.brun.dario.ballpopper.views.PannelloGameOver;
import it.brun.dario.ballpopper.views.PannelloInventario;
import it.brun.dario.ballpopper.views.PannelloTrofeo;
import it.brun.dario.ballpopper.Personaggio;
import it.brun.dario.ballpopper.ballpopper.bullets.Pietrone;
import it.brun.dario.ballpopper.Punto;
import it.brun.dario.ballpopper.R;
import it.brun.dario.ballpopper.Tempo;
import it.brun.dario.ballpopper.ballpopper.trophies.Trofeo;
import it.brun.dario.ballpopper.ballpopper.trophies.Trofeo10000Perforanti;
import it.brun.dario.ballpopper.ballpopper.trophies.Trofeo10000Punti;
import it.brun.dario.ballpopper.ballpopper.trophies.Trofeo1000Atomici;
import it.brun.dario.ballpopper.ballpopper.trophies.TrofeoBombaInfuocata;
import it.brun.dario.ballpopper.ballpopper.trophies.TrofeoBombeApe;
import it.brun.dario.ballpopper.ballpopper.trophies.TrofeoDistruttore;
import it.brun.dario.ballpopper.ballpopper.trophies.TrofeoFantasmi;
import it.brun.dario.ballpopper.ballpopper.trophies.TrofeoFineGioco;
import it.brun.dario.ballpopper.ballpopper.trophies.TrofeoGranchi;
import it.brun.dario.ballpopper.ballpopper.trophies.TrofeoLivello18;
import it.brun.dario.ballpopper.ballpopper.trophies.TrofeoLivello3;
import it.brun.dario.ballpopper.ballpopper.trophies.TrofeoLivello6;
import it.brun.dario.ballpopper.ballpopper.trophies.TrofeoMiliardario;
import it.brun.dario.ballpopper.ballpopper.trophies.TrofeoScoppiaAtomica;
import it.brun.dario.ballpopper.ballpopper.trophies.TrofeoScoppiaMina;
import it.brun.dario.ballpopper.ballpopper.trophies.TrofeoX100Atomico;
import it.brun.dario.ballpopper.ballpopper.trophies.TrofeoX10Detonante;
import it.brun.dario.ballpopper.ballpopper.trophies.TrofeoX10Perforante;
import it.brun.dario.ballpopper.ballpopper.trophies.TrofeoX2;
import it.brun.dario.ballpopper.ballpopper.trophies.TrofeoX25Mina;
import it.brun.dario.ballpopper.ballpopper.trophies.TrofeoX7;
import it.brun.dario.ballpopper.ballpopper.balls.Bomba;
import it.brun.dario.ballpopper.ballpopper.balls.BombaApe;
import it.brun.dario.ballpopper.ballpopper.balls.BombaAtomica;
import it.brun.dario.ballpopper.ballpopper.balls.BombaFantasma;
import it.brun.dario.ballpopper.ballpopper.balls.BombaFogliata;
import it.brun.dario.ballpopper.ballpopper.balls.BombaGranchiosa;
import it.brun.dario.ballpopper.ballpopper.balls.BombaInfuocata;
import it.brun.dario.ballpopper.ballpopper.balls.BombaSpinata;
import it.brun.dario.ballpopper.ballpopper.balls.CyberBomba;
import it.brun.dario.ballpopper.ballpopper.bonus.Bonus;
import it.brun.dario.ballpopper.ballpopper.bonus.BonusPIU1;
import it.brun.dario.ballpopper.ballpopper.bonus.BonusPIU10;
import it.brun.dario.ballpopper.ballpopper.bonus.BonusPIU2;
import it.brun.dario.ballpopper.ballpopper.bonus.BonusPIU3;
import it.brun.dario.ballpopper.ballpopper.bonus.BonusPIU4;
import it.brun.dario.ballpopper.ballpopper.bonus.BonusPIU5;
import it.brun.dario.ballpopper.ballpopper.bonus.BonusPIU6;
import it.brun.dario.ballpopper.ballpopper.bonus.BonusPIU7;
import it.brun.dario.ballpopper.ballpopper.bonus.BonusX2;
import it.brun.dario.ballpopper.ballpopper.bonus.BonusX4;
import it.brun.dario.ballpopper.ballpopper.shots.ColpiAtomici;
import it.brun.dario.ballpopper.ballpopper.shots.ColpiDetonanti;
import it.brun.dario.ballpopper.ballpopper.shots.ColpiEsplosivi;
import it.brun.dario.ballpopper.ballpopper.shots.ColpiMine;
import it.brun.dario.ballpopper.ballpopper.shots.ColpiNormali;
import it.brun.dario.ballpopper.ballpopper.shots.ColpiPerforanti;
import it.brun.dario.ballpopper.ballpopper.shots.ColpiPietroni;
import it.brun.dario.ballpopper.levels.Livello;
import it.brun.dario.ballpopper.levels.LivelloCinque;
import it.brun.dario.ballpopper.levels.LivelloDiciannove;
import it.brun.dario.ballpopper.levels.LivelloDiciassette;
import it.brun.dario.ballpopper.levels.LivelloDiciotto;
import it.brun.dario.ballpopper.levels.LivelloDieci;
import it.brun.dario.ballpopper.levels.LivelloDodici;
import it.brun.dario.ballpopper.levels.LivelloDue;
import it.brun.dario.ballpopper.levels.LivelloNove;
import it.brun.dario.ballpopper.levels.LivelloOtto;
import it.brun.dario.ballpopper.levels.LivelloQuattordici;
import it.brun.dario.ballpopper.levels.LivelloQuattro;
import it.brun.dario.ballpopper.levels.LivelloQuindici;
import it.brun.dario.ballpopper.levels.LivelloSedici;
import it.brun.dario.ballpopper.levels.LivelloSei;
import it.brun.dario.ballpopper.levels.LivelloSette;
import it.brun.dario.ballpopper.levels.LivelloTre;
import it.brun.dario.ballpopper.levels.LivelloTredici;
import it.brun.dario.ballpopper.levels.LivelloUndici;
import it.brun.dario.ballpopper.levels.LivelloUno;
import it.brun.dario.ballpopper.levels.LivelloVenti;
import it.brun.dario.ballpopper.levels.LivelloVentuno;
import it.brun.dario.ballpopper.views.Ambiente;
import it.brun.dario.ballpopper.views.AmbienteLivelli;

/**
 * Created by dario on 13/04/17.
 */
public class Giocatore
{
    private static final String STATUS="status";
    public static final String STATUS_BASE="nonacquisatato";
    public static final String STATUS_ACQUISTATO="acquisatato";

    private static String stato;
    public static String getStato()
    {
        return stato;
    }
    private static void assegnaStato(Activity act)
    {
        SharedPreferences sharedPref = act.getPreferences(Context.MODE_PRIVATE);


        stato=sharedPref.getString(STATUS,STATUS_BASE);

    }

    private static boolean assegnato=false;
    public static boolean getAssegnato()
    {
        return assegnato;
    }
    public static final String URI="URI";
    public static final String INFORMAZIONI="informazioni";
    private static final int COMPLETE=5;
    private static final int CHARA=64;

    public static Informazioni caricaPartita(BufferedReader reader)throws IOException,CaricamentoException
    {
        String line=reader.readLine();
        int n=0;
        if(line.substring(0,10).equals(InventarioMunizioni.CODICE))
        {
            n=(int)(line.charAt(10))-65;

            line=reader.readLine();
        }
        Informazioni retval=getInformazioni(InventarioMunizioni.decodifica(line,n));
        caricaTrofei(InventarioMunizioni.decodifica(reader.readLine(),n));

        creaInventarioMunizioni(InventarioMunizioni.getInventarioFromCodifica(reader,n));
        return retval;
    }

    public static Informazioni getInformazioni(String line)throws IOException
    {

        if(!line.substring(0,10).equals(InventarioMunizioni.CODIFICA))
        {
            throw new IOException();
        }

        String subline=line.substring(10);
        int index=subline.indexOf(InventarioMunizioni.SEPARATORE);
        int x=Integer.parseInt(subline.substring(0,index));


        subline=subline.substring(index+1);
        index=subline.indexOf(InventarioMunizioni.SEPARATORE);
        int y=Integer.parseInt(subline.substring(0,index));

        subline=subline.substring(index+1);
        index=subline.indexOf(InventarioMunizioni.SEPARATORE);
        int direzione=Integer.parseInt(subline.substring(0,index));

        boolean pippo=true;
        float pos;
        subline=subline.substring(index+1);
        index=subline.indexOf(InventarioMunizioni.SEPARATORE);

        if(index==-1)
        {
            pos = Float.parseFloat(subline);
        }
        else
        {
            pos=Float.parseFloat(subline.substring(0,index));
            subline=subline.substring(index+1);

            pippo=Boolean.parseBoolean(subline);

        }


        Informazioni info=new Informazioni(x,y,direzione,pos,pippo);
        return info;
    }
    private static Trofeo[] trofei;


    public static void creaInventarioMunizioni(InventarioMunizioni inve)
    {
        invmun=inve;
    }
    public static String codificaLivello()
    {

        StringBuilder retval=new StringBuilder();
        retval.append("AGHAGHAZAVA");
        for(int i=0;i<livelli.size();i++)
        {
            int pippo=livelli.get(i).getNumero();
            if(livelli.get(i).getCompletato())
            {
                pippo+=COMPLETE;
            }
            pippo+=CHARA;
            char aa=(char)pippo;
            retval.append(aa);

        }


       retval.append("GASSAMAVA");
       return retval.toString();
    }

    public static void decodificaLivello(String s)throws CaricamentoException
    {
        try
        {

            String ss=s.substring(11,s.length()-9);

            for(int i=0;i<ss.length();i++)
            {
                char pluto=ss.charAt(i);
                int pippo=((int)pluto)-CHARA-livelli.get(i).getNumero();
                if(pippo==COMPLETE)
                {
                    livelli.get(i).completa();
                }
            }


        }
        catch(Exception e)
        {
            throw new CaricamentoException();
        }

    }
    private static Typeface typeface;
    private static Typeface typeface2;
    private static void assegnaTypeface(Activity act)
    {
        typeface = Typeface.createFromAsset(act.getAssets(),"font/font1.ttf");
        typeface2 = Typeface.createFromAsset(act.getAssets(),"font/corsivo1.ttf");

    }
    public static void setTypeface(Paint p)
    {
        p.setTypeface(typeface);
    }
    public static void setTypeface(TextView p)
    {
        p.setTypeface(typeface);
    }
    public static void setTypefaceCorsivo(Paint p)
    {
        p.setTypeface(typeface2);
    }
    public static void setTypefaceCorsivo(TextView p)
    {
        p.setTypeface(typeface2);
    }


    private static void assegnaDescrizioniBonus(Activity act)
    {
        BonusPIU1.assegnaDescrizione(act);
        BonusPIU2.assegnaDescrizione(act);
        BonusPIU3.assegnaDescrizione(act);
        BonusPIU4.assegnaDescrizione(act);
        BonusPIU5.assegnaDescrizione(act);
        BonusPIU6.assegnaDescrizione(act);
        BonusPIU7.assegnaDescrizione(act);
        BonusPIU10.assegnaDescrizione(act);
        BonusX2.assegnaDescrizione(act);
        BonusX4.assegnaDescrizione(act);
    }
    public static void assegnazione(Activity act)
    {
        if(!assegnato)
        {
            assegnaStato(act);
            WindowManager wm = (WindowManager) act.getSystemService(Context.WINDOW_SERVICE);
            Display display = wm.getDefaultDisplay();
            Point size = new Point();
            assegnaTypeface(act);
            display.getSize(size);
            Livello.setCOMPLETATO(act.getString(R.string.completato));
            Ambiente.setWIDTH(size.x);
            Ambiente.setHEIGHT(size.y);
            assegnaDescrizioniBonus(act);
            Bomba.setDIAMETROBASE(size.x / 32);
            Bonus.assegnaDIAMETROBASE(Bomba.getDIAMETROBASE() * 1.5f);
            ColpiNormali.assegnaNome(act);
            ColpiPerforanti.assegnaNome(act);
            ColpiEsplosivi.assegnaNome(act);
            ColpiDetonanti.assegnaNome(act);
            ColpiMine.assegnaNome(act);
            ColpiPietroni.assegnaNome(act);
            ColpiAtomici.assegnaNome(act);
            PannelloInventario.assegnaImmagine(act);
            Ambiente.assegnaSfondo(act);
            Cannone.setWIDTH((Bomba.getDIAMETROBASE() / 4) * 5);
            Bomba.setINCREMENTATORE_RAGGIO(Bomba.getDIAMETROBASE() / 5);
            Bomba.setIncrementatori(Bomba.getDIAMETROBASE() / 40, Bomba.getDIAMETROBASE() / 10);
            Palla.assegnaVELOCITA(Ambiente.getWIDTH() / 160);
            AmbienteLivelli.setMIN_DISTANCE((int) ((Ambiente.getHEIGHT() / 4) * (Ambiente.getHEIGHT() / 4)));
            Negozio.assegnaImmagine(act);
            MessageView.assegnaImmagini(act);
            AmbienteLivelli.assemblaSfondo(act);
            Punto.assegnaImmagine(act);
            PannelloFineLivello.assegnaImmagine(act);
            PannelloGameOver.assegnaSfondo(act);
            Personaggio.assegnaImmagini(act);
            Trofeo.assegnaImmagine(act);
            Palla.assegnaImmagine(act);
            Combo.caricaImmagine(act);
            Timer.assegnaImmagine(act);
            Ambiente.assegnaRiproduttori(act);
            PallaPerforante.assegnaImmagine(act);
            BombaInfuocata.setImmagine(act);
            BombaSpinata.setImmagine(act);
            BombaFogliata.setImmagine(act);
            BombaGranchiosa.setImmagine(act);
            CyberBomba.setImmagine(act);
            BombaApe.setImmagine(act);
            BombaFantasma.setImmagine(act);
            PallaEsplosiva.assegnaImmagine(act);
            PallaDetonante.assegnaImmagine(act);
            MinaAntipalla.assegnaImmagine(act);
            Pietrone.assegnaImmagine(act);
            BombaAtomica.assegnaImmagine(act);
            PannelloTrofeo.assegnaBlocked(act);
            assegnato=true;
        }

    }
    public static final int AGGIORNA_WHAT=1;
    private static Handler handler=null;

   /* public static Bitmap getResizedBitmap(Bitmap bm, int newHeight, int newWidth)
    {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;

        Matrix matrix = new Matrix();

        matrix.postScale(scaleWidth, scaleHeight);


        Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false);
        bm.recycle();
        return resizedBitmap;
    }*/
   public static Bitmap getResizedBitmap(Bitmap bm, int newHeight, int newWidth)
   {
       Bitmap image = Bitmap.createScaledBitmap(bm, newWidth, newHeight, true);
       if (bm!= image)
       {

           bm.recycle();
       }
       bm.recycle();
       return image;
   }
    public static Bitmap getResizedBitmapBis(Bitmap bm, int newHeight, int newWidth)
    {
        Bitmap image = Bitmap.createScaledBitmap(bm, newWidth, newHeight, true);
        return image;
    }
    /*
    public static Bitmap getResizedBitmapBis(Bitmap bm, int newHeight, int newWidth)
    {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;

        Matrix matrix = new Matrix();

        matrix.postScale(scaleWidth, scaleHeight);


        Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false);
        return resizedBitmap;
    }
*/
    private static Trofeo getTrofeoFromCodifica(String s)
    {
        for(int i=0;i<trofei.length;i++)
        {
            if(trofei[i].getCODIFICA().equals(s))
            {
                return trofei[i];
            }
        }
        return null;
    }

    public static void caricaTrofei(String s) throws CaricamentoException
    {
        try
        {
            int index = 0;
            String ss = s.substring(index, index + 6);
            if (!Trofeo.CODIFICA_INIZIALE.equals(ss))
            {
                throw new CaricamentoException();
            }
            index += 6;
            ss = s.substring(index, index + 6);
            while (!Trofeo.CODIFICA_FINALE.equals(ss))
            {

                Trofeo t = getTrofeoFromCodifica(ss);
                index += 6;
                ss = s.substring(index, index + 6);
                Tempo tt = Tempo.getTempoFromCodifica(ss);
                t.sblocca(tt);
                index += 6;
                ss = s.substring(index, index + 6);


            }

        }
        catch (Exception e)
        {
            throw new CaricamentoException();
        }
    }

    public static List<Trofeo> controllaTrofei(Palla p,Livello lev)
    {

        List<Trofeo> retval=new ArrayList<Trofeo>();
        for(int i=0;i<trofei.length;i++)
        {
            if(!trofei[i].getSbloccato()&&trofei[i].condizione(p,lev))
            {
                trofei[i].sblocca();
                retval.add(trofei[i]);
            }
        }
        return retval;
    }
    public static void registraHandler(Handler han)
    {
        handler=han;
    }

    private static void inviaMessaggio()
    {
        if(handler!=null)
        {
            Message updateMessage = handler.obtainMessage(AGGIORNA_WHAT);

            handler.sendMessage(updateMessage);
        }
    }

    public static void completa(Livello level)
    {
        livelli.get(level.getNumero()-1).completa();
        inviaMessaggio();
    }
    private static void creaLivelli()
    {
        livelli = new ArrayList<Livello>();
        livelli.add(new LivelloUno(null,Giocatore.inventario()));

        livelli.add(new LivelloDue(null,Giocatore.inventario()));
        livelli.add(new LivelloTre(null,Giocatore.inventario()));
        livelli.add(new LivelloQuattro(null,Giocatore.inventario()));
        livelli.add(new LivelloCinque(null,Giocatore.inventario()));
        livelli.add(new LivelloSei(null,Giocatore.inventario()));
        livelli.add(new LivelloSette(null,Giocatore.inventario()));
        livelli.add(new LivelloOtto(null,Giocatore.inventario()));
        livelli.add(new LivelloNove(null,Giocatore.inventario()));
        livelli.add(new LivelloDieci(null,Giocatore.inventario()));
        livelli.add(new LivelloUndici(null,Giocatore.inventario()));
        livelli.add(new LivelloDodici(null,Giocatore.inventario()));
        livelli.add(new LivelloTredici(null,Giocatore.inventario()));
        livelli.add(new LivelloQuattordici(null,Giocatore.inventario()));
        livelli.add(new LivelloQuindici(null,Giocatore.inventario()));
        livelli.add(new LivelloSedici(null,Giocatore.inventario()));
        livelli.add(new LivelloDiciassette(null,Giocatore.inventario()));
        livelli.add(new LivelloDiciotto(null,Giocatore.inventario()));
        livelli.add(new LivelloDiciannove(null,Giocatore.inventario()));
        livelli.add(new LivelloVenti(null,Giocatore.inventario()));
        livelli.add(new LivelloVentuno(null,Giocatore.inventario()));







    }
    public static int getNumTrofei()
    {
        return trofei.length;
    }
    public static Trofeo getTrofeo(int i)
    {
        return trofei[i];
    }

    public static void creaTrofei(Activity act)
    {
        trofei=new Trofeo[21];
        trofei[0]=new TrofeoScoppiaMina(act);
        trofei[1]=new TrofeoScoppiaAtomica(act);
        trofei[2]=new Trofeo1000Atomici(act);
        trofei[3]=new Trofeo10000Perforanti(act);
        trofei[4]=new TrofeoLivello3(act);
        trofei[5]=new TrofeoLivello6(act);
        trofei[6]=new TrofeoLivello18(act);
        trofei[7]=new TrofeoFineGioco(act);
        trofei[8]=new TrofeoX2(act);
        trofei[9]=new TrofeoX7(act);
        trofei[10]=new TrofeoX10Perforante(act);
        trofei[11]=new TrofeoX10Detonante(act);
        trofei[12]=new TrofeoX25Mina(act);
        trofei[13]=new TrofeoX100Atomico(act);
        trofei[14]=new TrofeoDistruttore(act);
        trofei[15]=new TrofeoBombaInfuocata(act);
        trofei[16]=new TrofeoGranchi(act);
        trofei[17]=new TrofeoBombeApe(act);
        trofei[18]=new TrofeoFantasmi(act);
        trofei[19]=new TrofeoMiliardario(act);
        trofei[20]=new Trofeo10000Punti(act);

    }
    public static int getNumLivelli()
    {
        return livelli.size();
    }
    public static int getNumLivelliCompletati()
    {
        int n=0;
        for(int i=0;i<livelli.size();i++)
        {
            if(livelli.get(i).getCompletato())
            {
                n++;
            }
        }
        return n;
    }



    public static Livello getLivello(int i)
    {
        return livelli.get(i);
    }
    private static InventarioMunizioni invmun;
    private static List<Livello> livelli;
    public static InventarioMunizioni inventario()
    {
        return invmun;
    }
    public static void creaInventario(Activity act)
    {
        creaTrofei(act);

        invmun=new InventarioMunizioni();
        invmun.creaMunizioniBase();
        creaLivelli();

    }
}
