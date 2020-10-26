package it.brun.dario.ballpopper.ballpopper;

import android.graphics.Bitmap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import it.brun.dario.ballpopper.exceptions.CaricamentoException;
import it.brun.dario.ballpopper.ballpopper.shots.Munizioni;
import it.brun.dario.ballpopper.ballpopper.bullets.Palla;
import it.brun.dario.ballpopper.Tempo;
import it.brun.dario.ballpopper.ballpopper.trophies.Trofeo;
import it.brun.dario.ballpopper.ballpopper.bonus.Bonus;
import it.brun.dario.ballpopper.ballpopper.shots.ColpiNormali;
import it.brun.dario.ballpopper.ballpopper.shots.ColpiPerforanti;
import it.brun.dario.ballpopper.views.Ambiente;

/**
 * Created by dario on 10/04/17.
 */
public class InventarioMunizioni
{



    private long palleScoppiate;
    private long palleFogliateScoppiate;
    private long palleSpinateScoppiate;
    private long palleInfuocateScoppiate;
    private long palleGranchioScoppiate;
    private long palleCyberScoppiate;
    private long palleApeScoppiate;
    private long palleFantasmaScoppiate;


    public long getPalleScoppiate()
    {
        return palleScoppiate;
    }
    public long getPalleFogliateScoppiate()
    {
        return palleFogliateScoppiate;
    }
    public long getPalleSpinateScoppiate()
    {
        return palleSpinateScoppiate;
    }
    public long getPalleInfuocateScoppiate()
    {
        return palleInfuocateScoppiate;
    }
    public long getPalleGranchioScoppiate()
    {
        return palleGranchioScoppiate;
    }
    public long getPalleCyberScoppiate()
    {
        return palleCyberScoppiate;
    }
    public long getPalleApeScoppiate()
    {
        return palleApeScoppiate;
    }
    public long getPalleFantasmaScoppiate()
    {
        return palleFantasmaScoppiate;
    }
    public void incrementaScoppiate()
    {
        palleScoppiate++;
    }
    public void incrementaFogliateScoppiate()
    {
        palleScoppiate++;
        palleFogliateScoppiate++;
    }
    public void incrementaSpinateScoppiate()
    {
        palleScoppiate++;
        palleSpinateScoppiate++;
    }
    public void incrementaInfuocateScoppiate()
    {
        palleScoppiate++;
        palleInfuocateScoppiate++;
    }
    public void incrementaGranchiScoppiati()
    {
        palleScoppiate++;
        palleGranchioScoppiate++;

    }
    public void incrementaCyberScoppiate()
    {
        palleScoppiate++;
        palleCyberScoppiate++;
    }
    public void incrementaApiScoppiate()
    {
        palleScoppiate++;
        palleApeScoppiate++;
    }

    public void incrementaFantasmiScoppiati()
    {
        palleScoppiate++;
        palleFantasmaScoppiate++;
    }




    public static final String CODICE="CASTANCAOI";
    public static final String SEPARATORE="#";
    public static final String CODIFICA="IVNAIMNZOI";

    public void addNormaliSparati()
    {
        normaliSparati++;
    }
    public void addPerforantiSparati()
    {
        perforantiSparati++;
    }

    public void addEsplosiviSparati()
    {
        esplosiviSparati++;
    }

    public void addDetonantiSparati()
    {
        detonantiSparati++;
    }
    public void addPietroniSparati()
    {
        pietroniSparati++;
    }
    public void addMineSparate(boolean esplosa)
    {
        mineSparate++;
        if(esplosa)
        {
            mineEsplose++;
        }
    }
    public void addAtomiciSparati(boolean esplosa)
    {
        atomiciSparati++;
        if(esplosa)
        {
            atomiciEsplosi++;
        }

    }

