/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifunnyscrapper.crawler;

import com.ifunnyscrapper.dao.IfunnyDataDao;
import com.ifunnyscrapper.dao.IfunnyDataDaoImpl;
import com.ifunnyscrapper.entity.HashData;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author GLB-214
 */
public class Crawlling {
//    HashData objHashData;
//    IfunnyDataDao objIfunnyDataDao;

    ApplicationContext context
            = new ClassPathXmlApplicationContext("Beans.xml");
    // TODO code application logic here

    IfunnyDataDao objIfunnyDataDao
            = (IfunnyDataDaoImpl) context.getBean("IfunnyDataDaoImpl");

    public static void main(String[] args) {

        Crawlling objCrawlling = new Crawlling();
        objCrawlling.getdatafromdb();

    }

    public void getdatafromdb() {
        List<HashData> hashDataList = objIfunnyDataDao.getHashDataList();
        IfunnyScrapper objIfunnyScrapper = new IfunnyScrapper();
        
        try {if(hashDataList.get(0).getHashUrl().equals("http://ifunny.co/")){
                    objIfunnyScrapper.getImagesUrl(hashDataList.get(0));}
                } catch (IOException ex) {
                    Logger.getLogger(Crawlling.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Crawlling.class.getName()).log(Level.SEVERE, null, ex);
                } catch (URISyntaxException ex) {
                    Logger.getLogger(Crawlling.class.getName()).log(Level.SEVERE, null, ex);
                }
                
        
    hashDataList = objIfunnyDataDao.getHashDataList();


    while (hashDataList.size() > 0) {
        System.out.println("start");
//            List<Future<String>> list = new ArrayList<Future<String>>();
//            ExecutorService executor = Executors.newFixedThreadPool(10);
//            for (HashData objHashData : hashDataList) {
//                try {
//                    Callable worker = new Threading(objHashData, objIfunnyDataDao);
//                    Future<String> future = executor.submit(worker);
//                    list.add(future);
//                } catch (Exception exx) {
//                    System.out.println(exx);
//                }
//
//            }
//            for (Future<String> fut : list) {
//                try {
//                    //print the return value of Future, notice the output delay in console
//                    // because Future.get() waits for task to get completed
//                    System.out.println(new Date() + "::" + fut.get());
//                } catch (InterruptedException | ExecutionException ep) {
//                    ep.printStackTrace();
//                }
//            }
//             //shut down the executor service now
//            executor.shutdown();
            
            for (HashData hashDataList1 : hashDataList) {
                
                try {
                    objIfunnyScrapper.tagsUrlCrawling(hashDataList1);
                } catch (IOException ex) {
                    Logger.getLogger(Crawlling.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Crawlling.class.getName()).log(Level.SEVERE, null, ex);
                } catch (URISyntaxException ex) {
                    Logger.getLogger(Crawlling.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(Crawlling.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
            
            hashDataList = objIfunnyDataDao.getHashDataList();

        } 

    }

}
