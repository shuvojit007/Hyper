package io.geeteshk.hyper.helper;

import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class HTMLParser {

    private static final String TAG = HTMLParser.class.getSimpleName();

    private static Document getSoup(String name) {
        try {
            return Jsoup.parse(ProjectManager.getIndexFile(name), "UTF-8");
        } catch (IOException e) {
            Log.e(TAG, e.toString());
            return null;
        }
    }

    public static String[] getProperties(String projName) {
        Document soup = getSoup(projName);
        String[] properties = new String[4];

        if (soup != null) {
            // title
            properties[0] = soup.head().getElementsByTag("title").text();

            // meta
            Elements metas = soup.head().getElementsByTag("meta");
            for (Element meta : metas) {
                String content = meta.attr("content");
                String name = meta.attr("name");

                switch (name) {
                    case "author":
                        properties[1] = content;
                        break;
                    case "description":
                        properties[2] = content;
                        break;
                    case "keywords":
                        properties[3] = content;
                        break;
                }
            }
        }

        return properties;
    }
}