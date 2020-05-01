package pokerequitychallenge.utils;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PermutationCalculator {

    public static ArrayList<ArrayList<Integer>> findKSizePermutationsOfNIndexes(int k, int n) {
        ArrayList<Integer> a = (ArrayList<Integer>) IntStream.range(0, n).boxed().collect(Collectors.toList());
        ArrayList<ArrayList<Integer>> allPermutations = new ArrayList<>();

        enumerate(a, n, k, allPermutations);

        return allPermutations;
    }

    // a is the original array
    // n is the original array size
    // k is the number of elements in each permutation
    // allPermutations is all different permutations
    private static void enumerate(ArrayList<Integer> a, int n, int k, ArrayList<ArrayList<Integer>> allPermutations) {
        if (k == 0) {
            ArrayList<Integer> singlePermutation = new ArrayList<>();
            for (int i = n; i < a.size(); i++) {
                singlePermutation.add(a.get(i));
            }
            allPermutations.add(singlePermutation);
            return;
        }

        for (int i = 0; i < n; i++) {
            swap(a, i, n - 1);
            enumerate(a, n - 1, k - 1, allPermutations);
            swap(a, i, n - 1);
        }
    }

    private static void swap(ArrayList<Integer> a, int i, int j) {
        Integer temp = a.get(i);
        a.set(i, a.get(j));
        a.set(j, temp);
    }
}