package com.equitativa;

import com.equitativa.home.HomePage;
import de.agilecoders.wicket.core.Bootstrap;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;

/**
 * Application object for your web application.
 * If you want to run this application without deploying, run the Start class.
 * 
 * @see com.equitativa.Start#main(String[])
 */
public class WicketApplication extends WebApplication
{
	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class<? extends WebPage> getHomePage()
	{
		return HomePage.class;
	}

	/**
	 * @see org.apache.wicket.Application#init()
	 */
	@Override
	public void init()
	{
		super.init();
		Bootstrap.install(this);
		// Disable Content Security Policy for Testing Purposes
		getCspSettings().blocking().disabled();

		// needed for the styling used by the quickstart
//		getCspSettings().blocking()
//			.add(CSPDirective.STYLE_SRC, CSPDirectiveSrcValue.SELF)
//			.add(CSPDirective.STYLE_SRC, "https://fonts.googleapis.com/css")
//			.add(CSPDirective.FONT_SRC, "https://fonts.gstatic.com");

		// add your configuration here
	}
}
