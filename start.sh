#!/bin/bash

cd doclist
mvn war:exploded gwt:generate-module gwt:devmode &
cd ..
cd docpreview
mvn war:exploded gwt:generate-module gwt:devmode &
cd ..
cd platform
mvn war:exploded gwt:generate-module gwt:devmode &
cd ..

