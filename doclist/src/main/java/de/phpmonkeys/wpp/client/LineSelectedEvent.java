package de.phpmonkeys.wpp.client;

import com.google.gwt.event.shared.GwtEvent;

public class LineSelectedEvent extends GwtEvent<LineSelectedEventHandler> {
  private final int docId;

  public static final Type<LineSelectedEventHandler> TYPE = new Type<>();

  public LineSelectedEvent(int finalRow) {
    docId = finalRow;
  }

  @Override public Type<LineSelectedEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override protected void dispatch(LineSelectedEventHandler handler) {
    handler.onHandle(this);
  }

  public int getDocId() {
    return docId;
  }
}
