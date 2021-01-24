package github.com.clones;

public class Main {
    private static final String projectToAnalyze = "C:\\Repos\\clone-evolution\\src\\main\\github\\com\\clones\\tests\\playground";

    public static void main(String[] args) {

        var dudeParameters = getDudeParameters();
        var dudeRunner = new DudeRunner(dudeParameters);
        var results = dudeRunner.run();

        var cloneExtractor = new CloneExtractor();
        var clones = cloneExtractor.extract(results);

        if (clones.size() == 0)
            System.out.println("No clones found");
        else {
            for (var clone : clones) {
                System.out.println(clone);
            }
        }
    }

    private static DudeParameters getDudeParameters() {
        // TODO: read from config file

        return new DudeParameters(projectToAnalyze,
                null,
                2,
                10,
                2,
                true);
    }
}
