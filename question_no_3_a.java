public static int minProductDiff(int[] nums) {
    int n = nums.length;
    int minDiff = Integer.MAX_VALUE;
    for (int i = 0; i < (1 << n); i++) {
        int prod1 = 1, prod2 = 1;
        int count = 0;
        for (int j = 0; j < n; j++) {
            if ((i & (1 << j)) != 0) {
                prod1 *= nums[j];
                count++;
            } else {
                prod2 *= nums[j];
            }
        }
        if (count == n / 2) {
            minDiff = Math.min(minDiff, Math.abs(prod1 - prod2));
        }
    }
    return minDiff;
}

//////The time complexity of this approach is O(2^n * n), which is exponen
////exponential in the size of the input. However, since n is limited to even length arrays, 
////the actual running time is reasonable for small values of n.///