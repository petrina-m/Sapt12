package com;

import java.util.Locale;

public class SetLocale {

    private static Locale  locale;

    static public void run(String reg) {
       /* locale=Locale.getDefault();
        Locale.setDefault(locale);

        */
        locale=new Locale(reg);
        Locale.setDefault(locale);
    }


}
