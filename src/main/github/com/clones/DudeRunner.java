package github.com.clones;

import lrg.dude.duplication.*;

public class DudeRunner {
    private final DudeParameters parameters;

    public DudeRunner(DudeParameters parameters) {
        this.parameters = parameters;
    }

    public Duplication[] run() {
        DuDe.fileExtensions = parameters.getFileExtensionsToAnalyze();
        Processor processor = new SuffixTreeProcessor(parameters.getProjectFolderPath(), new IdenticalCompareStrategy());

        processor.setParams(parameters);

        //noinspection CallToThreadRun
        processor.run();

        return processor.getSearchResults();
    }
}
