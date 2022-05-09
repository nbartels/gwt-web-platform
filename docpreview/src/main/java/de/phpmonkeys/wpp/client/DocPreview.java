package de.phpmonkeys.wpp.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import elemental2.dom.DomGlobal;
import elemental2.dom.MessageEvent;
import jsinterop.base.Js;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class DocPreview implements EntryPoint {

  /**
   * Create a remote service proxy to talk to the server-side Greeting service.
   */
  private final DocumentServiceAsync documentService = GWT.create(DocumentService.class);

  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {
    TextArea textarea = new TextArea();
    textarea.setWidth("500px");
    DecoratorPanel panel = new DecoratorPanel();
    panel.add(textarea);
    RootPanel.get("previewContainer").add(panel);

    DomGlobal.window.addEventListener("message", evt -> {
      MessageEvent<Object> messageEvent = (MessageEvent<Object>) evt;
      Object mData = messageEvent.data;
      EventTransferer transferer = Js.uncheckedCast(mData);
      int inDocId = transferer.docId;
      String testString = transferer.evt;
      GWT.log("[Preview] docID received: " + mData);
      documentService.getPreview((Integer) inDocId, new AsyncCallback<String>() {
        @Override public void onFailure(Throwable throwable) {
          GWT.log("[Preview] document error: " + mData);
          textarea.setValue("Fehler!");
        }

        @Override public void onSuccess(String s) {
          GWT.log("[Preview] document received: " + mData);
          textarea.setValue(s);
        }
      });
    });
  }
}
