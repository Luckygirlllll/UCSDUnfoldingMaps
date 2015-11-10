package module6;

import java.util.List;

import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.marker.SimpleLinesMarker;
import processing.core.PConstants;
import processing.core.PGraphics;

/** 
 * A class to represent AirportMarkers on a world map.
 *   
 * @author Adam Setters and the UC San Diego Intermediate Software Development
 * MOOC team
 *
 */
public class AirportMarker extends CommonMarker {
	public static List<SimpleLinesMarker> routes;
	
	public AirportMarker(Feature Kiev) {
		super(((PointFeature)Kiev).getLocation(), Kiev.getProperties());
	
	}
	
	
	double d = 50.345;
	float x= (float) d;
	double b=30.894722;
	float y = (float)b;
	
	
	@Override
	public void drawMarker(PGraphics pg, float x, float y) {
		double d = 440;
		 x= (float) d;
		double b=150;
		 y = (float)b; 
		pg.fill(0, 0, 0);
	pg.ellipse(x ,y, 15, 15);
		pg.text("Kiev", x+3, y-33);
		pg.textSize(12);
		

		
		
	}
	
	

	@Override
	public void showTitle(PGraphics pg, float x, float y) {
	
	
		 // show rectangle with title
		
		// show routes
		
		
	}
	
}
