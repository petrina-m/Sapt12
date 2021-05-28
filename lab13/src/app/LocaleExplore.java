package app;

import com.DisplayLocales;
import com.Info;
import com.SetLocale;

import java.util.Locale;
import java.util.Scanner;

public class LocaleExplore {
    public static void main(String[] args) {
        Locale locale = new Locale("en", "US");
        Locale locale2 = new Locale ("ro", "RO");

        while(true) {
            Scanner scanner = new Scanner(System.in);
            String inputString = scanner.nextLine();
            ExecuteCommand(inputString);
        }

    }
    static void ExecuteCommand(String command)
    {
        int displayIndex=command.indexOf("display");
        int setIndex=command.indexOf("set");
        int infoIndex=command.indexOf("info");

        if (displayIndex!=-1)
        {
            DisplayLocales.run();
        }
        if(setIndex!=-1)
        {
            String[] words=command.split(" ");
            SetLocale.run(words[1]);
        }
        if(infoIndex!=-1)
        {
            Info.run();
        }
    }

}
