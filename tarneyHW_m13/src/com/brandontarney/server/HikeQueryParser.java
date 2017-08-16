/** HikeQueryParser Class
 * <p>
 * Contains static methods to extract parameters from an HTTP form query</p>
 *
 * @author Brandon Tarney
 * @since 6/28/2017
 */
package com.brandontarney.server;

import com.brandontarney.server.Rates.HIKE;

public class HikeQueryParser {

    public static HIKE getHike(String hikeString) throws BadQueryStringException {
        HIKE hikeEnum;
        String hikeEnumStr = hikeString.toUpperCase();
        switch (hikeEnumStr) {
            case "HELLROARING PLATEAU":
                hikeEnum = HIKE.HELLROARING;
                break;
            case "GARDINER LAKE":
                hikeEnum = HIKE.GARDINER;
                break;
            case "THE BEATEN PATH":
                hikeEnum = HIKE.BEATEN;
                break;
            default:
                throw new BadQueryStringException("Invalid hike value");
        }
        return hikeEnum;
    }

    
    public static int[] getDate(String dateString) throws BadQueryStringException {
        int[] monthDayYear = new int[3]; 
        String[] monthDayYearStr = dateString.split("/");

        try {
            monthDayYear[0] = Integer.parseInt(monthDayYearStr[0]);
            monthDayYear[1] = Integer.parseInt(monthDayYearStr[1]);
            monthDayYear[2] = Integer.parseInt(monthDayYearStr[2]);
        } catch (NumberFormatException e) {
            throw new BadQueryStringException("Invalid day, month, and/or year value");
        }

        return monthDayYear;
    }
}
