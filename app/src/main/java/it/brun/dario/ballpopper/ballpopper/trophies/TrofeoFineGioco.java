package it.brun.dario.ballpopper.ballpopper.trophies;

import android.app.Activity;

import it.brun.dario.ballpopper.R;
import it.brun.dario.ballpopper.ballpopper.Giocatore;
import it.brun.dario.ballpopper.ballpopper.bullets.Palla;
import it.brun.dario.ballpopper.levels.Livello;

/**
 * Created by dario on 16/05/17.
 */
public class TrofeoFineGioco extends Trofeo
{
    public static final String LACODIFICA="AGOCIF";

    public TrofeoFineGioco(Activity act)
    {
        super(act.getResources().getString(R.string.trofeofinegioco),act.getResources().getString(R.string.trofeofinegiocodescription));
        setCODIFICA_TROFEO(LACODIFICA);

    }

    @Override
    public boolean condizione(Palla palla, Livello lev)
    {
        if(lev==null)
        {
            return false;
        }

        for(int i = 0; i<Giocatore.getNumLivelli(); i++)
        {
            if(!Giocatore.getLivello(i).getCompletato())
            {
                return false;
            }
        }
        return true;
    }
}
