package example.payload.response;

public class ProfileUserResponse {
	
	private int numOrderWaiting;
	private int numOrderApproved;
	private int numOrderExperienced;
	
	private ProfileResponse infor;
	
	public int getNumOrderWaiting() {
		return numOrderWaiting;
	}
	
	public void setNumOrderWaiting(int numOrderWaiting) {
		this.numOrderWaiting = numOrderWaiting;
	}
	
	public int getNumOrderApproved() {
		return numOrderApproved;
	}
	
	public void setNumOrderApproved(int numOrderApproved) {
		this.numOrderApproved = numOrderApproved;
	}
	
	public int getNumOrderExperienced() {
		return numOrderExperienced;
	}
	
	public void setNumOrderExperienced(int numOrderExperienced) {
		this.numOrderExperienced = numOrderExperienced;
	}
	
	public ProfileResponse getInfor() {
		return infor;
	}
	
	public void setInfor(ProfileResponse infor) {
		this.infor = infor;
	}
}