    public long getNormaliSparati()
    {
        return normaliSparati;
    }
    public long getPerforantiSparati()
    {
        return perforantiSparati;
    }
    public long getEsplosiviSparati()
    {
        return esplosiviSparati;
    }
    public long getDetonantiSparati()
    {
        return detonantiSparati;
    }
    public long getPietroniSparati()
    {
        return pietroniSparati;
    }
    public long getMineSparate()
    {
        return mineSparate;
    }
    public long getMineEsplose()
    {
        return mineEsplose;
    }
    public long getAtomiciSparati()
    {
        return atomiciSparati;
    }
    public long getAtomiciEsplosi()
    {
        return atomiciEsplosi;
    }




/*
    public static InventarioMunizioni getInventarioFromCodifica(BufferedReader buf)throws CaricamentoException
    {

        InventarioMunizioni inv=new InventarioMunizioni();
        try
        {
            String line=buf.readLine();
            if(!line.substring(0,10).equals(CODIFICA))
            {
                throw new IOException();
            }

            String subline=line.substring(11);

            int index=subline.indexOf(SEPARATORE);
            int numMunizioni=Integer.parseInt(subline.substring(0,index));


            subline=subline.substring(index+1);
            index=subline.indexOf(SEPARATORE);
            int numPotenziamenti=Integer.parseInt(subline.substring(0,index));
            subline=subline.substring(index+1);
            index=subline.indexOf(SEPARATORE);

            int punt=Integer.parseInt(subline.substring(0,index));
            inv.punti=punt;

            subline=subline.substring(index+1);
            index=subline.indexOf(SEPARATORE);
            inv.puntiTotali=Integer.parseInt(subline.substring(0,index));

            subline=subline.substring(index+1);
            inv.maxPotenziamenti=Integer.parseInt(subline);





            line=buf.readLine();
            if(!line.substring(0,10).equals(CODIFICA))
            {
                throw new IOException();
            }

            subline=line.substring(11);
            index=subline.indexOf(SEPARATORE);
            inv.normaliSparati=Long.parseLong(subline.substring(0,index));

            subline=subline.substring(index+1);
            index=subline.indexOf(SEPARATORE);
            inv.perforantiSparati=Long.parseLong(subline.substring(0,index));

            subline=subline.substring(index+1);
            index=subline.indexOf(SEPARATORE);
            inv.esplosiviSparati=Long.parseLong(subline.substring(0,index));

            subline=subline.substring(index+1);
            index=subline.indexOf(SEPARATORE);
            inv.detonantiSparati=Long.parseLong(subline.substring(0,index));

            subline=subline.substring(index+1);
            index=subline.indexOf(SEPARATORE);
            inv.pietroniSparati=Long.parseLong(subline.substring(0,index));

            subline=subline.substring(index+1);
            index=subline.indexOf(SEPARATORE);
            inv.mineEsplose=Long.parseLong(subline.substring(0,index));

            subline=subline.substring(index+1);
            index=subline.indexOf(SEPARATORE);
            inv.mineSparate=Long.parseLong(subline.substring(0,index));

            subline=subline.substring(index+1);
            index=subline.indexOf(SEPARATORE);
            inv.atomiciEsplosi=Long.parseLong(subline.substring(0,index));

            subline=subline.substring(index+1);
            inv.atomiciSparati=Long.parseLong(subline);


            line=buf.readLine();
            if(!line.substring(0,10).equals(CODIFICA))
            {
                throw new IOException();
            }

            subline=line.substring(11);
            index=subline.indexOf(SEPARATORE);
            inv.palleScoppiate=Long.parseLong(subline.substring(0,index));

            subline=subline.substring(index+1);
            index=subline.indexOf(SEPARATORE);
            if(index>-1)
            {
                inv.palleFogliateScoppiate = Long.parseLong(subline.substring(0, index));
            }

            subline=subline.substring(index+1);
            index=subline.indexOf(SEPARATORE);
            if(index>-1)
            {
                inv.palleSpinateScoppiate = Long.parseLong(subline.substring(0, index));
            }

            subline=subline.substring(index+1);
            index=subline.indexOf(SEPARATORE);
            if(index>-1)
            {
                inv.palleInfuocateScoppiate = Long.parseLong(subline.substring(0, index));
            }

            subline=subline.substring(index+1);
            index=subline.indexOf(SEPARATORE);
            if(index>-1)
            {
                inv.palleGranchioScoppiate = Long.parseLong(subline.substring(0, index));
            }

            subline=subline.substring(index+1);
            index=subline.indexOf(SEPARATORE);
            if(index>-1)
            {
                inv.palleCyberScoppiate = Long.parseLong(subline.substring(0, index));
            }

            subline=subline.substring(index+1);
            index=subline.indexOf(SEPARATORE);
            if(index>-1)
            {
                inv.palleApeScoppiate = Long.parseLong(subline.substring(0, index));
            }





            if(numMunizioni>0)
            {
                line=buf.readLine();
                for(int i=0;i<numMunizioni;i++)
                {
                    index=line.indexOf(SEPARATORE);
                    inv.addMunizioni( Munizioni.getMunizioniFromCodifica(line.substring(0,index+1)));

                    line=line.substring(index+1);
                }
            }

            Giocatore.decodificaLivello( buf.readLine());
            if(numPotenziamenti>0)
            {
                line=buf.readLine();
                for(int i=0;i<numPotenziamenti;i++)
                {
                    inv.addPotenziamento(Bonus.getBonusFromCodifica(line.substring(0,10)));
                    line=line.substring(10);
                }
            }
            return inv;
        }
        catch (Exception e)
        {
            throw new CaricamentoException();
        }



    }
    */
public static InventarioMunizioni getInventarioFromCodifica(BufferedReader buf,int cod)throws CaricamentoException
{

    InventarioMunizioni inv=new InventarioMunizioni();
    try
    {
        String line=decodifica(buf.readLine(),cod);

        if(!line.substring(0,10).equals(CODIFICA))
        {
            throw new IOException();
        }

        String subline=line.substring(11);

        int index=subline.indexOf(SEPARATORE);
        int numMunizioni=Integer.parseInt(subline.substring(0,index));


        subline=subline.substring(index+1);
        index=subline.indexOf(SEPARATORE);
        int numPotenziamenti=Integer.parseInt(subline.substring(0,index));
        subline=subline.substring(index+1);
        index=subline.indexOf(SEPARATORE);

        int punt=Integer.parseInt(subline.substring(0,index));
        inv.punti=punt;

        subline=subline.substring(index+1);
        index=subline.indexOf(SEPARATORE);
        inv.puntiTotali=Integer.parseInt(subline.substring(0,index));

        subline=subline.substring(index+1);
        inv.maxPotenziamenti=Integer.parseInt(subline);





        line=decodifica(buf.readLine(),cod);

        if(!line.substring(0,10).equals(CODIFICA))
        {
            throw new IOException();
        }

        subline=line.substring(11);
        index=subline.indexOf(SEPARATORE);
        inv.normaliSparati=Long.parseLong(subline.substring(0,index));

        subline=subline.substring(index+1);
        index=subline.indexOf(SEPARATORE);
        inv.perforantiSparati=Long.parseLong(subline.substring(0,index));

        subline=subline.substring(index+1);
        index=subline.indexOf(SEPARATORE);
        inv.esplosiviSparati=Long.parseLong(subline.substring(0,index));

        subline=subline.substring(index+1);
        index=subline.indexOf(SEPARATORE);
        inv.detonantiSparati=Long.parseLong(subline.substring(0,index));

        subline=subline.substring(index+1);
        index=subline.indexOf(SEPARATORE);
        inv.pietroniSparati=Long.parseLong(subline.substring(0,index));

        subline=subline.substring(index+1);
        index=subline.indexOf(SEPARATORE);
        inv.mineEsplose=Long.parseLong(subline.substring(0,index));

        subline=subline.substring(index+1);
        index=subline.indexOf(SEPARATORE);
        inv.mineSparate=Long.parseLong(subline.substring(0,index));

        subline=subline.substring(index+1);
        index=subline.indexOf(SEPARATORE);
        inv.atomiciEsplosi=Long.parseLong(subline.substring(0,index));

        subline=subline.substring(index+1);
        inv.atomiciSparati=Long.parseLong(subline);


        line=decodifica(buf.readLine(),cod);

        if(!line.substring(0,10).equals(CODIFICA))
        {
            throw new IOException();
        }

        subline=line.substring(11);
        index=subline.indexOf(SEPARATORE);
        inv.palleScoppiate=Long.parseLong(subline.substring(0,index));

        subline=subline.substring(index+1);
        index=subline.indexOf(SEPARATORE);
        if(index>-1)
        {
            inv.palleFogliateScoppiate = Long.parseLong(subline.substring(0, index));
        }

        subline=subline.substring(index+1);
        index=subline.indexOf(SEPARATORE);
        if(index>-1)
        {
            inv.palleSpinateScoppiate = Long.parseLong(subline.substring(0, index));
        }

        subline=subline.substring(index+1);
        index=subline.indexOf(SEPARATORE);
        if(index>-1)
        {
            inv.palleInfuocateScoppiate = Long.parseLong(subline.substring(0, index));
        }

        subline=subline.substring(index+1);
        index=subline.indexOf(SEPARATORE);
        if(index>-1)
        {
            inv.palleGranchioScoppiate = Long.parseLong(subline.substring(0, index));
        }

        subline=subline.substring(index+1);
        index=subline.indexOf(SEPARATORE);
        if(index>-1)
        {
            inv.palleCyberScoppiate = Long.parseLong(subline.substring(0, index));
        }

        subline=subline.substring(index+1);
        index=subline.indexOf(SEPARATORE);
        if(index>-1)
        {
            inv.palleApeScoppiate = Long.parseLong(subline.substring(0, index));
        }

        subline=subline.substring(index+1);
        index=subline.indexOf(SEPARATORE);
        if(index>-1)
        {
            inv.palleFantasmaScoppiate = Long.parseLong(subline.substring(0, index));
        }



        if(numMunizioni>0)
        {
            line=decodifica(buf.readLine(),cod);
            for(int i=0;i<numMunizioni;i++)
            {
                index=line.indexOf(SEPARATORE);
                inv.addMunizioni( Munizioni.getMunizioniFromCodifica(line.substring(0,index+1)));

                line=line.substring(index+1);
            }
        }

        Giocatore.decodificaLivello( decodifica(buf.readLine(),cod));

        if(numPotenziamenti>0)
        {
            line=decodifica(buf.readLine(),cod);

            for(int i=0;i<numPotenziamenti;i++)
            {
                inv.addPotenziamento(Bonus.getBonusFromCodifica(line.substring(0,10)));
                line=line.substring(10);
            }
        }

        return inv;
    }
    catch (Exception e)
    {
        throw new CaricamentoException();
    }



}

