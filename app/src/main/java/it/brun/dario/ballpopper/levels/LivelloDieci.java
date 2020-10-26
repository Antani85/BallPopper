package it.brun.dario.ballpopper.levels;

import it.brun.dario.ballpopper.ballpopper.balls.Bomba;
import it.brun.dario.ballpopper.ballpopper.balls.Bombolone;
import it.brun.dario.ballpopper.ballpopper.bonus.BonusPIU1;
import it.brun.dario.ballpopper.ballpopper.bonus.BonusPIU4;
import it.brun.dario.ballpopper.ballpopper.bonus.BonusPIU6;
import it.brun.dario.ballpopper.ballpopper.InventarioMunizioni;
import it.brun.dario.ballpopper.activities.MainActivity;
import it.brun.dario.ballpopper.views.Ambiente;

/**
 * Created by dario on 13/05/17.
 */
public class LivelloDieci extends Livello
{
    public void creaLanciate(Ambiente a)
    {
        Bombolone b1=new Bombolone(null,0,0,0,0,0);

        Bomba b=new Bomba(null,0,0,0,0,0);
        Bomba b2=new Bomba(null,0,0,0,0,0,new BonusPIU1());
        Bomba b3=new Bomba(null,0,0,0,0,0,new BonusPIU4());

        Bomba b4=new Bomba(null,0,0,0,0,0,new BonusPIU6());

        resettaLanciate();

        for(int i=0;i<200;i++)
        {
            if(i%5==0)
            {
                addLanciata(generaBomba(a, b1, Ambiente.getPuntoUscita(i%8)));

            }
            else
            {
                addLanciata(generaBomba(a, b, Ambiente.getPuntoUscita(i%8)));

            }

        }

        double d=Math.random();
        if(d<0.25)
        {
            addLanciata(generaBomba(a, b4, Ambiente.getPuntoUscita(4)));
        }
        else if(d<0.75)
        {
            addLanciata(generaBomba(a, b2, Ambiente.getPuntoUscita(4)));
        }
        else
        {
            addLanciata(generaBomba(a, b3, Ambiente.getPuntoUscita(4)));
        }
        for(int i=0;i<20;i++)
        {
            addLanciata(generaBomba(a, b, Ambiente.getPuntoUscita(i%8)));


        }

    }


    public LivelloDieci(MainActivity act, InventarioMunizioni invmun)
    {
        super(act,10,invmun,500,180000);
        setPuntiBonus(4);
    }




}
