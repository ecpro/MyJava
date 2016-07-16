
public class Customer {
	private int custNO;
	private String custName;
	private String custAddr;
	private static int billNo=0;
	
	static{
		billNo++;
	}
	public int getCustNO() {
		return custNO;
	}
	public void setCustNO(int custNO) {
		this.custNO = custNO;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getCustAddr() {
		return custAddr;
	}
	public void setCustAddr(String custAddr) {
		this.custAddr = custAddr;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	
	void display()
	{
		System.out.println("BillNo: "+billNo+"customer name: "+custName+"\n"+"customer #: "+custNO+"\n"+"customer address: "+custAddr);
	}
	
}
