import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EnrolleesByInsCompGenerator {
  private static final Path LOAD_FILE_PATH = Paths.get("src/main/resources/enrollments.csv");
  private static final Path GENERATE_BASE_FILE_PATH = Paths.get("src/main/resources/enrollments");
  private static final String DELIMITTER = ",";

  public static void main(String[] args) throws IOException {
    final List<String> inputData = new ArrayList<>();
    loadData(inputData);
    final List<Enrollee> enrollees = new ArrayList<>();
    for (String str : inputData) {
      String[] token = str.split(DELIMITTER);
      final Enrollee enrollee = new Enrollee();
      enrollee.setUserId(token[0].trim());
      enrollee.setFirstName(token[1].trim());
      enrollee.setLastName(token[2].trim());
      enrollee.setVersion(Integer.parseInt(token[3].trim()));
      enrollee.setInsuranceCompany(token[4].trim());
      enrollees.add(enrollee);
    }

    final Map<String, List<Enrollee>> companies =
        enrollees.stream().collect(Collectors.groupingBy(Enrollee::getInsuranceCompany));

    for (Map.Entry<String, List<Enrollee>> entry : companies.entrySet()) {
      final List<Enrollee> sortedList =
          entry.getValue().stream()
              .sorted(
                  Comparator.comparing(Enrollee::getLastName).thenComparing(Enrollee::getFirstName))
              .collect(Collectors.toList());

      try (BufferedWriter writer =
          Files.newBufferedWriter(Paths.get(GENERATE_BASE_FILE_PATH + entry.getKey() + ".csv"))) {
        for (Enrollee enrollee : sortedList) {
          try {
            writer.write(enrollee.getUserId());
            writer.write(DELIMITTER);
            writer.write(enrollee.getFirstName());
            writer.write(DELIMITTER);
            writer.write(enrollee.getLastName());
            writer.write(DELIMITTER);
            writer.write(String.valueOf(enrollee.getVersion()));
            writer.write(DELIMITTER);
            writer.write(enrollee.getInsuranceCompany());
            writer.newLine();
            writer.flush();
          } catch (IOException ex) {
            ex.printStackTrace();
          }
        }
      } catch (IOException ioException) {
      }
    }
  }

  private static void loadData(final List<String> inputData) throws IOException {
    try (BufferedReader reader = Files.newBufferedReader(LOAD_FILE_PATH)) {
      reader.lines().forEach(inputData::add);
    }
  }
}
