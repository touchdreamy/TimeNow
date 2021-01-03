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
        Document page = null;
        try {
            String url = "https://time100.ru/" + city;
            page = Jsoup.parse(new URL(url), 2000);
        } catch (Exception e)
        {
            System.out.println("Такой город не найден");
            System.exit(1);
        }
        return page;
    }

    public static String getCity()
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите название города: ");
        String city = sc.nextLine();
        return city;
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
