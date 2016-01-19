/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifunnyscrapper.thread;

import com.ifunnyscrapper.dao.IfunnyDataDao;
import com.ifunnyscrapper.entity.HashData;
import com.ifunnyscrapper.utility.FetchPageWithProxy;
import java.util.concurrent.Callable;

/**
 *
 * @author GLB-214
 */
public class Threading implements Callable<String> {

    IfunnyDataDao objIfunnyDataDao = null;
    FetchPageWithProxy objfetchPageWithProxy = new FetchPageWithProxy();
    HashData objHashdata = null;

    public Threading(HashData objHashdata, IfunnyDataDao objIfunnyDataDao) {
        this.objHashdata = objHashdata;
        this.objIfunnyDataDao = objIfunnyDataDao;
    }

    @Override
    public String call() {

        return null;
    }

}
