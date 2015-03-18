package com.anilbhaila.solrJTest;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;

import java.io.IOException;

/**
 * Created by tektak on 3/18/15.
 */
public class Sample {
    public static void main(String[] args){
        String urlString = "http://localhost:8983/solr/gettingstarted";
        SolrClient solr = new HttpSolrClient(urlString);

        SolrQuery parameters = new SolrQuery();
        parameters.set("q", "Gouda");
        try {
            QueryResponse response = solr.query(parameters);
            SolrDocumentList list = response.getResults();
            System.out.println("Search Result:"+list.size());
            System.out.println("==================================");
            for(SolrDocument d:list){
                System.out.println(d);
            }

            SolrInputDocument document = new SolrInputDocument();
            document.addField("id", "552199");
            document.addField("name", "Gouda cheese wheel");
            document.addField("price", "49.99");
            UpdateResponse updateResponse = solr.add(document);
            System.out.println("UpdateResponse:"+updateResponse);
            // Remember to commit your changes!
            solr.commit();
            response = solr.query(parameters);
            list = response.getResults();
            System.out.println("Search Result:"+list.size());
            System.out.println("==================================");
            for(SolrDocument d:list){
                System.out.println(d);
            }
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