    public static String codifica(String s,int n)
    {
        StringBuilder builder=new StringBuilder();
        for(int i=0;i<s.length();i++)
        {
            int k=(int)(s.charAt(i))+n;
            builder.append((char)k);
        }
        return builder.toString();
    }


    public static String decodifica(String s,int n)
    {
        StringBuilder builder=new StringBuilder();
        for(int i=0;i<s.length();i++)
        {
            int k=(int)(s.charAt(i))-n;
            builder.append((char)k);
        }
        return builder.toString();
    }


    public void salva(PrintWriter wr,Informazioni info)
    {

        int codice=(int)(Math.random()*25);
        wr.println(CODICE+(char)(codice+65));
        StringBuilder  builder=new StringBuilder(CODIFICA);
        builder.append(info.getX());
        builder.append(SEPARATORE);
        builder.append(info.getY());
        builder.append(SEPARATORE);
        builder.append(info.getDirezione());
        builder.append(SEPARATORE);
        builder.append(info.getPos());
        builder.append(SEPARATORE);
        builder.append(info.getSuonabile());
        wr.println(codifica(builder.toString(),codice));

        builder=new StringBuilder(Trofeo.CODIFICA_INIZIALE);
        for(int i=0;i<Giocatore.getNumTrofei();i++)
        {
            if(Giocatore.getTrofeo(i).getSbloccato())
            {
                Tempo t=Giocatore.getTrofeo(i).getTempo();
                builder.append(Giocatore.getTrofeo(i).getCODIFICA());
                builder.append(t.codificaTempo());

            }
        }
        builder.append(Trofeo.CODIFICA_FINALE);
        wr.println(codifica(builder.toString(),codice));

        builder=new StringBuilder(CODIFICA);
        builder.append(SEPARATORE);
        builder.append(munizioni.size());
        builder.append(SEPARATORE);
        builder.append(potenziamenti.size());
        builder.append(SEPARATORE);
        builder.append(punti);
        builder.append(SEPARATORE);
        builder.append(puntiTotali);
        builder.append(SEPARATORE);
        builder.append(maxPotenziamenti);
        wr.println(codifica(builder.toString(),codice));

        builder=new StringBuilder(CODIFICA);
        builder.append(SEPARATORE);
        builder.append(normaliSparati);
        builder.append(SEPARATORE);
        builder.append(perforantiSparati);
        builder.append(SEPARATORE);
        builder.append(esplosiviSparati);
        builder.append(SEPARATORE);
        builder.append(detonantiSparati);
        builder.append(SEPARATORE);
        builder.append(pietroniSparati);
        builder.append(SEPARATORE);
        builder.append(mineEsplose);
        builder.append(SEPARATORE);
        builder.append(mineSparate);
        builder.append(SEPARATORE);
        builder.append(atomiciEsplosi);
        builder.append(SEPARATORE);
        builder.append(atomiciSparati);
        wr.println(codifica(builder.toString(),codice));

        builder=new StringBuilder(CODIFICA);
        builder.append(SEPARATORE);
        builder.append(palleScoppiate);
        builder.append(SEPARATORE);
        builder.append(palleFogliateScoppiate);
        builder.append(SEPARATORE);
        builder.append(palleSpinateScoppiate);
        builder.append(SEPARATORE);
        builder.append(palleInfuocateScoppiate);
        builder.append(SEPARATORE);
        builder.append(palleGranchioScoppiate);
        builder.append(SEPARATORE);
        builder.append(palleCyberScoppiate);
        builder.append(SEPARATORE);
        builder.append(palleApeScoppiate);
        builder.append(SEPARATORE);
        builder.append(palleFantasmaScoppiate);
        builder.append(SEPARATORE);
        wr.println(codifica(builder.toString(),codice));


        if(munizioni.size()>0)
        {
            builder=new StringBuilder();
            for (int i = 0; i < munizioni.size(); i++)
            {

                builder.append(munizioni.get(i).codifica());
            }
            wr.println(codifica(builder.toString(),codice));
        }

        wr.println(codifica(Giocatore.codificaLivello(),codice));

        if(potenziamenti.size()>0)
        {
            builder=new StringBuilder();
            for (int i = 0; i < potenziamenti.size(); i++)
            {

                builder.append(potenziamenti.get(i).codifica());
            }
            wr.println(codifica(builder.toString(),codice));
        }

    }


/*

    public void salva(PrintWriter wr,Informazioni info)
    {


        StringBuilder  builder=new StringBuilder(CODIFICA);
        builder.append(info.getX());
        builder.append(SEPARATORE);
        builder.append(info.getY());
        builder.append(SEPARATORE);
        builder.append(info.getDirezione());
        builder.append(SEPARATORE);
        builder.append(info.getPos());
        wr.println(builder.toString());

        builder=new StringBuilder(Trofeo.CODIFICA_INIZIALE);
        for(int i=0;i<Giocatore.getNumTrofei();i++)
        {
            if(Giocatore.getTrofeo(i).getSbloccato())
            {
                Tempo t=Giocatore.getTrofeo(i).getTempo();
                builder.append(Giocatore.getTrofeo(i).getCODIFICA());
                builder.append(t.codificaTempo());

            }
        }
        builder.append(Trofeo.CODIFICA_FINALE);
        wr.println(builder.toString());

        builder=new StringBuilder(CODIFICA);
        builder.append(SEPARATORE);
        builder.append(munizioni.size());
        builder.append(SEPARATORE);
        builder.append(potenziamenti.size());
        builder.append(SEPARATORE);
        builder.append(punti);
        builder.append(SEPARATORE);
        builder.append(puntiTotali);
        builder.append(SEPARATORE);
        builder.append(maxPotenziamenti);
        wr.println(builder.toString());

        builder=new StringBuilder(CODIFICA);
        builder.append(SEPARATORE);
        builder.append(normaliSparati);
        builder.append(SEPARATORE);
        builder.append(perforantiSparati);
        builder.append(SEPARATORE);
        builder.append(esplosiviSparati);
        builder.append(SEPARATORE);
        builder.append(detonantiSparati);
        builder.append(SEPARATORE);
        builder.append(pietroniSparati);
        builder.append(SEPARATORE);
        builder.append(mineEsplose);
        builder.append(SEPARATORE);
        builder.append(mineSparate);
        builder.append(SEPARATORE);
        builder.append(atomiciEsplosi);
        builder.append(SEPARATORE);
        builder.append(atomiciSparati);
        wr.println(builder.toString());

        builder=new StringBuilder(CODIFICA);
        builder.append(SEPARATORE);
        builder.append(palleScoppiate);
        builder.append(SEPARATORE);
        builder.append(palleFogliateScoppiate);
        builder.append(SEPARATORE);
        builder.append(palleSpinateScoppiate);
        builder.append(SEPARATORE);
        builder.append(palleInfuocateScoppiate);
        builder.append(SEPARATORE);
        builder.append(palleGranchioScoppiate);
        builder.append(SEPARATORE);
        builder.append(palleCyberScoppiate);
        builder.append(SEPARATORE);
        builder.append(palleApeScoppiate);
        builder.append(SEPARATORE);
        wr.println(builder.toString());


        if(munizioni.size()>0)
        {
            builder=new StringBuilder();
            for (int i = 0; i < munizioni.size(); i++)
            {

                builder.append(munizioni.get(i).codifica());
            }
            wr.println(builder.toString());
        }

        wr.println(Giocatore.codificaLivello());

        if(potenziamenti.size()>0)
        {
            builder=new StringBuilder();
            for (int i = 0; i < potenziamenti.size(); i++)
            {

                builder.append(potenziamenti.get(i).codifica());
            }
            wr.println(builder.toString());
        }

    }
*/


