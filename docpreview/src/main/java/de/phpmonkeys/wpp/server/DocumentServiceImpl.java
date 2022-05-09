package de.phpmonkeys.wpp.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import de.phpmonkeys.wpp.client.DocumentService;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class DocumentServiceImpl extends RemoteServiceServlet implements DocumentService {

  public String getPreview(int input) throws IllegalArgumentException {
    return "Document ID: " + input + "\n" + "Some preview here";
  }
}
