package ca.ntro.web;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import ca.ntro.jj.assertions.MustNot;
import ca.ntro.jj.log.Log;
import ca.ntro.jj.trace.T;
import ca.ntro.jj.web.dom.HtmlDocument;
import ca.ntro.web.dom.HtmlDocumentJdk;

public class NtroWindowServer extends NtroWindowWeb implements Cloneable {
	
	private HtmlDocumentJdk document;

	public NtroWindowServer(HtmlDocumentJdk document) {
		super();
		T.call(this);
		
		this.document = document;
	}
	
	public NtroWindowServer(String indexHtmlPath) {
		super();
		T.call(this);
		
        loadDocument(indexHtmlPath);
	}

	private void loadDocument(String indexHtmlPath) {
		T.call(this);

		InputStream stream = NtroWindowServer.class.getResourceAsStream(indexHtmlPath);

        MustNot.beNull(stream);

        try {

            Document jsoupDocument = Jsoup.parse(stream, StandardCharsets.UTF_8.name(), indexHtmlPath);
            document = new HtmlDocumentJdk(jsoupDocument);

        } catch (IOException e) {

        	Log.fatalError("FATAL: cannot load " + indexHtmlPath, e);

        }

        MustNot.beNull(document);
	}

	@Override
	protected HtmlDocument getDocument() {
		T.call(this);

        return document;
	}

	@Override
	public NtroWindowServer clone() {
		return new NtroWindowServer(document.clone());
	}
}
