package com.vois.poc.util;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.http.HttpHost;

import java.io.File;
import java.util.Objects;

public class JiraUtility {
	
    
    public HttpResponse<JsonNode> attachFile(String requestURL, String proxyIP, int proxyPort, String authorization, File file) throws UnirestException {
        if(Objects.nonNull(proxyIP)){
            Unirest.setProxy(new HttpHost(proxyIP, proxyPort));
        }
    	HttpResponse<JsonNode> response = Unirest.post(requestURL)
    			.header("Accept", "application/json")
    			.header("Authorization", authorization)
    			//.header("X-Atlassian-Token", "no-check")
    			.field("file", file)
    			.asJson();
    	
    	return response;    	
    }
    

            
    
    public void uploadFilesToJira(String filePath,String ticketId) throws UnirestException {
        File uploadFile = new File(filePath);
        String requestURL = "https://gno-poc.atlassian.net/rest/api/3/issue/"+ticketId+"/attachments";
        String proxyIP = null;
        int proxyPort = 8080;
        String authorization = "Basic dHlwZXRvbWFoYW50ZXNoQGdtYWlsLmNvbTpBVEFUVDN4RmZHRjB4U3I0YXVOdmN2QXRDYk5Xa3NPem1PcDFGS25kaWNEVUtkR3FQM3FDaVVUZF9TTFAzYVJHWlBaWHRnZHhJZFhDMm05MVE3ZjdDMzlXTHRZeDJHam5oMDNUcEZlOXdNSURqd1lrbDVYMm5nckZrdnRrT1p6TkJ3Qm1HdjEzWWpCaTVLaTlxZ1hCbnJ1ajk4MnZHSjNnTDN0RU15dWJ2REFYMC1CQ0RXUEl6Vm89MzEwREJFMzg=";
        
        JiraUtility utility = new JiraUtility();
        HttpResponse<JsonNode> response = utility.attachFile(requestURL, proxyIP, proxyPort, authorization, uploadFile);
        
        if(response.getStatus() == 200)
        	System.out.println("File Attached to jira successfully!!!");
        else
        	System.out.println("Error in file attachment to jira!!!, Respose:"+response.getBody());
    }

}
