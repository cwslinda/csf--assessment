package vttp2022.csf.assessment.server.models;

import org.bson.Document;

// Do not modify this class
public class LatLng {
	private float latitude;
	private float longitude;

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}
	public float getLatitude() {
		return this.latitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}
	public float getLongitude() {
		return this.longitude;
	}


	// public static LatLng create(Document d){
	// 	LatLng latLng = new LatLng();
		
	// }
}
