package com.brandontarney.server;

import com.brandontarney.client.MyService;
import com.brandontarney.shared.FieldVerifier;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class MyServiceImpl extends RemoteServiceServlet implements MyService {

	public String greetServer(String hikeStr, String dateStr, String durationStr, String partySizeStr)
			throws IllegalArgumentException {

		//String serverInfo = getServletContext().getServerInfo();
		//String userAgent = getThreadLocalRequest().getHeader("User-Agent");

		// Escape data from the client to avoid cross-site script
		// vulnerabilities.
		// input = escapeHtml(input);
		// userAgent = escapeHtml(userAgent);

		// TODO NORMAL LOGIC
		/*
		double hikeCost = 0.0;
		try {
			Rates.HIKE hike = HikeQueryParser.getHike(hikeStr);
			Rates rate = new Rates(hike);

			hikeCost = partySizeInt * rate.getCost();
		} catch (BadQueryStringException exception) {
			return "BAD QUERY STRING";
		}

		 * int[] monthDayYear = HikeQueryParser.getDate(date); int month =
		 * monthDayYear[0]; int day = monthDayYear[1]; int year =
		 * monthDayYear[2];
		 * 
		 * 
		 * Rates rate = this.getRate(hike, duration, year, month, day);
		 * 
		 * if (rate.isValidDates()) { int totalCost = rate.getCost() *
		 * partySize; //FORMAT RATES as HTML & RETURN
		 * System.out.println("rate IS valid"); return formatHtmlResponse(rate,
		 * "VALID HIKE RATE"); } else { //FORMAT ERROR/RATES as HTML & RETURN
		 * System.out.println("rate is INvalid"); return
		 * formatHtmlResponse(rate, "INVALID HIKE RATE"); }
		 * 
		 */

		//return "HIKE COST = " + Double.toString(hikeCost);
		return "HIKE COST = ";
	}

	/**
	 * Escape an html string. Escaping data received from the client helps to
	 * prevent cross-site script vulnerabilities.
	 * 
	 * @param html
	 *            the html string to escape
	 * @return the escaped string
	 */
	private String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;");
	}
}
