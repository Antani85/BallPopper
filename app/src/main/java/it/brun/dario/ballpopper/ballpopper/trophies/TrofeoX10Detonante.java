package it.brun.dario.ballpopper.ballpopper.trophies;

import android.app.Activity;

import it.brun.dario.ballpopper.R;
import it.brun.dario.ballpopper.ballpopper.bullets.Palla;
import it.brun.dario.ballpopper.ballpopper.bullets.PallaDetonante;
import it.brun.dario.ballpopper.levels.Livello;

/**
 * Created by dario on 27/04/17.
 */
public class TrofeoX10Detonante extends Trofeo
{
    public static final String LACODIFICA="AGET01";

    public TrofeoX10Detonante(Activity act)
    {
        super(act.getResources().getString(R.string.trofeox10detonante),act.getResources().getString(R.string.trofeox10detonantedescription));
        setCODIFICA_TROFEO(LACODIFICA);

    }

    @Override
    public boolean condizione(Palla palla, Livello lev)
    {
        if(palla==null)
        {
            return false;
        }
        return ((palla instanceof PallaDetonante)&&palla.getNumScoppiate()>=10);
    }
}
