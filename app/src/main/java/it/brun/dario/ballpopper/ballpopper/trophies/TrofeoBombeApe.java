package it.brun.dario.ballpopper.ballpopper.trophies;

import android.app.Activity;

import it.brun.dario.ballpopper.R;
import it.brun.dario.ballpopper.ballpopper.Giocatore;
import it.brun.dario.ballpopper.ballpopper.bullets.Palla;
import it.brun.dario.ballpopper.levels.Livello;

/**
 * Created by dario on 15/05/17.
 */
public class TrofeoBombeApe  extends Trofeo
{
    public static final String LACODIFICA="AGEPOB";

    public TrofeoBombeApe(Activity act)
    {
        super(act.getResources().getString(R.string.trofeobombeape),act.getResources().getString(R.string.trofeobombeapedescription));
        setCODIFICA_TROFEO(LACODIFICA);

    }

    @Override
    public boolean condizione(Palla palla, Livello lev)
    {

        if(palla==null)
        {
            return false;
        }

        return Giocatore.inventario().getPalleApeScoppiate()>=100;
    }
}
