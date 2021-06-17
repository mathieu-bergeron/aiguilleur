package ca.ntro.web.dom;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import ca.ntro.jj.trace.T;
import ca.ntro.jj.web.dom.HtmlDocument;
import ca.ntro.jj.web.dom.HtmlElements;


public class HtmlDocumentJdk implements HtmlDocument, Cloneable {
	
	private Document document;
	
	public HtmlDocumentJdk(Document document) {
		this.document = document;
	}

	@Override
	public HtmlElements select(String cssQuery) {
		T.call(this);

		Elements elements = document.select(cssQuery);
		
		return new HtmlElementsJdk(elements);
	}

	@Override
	public void writeHtml(StringBuilder out) {
		T.call(this);
		
		out.append(document.outerHtml());
	}

	@Override
	public HtmlDocumentJdk clone() {
		return new HtmlDocumentJdk(document.clone());
	}
	
	@Override 
	public String toString() {
		return "document@" + System.identityHashCode(document);
	}
}
