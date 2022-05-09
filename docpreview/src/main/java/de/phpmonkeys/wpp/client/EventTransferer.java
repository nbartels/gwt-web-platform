package de.phpmonkeys.wpp.client;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

@JsType(isNative=true, name="Object", namespace= JsPackage.GLOBAL)
public class EventTransferer {

  public String evt;

  public int docId;

}