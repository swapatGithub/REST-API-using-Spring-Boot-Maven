package com.controller;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.model.Gene;
import com.model.JData;
import com.model.JDataDeserializer;


@RestController
@RequestMapping("/proj")
public class GeneRestController {
              
        @RequestMapping(value="/oncogenes", method=RequestMethod.GET)
        @ResponseBody
        public JData getOncoGenes(){

        	FileReader fr = null;
	    	BufferedReader br = null;
	    	JData jd = new JData();
	    	try{
	    	fr = new FileReader("GeneData");
	    	br = new BufferedReader(fr);
	    	String line = null;    	
	    	br.readLine();
	    	List<Gene> list = new ArrayList<Gene>();
	    	while((line=br.readLine())!=null){
	    		String[] vals = line.split("\t");
	    		if(vals[2].equals("1")){
	    			Gene gene = new Gene();        		
	        		gene.setEntrezGeneId(Integer.parseInt(vals[0]));
	        		gene.setHugoSymbol(vals[1]);
	        		gene.setOncogene(true);
	        		gene.setTsg(((vals[3].equals("1"))?true:false));
	        		gene.setConseq_term(vals[4]);
	        		gene.setAlteration(vals[5]);
	        		gene.setGenerallyTruncating(((vals[6].equals("1"))?true:false));
	        		gene.setGeneAliases(vals[7].split(","));
	        		list.add(gene);
	    		}    		    		
	    	}
	    	Gene[] genes = list.toArray(new Gene[list.size()]);    		    	 	
	    	jd.setData(genes);
	    	jd.add(linkTo(methodOn(GeneRestController.class).getOncoGenes()).withSelfRel());
	    	}catch (IOException e) {
				e.printStackTrace();
			}
	    	finally{
	    		try{
	    		if(br!=null){
	    			br.close();
	    		}
	    		if(fr!=null){
	    			fr.close();
	    		}
		    	}catch (IOException e1) {
					System.out.println("Error closing file");
				}
	    	}
	        return jd;
	    }

	    @RequestMapping(value="/missingEntrezId", method=RequestMethod.GET)
        @ResponseBody
        public JData getMissingEntrezGenes(){

        	FileReader fr = null;
	    	BufferedReader br = null;
	    	JData jd = new JData();
	    	try{
	    	fr = new FileReader("GeneData");
	    	br = new BufferedReader(fr);
	    	String line = null;    	
	    	br.readLine();
	    	List<Gene> list = new ArrayList<Gene>();
	    	while((line=br.readLine())!=null){
	    		String[] vals = line.split("\t");
	    		if(vals[0].equals("0")){
	    			Gene gene = new Gene();        		
	        		gene.setEntrezGeneId(Integer.parseInt(vals[0]));
	        		gene.setHugoSymbol(vals[1]);
	        		gene.setOncogene(true);
	        		gene.setTsg(((vals[3].equals("1"))?true:false));
	        		gene.setConseq_term(vals[4]);
	        		gene.setAlteration(vals[5]);
	        		gene.setGenerallyTruncating(((vals[6].equals("1"))?true:false));
	        		gene.setGeneAliases(vals[7].split(","));
	        		list.add(gene);
	    		}    		    		
	    	}
	    	Gene[] genes = list.toArray(new Gene[list.size()]);    		    	    	 
	    	jd.setData(genes);
	    	jd.add(linkTo(methodOn(GeneRestController.class).getMissingEntrezGenes()).withSelfRel());
	    	}catch (IOException e) {
				e.printStackTrace();
			}
	    	finally{
	    		try{
	    		if(br!=null){
	    			br.close();
	    		}
	    		if(fr!=null){
	    			fr.close();
	    		}
		    	}catch (IOException e1) {
					System.out.println("Error closing file");
				}
	    	}
	        return jd;
	    }

}