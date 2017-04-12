package com.model;

import java.lang.reflect.Type;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class JDataDeserializer implements JsonDeserializer<JData>{

	public JData deserialize(JsonElement json,Type typeOfT,JsonDeserializationContext context) throws JsonParseException {
		JData jd = new JData();
		JsonArray array = json.getAsJsonArray();
		Gene[] genes = new Gene[array.size()];
		for(int i=0;i<genes.length;i++){
			Gene genedata = new Gene();
			JsonObject gene = array.get(i).getAsJsonObject();
			if(gene.get("gene").getAsJsonObject().has("entrezGeneId")){
				genedata.setEntrezGeneId(gene.get("gene").getAsJsonObject().get("entrezGeneId").getAsInt());
			}
			else{
				genedata.setEntrezGeneId(0);
			}
			genedata.setHugoSymbol(gene.get("gene").getAsJsonObject().get("hugoSymbol").getAsString());
			genedata.setAlteration((gene.get("alteration").getAsString()));
			genedata.setConseq_term(gene.get("consequence").getAsJsonObject().get("term").getAsString());
			JsonArray aliasarr = gene.get("gene").getAsJsonObject().get("geneAliases").getAsJsonArray();
			String[] aliases = new String[aliasarr.size()];
			for(int j=0;j<aliases.length;j++){
				aliases[j]=aliasarr.get(j).getAsString();
			}
			genedata.setGeneAliases(aliases);
			genedata.setGenerallyTruncating(gene.get("consequence").getAsJsonObject().get("isGenerallyTruncating").getAsBoolean());
			genedata.setOncogene(gene.get("gene").getAsJsonObject().get("oncogene").getAsBoolean());
			genedata.setTsg(gene.get("gene").getAsJsonObject().get("tsg").getAsBoolean());
			genes[i] = genedata;
		}
		jd.setData(genes);
		return jd;
	}
}
