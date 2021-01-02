import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class ParserTime
{
    public static Document getPage(String city) throws IOException
    {
        String url = "https://time100.ru/" + city;
        Document page = Jsoup.parse(new URL(url), 2000);
        return page;
    }

    public static String getCity()
    {
        String[] citys = new String[] {"London", "Tokyo", "New-York", "Hong-Kong", "Sydney", "Tel-Aviv", "Kyiv",
                                        "Moscow", "Saint-Petersburg", "Nizhny-Novgorod", "Yekaterinburg", "Saratov",
                                        "Novosibirsk", "Vladivostok", "Omsk", "Volgograd", "Kaliningrad", "Krasnoyarsk",
                                        "Magadan", "Samara", "Irkutsk", "Kamchatka", "Stavropol"};

        System.out.println("Select number of city where you want to know the time:");
        for (int i=0; i < citys.length; i++)
        {
            System.out.println(i+1 + " - " + citys[i]);
        }

        System.out.println("");
        int num = inputNumber(1, citys.length);

        System.out.println("\n" + citys[num - 1]);
        return (citys[num - 1]);
    }

    public static int inputNumber(int startIndex, int endIndex)
    {
        Scanner sc = new Scanner(System.in);
        int num;

        do {
            System.out.print("Enter the number of the city: ");

            while (!sc.hasNextInt())
            {
                System.out.println("Error! That not a number! Try again.");
                System.out.print("Enter the number of the city: ");
                sc.next(); // this is important!
            }

            num = sc.nextInt();

            if (num < startIndex || num > endIndex)
            {
                System.out.println("Error! The wrong number was entered. Try again.");
            }

        } while (num < startIndex || num > endIndex);

        return num;
    }


    public static void main(String[] args) throws IOException
    {
        Document page = getPage(getCity());

        // выбрали класс time из всего кода
        Element date = page.select("div.time").last();  // берем дату
        Element time = page.select("div.time").first(); // берем время

        // .text() помогает отбросить теги при выводе
        System.out.println(date.text());
        System.out.println("time =  " + time.text());
    }
}
