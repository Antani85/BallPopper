package it.brun.dario.ballpopper.levels;

import it.brun.dario.ballpopper.ballpopper.balls.Bomba;
import it.brun.dario.ballpopper.ballpopper.balls.BombaFantasma;
import it.brun.dario.ballpopper.ballpopper.balls.BombaVeloce;
import it.brun.dario.ballpopper.ballpopper.InventarioMunizioni;
import it.brun.dario.ballpopper.activities.MainActivity;
import it.brun.dario.ballpopper.views.Ambiente;

/**
 * Created by dario on 16/05/17.
 */
public class LivelloVentuno extends Livello
{
    public void creaLanciate(Ambiente a)
    {

        Bomba b=new BombaVeloce(null,0,0,0,0,0);

        Bomba b1=new BombaFantasma(null,0,0,0,0,0);
        resettaLanciate();


        for(int i=0;i<5;i++)
        {
            addLanciata(generaBomba(a,b,Ambiente.getPuntoUscita(i%8)));
        }
        addLanciata(generaBomba(a,b1,Ambiente.getPuntoUscita(5)));
        for(int i=5;i<10;i++)
        {
            addLanciata(generaBomba(a,b,Ambiente.getPuntoUscita(i%8)));
        }
        addLanciata(generaBomba(a,b1,Ambiente.getPuntoUscita(2)));
        for(int i=10;i<15;i++)
        {
            addLanciata(generaBomba(a,b,Ambiente.getPuntoUscita(i%8)));
        }
        addLanciata(generaBomba(a,b1,Ambiente.getPuntoUscita(7)));
        for(int i=15;i<20;i++)
        {
            addLanciata(generaBomba(a,b,Ambiente.getPuntoUscita(i%8)));
        }
        addLanciata(generaBomba(a,b1,Ambiente.getPuntoUscita(4)));
        addLanciata(generaBomba(a,b1,Ambiente.getPuntoUscita(5)));






    }


    public LivelloVentuno(MainActivity act, InventarioMunizioni invmun)
    {
        super(act,21,invmun,600,120000);
        setPuntiBonus(15);
    }




}
