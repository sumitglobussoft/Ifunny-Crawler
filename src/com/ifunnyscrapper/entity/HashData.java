/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifunnyscrapper.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author GLB-214
 */
@Entity
@Table(name = "hash_data")
public class HashData implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "HASH_ID")
    private Integer hashId;
    @Basic(optional = false)
    @Column(name = "HASH_TAG")
    private String hashTag;
    @Basic(optional = false)
    @Column(name = "HASH_URL")
    private String hashUrl;
    @Basic(optional = false)
    @Column(name = "ISCRAWLED")
    private int iscrawled;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hashId")
    private Collection<HashCrawledData> hashCrawledDataCollection;

    public HashData() {
    }

    public HashData(Integer hashId) {
        this.hashId = hashId;
    }

    public HashData(Integer hashId, String hashTag, String hashUrl, int iscrawled) {
        this.hashId = hashId;
        this.hashTag = hashTag;
        this.hashUrl = hashUrl;
        this.iscrawled = iscrawled;
    }

    public Integer getHashId() {
        return hashId;
    }

    public void setHashId(Integer hashId) {
        this.hashId = hashId;
    }

    public String getHashTag() {
        return hashTag;
    }

    public void setHashTag(String hashTag) {
        this.hashTag = hashTag;
    }

    public String getHashUrl() {
        return hashUrl;
    }

    public void setHashUrl(String hashUrl) {
        this.hashUrl = hashUrl;
    }

    public int getIscrawled() {
        return iscrawled;
    }

    public void setIscrawled(int iscrawled) {
        this.iscrawled = iscrawled;
    }

    public Collection<HashCrawledData> getHashCrawledDataCollection() {
        return hashCrawledDataCollection;
    }

    public void setHashCrawledDataCollection(Collection<HashCrawledData> hashCrawledDataCollection) {
        this.hashCrawledDataCollection = hashCrawledDataCollection;
    }
}
