package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Arrays;

import com.model.Gene;
import com.model.JData;
import com.model.JDataDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.controller.GeneRestController;

@SpringBootApplication
@ComponentScan(basePackageClasses = GeneRestController.class)
public class DemoApplication {

	public static void main(String[] args) throws Exception{

        String text = readUrl("http://oncokb.org/api/v1/genes/673/variants");        
        GsonBuilder gsonbuilder = new GsonBuilder(); 
        gsonbuilder.registerTypeAdapter(JData.class, new JDataDeserializer());
        Gson gson = gsonbuilder.create();
        JData datatoLoad = gson.fromJson(text, JData.class);
        createTSVfile(datatoLoad);
		SpringApplication.run(DemoApplication.class, args);
	}
	private static void createTSVfile(JData data){
    	FileWriter fw = null;
    	PrintWriter pw = null;
    	try{    		
    	fw = new FileWriter("GeneData");	
    	pw = new PrintWriter(fw);
    	pw.println("entrezGeneId\thugoSymbol\toncogene\ttsg\tconseq_term\talteration\tisGenerallyTruncating\tgeneAliases");
    	Gene[] genes = data.getData();
    	for(int i=0;i<genes.length;i++){
    		pw.println(genes[i].getEntrezGeneId()+"\t"+genes[i].getHugoSymbol()+"\t"+((genes[i].isOncogene())?1:0)+"\t"+((genes[i].isTsg())?1:0)+"\t"+genes[i].getConseq_term()+"\t"+genes[i].getAlteration()+"\t"+((genes[i].isGenerallyTruncating())?1:0)+"\t"+String.join(",", genes[i].getGeneAliases()));    		
    	}
    	
    	}catch(IOException e){
    		System.out.println("Error creating or writing to file");
    	}
    	finally{
    		if(pw!=null){
    			pw.close();
    		}
    		if(fw!=null){
    			try{
    			fw.close();
    			}catch (IOException e1) {
					System.out.println("Error closing file");
				}
    		}
    	}
    }
    private static String readUrl(String urlString) throws Exception{
        BufferedReader reader = null;
        try {
            URL url = new URL(urlString);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1)
                buffer.append(chars, 0, read); 

            return buffer.toString();
        }
        finally {
            if (reader != null)
                reader.close();
        }
    }
}
