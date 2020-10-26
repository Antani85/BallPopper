package it.brun.dario.ballpopper.levels;

import it.brun.dario.ballpopper.ballpopper.balls.Bomba;
import it.brun.dario.ballpopper.ballpopper.balls.CyberBomba;
import it.brun.dario.ballpopper.ballpopper.InventarioMunizioni;
import it.brun.dario.ballpopper.ballpopper.balls.MiniBomba;
import it.brun.dario.ballpopper.activities.MainActivity;
import it.brun.dario.ballpopper.views.Ambiente;

/**
 * Created by dario on 14/05/17.
 */
public class LivelloQuindici extends Livello
{
    public void creaLanciate(Ambiente a)
    {

        Bomba b=new MiniBomba(null,0,0,0,0,0);
        Bomba b1=new CyberBomba(null,0,0,0,0,0);


        resettaLanciate();

        for(int i=0;i<100;i++)
        {
            if(i%8==0)
            {
                addLanciata(generaBomba(a, b1, Ambiente.getPuntoUscita(i%7)));

            }
            else
            {
                addLanciata(generaBomba(a, b, Ambiente.getPuntoUscita(i%8)));

            }

        }


    }


    public LivelloQuindici(MainActivity act, InventarioMunizioni invmun)
    {
        super(act,15,invmun,600,180000);
        setPuntiBonus(10);
    }




}
