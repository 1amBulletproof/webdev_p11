package com.brandontarney.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface MyService extends RemoteService {
	String greetServer(String hike, String date, String duration, String partySize) throws IllegalArgumentException;
}
