package biz.buildit.util;


public enum Approval {

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
	
	public static Approval getApproval(int code){
		switch(code){
		case 5: return PENDINGAPPROVAL;
		case 1: return APPROVED;
		case 9: return REJECTED;
		default: return PENDINGAPPROVAL;
		}
	}
}
