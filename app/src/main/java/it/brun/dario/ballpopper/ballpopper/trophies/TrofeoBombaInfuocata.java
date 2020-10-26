package it.brun.dario.ballpopper.ballpopper.trophies;

import android.app.Activity;

import it.brun.dario.ballpopper.R;
import it.brun.dario.ballpopper.ballpopper.balls.BombaInfuocata;
import it.brun.dario.ballpopper.ballpopper.bullets.Palla;
import it.brun.dario.ballpopper.levels.Livello;

/**
 * Created by dario on 28/04/17.
 */
public class TrofeoBombaInfuocata extends Trofeo
{
    public static final String LACODIFICA="AGATNI";

    public TrofeoBombaInfuocata(Activity act)
    {
        super(act.getResources().getString(R.string.trofeobombainfuocata),act.getResources().getString(R.string.trofeobombainfuocatadescription));
        setCODIFICA_TROFEO(LACODIFICA);

    }

    @Override
    public boolean condizione(Palla palla, Livello lev)
    {
        if(palla==null)
        {
            return false;
        }
        for(int i=0;i<palla.getNumColpite();i++)
        {
            if(palla.getColpita(i).esplosa()&&palla.getColpita(i).getClass().getName().equals(BombaInfuocata.class.getName()))
            {
                return true;
            }
        }
        return false;
    }
}
