package it.brun.dario.ballpopper.ballpopper.trophies;

import android.app.Activity;

import it.brun.dario.ballpopper.R;
import it.brun.dario.ballpopper.ballpopper.bullets.Palla;
import it.brun.dario.ballpopper.ballpopper.bullets.PallaEsplosiva;
import it.brun.dario.ballpopper.levels.Livello;

/**
 * Created by dario on 26/04/17.
 */
public class TrofeoX2 extends Trofeo
{
    public static final String LACODIFICA="AGOE2X";

    public TrofeoX2(Activity act)
    {
        super(act.getResources().getString(R.string.trofeox2),act.getResources().getString(R.string.trofeox2description));
        setCODIFICA_TROFEO(LACODIFICA);

    }

    @Override
    public boolean condizione(Palla palla, Livello lev)
    {
        if(palla==null)
        {
            return false;
        }
        return ((palla instanceof PallaEsplosiva)&&palla.getNumScoppiate()>=7);
    }
}