    public void incrementaPotenziamenti()
    {
        maxPotenziamenti++;
    }
    private long normaliSparati;
    private long perforantiSparati;
    private long esplosiviSparati;
    private long detonantiSparati;
    private long pietroniSparati;
    private long mineSparate;
    private long mineEsplose;
    private long atomiciSparati;
    private long atomiciEsplosi;

    public void usaBonus()
    {
        if(bonusCorrente!=null)
        {
            if(!bonusCorrente.usa())
            {
                potenziamenti.remove(bonusCorrente);
                bonusCorrente=null;
            }
        }
    }
    private List<Bonus> potenziamenti;
    private Bonus bonusCorrente;
    private int maxPotenziamenti;
    public void setBonusCorrente(Bonus b)
    {
        bonusCorrente=b;
    }
    public Bonus getBonusCorrente()
    {
        return bonusCorrente;
    }

    public int numPotenziamenti()
    {
        return potenziamenti.size();
    }

    public Bonus getPotenziamento(int i)
    {
        try
        {
            return potenziamenti.get(i);
        }
        catch (IndexOutOfBoundsException e)
        {
            return null;
        }
    }
    public void addPotenziamento(Bonus b)
    {
        if(!potenziamenti.contains(b))
        {
            potenziamenti.add(b);
        }
    }
    public void addMunizioni(Munizioni m)
    {
        if(m==null)
        {
            return;
        }

        for (int i=0;i<munizioni.size();i++)
        {
            if(m.equals(munizioni.get(i)))
            {
                munizioni.get(i).aggiungi(m.getNumero());
                return;
            }
        }
        munizioni.add(m);
    }
    private int punti;
    private long puntiTotali;
    private List<Munizioni> munizioni;
    public InventarioMunizioni()
    {
        maxPotenziamenti=1;
        munizioni=new ArrayList<Munizioni>();
        potenziamenti=new ArrayList<Bonus>();
        bonusCorrente=null;
        normaliSparati=0;
        perforantiSparati=0;
        esplosiviSparati=0;
        detonantiSparati=0;
        pietroniSparati=0;
        mineSparate=0;
        mineEsplose=0;
        atomiciSparati=0;
        atomiciEsplosi=0;
        palleScoppiate=0;
        palleFogliateScoppiate=0;
        palleSpinateScoppiate=0;
        palleInfuocateScoppiate=0;
        palleGranchioScoppiate=0;
        palleCyberScoppiate=0;
        palleGranchioScoppiate=0;
        palleApeScoppiate=0;
        palleFantasmaScoppiate=0;
        puntiTotali=0;
        punti=0;
    }
    public boolean eccedente()
    {
        return potenziamenti.size()>maxPotenziamenti;
    }
    public void gettaBonus(int i)
    {
        if(bonusCorrente==potenziamenti.get(i))
        {
            bonusCorrente=null;
        }
        potenziamenti.remove(i);
    }
    public void gettaBonus(Bonus b)
    {

        if(bonusCorrente==b)
        {
            bonusCorrente=null;
        }
        potenziamenti.remove(b);
    }
    public void scambia(Bonus b1,Bonus b2)
    {
        int n1=potenziamenti.indexOf(b1);
        int n2=potenziamenti.indexOf(b2);
        Collections.swap(potenziamenti,n1,n2);
    }
    public int getMaxPotenziamenti()
    {
        return maxPotenziamenti;
    }

