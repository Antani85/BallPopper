package it.brun.dario.ballpopper.levels;

import it.brun.dario.ballpopper.ballpopper.balls.Bomba;
import it.brun.dario.ballpopper.ballpopper.balls.Bombolina;
import it.brun.dario.ballpopper.ballpopper.bonus.BonusPIU3;
import it.brun.dario.ballpopper.ballpopper.bonus.BonusPIU4;
import it.brun.dario.ballpopper.ballpopper.bonus.BonusPIU5;
import it.brun.dario.ballpopper.ballpopper.InventarioMunizioni;
import it.brun.dario.ballpopper.activities.MainActivity;
import it.brun.dario.ballpopper.views.Ambiente;

/**
 * Created by dario on 10/04/17.
 */
public class LivelloSette extends Livello
{

    public void creaLanciate(Ambiente a)
    {
        Bomba b=new Bomba(null,0,0,0,0,0);
        Bombolina gb=new Bombolina(null,0,0,0,0,0);
        Bomba b1=new Bomba(null,0,0,0,0,0,new BonusPIU3());

        Bomba b2=new Bomba(null,0,0,0,0,0,new BonusPIU4());
        Bomba b3=new Bomba(null,0,0,0,0,0,new BonusPIU5());



        resettaLanciate();

        for(int i=0;i<50;i++)
        {
            addLanciata(generaBomba(a,b,Ambiente.getPuntoUscita(i%8)));
            if(i%4==0)
            {
                addLanciata(generaBomba(a, gb, Ambiente.getPuntoUscita((i+4) % 8 )));
            }
        }
        double bb=Math.random();
        if(bb<0.3)
        {
            addLanciata(generaBomba(a, b1, Ambiente.getPuntoUscita(1)));
        }
        else if(bb<0.6)
        {
            addLanciata(generaBomba(a, b2, Ambiente.getPuntoUscita(1)));

        }
        else
        {
            addLanciata(generaBomba(a, b3, Ambiente.getPuntoUscita(1)));

        }
        for(int i=0;i<50;i++)
        {
            addLanciata(generaBomba(a,b,Ambiente.getPuntoUscita(i%8)));
            if(i%4==0)
            {
                addLanciata(generaBomba(a, gb, Ambiente.getPuntoUscita((i+4) % 8)));
            }
        }

    }


    public LivelloSette(MainActivity act, InventarioMunizioni invmun)
    {
        super(act,7,invmun,500,180000);
        setPuntiBonus(3);
    }




}
