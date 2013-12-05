package biz.buildit.util;


public enum Approval {

	NEW(7),
	PENDINGAPPROVAL(5),
	APPROVED(1),
	REJECTED(9);
	
	private int statusCode;
	private Approval(int code){
		statusCode = code;
	}
	
	public int getStatusCode(){
		return statusCode;
	}
}
