package github.com.clones;

public class Main {
    private static final String projectToAnalyze = "C:\\Repos\\clone-evolution\\src\\main\\github\\com\\clones\\tests\\playground";

    public static void main(String[] args) {

        var dudeParameters = getDudeParameters();
        var dudeRunner = new DudeRunner(dudeParameters);
        var results = dudeRunner.run();
        System.out.format("Got %d duplications\n", results.length);

        var cloneExtractor = new CloneExtractor();
        var clones = cloneExtractor.extract(results);

        if (clones.size() == 0)
            System.out.println("No clones found");
        else {
            for (var clone : clones) {
                // TODO: add toString() to Clone.cs
                System.out.println("Clone: " + clone.getReferenceFile());
                System.out.println("Clone type: " + clone.getCloneType());

                for (var sourceFile : clone.getSourceFiles()) {
                    System.out.println("File: " + sourceFile);
                }
                System.out.println("-------------------------------------\n\n");
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
