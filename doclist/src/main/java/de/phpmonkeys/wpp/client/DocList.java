package de.phpmonkeys.wpp.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.RootPanel;
import elemental2.dom.DomGlobal;
import elemental2.dom.Window;

public class DocList implements EntryPoint, LineSelectedEventHandler {

  private final EventBus bus = new SimpleEventBus();

  public void onModuleLoad() {
    bus.addHandler(LineSelectedEvent.TYPE, this);
    Grid grid = new Grid(20, 3);

    int numRows = grid.getRowCount();
    for (int row = 0; row < numRows; row++) {
      grid.setHTML(row, 0, "ID: " + row);
      grid.setHTML(row, 1, "Subject: " + row);
      Button btn = new Button("Select");
      int finalRow = row;
      btn.addClickHandler(clickEvent -> bus.fireEvent(new LineSelectedEvent(finalRow)));
      grid.setWidget(row, 2, btn);
    }

    grid.setWidth("80%");

    RootPanel.get("doclistContainer").add(grid);
  }

  @Override public void onHandle(LineSelectedEvent event) {
    Window window = DomGlobal.window;
    // iframe
    if (window.parent != null) {
      window = window.parent;
    }
    // window
    if (window.opener != null) {
      window = window.opener;
    }

    GWT.log("[DocList] button clicked: " + event.getDocId());
    GWT.log("[DocList] postMessage with: " + event.getDocId());
    EventTransferer evtt = new EventTransferer();
    evtt.docId = event.getDocId();
    evtt.evt = "LineSelectedEvent";
    window.postMessage(evtt, "*");
  }
}
