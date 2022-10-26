public class AddressTest {

	public static void main(String[] args) {
		
		System.out.println("***TESTING VALID ADDRESSES");
		Address a1 = new Address("50 Phelan Ave", "Ocean Campus", "San Francisco", "CA", "94112");
		System.out.println(a1);
		Address a2 = new Address("50 Phelan Ave", "", "San Francisco", "CA", "94112"); // no street2
		System.out.println(a2);
		
		System.out.println("\n***TESTING INVALID ADDRESSES");
		String[][] badAddresValuesArray = {
				{"50 Phelan Ave", "Ocean Campus", "San Francisco", "California", "94112"}, // too long state
				{"50 Phelan Ave", "Ocean Campus", "San Francisco", "", "94112"}, // no state
				{"", "", "San Francisco", "CA", "94112"}, // no street 1
				{"50 Phelan Ave", "Ocean Campus", "", "", "94112"}, // no city
				{"50 Phelan Ave", "Ocean Campus", "Llanfairpwllgwyngyllgogerychwyrndrobwllllantysiliogogogoch", "", "94112"}, // too long city
				{"50 Phelan Ave", "", "San Francisco", "CA", "9411"}, // too short zip
				{"50 Phelan Ave", "", "San Francisco", "CA", "941121"}}; // too long zip
		for(String[] badAddressValue : badAddresValuesArray) {				
			Address badAddress = null;
			try {
				badAddress = new Address(badAddressValue[0], badAddressValue[1], badAddressValue[2], badAddressValue[3], badAddressValue[4]);
			} catch(IllegalArgumentException ex) {
				System.out.println(ex.getMessage());
			} finally {
				System.out.println("Should print null: " + badAddress);
			}
		}
	}
}
