import java.util.List;

public class CreditCardProcessor {

    private static Strategy getStrategy(String filename) {
        if (filename.endsWith(".json")) {
            return new JSONStrategy();
        } else if (filename.endsWith(".csv")) {
            return new CSVStrategy();
        } else if (filename.endsWith(".xml")) {
            return new XMLStrategy();
        } else {
            throw new IllegalArgumentException("Unsupported file format");
        }
    }

    public static void processFile(String inputFilename, String outputFilename) {
        try {
            Strategy Strategy = getStrategy(inputFilename);

            List<CreditCard> creditCards = Strategy.readCardsFromFile(inputFilename);
            Strategy.writeCardsToFile(creditCards, outputFilename);

            System.out.println("File processed Successfully.");
        } catch (Exception e) {
            System.err.println("File processing errored due to: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java CreditCardApp <inputFile> <outputFile>");
            return;
        }

        processFile(args[0], args[1]);
    }
}
