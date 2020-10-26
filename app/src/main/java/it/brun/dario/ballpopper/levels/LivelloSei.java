package it.brun.dario.ballpopper.levels;

import it.brun.dario.ballpopper.ballpopper.balls.Bomba;
import it.brun.dario.ballpopper.ballpopper.balls.BombaSpinata;
import it.brun.dario.ballpopper.ballpopper.InventarioMunizioni;
import it.brun.dario.ballpopper.activities.MainActivity;
import it.brun.dario.ballpopper.views.Ambiente;

/**
 * Created by dario on 10/04/17.
 */
public class LivelloSei extends Livello
{
    public void creaLanciate(Ambiente a)
    {
        Bomba b=new Bomba(null,0,0,0,0,0);
        Bomba mb=new BombaSpinata(null,0,0,0,0,0);
        resettaLanciate();

        for(int i=0;i<10;i++)
        {
            addLanciata(generaBomba(a,b,Ambiente.getPuntoUscita(i%8)));
        }
        addLanciata(generaBomba(a,mb,Ambiente.getPuntoUscita(6)));
        for(int i=0;i<10;i++)
        {
            addLanciata(generaBomba(a,b,Ambiente.getPuntoUscita(i%8)));
        }
        addLanciata(generaBomba(a,mb,Ambiente.getPuntoUscita(1)));
    }


    public LivelloSei(MainActivity act, InventarioMunizioni invmun)
    {
        super(act,6,invmun,600,120000);
        setPuntiBonus(4);
    }





}
