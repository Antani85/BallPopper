package it.brun.dario.ballpopper;

import android.util.Log;

import java.util.Calendar;

/**
 * Created by dario on 27/04/17.
 */
public class Tempo
{
    public String codificaTempo()
    {
        StringBuilder builder=new StringBuilder();
        builder.append((char)(minute+65));
        builder.append((char)(second+65));
        builder.append((char)(hour+65));
        builder.append((char)(month+65));
        builder.append((char)(day+65));
        builder.append((char)(year-2000+65));
        return builder.toString();
    }
    public static Tempo getTempoFromCodifica(String s)
    {
        int minute=((int)s.charAt(0))-65;
        int second=((int)s.charAt(1))-65;
        int hour=((int)s.charAt(2))-65;
        int month=((int)s.charAt(3))-65;
        int day=((int)s.charAt(4))-65;
        int year=((int)s.charAt(5))-65+2000;
        return new Tempo(hour,minute,second,day,month,year);
    }
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private int second;

    public Tempo(int h,int mi,int s,int d,int mo,int y)
    {
        hour=h;
        minute=mi;
        second=s;
        day=d;
        month=mo;
        year=y;
    }

    public Tempo(Calendar calendar)
    {
        this(calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),calendar.get(Calendar.SECOND),calendar.get(Calendar.DAY_OF_MONTH),calendar.get(Calendar.MONTH)+1,calendar.get(Calendar.YEAR));
    }
    public String getData()
    {
        String D=""+day;
        if(day<10)
        {
            D="0"+day;
        }
        String M=""+month;
        if(month<10)
        {
            M="0"+month;
        }
        return D+"/"+M+"/"+year;
    }
    public String getTempo()
    {
        String H=""+hour;
        if(hour<10)
        {
            H="0"+hour;
        }
        String M=""+minute;
        if(minute<10)
        {
            M="0"+minute;
        }
        String S=""+second;
        if(second<10)
        {
            S="0"+second;
        }
        return H+":"+M+":"+S;
    }
    public String toString()
    {
        return getData()+" "+getTempo();
    }

    public boolean equals(Object o)
    {
        if(!(o instanceof Tempo))
        {
            return false;
        }
        Tempo t=(Tempo)o;
        return hour==t.hour&&minute==t.minute&&second==t.second&&day==t.day&&month==t.month&&year==t.year;
    }
}
