package jma.models;

import com.google.gson.Gson;

public class GPSRecord {

    private String _id;
    private String userId;
    private Double latituede;
    private Double longitude;
    private Double precision;
    
    
    public String get_id() {
        return _id;
    }
    public void set_id(String _id) {
        this._id = _id;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public Double getLatituede() {
        return latituede;
    }
    public void setLatituede(Double latituede) {
        this.latituede = latituede;
    }
    public Double getLongitude() {
        return longitude;
    }
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
    public Double getPrecision() {
        return precision;
    }
    public void setPrecision(Double precision) {
        this.precision = precision;
    }
    
    public boolean isValid() {
        return this.latituede != null && this.longitude != null;
    }
    
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
