import java.util.Arrays;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
class Kata {
    public static int consecutive(final int[] arr)  {
        if (arr == null || arr.length <= 1) return 0;

        int min = Arrays.stream(arr).min().getAsInt();
        int max = Arrays.stream(arr).max().getAsInt();

        int totalNeeded = max - min + 1;
        return totalNeeded - arr.length;
    }
}