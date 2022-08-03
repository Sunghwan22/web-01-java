package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FormParser {
  public List<String> parse(String line) {
    List<String> forms = new ArrayList<>();

    Arrays.stream(line.split("&"))
        .map(pair -> pair.split("="))
        .filter(pair -> pair.length == 2)
        .forEach(i -> {
          forms.add(i[1]);
        });

    return forms;
  }
}
