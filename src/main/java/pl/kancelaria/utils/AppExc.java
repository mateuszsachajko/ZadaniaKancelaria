package pl.kancelaria.utils;

import org.omg.CORBA.portable.ApplicationException;

public class AppExc extends Exception {
    public AppExc(String mes){
        super(mes);
    }

}
