package com.example.musicdiary2.music;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.regex.Pattern;

public class JsoupComponentLocalMain {

    public static boolean getType(String word) {
        return Pattern.matches("^[가-힣]*$", word);
    }

    public static void getMusicInfoList() throws UnsupportedEncodingException {

        String title = "gee";
        if (getType(title)) {
            URLEncoder.encode(title, "UTF-8");
        }
        String singer = "소녀시대";
        if (getType(singer)) {
            URLEncoder.encode(singer, "UTF-8");
        }

        final String musicList =
                "https://www.melon.com/search/song/index.htm?q=" + title + "+" + singer;

        System.out.println(musicList);

        Connection conn = Jsoup.connect(musicList);

        try {
            Document document = conn.get();
            String thead = getMusicHeader(document);
            String tbody = getMusicList(document);
            System.out.println(thead);
            System.out.println(tbody);
        } catch (IOException ignored) { }
    }

    public static String getMusicHeader(Document document) {
        Elements musicTableBody = document.select("div.tb_list.d_song_list.songTypeOne thead tr");
        StringBuilder sb = new StringBuilder();

        for (Element element : musicTableBody) {
            for (Element td : element.select("th")) {
                sb.append(td.text());
                sb.append(" ");
            }
            break;
        }
        return sb.toString();
    }

    public static String getMusicList(Document document) {
        Elements musicTableBody = document.select("div.tb_list.d_song_list.songTypeOne tbody tr");
        StringBuilder sb = new StringBuilder();

        Elements td = musicTableBody.select("td.t_left div.wrap.pd_none");
        String text = td.select("div.ellipsis a.btn.btn_icon_detail")
                .attr("href");

        sb.append(text);

        /*
        for (Element element : musicTableBody) {
//            if (element.attr("onmouseover").isEmpty()) {
//                continue;
//            }
            for (Element td : element.select("td.t_left div.wrap.pd_none")) {
                String text = td.select("div.ellipsis a.btn.btn_icon_detail")
                                .attr("href");

                sb.append(text);
                sb.append(" ");
            }
            sb.append(System.getProperty("line.separator"));
        }

        /*

        for (Element element : musicTableBody) {
            if (element.attr("onmouseover").isEmpty()) {
                continue;
            }

            for (Element td : element.select("td")) {
                String text;
                if (td.select("div.wrap.pd_none div.ellipsis a.btn.btn_icon_detail")
                        .attr("href").isEmpty()) {
                    text = td.text();
                } else {
                    text = td.select("div.wrap.pd_none div.ellipsis a.btn.btn_icon_detail")
                            .attr("href");
                }
                sb.append(text);
                sb.append(" ");
            }
            sb.append(System.getProperty("line.separator"));
        }

         */
        return sb.toString();
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        getMusicInfoList();
    }



}