    public void creaMunizioniBase()
    {
        munizioni.add(new ColpiNormali(10));
        munizioni.add(new ColpiPerforanti(10));
        /*
        munizioni.add(new ColpiEsplosivi(10));
        munizioni.add(new ColpiDetonanti(10));
        munizioni.add(new ColpiPietroni(10));
        munizioni.add(new ColpiMine(10));
        munizioni.add(new ColpiAtomici(10));
        */
    }
    public long getPuntiTotali()
    {
        return puntiTotali;
    }
    public void addPunti(int k)
    {
        punti+=k;
        puntiTotali+=k;
    }
    public boolean getPunti(int g)
    {
        if(punti<g)
        {
            return false;
        }
        punti-=g;
        return true;
    }
    public Bitmap immaginePallaSmall(int i)
    {
        return munizioni.get(i).immaginePallaSmall();
    }

    public Bitmap immaginePalla(int i)
    {
        return munizioni.get(i).immaginePalla();
    }
    public Bitmap immaginePalla(int i,int l)
    {
        return munizioni.get(i).immaginePalla(l);
    }

    public int totPunti()
    {
        return punti;
    }
    public Munizioni[] getMunizioni()
    {
        return munizioni.toArray(new Munizioni[munizioni.size()]);
    }


    public Palla usaMunizioni(int i, Ambiente a, float x, float y, double ang, int liv)
    {
        Munizioni m=munizioni.get(i);
        Palla p=null;
        if(m!=null)
        {

            p=m.getPalla(a,x,y, ang, liv);
            if(m.getNumero()==0)
            {
                munizioni.remove(m);
            }
        }
        return p;
    }


    public Palla usaMunizioni(Munizioni m,Ambiente a,float x,float y,double ang,int liv)
    {
        if((m==null)||!munizioni.contains(m))
        {
            return null;
        }
        Palla p=null;
        if(m!=null)
        {

            p=m.getPalla(a,x,y, ang, liv);
            if(m.getNumero()==0)
            {
                munizioni.remove(m);
            }
        }
        return p;
    }

    public int getNumeroMunizioni()
    {
        return munizioni.size();
    }
    public String getNomeMunizioni(int i)
    {
        return munizioni.get(i).toString();
    }
    public int getNumero()
    {
        int n=0;
        for(int i=0;i<munizioni.size();i++)
        {
            n+=munizioni.get(i).getNumero();
        }
        return n;
    }
    public int getNumero(int i)
    {
        return munizioni.get(i).getNumero();
    }







}
