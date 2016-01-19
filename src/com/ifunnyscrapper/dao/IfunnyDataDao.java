/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifunnyscrapper.dao;

import com.ifunnyscrapper.entity.HashCrawledData;
import com.ifunnyscrapper.entity.HashData;
import java.util.List;

/**
 *
 * @author GLB-214
 */
public interface IfunnyDataDao {
    
      public void insertIfunnyData(HashData objHashData);
      
      public List<HashData> getHashDataList();
      
      public void insertIfunnyData(HashCrawledData objHashCrawledData);
      
      public void updateHashData(HashData objHashData);
}
