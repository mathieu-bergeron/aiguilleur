package ca.ntro.web;



import ca.ntro.core.mvc.ViewLoader;
import ca.ntro.jj.trace.T;
import ca.ntro.jj.web.dom.HtmlElement;
import ca.ntro.web.dom.HtmlElementJdk;
import ca.ntro.web.mvc.ViewLoaderWeb;

public class ViewLoaderWebJdk extends ViewLoaderWeb {
	
	public ViewLoaderWebJdk() {
		super();
		T.call(this);
	}

	@Override
	protected ViewLoader clone() {
		T.call(this);
		
		ViewLoaderWebJdk clone = new ViewLoaderWebJdk();
		clone.setHtmlUrl(getHtmlUrl());
		clone.setTargetClass(getTargetClass());
		
		return clone;
	}

	@Override
	protected HtmlElement parseHtml(String html) {
		T.call(this);

		return new HtmlElementJdk(HtmlElementJdk.parseHtml(html));
	}

	@Override
	protected void initializeJs(String viewName, HtmlElement viewRootHtmlElement) {
		// XXX: not supported
	}

	@Override
	protected void initializeJs(String viewName, HtmlElement viewRootHtmlElement) {
		// TODO Auto-generated method stub
		
	}
}
