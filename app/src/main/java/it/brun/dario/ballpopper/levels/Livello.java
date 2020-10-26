package it.brun.dario.ballpopper.levels;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.List;

import it.brun.dario.ballpopper.ballpopper.balls.Bomba;
import it.brun.dario.ballpopper.ballpopper.InventarioMunizioni;
import it.brun.dario.ballpopper.activities.MainActivity;
import it.brun.dario.ballpopper.views.Ambiente;

/**
 * Created by dario on 10/04/17.
 */
public abstract  class Livello implements  Runnable
{

    private long tempo;
    private int stato;
    private static String COMPLETATO;
    private List<Bomba> lanciate;
    public void resettaLanciate()
    {
        lanciate=new ArrayList<Bomba>();

    }
    public int numLanciate()
    {
        return lanciate.size();
    }
    public Bomba getLanciata(int i)
    {
        return lanciate.get(i);
    }
    public void addLanciata(Bomba b)
    {
        lanciate.add(b);
    }
    public static void setCOMPLETATO(String s)
    {
        COMPLETATO=s;
    }
    public static String getCOMPLETATO()
    {
        return COMPLETATO;

    }
    public void run()
    {
        spara();
        contenente.setTerminato();
    }
    public static Livello getLivello(int num, MainActivity act, InventarioMunizioni inv)
    {

        switch (num)
        {
            case 1:  return new LivelloUno(act,inv);

            case 2: return new LivelloDue(act,inv);


            case 3: return new LivelloTre(act,inv);


            case 4: return new LivelloQuattro(act,inv);


            case 5: return new LivelloCinque(act,inv);


            case 6: return new LivelloSei(act,inv);


            case 7: return new LivelloSette(act,inv);


            case 8: return new LivelloOtto(act,inv);


            case 9: return new LivelloNove(act,inv);

            case 10: return new LivelloDieci(act,inv);

            case 11: return new LivelloUndici(act,inv);

            case 12: return new LivelloDodici(act,inv);

            case 13: return new LivelloTredici(act,inv);

            case 14: return new LivelloQuattordici(act,inv);

            case 15: return new LivelloQuindici(act,inv);

            case 16: return new LivelloSedici(act,inv);

            case 17: return new LivelloDiciassette(act,inv);

            case 18: return new LivelloDiciotto(act,inv);

            case 19: return new LivelloDiciannove(act,inv);

            case 20: return new LivelloVenti(act,inv);

            case 21: return new LivelloVentuno(act,inv);




            default: return null;



        }


    }

    public Bomba generaBomba(Ambiente a, Bomba b, float[] punto)
    {
        int cc= Color.argb(255, (int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255));
        float ix=(float)((Math.random()*Bomba.getINCREMENTATORE_MAX())+Bomba.getINCREMENTATORE_MIN())*punto[2];
        float iy=(float)((Math.random()*Bomba.getINCREMENTATORE_MAX())+Bomba.getINCREMENTATORE_MIN())*punto[3];

        Bomba bom=b.getBomba(a,ix,iy,cc,punto[0],punto[1]);


        return bom;

    }
    private boolean completato;
    public void completa()
    {
        completato=true;

    }
    public boolean getCompletato()
    {
        return completato;
    }
    private int puntiBonus;

    public void setPuntiBonus(int p)
    {
        puntiBonus=p;
    }
    public int getPuntiBonus()
    {
        return puntiBonus;
    }
    public InventarioMunizioni getInventarioMunizioni()
    {
        return inventario;
    }





    protected MainActivity contenente;
    private InventarioMunizioni inventario;
    private int numero;


    public  void spara()
    {



        try
        {

            int i=getStato();
            for(;i<numLanciate()-1;i++)
            {



                contenente.lancia(getLanciata(i));

                setStato(i+1);
                Thread.sleep(pausa);

            }
            contenente.lancia(getLanciata(i));

            setStato(i+1);


        }
        catch(Exception ex)
        {
            return;
        }
    }



    public Livello(MainActivity act,int numLivello,InventarioMunizioni invmun,long p,long t)
    {
        contenente=act;
        stato=0;
        numero=numLivello;
        completato=false;

        inventario=invmun;
        pausa=p;
        tempo=t;

    }
    public long getTempo()
    {
        return tempo;
    }
    private long pausa;
    public void setStato(int i)
    {
        stato=i;
    }
    public int getStato()
    {
        return stato;
    }
    public abstract void creaLanciate(Ambiente a);
    public void setContenente(MainActivity f)
    {
        contenente=f;
    }

    public Thread getLancio()
    {
        return new Thread(this);
    }
    public int getNumero()
    {
        return numero;
    }

    public String nomeLivello()
    {
        return "Livello "+numero;

    }

    public String toString()
    {
      return "Livello "+numero;

    }

}
