package github.com.clones.tests.playground;

@SuppressWarnings("ALL")
public class SameFileDuplication {
    public double max(double n1, double n2, double n3) {
        if( n1 >= n2 && n1 >= n3)
            return n1;

        else if (n2 >= n1 && n2 >= n3)
            return n2;

        else
            return n3;
    }

    public double maxOfThree(double n1, double n2, double n3) {
        if( n1 >= n2 && n1 >= n3)
            return n1;

        else if (n2 >= n1 && n2 >= n3)
            return n2;

        else
            return n3;
    }
}
