package com.mycompany.installedpackagelist;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import javax.ws.rs.core.Response;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.jsoup.Jsoup;

/**
 *
 * @author tszymanski
 */
public class Main_old {
//

    public static void main(String[] args) throws FileNotFoundException, IOException, InterruptedException {
//        Runtime.getRuntime().exec("cmd /c start adb.bat");
//        Thread.sleep(5000);
        try (
                FileReader input = new FileReader("in.txt");
                LineNumberReader count = new LineNumberReader(input);) {
            while (count.skip(Long.MAX_VALUE) > 0) {
                // Loop just in case the file is > Long.MAX_VALUE or skip() decides to not read the entire file
            }

            int result = count.getLineNumber() + 1; // +1 because line index starts at 0

//            Thread.sleep(100*result);
            BufferedReader bufferreader = new BufferedReader(new FileReader("in.txt"));
            FileWriter fw = new FileWriter("out.csv");
            char csvSeparator = ';';
            fw.write("App name" + csvSeparator
                    + "Description from Google Store" + csvSeparator + "Play Store URL" + csvSeparator
                    + "Packet name" + csvSeparator + "Is package name found?" + System.lineSeparator());
            String line;
            /*= bufferreader.readLine();*/
            String playStoreUrl = "https://play.google.com/store/apps/details?id=";

            System.out.println("Found " + result + " installed packages:"/* + bufferreader.lines().count()*/);
            while ((line = bufferreader.readLine()) != null) {
//                String[] pkg3=line.split("=");
                String pkg4 = (line.substring(line.lastIndexOf("=") + 1).trim());
//                System.out.println(pkg3[1]);
//            String appPkg = "com.tbegames.and.best_moto_race";
                ResteasyClient client = new ResteasyClientBuilder().build();
                ResteasyWebTarget target = client.target(playStoreUrl + pkg4);
                Response response = target.request().get();
                String value = response.readEntity(String.class);

                try {
                    String[] partsBeforeTitle = value.split("<div class=\"id-app-title\" tabindex=\"0\">");
                    String[] partsAfterTitle = partsBeforeTitle[1].split("</div>");
                    String appName = partsAfterTitle[0];

                    String[] partsBeforeDesc = value.split("<div class=\"show-more-content text-body\" itemprop=\"description\">");
                    String[] partsAfterDesc = partsBeforeDesc[1].split("<div class=\"show-more-end\">");
//                String appDesc = (partsAfterDesc[0].replace(csvSeparator, ' ')).replaceAll("<[^>]*>", "");
                    String appDescHtml = partsAfterDesc[0].replace(csvSeparator, ' ');
                    String appDesc = Jsoup.parse(appDescHtml).text();

                    System.out.println(appName);
                    fw.write(appName + csvSeparator
                            + appDesc + csvSeparator + playStoreUrl + pkg4 + csvSeparator
                            + pkg4 + csvSeparator + true + System.lineSeparator());
                } catch (Exception e) {
                    System.out.println("Nie znalezionono pakietu " + pkg4);
                    fw.write("" + csvSeparator
                            + "" + csvSeparator + "" + csvSeparator
                            + pkg4 + "" + false + System.lineSeparator());
                }
                response.close();
            }
            fw.close();

        }
    }
}
