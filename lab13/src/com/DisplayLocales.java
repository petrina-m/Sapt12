package com;

import java.util.Locale;

public class DisplayLocales {

    static public void run() {
        Locale[] locales = Locale.getAvailableLocales();
        for (Locale locale : locales)
        {
            System.out.println(locale.getCountry());
        }
    }
}
