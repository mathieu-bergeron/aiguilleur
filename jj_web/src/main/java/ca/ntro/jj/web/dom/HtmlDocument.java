package ca.ntro.jj.web.dom;

public interface HtmlDocument {
	
	HtmlElements select(String cssQuery);

	void writeHtml(StringBuilder out);

}
