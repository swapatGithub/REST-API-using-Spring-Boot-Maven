package com.model;

public class Gene {
    
    private String hugoSymbol;
	private String[] geneAliases;
	private int entrezGeneId;
	private boolean oncogene;
	private boolean tsg;
	private String conseq_term;
	private String alteration;
	private boolean isGenerallyTruncating;

    public Gene() {}    

	public String getHugoSymbol() {
		return hugoSymbol;
	}

	public String[] getGeneAliases() {
		return geneAliases;
	}

	public void setGeneAliases(String[] geneAliases) {
		this.geneAliases = geneAliases;
	}

	public void setHugoSymbol(String hugoSymbol) {
		this.hugoSymbol = hugoSymbol;
	}

	public void setEntrezGeneId(int entrezGeneId) {
		this.entrezGeneId = entrezGeneId;
	}

	public void setOncogene(boolean oncogene) {
		this.oncogene = oncogene;
	}

	public void setTsg(boolean tsg) {
		this.tsg = tsg;
	}

	public String getConseq_term() {
		return conseq_term;
	}

	public void setConseq_term(String conseq_term) {
		this.conseq_term = conseq_term;
	}

	public void setAlteration(String alteration) {
		this.alteration = alteration;
	}

	public void setGenerallyTruncating(boolean isGenerallyTruncating) {
		this.isGenerallyTruncating = isGenerallyTruncating;
	}

	public int getEntrezGeneId() {
		return entrezGeneId;
	}

	public boolean isOncogene() {
		return oncogene;
	}

	public boolean isTsg() {
		return tsg;
	}

	public String getAlteration() {
		return alteration;
	}

	public boolean isGenerallyTruncating() {
		return isGenerallyTruncating;
	}

}