
public class ActionSpace {
	
	private String name;
	private int tax;
	
	public ActionSpace(String name) {
		
		this.name = name;
		
		//System.out.println("The " + this.name + " action space has been initialized.");
	}
	
	public ActionSpace(String name, int tax) {

		//Taxes
		
		this.name = name;
		this.tax = tax;
		
		//System.out.println("The " + this.name + " action space has been initialized.");
	}
	
	public String getName() {
		
		return name;
	}
}
