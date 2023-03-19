import JavaClasses.Json;

public class Main {
    public static void main(String[] args) {
        Json json = new Json();
        System.out.println("file exists? : " + json.fileExists(json.getPerson1Path()));
        System.out.println("String takes from local JSON file: " + json.getStringFromFile(json.getPerson1Path()));

    }
}
