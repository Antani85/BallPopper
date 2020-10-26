package it.brun.dario.ballpopper.levels;

import it.brun.dario.ballpopper.ballpopper.balls.Bomba;
import it.brun.dario.ballpopper.ballpopper.bonus.BonusPIU2;
import it.brun.dario.ballpopper.ballpopper.bonus.BonusPIU3;
import it.brun.dario.ballpopper.ballpopper.InventarioMunizioni;
import it.brun.dario.ballpopper.activities.MainActivity;
import it.brun.dario.ballpopper.views.Ambiente;

/**
 * Created by dario on 10/04/17.
 */
public class LivelloCinque extends Livello
{
    public void creaLanciate(Ambiente a)
    {
        Bomba b=new Bomba(null,0,0,0,0,0);
        Bomba b1=new Bomba(null,0,0,0,0,0,new BonusPIU2());
        Bomba b2=new Bomba(null,0,0,0,0,0,new BonusPIU3());

        resettaLanciate();
        for(int i=0;i<40;i++)
        {
            addLanciata(generaBomba(a,b,Ambiente.getPuntoUscita(i%8)));
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
            addLanciata(generaBomba(a, b, Ambiente.getPuntoUscita(1)));

        }
        for(int i=0;i<39;i++)
        {
            addLanciata(generaBomba(a,b,Ambiente.getPuntoUscita(i%8)));
        }

    }


    public LivelloCinque(MainActivity act, InventarioMunizioni invmun)
    {
        super(act,5,invmun,500,120000);
        setPuntiBonus(2);
    }



}
