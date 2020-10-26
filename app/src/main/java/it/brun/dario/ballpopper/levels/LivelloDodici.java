package it.brun.dario.ballpopper.levels;

import it.brun.dario.ballpopper.ballpopper.balls.Bomba;
import it.brun.dario.ballpopper.ballpopper.balls.BombaGranchiosa;
import it.brun.dario.ballpopper.ballpopper.balls.Bombolina;
import it.brun.dario.ballpopper.ballpopper.InventarioMunizioni;
import it.brun.dario.ballpopper.activities.MainActivity;
import it.brun.dario.ballpopper.views.Ambiente;

/**
 * Created by dario on 13/05/17.
 */
public class LivelloDodici extends Livello
{
    public void creaLanciate(Ambiente a)
    {


        Bomba b=new Bombolina(null,0,0,0,0,0);
        Bomba b1=new BombaGranchiosa(null,0,0,0,0,0);

        resettaLanciate();

        for(int i=0;i<7;i++)
        {
            addLanciata(generaBomba(a,b,Ambiente.getPuntoUscita(i%8)));
        }
        addLanciata(generaBomba(a,b1,Ambiente.getPuntoUscita(6)));
        for(int i=0;i<7;i++)
        {
            addLanciata(generaBomba(a,b,Ambiente.getPuntoUscita(i%8)));
        }
        addLanciata(generaBomba(a,b1,Ambiente.getPuntoUscita(1)));
        for(int i=0;i<7;i++)
        {
            addLanciata(generaBomba(a,b,Ambiente.getPuntoUscita(i%8)));
        }
        addLanciata(generaBomba(a,b1,Ambiente.getPuntoUscita(3)));
    }






    public LivelloDodici(MainActivity act, InventarioMunizioni invmun)
    {
        super(act,12,invmun,600,150000);
        setPuntiBonus(8);
    }




}
