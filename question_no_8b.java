public static int findKthMissingEvenNumber(int[] arr, int k) {
    int missingCount = 0;
    for (int i = 1; i < arr.length && missingCount < k; i++) {
        int diff = arr[i] - arr[i-1] - 1;
        missingCount += diff / 2;
        if (missingCount >= k) {
            return arr[i-1] + (k - (missingCount - diff / 2)) * 2;
        }
    }
    return arr[arr.length-1] + (k - missingCount) * 2;
}