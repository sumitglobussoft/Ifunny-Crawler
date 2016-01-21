package com.ifunnyscrapper.entity;

import com.ifunnyscrapper.entity.HashCrawledData;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-01-19T16:15:53")
@StaticMetamodel(HashData.class)
public class HashData_ { 

    public static volatile SingularAttribute<HashData, String> hashTag;
    public static volatile CollectionAttribute<HashData, HashCrawledData> hashCrawledDataCollection;
    public static volatile SingularAttribute<HashData, Integer> hashId;
    public static volatile SingularAttribute<HashData, String> hashUrl;
    public static volatile SingularAttribute<HashData, Integer> iscrawled;

}