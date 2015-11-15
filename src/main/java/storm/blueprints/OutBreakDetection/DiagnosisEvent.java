package storm.buleprints.OutBreakDetection;

import java.io.Serializable;

public class DiagnosisEvent implements Serializable{
	private static final long SerialVersionUID = 1L;
	public double lat;
	public double lng;
	public long time;
	public String diagnosisCode;
	
	public DiagnosisEvent(double lat, double lng, long time, String diagnosisCode){
		super();
		this.time=time;
		this.lat=lat;
		this.lng=lng;
		this.diagnosisCode=diagnosisCode;
	}

}
