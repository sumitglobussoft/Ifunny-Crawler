/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifunnyscrapper.utility;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author GLB-214
 */
public class GetRequestHandler {

    public String doGetRequest(URL obj) throws Exception {

        String response = "";
        try {
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");

            if (con.getResponseCode() != 200) {
                return response;
            }

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                response = response + "\n" + inputLine;
            }
            in.close();
        } catch (Exception ex) {
        }

        return response;
    }
}
