package utils;

import models.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileLoader {

  public List<Task> loadTasks(String path, IdentifierManager identifierManager)
      throws FileNotFoundException {
    File file = new File(path);

    Scanner scanner = new Scanner(file);

    List<Task> tasks = new ArrayList<>();

    while (scanner.hasNextLine()) {
      String line = scanner.nextLine();

      String[] split = line.split(",");

      int identifier = Integer.parseInt(split[0]);
      String content = split[1];

      tasks.add(new Task(identifier, content));
    }

    identifierManager.setInitialIdentifierCount(tasks.size());

    return tasks;
  }

  public void saveTasks(String path, List<Task> tasks) throws IOException {
    FileWriter fileWriter = new FileWriter(path);

    for (Task task : tasks) {
      fileWriter.write(task.identifier() + "," + task.content() + "\n");
    }

    fileWriter.close();
  }
}
