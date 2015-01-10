package com.curso.android.ciant.worldwondersapp.integrator;

import java.net.URI;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class BaseIntegrator {

    /**
     * Run a GET call against a rest api service.
     * P.S.: This method must be called in background.
     *
     * @param path - the path of the service
     * @return the response in json format
     */
    protected String doGetRequest(final String protocol, final String host, final String path) {

        String responseStr = null;

        try {
            URI uri = URIUtils.createURI(protocol,
                    host, 0,
                    path, null, null);
            
            HttpGet get = new HttpGet(uri);
            HttpClient client = new DefaultHttpClient();
            HttpResponse response = client.execute(get);
            HttpEntity entity = response.getEntity();
            responseStr = EntityUtils.toString(entity);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return responseStr;
    }

}