/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifunnyscrapper.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author GLB-214
 */
@Entity
@Table(name = "hash_crawled_data")
public class HashCrawledData implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "DATA_ID")
    private Integer dataId;
    @Column(name = "IMAGE_URL")
    private String imageUrl;
    @Column(name = "RELATED_TAGS")
    private String relatedTags;
    @JoinColumn(name = "HASH_ID", referencedColumnName = "HASH_ID")
    @ManyToOne(optional = false)
    private HashData hashId;

    public HashCrawledData() {
    }

    public HashCrawledData(Integer dataId) {
        this.dataId = dataId;
    }

    public Integer getDataId() {
        return dataId;
    }

    public void setDataId(Integer dataId) {
        this.dataId = dataId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getRelatedTags() {
        return relatedTags;
    }

    public void setRelatedTags(String relatedTags) {
        this.relatedTags = relatedTags;
    }

    public HashData getHashId() {
        return hashId;
    }

    public void setHashId(HashData hashId) {
        this.hashId = hashId;
    }
}
