package com.mycompany;

import de.agilecoders.wicket.core.Bootstrap;
import org.apache.wicket.csp.CSPDirective;
import org.apache.wicket.csp.CSPDirectiveSrcValue;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;

/**
 * Application object for your web application.
 * If you want to run this application without deploying, run the Start class.
 * 
 * @see com.mycompany.Start#main(String[])
 */
public class WicketApplication extends WebApplication
{
	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class<? extends WebPage> getHomePage()
	{
		return TodoListPage.class;
	}

	/**
	 * @see org.apache.wicket.Application#init()
	 */
	@Override
	public void init()
	{
		super.init();
		Bootstrap.install(this);
		getCspSettings().blocking()
				.add(CSPDirective.STYLE_SRC, "https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css")
				.add(CSPDirective.SCRIPT_SRC, "https://code.jquery.com/jquery-3.5.1.slim.min.js");

		// needed for the styling used by the quickstart
//		getCspSettings().blocking()
//			.add(CSPDirective.STYLE_SRC, CSPDirectiveSrcValue.SELF)
//			.add(CSPDirective.STYLE_SRC, "https://fonts.googleapis.com/css")
//			.add(CSPDirective.FONT_SRC, "https://fonts.gstatic.com");

		// add your configuration here
	}
}
