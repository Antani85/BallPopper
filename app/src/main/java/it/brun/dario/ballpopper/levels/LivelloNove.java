package it.brun.dario.ballpopper.levels;

import it.brun.dario.ballpopper.ballpopper.balls.BombaInfuocata;
import it.brun.dario.ballpopper.ballpopper.balls.Bombolone;
import it.brun.dario.ballpopper.ballpopper.InventarioMunizioni;
import it.brun.dario.ballpopper.activities.MainActivity;
import it.brun.dario.ballpopper.views.Ambiente;

/**
 * Created by dario on 24/04/17.
 */
public class LivelloNove extends Livello
{
    public void creaLanciate(Ambiente a)
    {
        Bombolone b=new Bombolone(null,0,0,0,0,0);

        BombaInfuocata gb=new BombaInfuocata(null,0,0,0,0,0);

        resettaLanciate();

        for(int i=0;i<5;i++)
        {
            addLanciata(generaBomba(a,b,Ambiente.getPuntoUscita(i%8)));
        }
        addLanciata(generaBomba(a,gb,Ambiente.getPuntoUscita(6)));
        for(int i=0;i<5;i++)
        {
            addLanciata(generaBomba(a,b,Ambiente.getPuntoUscita(i%8)));
        }
    }


    public LivelloNove(MainActivity act, InventarioMunizioni invmun)
    {
        super(act,9,invmun,1000,120000);
        setPuntiBonus(6);
    }




}
