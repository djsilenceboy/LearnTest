
package dj.test.sorting;

public class BucketSort extends AbstractSorting
{
	private static final Integer maxValue = 99;

	public String getMethodName(){
		return "Bucket Sort";
	}

	@Override
	public Integer[] sort(Integer[] data, boolean output){
		Integer[] bucket = new Integer[maxValue + 1];
		int i, j, k;

		if (output) {
			printData(data);
		}

		for (i = 0; i < bucket.length; i++) {
			bucket[i] = 0;
		}

		for (i = 0; i < data.length; i++) {
			bucket[data[i]]++;

			if (output) {
				System.out.printf("Bucket[data[%2d]=%2d] = %2d\n", i, data[i], bucket[data[i]]);
			}
		}

		k = 0;
		for (i = 0; i < bucket.length; i++) {
			if (bucket[i] > 0) {
				for (j = 0; j < bucket[i]; j++) {
					data[k++] = i;
				}
			}
		}

		if (output) {
			printData(data);
		}

		return data;
	}
}
