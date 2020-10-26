package it.brun.dario.ballpopper.exceptions;

/**
 * Created by dario on 10/04/17.
 */
public class EsplosaException extends InterruptedException
{

    private boolean esplosa;
    public EsplosaException(boolean b)
    {

        super();
        esplosa=b;

    }
    public boolean esplosa()
    {
        return esplosa;
    }



}
