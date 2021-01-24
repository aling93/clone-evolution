package github.com.clones;

import lrg.dude.duplication.CodeFragment;

import java.util.Objects;

public class CloneSourceFile {
    private String sourceFile;
    private CodeFragment codeFragment;

    public CloneSourceFile(String sourceFile, CodeFragment codeFragment) {
        this.sourceFile = sourceFile;
        this.codeFragment = codeFragment;
    }

    @Override
    public String toString() {
        return "File " + sourceFile +
                " (" + codeFragment.getBeginLine() + "," +
                codeFragment.getEndLine() + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CloneSourceFile that = (CloneSourceFile) o;

        return sourceFile.equals(that.sourceFile) &&
                new CodeFragmentComparer(codeFragment, that.codeFragment).areEqual();
    }

    @Override
    public int hashCode() {
        return Objects.hash(sourceFile, codeFragment.getBeginLine(), codeFragment.getEndLine());
    }
}
