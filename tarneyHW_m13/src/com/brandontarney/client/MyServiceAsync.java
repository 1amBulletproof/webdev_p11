package com.brandontarney.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>MyService</code>.
 */
public interface MyServiceAsync {
	void greetServer(String hike, String date, String duration, String partySize, AsyncCallback<String> callback) throws IllegalArgumentException;
}
