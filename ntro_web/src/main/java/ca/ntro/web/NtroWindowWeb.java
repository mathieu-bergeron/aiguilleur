package ca.ntro.web;

import ca.ntro.core.mvc.NtroContext;
import ca.ntro.core.mvc.NtroView;
import ca.ntro.core.mvc.NtroWindow;
import ca.ntro.jj.Path;
import ca.ntro.jj.log.Log;
import ca.ntro.web.mvc.NtroViewWeb;

public abstract class NtroWindowWeb extends NtroWindow {

	protected abstract HtmlDocument getDocument();

	public void writeHtml(StringBuilder out) {
		T.call(this);

		getDocument().writeHtml(out);
	}

	@Override
	public void installRootView(NtroContext<?,?> context, NtroView rootView) {
		T.call(this);

		HtmlElement body = getDocument().select("body").get(0);

		NtroViewWeb rootViewWeb = (NtroViewWeb) rootView;
		
		if(body.children("*").size() == 0) {

			rootViewWeb.initializeView(context);
			body.appendElement(rootViewWeb.getRootElement());

		} else if(body.children("*").size() == 1) {
			
			HtmlElement rootElement = body.children("*").get(0);

			rootViewWeb.setRootElement(rootElement);

			rootViewWeb.initializeView(context);
			
			Log.info("[installRootView] using existing rootView: " + rootViewWeb);

		}else {
			
			Log.warning("[installRootView] body must have at most 1 child node");
		}
	}

	public void setCurrentPath(Path path) {
		T.call(this);

		HtmlElement body = getDocument().select("body").get(0);

		//body.setAttribute("current-path", path.toString());
	}


}
