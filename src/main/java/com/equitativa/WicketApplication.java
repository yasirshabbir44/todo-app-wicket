package com.equitativa;

import com.equitativa.home.HomePage;
import com.equitativa.temp.Temp;
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
		return Temp.class;
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
	}
}
