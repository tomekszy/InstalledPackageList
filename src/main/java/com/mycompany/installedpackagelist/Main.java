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
public class Main {
//

    public static String appName;
    public static String appName2;
    public static String appName3;
    public static String appName4;
    public static String appNameOut;
    public static String appDesc;
    public static String appDesc2;
    public static String appDesc3;
    public static String appDesc4;
    public static String pkg4;
    public static String successOut;
    public static boolean success;
    public static boolean success2;
    public static boolean success3;
    public static boolean success4;
    public static String urlOut;
    public static String urlOut2;
    public static String urlOut3;
    public static String urlOut4;

    public static void main(String[] args) throws FileNotFoundException, IOException, InterruptedException {

        try (
                FileReader input = new FileReader("in.txt");
                LineNumberReader count = new LineNumberReader(input);) {
            while (count.skip(Long.MAX_VALUE) > 0) {
                // Loop just in case the file is > Long.MAX_VALUE or skip() decides to not read the entire file
            }

            int result = count.getLineNumber() + 1; // +1 because line index starts at 0
            String outFile = "out.csv";
            BufferedReader bufferreader = new BufferedReader(new FileReader("in.txt"));
            FileWriter fw = new FileWriter(outFile);
            char csvSeparator = ';';
            fw.write("App name" + csvSeparator
                    + "Description from Google Store" + csvSeparator + "Play Store URL" + csvSeparator
                    + "Description from GetAPK" + csvSeparator + "GetAPK URL" + csvSeparator
                    + "Description from Apk Pure" + csvSeparator + "Apk Pure URL" + csvSeparator
                    + "Description from App Brain" + csvSeparator + "App Brain URL" + csvSeparator
                    + "Packet name" + csvSeparator + "Is package name found?" + System.lineSeparator());
            String line;

            String playStoreUrl = "https://play.google.com/store/apps/details?id=";
            String getApkUrl = "http://getapk.co/apk/";
            String apkPureUrl = "https://apkpure.com/apk/";
            String appBrainUrl = "http://www.appbrain.com/app/";

            /*
            String url[] = new String[5];
            url[1] = "https://play.google.com/store/apps/details?id=";
            url[2] = "http://getapk.co/apk/";
            url[3] = "https://apkpure.com/apk/";
            url[4] = "http://www.appbrain.com/app/";
             */
            System.out.println("Installed Package List (c) Tomasz Szymański");
            System.out.println("Found " + result + " installed packages:");
            while ((line = bufferreader.readLine()) != null) {

                pkg4 = (line.substring(line.lastIndexOf("=") + 1).trim());

                /*
                for (int i=1;i<5;i++) {
                    try {
                        if (i==1) {
                    System.out.print("Trying Play Store...");
                    String[] appResp = getTitleDesc(url[i], "<div class=\"id-app-title\" tabindex=\"0\">", "</div>",
                            "<div class=\"show-more-content text-body\" itemprop=\"description\">", "<div class=\"show-more-end\">", pkg4, csvSeparator);
                    
                        }
                        if (i==2) {
                    System.out.print("Trying GetAPK...");
                    String[] appResp = getTitleDesc(url[i], "<h1 class=\"appTitle\">", "</h1>",
                            "<h2 class=\"detailHead\">Description</h2>", "<div class=\"spacer\"></div>", pkg4, csvSeparator);        
                    
                        }
                        if (i==3) {
                    System.out.print("Trying ApkPure...");
                    String[] appResp = getTitleDesc(url[i], "<div class=\"title-like\">", "</div>",
                            "<div class=\"description\">", "</div>", pkg4, csvSeparator);
                    
                        }
                        if (i==4) {
                    System.out.print("Trying AppBrain...");
                    String[] appResp = getTitleDesc(url[i], "itemprop=\"name\">", "</h1>",
                            "<div class=\"app-description expandable\">", "</div>", pkg4, csvSeparator);
                    
                        }
                    appNameArr[i] = appResp[0];
                    appDescArr[i] = appResp[1];
                    successArr[i] = true;
                    urlOutArr[i] = success ? (url[i] + pkg4) : ("");
                    System.out.println("Success. ");
                } catch (Exception e) {
                    appNameArr[i] = "";
                    appDescArr[i] = "";
                    successArr[i] = false;
                    urlOutArr[i] = "";
                    System.out.println("Failed. ");
                }
                }
                 */
                try {
                    System.out.print("Trying Play Store...");
                    String[] appResp = getTitleDesc(playStoreUrl, "<div class=\"id-app-title\" tabindex=\"0\">", "</div>",
                            "<div class=\"show-more-content text-body\" itemprop=\"description\">", "<div class=\"show-more-end\">", pkg4, csvSeparator);
                    appName = appResp[0];
                    appDesc = appResp[1];
                    success = true;
                    urlOut = success ? (playStoreUrl + pkg4) : ("");
                    System.out.println("Success. ");
                } catch (Exception e) {
                    appName = "";
                    appDesc = "";
                    success = false;
                    urlOut = "";
                    System.out.println("Failed. ");
                }

                try {
                    System.out.print("Trying GetAPK...");
                    String[] appResp = getTitleDesc(getApkUrl, "<h1 class=\"appTitle\">", "</h1>",
                            "<h2 class=\"detailHead\">Description</h2>", "<div class=\"spacer\"></div>", pkg4, csvSeparator);
                    appName2 = appResp[0];
                    appDesc2 = appResp[1];
                    success2 = true;
                    urlOut2 = success2 ? (getApkUrl + pkg4) : ("");
                    System.out.println("Success. ");
                } catch (Exception e) {
                    appName2 = "";
                    appDesc2 = "";
                    success2 = false;
                    urlOut2 = "";
                    System.out.println("Failed. ");
                }

                try {
                    System.out.print("Trying ApkPure...");
                    String[] appResp = getTitleDesc(apkPureUrl, "<div class=\"title-like\">", "</div>",
                            "<div class=\"description\">", "</div>", pkg4, csvSeparator);
                    appName3 = appResp[0];
                    appDesc3 = appResp[1];
                    success3 = true;
                    urlOut3 = success3 ? (apkPureUrl + pkg4) : ("");
                    System.out.println("Success. ");
                } catch (Exception e) {
                    appName3 = "";
                    appDesc3 = "";
                    success3 = false;
                    urlOut3 = "";
                    System.out.println("Failed. ");
                }

                try {
                    System.out.print("Trying AppBrain...");
                    String[] appResp = getTitleDesc(appBrainUrl, "itemprop=\"name\">", "</h1>",
                            "<div class=\"app-description expandable\">", "</div>", pkg4, csvSeparator);
                    appName4 = appResp[0];
                    appDesc4 = appResp[1];
                    success4 = true;
                    urlOut4 = success4 ? (appBrainUrl + pkg4) : ("");
                    System.out.println("Success. ");
                } catch (Exception e) {
                    appName4 = "";
                    appDesc4 = "";
                    success4 = false;
                    urlOut4 = "";
                    System.out.println("Failed. ");
                }

                if (success) {
                    appNameOut = appName;
                    //przerobić na list.add
                    successOut = "In Play Store";
                } else if (success2) {
                    appNameOut = appName2;
                    successOut = "In GetAPK";
                } else if (success3) {
                    appNameOut = appName3;
                    successOut = "In Apk Pure";
                } else if (success4) {
                    appNameOut = appName4;
                    successOut = "In App Brain";
                } else {
                    appNameOut = "Not found";
                    successOut = "Not found";
                }

                System.out.println("App name " + appNameOut);
                fw.write(appNameOut + csvSeparator
                        + appDesc + csvSeparator + urlOut + csvSeparator
                        + appDesc2 + csvSeparator + urlOut2 + csvSeparator
                        + appDesc3 + csvSeparator + urlOut3 + csvSeparator
                        + appDesc4 + csvSeparator + urlOut4 + csvSeparator
                        + pkg4 + csvSeparator + successOut + System.lineSeparator());
                System.out.println("");
            }
            fw.close();
            System.out.println("File " + outFile + " written.");
        }
    }

    static String restEasy(String url, String pkg4) {

        ResteasyClient client5 = new ResteasyClientBuilder().build();
        ResteasyWebTarget target5 = client5.target(url + pkg4);
        Response response5 = target5.request().get();
        String value5 = response5.readEntity(String.class);
        response5.close();

        return value5;
    }

    static String[] getTitleDesc(String url, String splitBeforeTitle, String splitAfterTitle,
            String splitBeforeDesc, String splitAfterDesc, String pkg4, char csvSeparator) {

        String value5 = restEasy(url, pkg4);

        String[] partsBeforeTitle = value5.split(splitBeforeTitle);
        String[] partsAfterTitle = partsBeforeTitle[1].split(splitAfterTitle);
        String appName5 = partsAfterTitle[0].replace(csvSeparator, ' ');

        String[] partsBeforeDesc5 = value5.split(splitBeforeDesc);
        String[] partsAfterDesc5 = partsBeforeDesc5[1].split(splitAfterDesc);
        String appDescHtml5 = partsAfterDesc5[0].replace(csvSeparator, ' ');
        String appDesc5 = Jsoup.parse(appDescHtml5).text();

        String[] resp = new String[2];
        resp[0] = appName5;
        resp[1] = appDesc5;

        return resp;
    }

}
