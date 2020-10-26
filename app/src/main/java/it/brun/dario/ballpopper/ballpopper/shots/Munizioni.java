package it.brun.dario.ballpopper.ballpopper.shots;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import it.brun.dario.ballpopper.exceptions.CaricamentoException;
import it.brun.dario.ballpopper.ballpopper.bullets.Palla;
import it.brun.dario.ballpopper.ballpopper.Giocatore;
import it.brun.dario.ballpopper.views.Ambiente;

/**
 * Created by dario on 10/04/17.
 */
public abstract class Munizioni implements Parcelable
{
    private static final int MAX=9999;
    public static Munizioni getMunizioniFromCodifica(String cod)throws CaricamentoException
    {
        try
        {
            String s = cod.substring(0, 9);
            int num = Integer.parseInt(cod.substring(9, cod.length() - 1));
            if (ColpiNormali.CODIFICA.equals(s))
            {
                return new ColpiNormali(num);
            }
            else if (ColpiPerforanti.CODIFICA.equals(s))
            {
                return new ColpiPerforanti(num);
            }
            else if (ColpiEsplosivi.CODIFICA.equals(s))
            {
                return new ColpiEsplosivi(num);
            }
            else if (ColpiDetonanti.CODIFICA.equals(s))
            {
                return new ColpiDetonanti(num);
            }
            else if (ColpiMine.CODIFICA.equals(s))
            {
                return new ColpiMine(num);
            }
            else if (ColpiPietroni.CODIFICA.equals(s))
            {
                return new ColpiPietroni(num);
            }
            else if (ColpiAtomici.CODIFICA.equals(s))
            {
                return new ColpiAtomici(num);
            }
            else
            {
                throw new Exception();
            }
        }
        catch (Exception e)
        {
            throw new CaricamentoException();
        }
    }

    public abstract String codifica();
    private int costo;
    public abstract Palla creaPalla(Ambiente a, float x, float y, double ang, int liv);

    public abstract Bitmap immaginePalla();
    public abstract Bitmap immaginePallaSmall();

    public Bitmap immaginePalla(int lunghezza)
    {
        return Giocatore.getResizedBitmapBis(immaginePalla(),lunghezza,lunghezza);
    }
    public Palla getPalla(Ambiente a,float x,float y,double ang,int liv)
    {

        if(numero<0)
        {

            return creaPalla(a,x,y,ang,liv);
        }
        else if(numero==0)
        {
            return null;
        }
        else
        {
            aggiornaNumero();
            return creaPalla(a,x,y,ang,liv);

        }

    }
    private int numero;
    private String nome;
    public String toString()
    {

        if(numero>=0)
        {

            return( nome+numero);
        }
        else
        {
            return  nome+"infiniti";

        }

    }


    public int getCosto()
    {
        return costo;
    }

    public Munizioni(String n,int nn,int c)
    {

        costo=c;
        nome=n;
        numero=nn;

    }

    public Munizioni(Parcel in)
    {
        nome=in.readString();
        numero=in.readInt();
        costo=in.readInt();
    }


    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(nome);
        dest.writeInt(numero);
        dest.writeInt(costo);
    }


    public int describeContents()
    {
        return 0;
    }





    public void aggiornaNumero()
    {
        if(numero>0)
        {

            numero--;

        }

    }
    public int getNumero()
    {
        return numero;

    }


    public String getNome()
    {

        return nome;
    }
    public void aggiungi(int n)
    {
        numero+=n;
        if(numero<0)
        {
            numero=0;
        }
        if(numero>MAX)
        {
            numero=MAX;
        }
    }

    public boolean equals(Object o)
    {
        return getClass().getName().equals(o.getClass().getName());
    }





}
