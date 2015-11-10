package module3;

//Java utilities libraries
import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Comparator;
import java.util.List;

//Processing library
import processing.core.PApplet;

//Unfolding libraries
import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.providers.MBTilesMapProvider;
import de.fhpotsdam.unfolding.utils.MapUtils;

//Parsing library
import parsing.ParseFeed;

public class EarthquakeCityMap extends PApplet {

private static final long serialVersionUID = 1L;

private static final boolean offline = false;

public static final float THRESHOLD_MODERATE = 5;

public static final float THRESHOLD_LIGHT = 4;

public static String mbTilesString = "blankLight-1-3.mbtiles";

private UnfoldingMap map;

private String earthquakesURL = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/2.5_week.atom";

public void setup() {

size(950, 600, OPENGL);

if (offline) {

map = new UnfoldingMap(this, 200, 50, 700, 500, new MBTilesMapProvider(mbTilesString));

earthquakesURL = "2.5_week.atom";

}

else {

map = new UnfoldingMap(this, 200, 50, 700, 500, new Google.GoogleMapProvider());

}

map.zoomToLevel(2);

MapUtils.createDefaultEventDispatcher(this, map);

List<Marker> markers = new ArrayList<Marker>();

List<PointFeature> earthquakes = ParseFeed.parseEarthquake(this, earthquakesURL);

int green = color(0,0,255);

int yellow = color(255, 255, 0);
 
int red = color(255,0,0);

for(int i=0; i<earthquakes.size();i++){

PointFeature f = earthquakes.get(i);

System.out.println(f.getProperties());

Object magObj = f.getProperty("magnitude");

float mag = Float.parseFloat(magObj.toString());

SimplePointMarker s= createMarker(earthquakes.get(i));

if(mag<4.0){

s.setColor(green);
s.setRadius(5);

}

else if(mag>=4.0 && mag<5){

s.setColor(yellow);

s.setRadius(10);

}

else if (mag>=5.0){s.setColor(red);

s.setRadius(15);

}

markers.add(s);

}

map.addMarkers(markers);	}

private SimplePointMarker createMarker(PointFeature feature){

return new SimplePointMarker(feature.getLocation());

}public void draw() {

background(10);

map.draw();

addKey();

}

private void addKey(){
	// Remember you can use Processing's graphics methods here
			fill(255, 250, 240);
			rect(25, 50, 150, 250);
			
			fill(0);
			textAlign(LEFT, CENTER);
			textSize(12);
			text("Earthquake Key", 50, 75);
			
			fill(color(255, 0, 0));
			ellipse(50, 125, 15, 15);
			fill(color(255, 255, 0));
			ellipse(50, 175, 10, 10);
			fill(color(0, 0, 255));
			ellipse(50, 225, 5, 5);
			
			fill(0, 0, 0);
			text("5.0+ Magnitude", 75, 125);
			text("4.0+ Magnitude", 75, 175);
			text("Below 4.0", 75, 225);

}

}
