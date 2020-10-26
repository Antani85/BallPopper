package it.brun.dario.ballpopper.levels;

import it.brun.dario.ballpopper.ballpopper.balls.Bomba;
import it.brun.dario.ballpopper.ballpopper.balls.Bombolina;
import it.brun.dario.ballpopper.ballpopper.balls.Bombolone;
import it.brun.dario.ballpopper.ballpopper.bonus.BonusPIU3;
import it.brun.dario.ballpopper.ballpopper.bonus.BonusPIU5;
import it.brun.dario.ballpopper.ballpopper.InventarioMunizioni;
import it.brun.dario.ballpopper.activities.MainActivity;
import it.brun.dario.ballpopper.views.Ambiente;

/**
 * Created by dario on 13/05/17.
 */
public class LivelloUndici extends Livello
{
    public void creaLanciate(Ambiente a)
    {
        Bombolone b1=new Bombolone(null,0,0,0,0,0);
        Bomba b=new Bomba(null,0,0,0,0,0);

        Bombolina b0=new Bombolina(null,0,0,0,0,0);
        Bomba b2=new Bomba(null,0,0,0,0,0,new BonusPIU3());
        Bomba b3=new Bomba(null,0,0,0,0,0,new BonusPIU5());

        resettaLanciate();

        for(int i=0;i<100;i++)
        {
            if(i%5==0)
            {
                addLanciata(generaBomba(a, b1, Ambiente.getPuntoUscita(i%8)));

            }
            else if (i%5==1)
            {
                addLanciata(generaBomba(a, b0, Ambiente.getPuntoUscita(i%8)));

            }
            else
            {
                addLanciata(generaBomba(a, b, Ambiente.getPuntoUscita(i%8)));

            }

        }

        double d=Math.random();
        if(d<0.3)
        {
            addLanciata(generaBomba(a, b3, Ambiente.getPuntoUscita(4)));
        }
        else
        {
            addLanciata(generaBomba(a, b2, Ambiente.getPuntoUscita(4)));
        }
        for(int i=0;i<99;i++)
        {
            if(i%5==0)
            {
                addLanciata(generaBomba(a, b1, Ambiente.getPuntoUscita(i%8)));

            }
            else if (i%5==1)
            {
                addLanciata(generaBomba(a, b0, Ambiente.getPuntoUscita(i%8)));

            }
            else
            {
                addLanciata(generaBomba(a, b, Ambiente.getPuntoUscita(i%8)));

            }

        }

    }


    public LivelloUndici(MainActivity act, InventarioMunizioni invmun)
    {
        super(act,11,invmun,350,180000);
        setPuntiBonus(4);
    }




}
