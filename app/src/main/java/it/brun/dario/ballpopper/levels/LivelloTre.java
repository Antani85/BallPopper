package it.brun.dario.ballpopper.levels;

import it.brun.dario.ballpopper.ballpopper.balls.Bomba;
import it.brun.dario.ballpopper.ballpopper.balls.BombaFogliata;
import it.brun.dario.ballpopper.ballpopper.InventarioMunizioni;
import it.brun.dario.ballpopper.activities.MainActivity;
import it.brun.dario.ballpopper.views.Ambiente;

/**
 * Created by dario on 10/04/17.
 */
public class LivelloTre extends Livello
{

    public void creaLanciate(Ambiente a)
    {
        Bomba b=new Bomba(null,0,0,0,0,0);

        Bomba b1=new BombaFogliata(null,0,0,0,0,0);
        resettaLanciate();
        for(int i=0;i<10;i++)
        {
            addLanciata(generaBomba(a,b,Ambiente.getPuntoUscita(i%3+5)));
        }
        addLanciata(generaBomba(a,b1,Ambiente.getPuntoUscita(1)));
        for(int i=0;i<10;i++)
        {
            addLanciata(generaBomba(a,b,Ambiente.getPuntoUscita(i%3+5)));
        }
        addLanciata(generaBomba(a,b1,Ambiente.getPuntoUscita(1)));
        for(int i=0;i<10;i++)
        {
            addLanciata(generaBomba(a,b,Ambiente.getPuntoUscita(i%3+5)));
        }
        addLanciata(generaBomba(a,b1,Ambiente.getPuntoUscita(1)));
    }


    public LivelloTre(MainActivity act, InventarioMunizioni invmun)
    {
        super(act,3,invmun,1000,150000);
        setPuntiBonus(2);
    }



}
