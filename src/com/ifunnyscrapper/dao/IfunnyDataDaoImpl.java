/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifunnyscrapper.dao;

import com.ifunnyscrapper.entity.HashCrawledData;
import com.ifunnyscrapper.entity.HashData;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

/**
 *
 * @author GLB-214
 */
public class IfunnyDataDaoImpl implements IfunnyDataDao {

    private SimpleJdbcInsert launchDataInsert;
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    @Override
    public void insertIfunnyData(HashData objHashData) {

        String SQL = "insert into hash_data (HASH_TAG, HASH_URL, ISCRAWLED) values (?, ?, ?)";
        try {
            jdbcTemplateObject.update(SQL, objHashData.getHashTag(), objHashData.getHashUrl(), objHashData.getIscrawled());
            System.out.println("Hash Data inserted");
        } catch (Exception e) {
        }
    }
    
    @Override
    public List<HashData> getHashDataList() {

        List<HashData> hashDataList=null;
        String SQL = "Select * from hash_data where iscrawled=0";
        try {
            hashDataList=jdbcTemplateObject.query(SQL,
                new BeanPropertyRowMapper(HashData.class));
            System.out.println("Fetching data");
        } catch (Exception e) {
        }
        return hashDataList;
    }
    
    @Override
    public void insertIfunnyData(HashCrawledData objHashCrawledData) {

        String SQL = "insert into hash_crawled_data (HASH_ID, IMAGE_URL, RELATED_TAGS) values (?, ?, ?)";
        try {
            jdbcTemplateObject.update(SQL, objHashCrawledData.getHashId().getHashId(), objHashCrawledData.getImageUrl(), objHashCrawledData.getRelatedTags());
            System.out.println("Hash Crawled Data inserted");
        } catch (Exception e) {
//            e.printStackTrace();
        }
    }
    
    @Override
    public void updateHashData(HashData objHashData) {
        
         String SQL = "update hash_data set ISCRAWLED = 1 where HASH_ID	 = ?";
        jdbcTemplateObject.update(SQL, objHashData.getHashId());

        try {
             jdbcTemplateObject.update(SQL, objHashData.getHashId());
            System.out.println("Hash Data updated");
        } catch (Exception e) {
        }
    }
}
