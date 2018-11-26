public abstract class Mark {
    private static final int A2 = 95;
    private static final int A1 = 90;
    private static final int B3 = 85;
    private static final int B2 = 80;
    private static final int B1 = 75;
    private static final int C3 = 70;
    private static final int C2 = 65;
    private static final int C1 = 60;
    private static final int D3 = 55;
    private static final int D2 = 50;

    public static double toGpa(double score) {
        if (score < D2) return 0;

        else if (score < D3) return 1.00;

        else if (score < C1) return 1.33;

        else if (score < C2) return 1.67;

        else if (score < C3) return 2.00;

        else if (score < B1) return 2.33;

        else if (score < B2) return 2.67;

        else if (score < B3) return 3.00;

        else if (score < A1) return 3.33;

        else if (score < A2) return 3.67;

        else return 4.00;
    }

    public static String toMark(double score) {
        if (score < D2) return "F";

        else if (score < D3) return "D";

        else if (score < C1) return "D+";

        else if (score < C2) return "C-";

        else if (score < C3) return "C";

        else if (score < B1) return "C+";

        else if (score < B2) return "B-";

        else if (score < B3) return "B";

        else if (score < A1) return "B+";

        else if (score < A2) return "A-";

        else return "A";
    }
}