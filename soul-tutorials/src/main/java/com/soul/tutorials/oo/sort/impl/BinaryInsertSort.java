package com.soul.tutorials.oo.sort.impl;

import com.soul.tutorials.oo.sort.ISortNumber;

public class BinaryInsertSort implements ISortNumber {

	@Override
	public int[] sortASC(int[] intArray) {
		int length = intArray.length;
		for (int i = 0; i < length; i++) {
			int temp = intArray[i];
			int left = 0;
			int right = i - 1;
			int mid;
			// 通过二分查找到位置，为什么可以用二分查找，是因为i位置前面所有的数据都已经排好序(从下标0开始排序的)
			while (left <= right) {
				mid = (left + right) / 2;
				if (temp < intArray[mid]) {
					right = mid - 1;
				} else {
					// 这里同时处理等于的情况，因为是按照left下标来后移的
					left = mid + 1;
				}
			}
			// 把left之后包括left的元素后移一位
			for (int j = i - 1; j >= left; j--) {
				intArray[j + 1] = intArray[j];
			}
			// left == i 表示该元素不用移动
			if (left != i) {
				intArray[left] = temp;
			}
		}
		return intArray;
	}

}
