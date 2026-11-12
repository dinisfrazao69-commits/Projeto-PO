package erp.core;

import java.util.Locale;;

class Format{
    static String money(double value){
        return String.format(Locale.US, "%.2f", value);
    } 
}