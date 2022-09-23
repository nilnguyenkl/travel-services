package example.payload.response;

public class ProfileUserResponse {
	private int numOrder;
	private ProfileResponse infor;
	public int getNumOrder() {
		return numOrder;
	}
	public void setNumOrder(int numOrder) {
		this.numOrder = numOrder;
	}
	public ProfileResponse getInfor() {
		return infor;
	}
	public void setInfor(ProfileResponse infor) {
		this.infor = infor;
	}
}
