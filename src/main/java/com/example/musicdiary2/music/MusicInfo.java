package com.example.musicdiary2.music;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.regex.Pattern;

public class MusicInfo {

    public static void main(String[] args) throws IOException {
        String title = "gee";
        String singer = "소녀시대";
        getMusicInfo(title, singer);
    }

    public static String[] getMusicInfo(String title, String singer) throws IOException {

        String musicId = getMusicId(title, singer);

        if (musicId == null) {
            return null;
        }

        final String url = "https://www.melon.com/song/detail.htm?songId=" + musicId;

        Connection conn = Jsoup.connect(url);
        Document document = conn.get();

        String mTitle = document.select("div.song_name")
                .text().substring(3);

        String mSinger = document.select("div.artist a.artist_name")
                .attr("title");

        String mImgSrc = document.select("div.thumb a.image_typeAll img")
                .attr("src");
        // https://cdnimg.melon.co.kr/cm/album/images/004/58/355/458355_500.jpg/melon/resize/282/quality/80/optimize
        String target = ".jpg";
        int targetNum = mImgSrc.indexOf(target);
        mImgSrc = mImgSrc.substring(0, targetNum+4);

        System.out.println(mTitle);
        System.out.println(mSinger);
        System.out.println(mImgSrc);

        String[] result = {mTitle, mSinger, mImgSrc};

        return result;
    }

    public static String getMusicId(String title, String singer) throws IOException {

        if (getType(title)) {
            URLEncoder.encode(title, "UTF-8");
        }
        if (getType(singer)) {
            URLEncoder.encode(singer, "UTF-8");
        }

        final String url = "https://www.melon.com/search/song/index.htm?q=" + title + "+" + singer;

        Connection conn = Jsoup.connect(url);
        Document document = conn.get();

        // 곡 수 확인
        String check = document.select("div.section_song em").text();
        if (check.equals("0")) {
            return null;
        }

        Elements musicTableBody = document.select("div.tb_list.d_song_list.songTypeOne tbody tr");
        Elements td = musicTableBody.select("td.t_left div.wrap.pd_none");
        String detailUrl = td.select("div.ellipsis a.btn.btn_icon_detail")
                .attr("href");
        // javascript:searchLog('web_song','SONG','SO','gee 소녀시대','2012282');melon.link.goSongDetail('2012282');

        String target = "goSongDetail";
        int targetNum = detailUrl.indexOf(target);
        String musicId = detailUrl.substring(targetNum);
        musicId = musicId.substring(14, musicId.length()-3);

        return musicId;
    }

    public static boolean getType(String word) {
        return Pattern.matches("^[가-힣]*$", word);
    }

}
