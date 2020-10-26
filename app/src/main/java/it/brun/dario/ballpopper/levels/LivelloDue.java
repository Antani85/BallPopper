package it.brun.dario.ballpopper.levels;

import it.brun.dario.ballpopper.ballpopper.balls.Bomba;
import it.brun.dario.ballpopper.ballpopper.balls.BombaBonusUp;
import it.brun.dario.ballpopper.ballpopper.Giocatore;
import it.brun.dario.ballpopper.ballpopper.InventarioMunizioni;
import it.brun.dario.ballpopper.activities.MainActivity;
import it.brun.dario.ballpopper.views.Ambiente;

/**
 * Created by dario on 10/04/17.
 */
public class LivelloDue extends Livello
{


    public void creaLanciate(Ambiente a)
    {
        Bomba b=new Bomba(null,0,0,0,0,0);
        Bomba b1=new BombaBonusUp(null,0,0,0,0,0);

        resettaLanciate();
        for(int i=0;i<10;i++)
        {
            addLanciata(generaBomba(a,b,Ambiente.getPuntoUscita(i%3)));
        }
        if(Giocatore.inventario().getMaxPotenziamenti()<2)
        {
            addLanciata(generaBomba(a,b1,Ambiente.getPuntoUscita(3)));

        }
        else
        {
            addLanciata(generaBomba(a,b,Ambiente.getPuntoUscita(3)));

        }
        for(int i=0;i<9;i++)
        {
            addLanciata(generaBomba(a,b,Ambiente.getPuntoUscita(i%3)));
        }
    }


    public LivelloDue(MainActivity act, InventarioMunizioni invmun)
    {
        super(act,2,invmun,1500,120000);
        setPuntiBonus(1);
    }



}
