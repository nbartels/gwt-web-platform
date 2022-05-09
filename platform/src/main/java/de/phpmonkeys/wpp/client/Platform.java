package de.phpmonkeys.wpp.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.IFrameElement;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import elemental2.core.JsNumber;
import elemental2.core.JsObject;
import elemental2.dom.*;
import jsinterop.base.Js;

import java.util.concurrent.atomic.AtomicReference;

public class Platform implements EntryPoint {

  public static final String DOC_PREVIEW_URL = "http://127.0.0.1:9998/DocPreview.html";
  public static final String DOC_LIST_URL = "http://127.0.0.1:9999/DocList.html";
  public static final String UNDOCK = "Undock";
  public static final String DOCK = "Dock";

  private JsObject lastId;
  private Window doclistWindow;
  private Window previewWindow;

  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {
    initIFrames();

    initButtons();

    initPreviewWindow();

    // event dispatcher
    DomGlobal.window.addEventListener("message", evt -> {
      MessageEvent<JsObject> messageEvent = ((MessageEvent<JsObject>) evt);
      GWT.log("[Platform] message event received: " + messageEvent.data);
      GWT.log("[Platform] message event origin: " + messageEvent.origin);
      lastId = messageEvent.data;
      previewWindow.postMessage(messageEvent.data, "*");
    });
  }

  private void initPreviewWindow() {
    HTMLIFrameElement element = ((HTMLIFrameElement) DomGlobal.document.getElementById("preview"));
    previewWindow = element.contentWindow;
  }

  private void initButtons() {
    Button doclistBtn = new Button(UNDOCK);
    RootPanel.get("doclistButton").add(doclistBtn);
    doclistBtn.addClickHandler(clickEvent -> {
      if (doclistBtn.getHTML().equals(UNDOCK)) {
        RootPanel.get("docListIframe").getElement().getStyle().setVisibility(Style.Visibility.HIDDEN);
        doclistWindow = DomGlobal.window.open(DOC_LIST_URL);
        doclistBtn.setHTML(DOCK);
      } else {
        RootPanel.get("docListIframe").getElement().getStyle().setVisibility(Style.Visibility.VISIBLE);
        doclistWindow.close();
        doclistBtn.setHTML(UNDOCK);
      }
    });
    Button previewBtn = new Button(UNDOCK);
    RootPanel.get("previewButton").add(previewBtn);
    previewBtn.addClickHandler(clickEvent -> {
      if (previewBtn.getHTML().equals(UNDOCK)) {
        RootPanel.get("previewIframe").getElement().getStyle().setVisibility(Style.Visibility.HIDDEN);
        previewWindow = DomGlobal.window.open(DOC_PREVIEW_URL);
        previewBtn.setHTML(DOCK);
      } else {
        RootPanel.get("previewIframe").getElement().getStyle().setVisibility(Style.Visibility.VISIBLE);
        previewWindow.close();
        HTMLIFrameElement element = ((HTMLIFrameElement) DomGlobal.document.getElementById("preview"));
        previewWindow = element.contentWindow;
        previewBtn.setHTML(UNDOCK);
      }

      if (lastId != null)
        previewWindow.postMessage(lastId, "*");
    });
  }

  private void initIFrames() {
    IFrameElement elementDocList = RootPanel.get("doclist").getElement().cast();
    elementDocList.setSrc(DOC_LIST_URL);
    elementDocList.getStyle().setWidth(95, Style.Unit.PCT);
    IFrameElement elementPreview = RootPanel.get("preview").getElement().cast();
    elementPreview.setSrc(DOC_PREVIEW_URL);
    elementPreview.getStyle().setWidth(95, Style.Unit.PCT);
  }
}
