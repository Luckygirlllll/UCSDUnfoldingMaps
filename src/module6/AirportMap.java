package module6;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.data.ShapeFeature;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.marker.SimpleLinesMarker;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import de.fhpotsdam.unfolding.utils.MapUtils;
import de.fhpotsdam.unfolding.geo.Location;
import parsing.ParseFeed;
import processing.core.PApplet;

/** An applet that shows airports (and routes)
 * on a world map.  
 * @author Adam Setters and the UC San Diego Intermediate Software Development
 * MOOC team
 *
 */
public class AirportMap extends PApplet {
	
	UnfoldingMap map;
	private List<Marker> airportList;
	List<Marker> routeList;
	 public int source;
	 public int dest;
	 ShapeFeature route;
	 private List <Marker> airportListnew;
	
	
	public void setup() {
		// setting up PAppler
		size(800,600, OPENGL);
		
		// setting up map and default events
		map = new UnfoldingMap(this, 50, 50, 750, 550);
		MapUtils.createDefaultEventDispatcher(this, map);
		
		// get features from airport data
		List<PointFeature> features = ParseFeed.parseAirports(this, "airports.dat");
		
		// list for markers, hashmap for quicker access when matching with routes
		airportList = new ArrayList<Marker>();
		HashMap<Integer, Location> airports = new HashMap<Integer, Location>();
		
		// create markers from features
		for(PointFeature feature : features) {
			AirportMarker m = new AirportMarker(feature);
	
			m.setRadius(5);
			airportList.add(m);
			
			// put airport in hashmap with OpenFlights unique id for key
			airports.put(Integer.parseInt(feature.getId()), feature.getLocation());
		
		}
		
		
		// parse route data
		List<ShapeFeature> routes = ParseFeed.parseRoutes(this, "routes.dat");
		routeList = new ArrayList<Marker>();
		for(ShapeFeature route : routes) {
			
			// get source and destination airportIds
			int source = Integer.parseInt((String)route.getProperty("source"));
			int dest = Integer.parseInt((String)route.getProperty("destination"));
			
			
			
			// get locations for airports on route
			
			if(airports.containsKey(source) && airports.containsKey(dest)) {
				if(source==193){route.addLocation(airports.get(source));
			}
				route.addLocation(airports.get(dest));	
				
			}
			
			SimpleLinesMarker sl = new SimpleLinesMarker(route.getLocations(), route.getProperties());
			System.out.println(sl.getProperties());
			
			//UNCOMMENT IF YOU WANT TO SEE ALL ROUTES
			routeList.add(sl);
		}
		
		Location berlinLocation = new Location(52.5, 13.4);
		Location dublinLocation = new Location(53.35, -6.26);
		Location parisLocation = new Location(49.012779, 2.55);
		Location spainLocation =new Location (41.727778, 0.535833);
		Location istanbulLocation = new Location(41.506111, 32.088611);
		Location hanoiLocation = new Location (21.040975, 105.886011);
		
		
		SimplePointMarker berlinMarker = new SimplePointMarker(berlinLocation);
		SimplePointMarker dublinMarker = new SimplePointMarker(dublinLocation);
		SimplePointMarker parisMarker = new SimplePointMarker(parisLocation);
		SimplePointMarker spainMarker = new SimplePointMarker(spainLocation);
		SimplePointMarker istanbulMarker = new SimplePointMarker(istanbulLocation);
		SimplePointMarker hanoiMarker = new SimplePointMarker(hanoiLocation);
		
		berlinMarker.setColor(color(255, 0, 0, 100));
		berlinMarker.setStrokeColor(color(255, 0, 0));
		berlinMarker.setStrokeWeight(1);
		dublinMarker.setColor(color(255, 0, 0, 100));
		dublinMarker.setStrokeColor(color(255, 0, 0));
		dublinMarker.setStrokeWeight(1);
		parisMarker.setColor(color(255, 0, 0, 100));
		parisMarker.setStrokeColor(color(255, 0, 0));
		parisMarker.setStrokeWeight(1);
		spainMarker.setColor(color(255, 0, 0, 100));
		spainMarker.setStrokeColor(color(255, 0, 0));
		spainMarker.setStrokeWeight(1);
		istanbulMarker.setColor(color(255, 0, 0, 100));
		istanbulMarker.setStrokeColor(color(255, 0, 0));
		istanbulMarker.setStrokeWeight(1);
		hanoiMarker.setColor(color(255, 0, 0, 100));
		hanoiMarker.setStrokeColor(color(255, 0, 0));
		hanoiMarker.setStrokeWeight(1);
		
		
		
		//UNCOMMENT IF YOU WANT TO SEE ALL ROUTES
		map.addMarkers(routeList);
		map.addMarkers(berlinMarker, dublinMarker, parisMarker, spainMarker, istanbulMarker, hanoiMarker);
		
		map.addMarkers(airportList);
		}
		

	
	public void draw() {
		background(0);
		map.draw();
		
	}
	

}
