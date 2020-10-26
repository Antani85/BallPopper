package it.brun.dario.ballpopper.ballpopper.trophies;

import android.app.Activity;

import it.brun.dario.ballpopper.R;
import it.brun.dario.ballpopper.ballpopper.bullets.Palla;
import it.brun.dario.ballpopper.ballpopper.bullets.PallaPerforante;
import it.brun.dario.ballpopper.levels.Livello;

/**
 * Created by dario on 13/05/17.
 */
public class TrofeoX10Perforante extends Trofeo
{
    public static final String LACODIFICA="AGEP0X";

    public TrofeoX10Perforante(Activity act)
    {
        super(act.getResources().getString(R.string.trofeox10perforante),act.getResources().getString(R.string.trofeox10perforantedescription));
        setCODIFICA_TROFEO(LACODIFICA);

    }

    @Override
    public boolean condizione(Palla palla, Livello lev)
    {
        if(palla==null)
        {
            return false;
        }
        return ((palla instanceof PallaPerforante)&&palla.getNumScoppiate()>=10);
    }
}
