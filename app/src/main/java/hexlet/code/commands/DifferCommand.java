package hexlet.code.commands;

import hexlet.code.Differ;
import picocli.CommandLine;

import java.util.Arrays;
import java.util.concurrent.Callable;

@CommandLine.Command(name = "gendiff",
        mixinStandardHelpOptions = true,
        version = "gendiff 0.1",
        description = "Compares two configuration files and shows a difference.")
public final class DifferCommand implements Callable<Integer> {

    @CommandLine.Option(names = {"-f", "--format"},
            defaultValue = "stylish",
            description = "output format [default: ${DEFAULT-VALUE}]",
            paramLabel = "format")
    private String format;

    @CommandLine.Parameters(index = "0", description = "path to first file", paramLabel = "filePath1")
    private String filePath1;

    @CommandLine.Parameters(index = "1", description = "path to second file", paramLabel = "filePath2")
    private String filePath2;

    @Override
    public Integer call() {
        try {
            String diff = Differ.generate(filePath1, filePath2, format);
            System.out.println(diff);
            return 0;
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
            return 1;
        }
    }
}
