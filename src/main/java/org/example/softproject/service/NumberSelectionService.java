package org.example.softproject.service;


import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * Service for finding the Nth minimum number using a custom selection algorithm.
 */
@Service
public class NumberSelectionService {

    private final ExcelFileService excelFileService;

    public NumberSelectionService(ExcelFileService excelFileService) {
        this.excelFileService = excelFileService;
    }

    /**
     * Finds the Nth minimum number in the specified Excel file using a custom selection algorithm.
     *
     * @param filePath path to the Excel file
     * @param n        the position of the minimum to find (1-based)
     * @return the Nth minimum number
     * @throws IOException if there's an error reading the file
     * @throws IllegalArgumentException if n is invalid or file doesn't contain enough numbers
     */
    public int findNthMinimumNumber(String filePath, int n) throws IOException {
        List<Integer> numbers = excelFileService.readNumbersFromExcel(filePath);

        if (n <= 0 || n > numbers.size()) {
            throw new IllegalArgumentException("Invalid value for N: " + n);
        }

        return quickSelect(numbers, 0, numbers.size() - 1, n - 1);
    }

    /**
     * Implements the Quickselect algorithm to find the Nth smallest element.
     *
     * @param list  the list of numbers
     * @param left  left index of the current partition
     * @param right right index of the current partition
     * @param n     the index of the element to find (0-based)
     * @return the Nth smallest element
     */
    private int quickSelect(List<Integer> list, int left, int right, int n) {
        if (left == right) {
            return list.get(left);
        }

        int pivotIndex = partition(list, left, right);

        if (n == pivotIndex) {
            return list.get(n);
        } else if (n < pivotIndex) {
            return quickSelect(list, left, pivotIndex - 1, n);
        } else {
            return quickSelect(list, pivotIndex + 1, right, n);
        }
    }

    /**
     * Partitions the list for the Quickselect algorithm.
     *
     * @param list  the list to partition
     * @param left  left index
     * @param right right index
     * @return the pivot index
     */
    private int partition(List<Integer> list, int left, int right) {
        int pivotValue = list.get(right);
        int storeIndex = left;

        for (int i = left; i < right; i++) {
            if (list.get(i) < pivotValue) {
                swap(list, i, storeIndex);
                storeIndex++;
            }
        }

        swap(list, storeIndex, right);
        return storeIndex;
    }

    /**
     * Swaps two elements in a list.
     *
     * @param list the list
     * @param i    first index
     * @param j    second index
     */
    private void swap(List<Integer> list, int i, int j) {
        int temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }
}
