import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringJoiner;
import java.util.stream.Collectors;

/**
 * Read the enrollee information from a csv file, seperate the enrollees by insurance company to
 * it's own files.
 */
public class EnrolleesByInsCompGenerator {
  private static final Path LOAD_FILE_PATH = Paths.get("src/main/resources/enrollments.csv");
  private static final Path GENERATE_BASE_FILE_PATH = Paths.get("src/main/resources/enrollments-");
  private static final String DELIMITTER = ",";

  public static void main(String[] args) throws IOException {
    final List<String> inputData = loadData();
    final Set<Enrollee> enrollees = buildEnrollees(inputData);

    final Map<String, List<Enrollee>> enrolleesByInsCompany =
        enrollees.stream().collect(Collectors.groupingBy(Enrollee::getInsuranceCompany));

    for (Map.Entry<String, List<Enrollee>> entry : enrolleesByInsCompany.entrySet()) {
      // filter the enrollees with same userId
      final Map<String, Enrollee> filteredEnrolleesByUserId =
          entry.getValue().stream()
              .collect(
                  Collectors.toMap(
                      Enrollee::getUserId,
                      e -> e,
                      (a, b) -> a.getVersion() > b.getVersion() ? a : b));

      // sort the enrollees by lastname and first name
      final List<Enrollee> sortedList =
          filteredEnrolleesByUserId.values().stream()
              .sorted(
                  Comparator.comparing(Enrollee::getLastName).thenComparing(Enrollee::getFirstName))
              .collect(Collectors.toList());

      writeData(sortedList, entry.getKey());
    }
  }

  /**
   * Write the {@link Enrollee}'s data to the files
   *
   * @param enrollees {@link Enrollee}'s to write
   * @param insComp String represents Insurance company
   */
  private static void writeData(List<Enrollee> enrollees, String insComp) {
    try (BufferedWriter writer =
        Files.newBufferedWriter(Paths.get(GENERATE_BASE_FILE_PATH + insComp + ".csv"))) {
      for (Enrollee enrollee : enrollees) {
        try {
          writer.write(
              new StringJoiner(", ")
                  .add(enrollee.getUserId())
                  .add(enrollee.getFirstName())
                  .add(enrollee.getLastName())
                  .add(enrollee.getVersion().toString())
                  .add(enrollee.getInsuranceCompany())
                  .toString());
          writer.newLine();
          writer.flush();
        } catch (IOException ex) {
          ex.printStackTrace();
        }
      }
    } catch (IOException ioException) {
      // TODO add logger
    }
  }

  /**
   * Build the {@link Enrollee}'s from input rows
   *
   * @param inputData each row in the file
   * @return {@link Set} of enrollee
   */
  private static Set<Enrollee> buildEnrollees(final List<String> inputData) {
    final Set<Enrollee> enrollees = new HashSet<>();
    for (String row : inputData) {
      String[] tokens = row.split(DELIMITTER);

      if (tokens.length != 5) {
        // TODO Add logger
        continue;
      }
      final Enrollee enrollee = new Enrollee();
      enrollee.setUserId(tokens[0].trim());
      enrollee.setFirstName(tokens[1].trim());
      enrollee.setLastName(tokens[2].trim());
      try {
        enrollee.setVersion(Integer.parseInt(tokens[3].trim()));
      } catch (NumberFormatException e) {
        // TODO Add logger
        continue;
      }
      enrollee.setInsuranceCompany(tokens[4].trim());
      enrollees.add(enrollee);
    }

    return enrollees;
  }

  /**
   * Loads the data from the file.
   *
   * @return the list of string's
   * @throws IOException
   */
  private static List<String> loadData() throws IOException {
    final List<String> inputData = new ArrayList<>();
    try (BufferedReader reader = Files.newBufferedReader(LOAD_FILE_PATH)) {
      reader.lines().forEach(inputData::add);
    }
    return inputData;
  }
}
