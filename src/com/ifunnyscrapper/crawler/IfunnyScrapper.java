/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifunnyscrapper.crawler;

import com.ifunnyscrapper.dao.IfunnyDataDao;
import com.ifunnyscrapper.dao.IfunnyDataDaoImpl;
import com.ifunnyscrapper.entity.HashCrawledData;
import com.ifunnyscrapper.entity.HashData;
import static com.ifunnyscrapper.utility.FetchPageWithProxy.fetchPage;
import com.ifunnyscrapper.utility.GetRequestHandler;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import javax.imageio.ImageIO;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author GLB-214
 */
public class IfunnyScrapper {

    HashData objHashData;

    HashCrawledData objHashCrawledData;
//    IfunnyDataDao objIfunnyDataDao;
    ApplicationContext context
            = new ClassPathXmlApplicationContext("Beans.xml");
    // TODO code application logic here

    IfunnyDataDao objIfunnyDataDao
            = (IfunnyDataDaoImpl) context.getBean("IfunnyDataDaoImpl");

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException, JSONException, Exception {

        IfunnyScrapper objIfunnyScrapper = new IfunnyScrapper();
        String url = "https://ifunny.co/";
//        objIfunnyScrapper.getImagesUrl(url);
//        objIfunnyScrapper.tagsUrlCrawling();
    }

    public void getImagesUrl(HashData obj) throws IOException, InterruptedException, URISyntaxException {
        objIfunnyDataDao.updateHashData(obj);
        Document doc = Jsoup.parse(fetchPage(new URI(obj.getHashUrl())));

        Element e = doc.select("div[class=js-vertical-gallery] a[class=get-more js-get-more]").first();
        System.out.println("Next : " + e.attr("next"));
        System.out.println("Href : " + e.attr("href"));

        String next = e.attr("data-test");

        if (next.contains("get-more")) {
            String nextcode = e.attr("next");
            System.out.println("----- Next Code ----- ::::: " + nextcode);
            recursive(nextcode, obj);
        }

    }

    public void recursive(String nextcode, HashData obj) throws URISyntaxException, IOException, InterruptedException {

        String pagenationUrl = "https://ifunny.co/api/feeds/featured?next=" + nextcode + "&limit=20&_=1452323423752";
        System.out.println("----- Pagination Url ----- ::::: " + pagenationUrl);
        Document doc = Jsoup.parse(fetchPage(new URI(pagenationUrl)));
//        System.out.println("---DOC----" + doc);
        Element e = doc.select("div[class=js-vertical-gallery] a[class=get-more js-get-more]").first();
        System.out.println("Next : " + e.attr("next"));
        String code = e.attr("next");

//        Elements ee = doc.select("div[class=content-resource__crop] img");//.first();
//        for (Element e1 : ee) {
//            objHashCrawledData = new HashCrawledData();
//            System.out.println("---" + e1.attr("src"));
//            objHashCrawledData.setImageUrl(e1.attr("src"));
//            objHashCrawledData.setHashId(obj);
//            objIfunnyDataDao.insertIfunnyData(objHashCrawledData);
//        }
       
        Elements ele = doc.select("div[class=content-list__item]");
        System.out.println("" + ele.size());
        for (Element e1 : ele) {
             JSONObject json = new JSONObject();
            JSONArray jsonarray = new JSONArray();
            String urlFinal = "";
            String tagsFinal = "";
            Elements eleurl = e1.select("div[class=content-list__item__link] div[class=content-resource] div[class=content-resource__crop] img");
            urlFinal = eleurl.attr("src");
            Elements tags = e1.select("div[class*=content-info content-info--in-list  ] h2 a");
            System.out.println("----- Tags Size ----- ::::: " + tags.size());
            for (Element tag : tags) {
                try {
                    
                    String hashtags = tag.attr("href").replace("/tags/", "");
                    System.out.println("----- HashTags ----- ::::: " + hashtags);
                    tagsFinal = tagsFinal + hashtags + ",";
                    jsonarray.put(hashtags);
                    
                } catch (Exception euhuy) {
                }
            }
            try {
                json.put("tags", jsonarray);
            } catch (JSONException jSONException) {
            }
            System.out.println("----- Tags Final ----- ::::: " + tagsFinal);
            System.out.println("----- Urls Final ----- ::::: " + urlFinal);
            
           

            objHashCrawledData = new HashCrawledData();
            System.out.println("----- Image Url ----- ::::: " + e1.attr("src"));
            String imagesurl = urlFinal;
            String destinationFile = downloadImages(imagesurl);
            System.out.println("-----------------destinationFile   " + destinationFile);
            objHashCrawledData.setRelatedTags(json.toString());
            objHashCrawledData.setImageUrl(destinationFile);
            objHashCrawledData.setHashId(obj);
            objIfunnyDataDao.insertIfunnyData(objHashCrawledData);

        }

        Elements eee = doc.select("h2[class=content-info__header] a");//.first();
        for (Element ee1 : eee) {
            objHashData = new HashData();
            String hashtags = ee1.attr("href").replace("/tags/", "");
            System.out.println("----- Hash Tags ----- ::::: " + hashtags);
            objHashData.setHashTag(hashtags);
            objHashData.setHashUrl("https://ifunny.co/tags/" + hashtags);
            objHashData.setIscrawled(0);
            objIfunnyDataDao.insertIfunnyData(objHashData);
        }

        recursive(code, obj);

    }

