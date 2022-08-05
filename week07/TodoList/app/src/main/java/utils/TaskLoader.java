package utils;

import models.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskLoader {
  public List<Task> loadTask() throws FileNotFoundException {
    List<Task> tasks = new ArrayList<>();

    File file = new File("Tasks.csv");

    Scanner scanner = new Scanner(file);

    while (scanner.hasNextLine()) {
      String line = scanner.nextLine();

      Task task = parseTask(line);

      tasks.add(task);
    }
    return tasks;
  }

  public void writeTask(List<Task> tasks) throws IOException {
    FileWriter fileWriter = new FileWriter("Tasks.csv");

    for (Task task : tasks) {
      String line = task.toCsvRow();

      fileWriter.write(line + "\n");
    }
    fileWriter.close();
  }

  private Task parseTask(String line) {
    String[] word = line.split(",");
    String content = word[0];
    int registrationNumber = Integer.parseInt(word[1]);

    return new Task(content, registrationNumber);
  }
}
