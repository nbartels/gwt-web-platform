package de.phpmonkeys.wpp.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface DocumentServiceAsync {
  void getPreview(int id, AsyncCallback<String> callback)
      throws IllegalArgumentException;
}
