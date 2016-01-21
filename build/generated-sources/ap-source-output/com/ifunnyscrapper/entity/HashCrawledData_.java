package com.ifunnyscrapper.entity;

import com.ifunnyscrapper.entity.HashData;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-01-19T16:15:53")
@StaticMetamodel(HashCrawledData.class)
public class HashCrawledData_ { 

    public static volatile SingularAttribute<HashCrawledData, String> imageUrl;
    public static volatile SingularAttribute<HashCrawledData, Integer> dataId;
    public static volatile SingularAttribute<HashCrawledData, String> relatedTags;
    public static volatile SingularAttribute<HashCrawledData, HashData> hashId;

}