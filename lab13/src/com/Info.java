package com;


import java.util.Locale;

public class Info {

    static  public void run() {
        Locale currentLocale = Locale.getDefault();

        System.out.println(currentLocale.getDisplayCountry());

        System.out.println(currentLocale.getDisplayLanguage());

        System.out.println(currentLocale.getCountry());
        System.out.println(currentLocale.getLanguage());


    }

}
