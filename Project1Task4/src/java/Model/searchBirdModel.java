/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Jin
 */
public class searchBirdModel {
    private String pictureTag;
    private String pictureURL;
    
    public Elements extrateData(String url) throws Exception{
        Document doc = Jsoup.connect(url).get();
        return doc.select("select[name=pix] > option");
    }
    
    public HashMap extratePicture(String url) throws Exception{
        Document doc = Jsoup.connect(url).get();
        //return doc.select("div.clearfix > div > img[src$=.jpg]");
        Elements val = doc.select("div.modal-content");
        HashMap<String, ArrayList> hs = new HashMap<String, ArrayList>();
        for(Element div : val){
                System.out.println(div.toString());
                String author = div.getElementsByTag("p").first().html();
                
                String imgSrc = div.getElementsByTag("img").first().attr("src");
                if(hs.containsKey(author)) {
                    hs.get(author).add(imgSrc);
                } else {
                    ArrayList<String> imgSrcs = new  ArrayList<String>();
                    imgSrcs.add(imgSrc);
                    hs.put(author, imgSrcs);
                }
                
        }
        
        //System.out.println(val.size());
        
        return hs;
    }
}
