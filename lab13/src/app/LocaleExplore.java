package app;

import com.DisplayLocales;
import com.Info;
import com.SetLocale;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class LocaleExplore {
    public static void main(String[] args) {
        Locale locale = new Locale("en", "US");
        Locale locale2 = new Locale ("ro", "RO");

        while(true) {
            String baseName = "res.Messages";
            ResourceBundle messages =
                    ResourceBundle.getBundle(baseName, locale);
            System.out.println(messages.getString("prompt"));
            Scanner scanner = new Scanner(System.in);
            String inputString = scanner.nextLine();
            ExecuteCommand(inputString);
        }

    }
    static void ExecuteCommand(String command)
    {  Locale locale=Locale.getDefault();
        String baseName = "res.Messages";
        ResourceBundle messages =
                ResourceBundle.getBundle(baseName, locale);

        int displayIndex=command.indexOf("display");
        int setIndex=command.indexOf("set");
        int infoIndex=command.indexOf("info");

        if (displayIndex!=-1)
        {
            System.out.println(messages.getString("locales"));
            DisplayLocales.run();
        }
         else if(setIndex!=-1)
        {
            String[] words=command.split(" ");
            SetLocale.run(words[1]);
            locale = Locale.getDefault();
            System.out.println(MessageFormat.format(messages.getString("locale.set"), locale.getLanguage()));
        }
        else if(infoIndex!=-1)
        {
            System.out.println(MessageFormat.format(messages.getString("info"), locale.getLanguage()));
            Info.run();
        }
        else
        {
            System.out.println(messages.getString("invalid"));
        }
    }

}
