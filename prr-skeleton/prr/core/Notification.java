package prr.core;
import java.io.Serializable;

public class Notification implements Serializable{
	
	private String _arrivalId;
	private String _departureId;
	private NotificationType _notificationType;

	private static final long serialVersionUID = 202208091753L;
	/**
	 * Constructor.
	 */

	public Notification(String type, String idDeparture, String idArrival) {
		setNotificationType(type);
		_arrivalId = idArrival;
		_departureId = idDeparture;
	}


	/**
     * Gets the Notification Type
     * 
     * @return the Notification Type
     */

	public NotificationType getNotificationType(){
		return _notificationType;
	}

	public String getNotificationArrivalId(){
		return _arrivalId;
	}

	public String getNotificationDepartureId(){
		return _departureId;
	}

	public void setNotificationType(String type){
		if(NotificationType.B2I.name().equals(type)){
			_notificationType = NotificationType.B2I;
		}
		if(NotificationType.S2I.name().equals(type)){
			_notificationType = NotificationType.S2I;
		}
		if(NotificationType.O2I.name().equals(type)){
			_notificationType = NotificationType.O2I;
		}
		if(NotificationType.O2S.name().equals(type)){
			_notificationType = NotificationType.O2S;
		}
	}

	/**
     * Transforms the Notification into a String
     * 
     * @return the string corresponding to the Notification
     */

	@Override
	public String toString() {
		return (this._notificationType.name()) + "|" +_arrivalId;
	}
}