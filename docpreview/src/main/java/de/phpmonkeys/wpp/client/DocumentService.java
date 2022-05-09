package de.phpmonkeys.wpp.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("preview")
public interface DocumentService extends RemoteService {
  String getPreview(int id) throws IllegalArgumentException;
}
