import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class HomeworkM11PartTwo {

    public static void main(String[] args) {

        long currentMS = System.currentTimeMillis();

        List<Product> productList = createList();

        System.out.format("Elapsed %,dms%n", (System.currentTimeMillis() - currentMS));

        int numberOfProducts = productList.size();
        System.out.println("Number of products \t\t\t\t\t73425: " + numberOfProducts);

        // QUESTION 1: How many total chemicals appear across all products? 
        // Example: Product1 contains Chemical1, Chemical2 and Product2 contains Chemical2 and Chemical3
        //          count would be 4 total chemicals
        int numberChemicalsInAllProducts = productList.stream().mapToInt(p -> p.getChemicals().size()).sum();
        System.out.println("Number of chemicals in all products \t\t\t81791: " + numberChemicalsInAllProducts);

        // QUESTION 2: How many different companies are in the dataset?
        int numberCompanies = (int) productList.stream().map(Product::getCompany).distinct().count();
        System.out.println("Number of companies \t\t\t\t\t549: " + numberCompanies);

        // QUESTION 3: How many products have 4 or more chemicals?
        long numberProductsFourOrMore = productList.stream().filter(p -> p.getChemicals().size() >= 4).count();
        System.out.println("Number of products with 4 or more chemicals \t\t193 :" + numberProductsFourOrMore);

        //  QUESTION 4: Create a Map with key = company name and value = list of products for that company.
        // Hint: use Collectors.groupingBy!
        Map<String, List<Product>> companyProductMap = productList.stream().collect(Collectors.groupingBy(Product::getCompany));

        // checks that the map is correct; consider adding additional checks!
        System.out.println("Number of companies (keys) \t\t\t\t549: " + companyProductMap.size());
        System.out.println("Number of products for Aloette Cosmetics Inc. \t\t77: " + (companyProductMap.get("Aloette Cosmetics Inc.")).size());
        System.out.println("Number of products for Yves Rocher Inc. \t\t416: " + (companyProductMap.get("Yves Rocher Inc.")).size());
        System.out.println("Number of products for label.m USA INC \t\t\t4: " + (companyProductMap.get("label.m USA INC")).size());


        // QUESTION 5: Use the map you created above. Which company has the most products?
        // Hint: Use .max(Comparator) and define the Comparator to compare companies based on size of their lists.
        String companyMostProducts = companyProductMap.entrySet().stream().collect(Collectors.toMap(
                Map.Entry::getKey,
                e -> e.getValue().size())
        ).entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey();
        System.out.println("Company with most products \t\t\t\tLOreal USA: " + companyMostProducts);

        // Use this map for the next questions.
        // This is a map with key = chemical name and value = list of products that contain that chemical.
        // This code creates a map with all chemicals and empty lists.
        Map<String, List<Product>> chemicalProductMap = new HashMap<>();
        productList.stream().forEach(
                product ->
                        product.getChemicals().stream().forEach(
                                chemicalName -> chemicalProductMap.putIfAbsent(chemicalName, new ArrayList<Product>()))
        );


        // QUESTION 6: Fill the lists (the value) of the map above.
        // Hint: Use a nested stream (one stream of productList and then a separate stream for each list of each product).
        // YOUR CODE HERE- NO RETURN VALUE
        productList.stream().forEach(
                product ->
                        product.getChemicals().stream().forEach(chemical -> chemicalProductMap.get(chemical).add(product)));

        // checks that the map is correct; consider adding additional checks!
        System.out.println("Number of products that contain Formaldehyde (gas) \t\t121: " + (chemicalProductMap.get("Formaldehyde (gas)")).size());
        System.out.println("Number of products that contain Lauramide DEA \t\t\t20: " + (chemicalProductMap.get("Lauramide DEA")).size());
        System.out.println("Number of products that contain Arsenic (inorganic oxides) \t1: " + (chemicalProductMap.get("Arsenic (inorganic oxides)")).size());

        // QUESTION 7: Which chemical appears in the most products?
        // Hint: use max(Comparator) again. Define your comparator to compare chemical names based on the size of the list of products.
        String mostOccurringChemical = chemicalProductMap.entrySet().stream().collect(Collectors.toMap(
                Map.Entry::getKey,
                e -> e.getValue().size())
        ).entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey();
        System.out.println("Most common chemical \t\t\t\t\t\tTitanium dioxide: " + mostOccurringChemical);
        System.out.println("It appears in this many products \t\t\t\t67898: " + (chemicalProductMap.get("Titanium dioxide")).size());

    }


    private static List<Product> createList() {
        String line = "";
        String fileName = "ChemicalData.csv";

        List<Product> list = new ArrayList<>();

        Map<Integer, List<Integer>> digestCodeMap = new HashMap<>();

        try (BufferedReader fileScan = new BufferedReader(new FileReader(fileName))) {

            int lineNo = 0;

            while ((line = fileScan.readLine()) != null) {

                // Skip column headers
                if (lineNo++ == 0) {
                    continue;
                }

                String[] columnValues = line.split(",");

                String name = columnValues[0];
                String colorScentFlavor = columnValues[1];
                String company = columnValues[2];
                String brand = columnValues[3];
                String categoryString = columnValues[4];
                String chemicalName = columnValues[5];

                Category category = null;
                Category[] categories = Category.values();
                for (Category categoryOption : categories) {
                    if (categoryString.equalsIgnoreCase(categoryOption.getDescription())) {
                        category = categoryOption;
                        break;
                    }
                }

                Product product = new Product(name, company, brand, colorScentFlavor, category);

                boolean processed = false;

                int digestCode = product.getDigestCode();
                if (digestCodeMap.containsKey(digestCode)) {
                    List<Integer> indexList = digestCodeMap.get(digestCode);

                    for (Integer index : indexList) {
                        if (list.get(index).equals(product)) {
                            Product existingProduct = list.get(index);
                            existingProduct.addChemical(chemicalName);
                            processed = true;
                            break;
                        }
                    }
                }

                if (!processed) {
                    product.addChemical(chemicalName);
                    list.add(product);

                    List<Integer> indexList = new ArrayList<>();
                    indexList.add(list.size() - 1);
                    digestCodeMap.put(digestCode, indexList);
                }
            }
        } catch (IOException ex) {
            System.out.println(line);
            ex.printStackTrace();
        }
        return list;
    }

}
