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

		int[] monthDayYear = new int[3];
		double hikeCost = -1;
		String details = "";

		try {
			int partySizeInt = Integer.parseInt(partySizeStr);

			Rates.HIKE hike = HikeQueryParser.getHike(hikeStr);
			Rates rate = new Rates(hike);
			
			monthDayYear = HikeQueryParser.getDate(dateStr);
			int month = monthDayYear[0];
			int day = monthDayYear[1];
			int year = monthDayYear[2];

			BookingDay bookingDay = new BookingDay(year, month, day);
			rate.setBeginDate(bookingDay);

			int durationInt = Integer.parseInt(durationStr);
			rate.setDuration(durationInt);

			String valid = "";
			if (rate.isValidDates()) {
				hikeCost = rate.getCost() * partySizeInt;
				valid = "VALID";
			} else {
				hikeCost = -1;
				valid = "INVALID";
			}
			details = ("<b>" + valid + " COST = $" + Double.toString(hikeCost) + "</b><br>Details: " + rate.getDetails()
					+ "<br>HIKE: " + hikeStr + "<br>DATE: " + dateStr + "<br>DURATION: " + durationStr + "<br>PEOPLE: "
					+ partySizeStr);
		} catch (BadQueryStringException exception) {
			return "BAD QUERY STRING: " + exception.getMessage();
		} catch (NumberFormatException exception) {
			return "BAD NUMBER FORMAT: " + exception.getMessage();
		}
		return details;
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