    public void tagsUrlCrawling(HashData obj) throws URISyntaxException, IOException, InterruptedException, JSONException, Exception {
        objIfunnyDataDao.updateHashData(obj);
//        String url = "http://ifunny.co/tags/powerball";
        Document doc = Jsoup.parse(fetchPage(new URI(obj.getHashUrl())));
//        System.out.println(""+doc);
        String nextcode = "";
        Element e = doc.select("div[class=js-vertical-gallery] a[class=get-more js-get-more]").first();
        System.out.println("Next : " + e.attr("next"));
        System.out.println("Href : " + e.attr("href"));

        String next = e.attr("data-test");

        if (next.contains("get-more")) {
            nextcode = e.attr("next");
            System.out.println("----Next Code-----" + nextcode);
            RecursiveCrawling(nextcode, obj);
        }
    }

    public void RecursiveCrawling(String nextcode, HashData objhash) throws Exception {

        String urls = "http://ifunny.co/api/tag/gallery?tag=powerball&next=" + nextcode + "&limit=20&_=1452491645605";
        System.out.println("Ulr : " + urls);
        String response = "";
        response = new GetRequestHandler().doGetRequest(new URL(urls));

        JSONObject obj = new JSONObject(response);

        JSONObject imageurlobject = obj.getJSONObject("content");
//        System.out.println("" + imageurlobject.getJSONObject("paging").getJSONObject("cursors").getString("next"));

        JSONArray objJSONArray = imageurlobject.getJSONArray("items");
        System.out.println("" + objJSONArray.length());
        for (int i = 0; i < objJSONArray.length(); i++) {
            objHashCrawledData = new HashCrawledData();
            String imageurl = objJSONArray.getJSONObject(i).getString("url");
            String title = objJSONArray.getJSONObject(i).getString("title");
            System.out.println("Image Url : " + imageurl);
            String imagesurl = imageurl;
            downloadImages(imagesurl);
            objHashCrawledData.setImageUrl(imageurl);
            objHashCrawledData.setHashId(objhash);
            objIfunnyDataDao.insertIfunnyData(objHashCrawledData);
            String split[] = title.split("#");
            for (int j = 1; j < split.length; j++) {
                System.out.println("Title : " + split[j]);
                String hashtags = split[j];
                objHashData = new HashData();
                objHashData.setHashTag(hashtags);
                objHashData.setHashUrl("https://ifunny.co/tags/" + hashtags);
                objHashData.setIscrawled(0);
                objIfunnyDataDao.insertIfunnyData(objHashData);
            }
        }
        try {
            String nextpagecode = imageurlobject.getJSONObject("paging").getJSONObject("cursors").getString("next");
            System.out.println("NextPageCode" + nextpagecode);
            RecursiveCrawling(nextpagecode, objhash);
        } catch (Exception e) {
        }
    }

    public String downloadImages(String imagesurl) throws IOException {

//        String imageUrl = "http://img.ifcdn.com/images/ebcb689372ca9722f8bfc9f113d7766781313b3040135bb6296de074fde24b3f_1.jpg";
        String imageUrl = imagesurl;

        String imagerlappend = imageUrl.replace("http://img.ifcdn.com/images/", "");

        String destinationFile = "D:\\AuditMySite Project\\AuditeMySite\\IfunnyScrapper\\images\\" + imagerlappend;

        saveImage(imageUrl, destinationFile);
        cropImages(destinationFile);
        return destinationFile;
    }

    public void saveImage(String imageUrl, String destinationFile) throws IOException {
        try {
            URL url = new URL(imageUrl);
            InputStream is = url.openStream();
            FileOutputStream os = new FileOutputStream(destinationFile);

            byte[] b = new byte[2048];
            int length;

            while ((length = is.read(b)) != -1) {
                os.write(b, 0, length);
            }

            is.close();
            os.close();
        } catch (IOException iOException) {
            System.out.println("iOException");
//            iOException.printStackTrace();
        } catch (Exception e) {
            System.out.println("Exception");
//            e.printStackTrace();
        }
    }

    public void cropImages(String destinationFile) {

        try {
            BufferedImage originalImgage = ImageIO.read(new File(destinationFile));

            System.out.println("Original Image Dimension: " + originalImgage.getWidth() + "x" + originalImgage.getHeight());

            BufferedImage SubImgage = originalImgage.getSubimage(0, 0, originalImgage.getWidth(), originalImgage.getHeight() - 35);
            System.out.println("Cropped Image Dimension: " + SubImgage.getWidth() + "x" + SubImgage.getHeight());

            File outputfile = new File(destinationFile);
            ImageIO.write(SubImgage, "jpg", outputfile);
            System.out.println("Image cropped successfully: " + outputfile.getPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
