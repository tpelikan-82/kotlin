package com.kotlin.example.algoritm

/**
 * O(n*n)
 */
fun bubbleSort(arr: IntArray) {
    val n = arr.size

    // Outer loop iterates through each pass (n-1 passes are required)
    for (i in 0 until n - 1) {
        var swapped = false

        // Inner loop compares adjacent elements and performs the swap.
        // We subtract 'i' because the last 'i' elements are already in their correct,
        // sorted position and don't need to be checked again.
        for (j in 0 until n - 1 - i) {
            // Compare the current element with the next element
            if (arr[j] > arr[j + 1]) {

                // Swap arr[j] and arr[j + 1]
                val temp = arr[j]
                arr[j] = arr[j + 1]
                arr[j + 1] = temp

                println("swap: ${arr.joinToString()}")

                swapped = true
            }
        }

        // Optimization: If no two elements were swapped by the inner loop,
        // then the array is already sorted, and we can stop early.
        if (!swapped) {
            break
        }
    }
}

/*
    O(n*n)
 */
fun insertionSort(arr: IntArray) {
    val n = arr.size

    // Start from the second element (index 1), as the first element (index 0)
    // is considered trivially sorted.
    for (i in 1 until n) {
        val key = arr[i]
        var j = i - 1

        // Move elements of arr[0..i-1], that are greater than the key,
        // to one position ahead of their current position.
        while (j >= 0 && arr[j] > key) {
            arr[j + 1] = arr[j]
            j--
        }
        // Insert the key into its correct position.
        arr[j + 1] = key
        println("swap: ${arr.joinToString()}")
    }
}

/*
    O(n*logn)
 */
fun mergeSort(arr: IntArray) {
    // Start the recursive sorting process on the entire array.
    mergeSortRecursive(arr, 0, arr.size - 1)
}

/**
 * Merges two sorted subarrays of arr[].
 * The first subarray is arr[l ..m].
 * The second subarray is arr[m+1..r].
 */
private fun merge(arr: IntArray, l: Int, m: Int, r: Int) {
    // Calculate sizes of the two temporary arrays
    val n1 = m - l + 1
    val n2 = r - m

    // Create temporary arrays (L for left, R for right)
    val L = IntArray(n1)
    val R = IntArray(n2)

    // Copy data to temp arrays L[] and R[]
    for (i in 0 until n1) {
        L[i] = arr[l + i]
    }
    for (j in 0 until n2) {
        R[j] = arr[m + 1 + j]
    }

    // Initial indices of first, second subarrays, and merged subarray
    var i = 0 // Initial index of first subarray (L)
    var j = 0 // Initial index of second subarray (R)
    var k = l // Initial index of merged subarray (arr)

    // Merge the temp arrays back into arr[l..r]
    while (i < n1 && j < n2) {
        if (L[i] <= R[j]) {
            arr[k] = L[i]
            i++
        } else {
            arr[k] = R[j]
            j++
        }
        k++
    }

    // Copy remaining elements of L[] if any
    while (i < n1) {
        arr[k] = L[i]
        i++
        k++
    }

    // Copy remaining elements of R[] if any
    while (j < n2) {
        arr[k] = R[j]
        j++
        k++
    }
}

/**
 * Main recursive function that sorts arr [l .. r] using merge().
 */
private fun mergeSortRecursive(arr: IntArray, l: Int, r: Int) {
    if (l < r) {
        // Find the middle point. Uses (r - l) / 2 to prevent overflow.
        val m = l + (r - l) / 2

        // Sort first and second halves recursively
        mergeSortRecursive(arr, l, m)
        mergeSortRecursive(arr, m + 1, r)

        // Merge the sorted halves
        merge(arr, l, m, r)
    }
}

/**
 * O(n*logn)
 */
fun heapSort(arr: IntArray) {
    val n = arr.size

    // 1. Build a Max Heap (rearrange array)
    // Start from the last non-leaf node (n/2 - 1) and heapify backwards up to the root (index 0).
    for (i in n / 2 - 1 downTo 0) {
        heapify(arr, n, i)
    }

    println("heapify array: ${arr.joinToString()}")

    // 2. One by one extract elements from the heap (sort the array)
    // The loop runs from the last element down to the second element (index 1).
    for (i in n - 1 downTo 1) {
        // Move current root (maximum element) to the end of the array
        val temp = arr[0]
        arr[0] = arr[i]
        arr[i] = temp

        // Call heapify on the reduced heap (the remaining 'i' elements)
        heapify(arr, i, 0)
    }
}

/**
 * To heapify a subtree rooted with node 'i' which is an index in arr[].
 * 'n' is the size of the heap (which decreases during the sort process).
 *
 * @param arr The array containing the heap data.
 * @param n The size of the heap (number of elements remaining in the unsorted portion).
 * @param i The root index of the subtree to heapify.
 */
private fun heapify(arr: IntArray, n: Int, i: Int) {
    var largest = i // Initialize largest as root
    val l = 2 * i + 1 // left child index
    val r = 2 * i + 2 // right child index

    // If left child is larger than root and within the heap bounds
    if (l < n && arr[l] > arr[largest]) {
        largest = l
    }

    // If right child is larger than current largest and within the heap bounds
    if (r < n && arr[r] > arr[largest]) {
        largest = r
    }

    // If the largest is not the root
    if (largest != i) {
        // Swap the root with the largest element
        val swap = arr[i]
        arr[i] = arr[largest]
        arr[largest] = swap

        // Recursively heapify the affected sub-tree
        heapify(arr, n, largest)
    }
}

/**
 * best O(n*logn) worst O(n*n)
 */
fun quickSort(arr: IntArray) {
    // Start the recursive sorting process on the entire array.
    quickSortRecursive(arr, 0, arr.size - 1)
}

/**
 * Main recursive function that implements the Quick Sort logic.
 *
 * @param arr The array being sorted.
 * @param low The starting index for the current sub-array.
 * @param high The ending index for the current sub-array.
 */
private fun quickSortRecursive(arr: IntArray, low: Int, high: Int) {
    if (low < high) {
        // pi is partitioning index, arr[pi] is now at the right place
        val pi = partition(arr, low, high)

        // Recursively sort elements before partition and after partition
        quickSortRecursive(arr, low, pi - 1)
        quickSortRecursive(arr, pi + 1, high)
    }
}

/**
 * This function implements the Lomuto partition scheme:
 * It takes the last element as the pivot, places the pivot element at its correct
 * position in the sorted array, and places all smaller elements (<= pivot) to the
 * left of the pivot and all greater elements to the right.
 *
 * @param arr The array to partition.
 * @param low The starting index of the sub-array.
 * @param high The ending index of the sub-array (the pivot index).
 * @return The index of the pivot element after partitioning.
 */
private fun partition(arr: IntArray, low: Int, high: Int): Int {
    val pivot = arr[high] // Choose the last element as the pivot
    var i = low - 1 // Index of smaller element

    for (j in low until high) {
        // If current element is smaller than or equal to pivot
        if (arr[j] <= pivot) {
            i++ // increment index of smaller element

            // Swap arr[i] and arr[j]
            val temp = arr[i]
            arr[i] = arr[j]
            arr[j] = temp
        }
    }

    // Swap arr[i+1] (the element right after the smaller elements) and arr[high] (the pivot)
    val temp = arr[i + 1]
    arr[i + 1] = arr[high]
    arr[high] = temp

    return i + 1 // Return the pivot's final position
}

/**
 * O(nlogn) and O(n5/4),
 */
fun shellSort(arr: IntArray) {
    val n = arr.size

    // Start with a large gap (n/2), then reduce the gap by dividing by 2 in each iteration.
    var gap = n / 2

    // Loop through the decreasing gap sizes until gap is 0 (the final pass is a standard Insertion Sort)
    while (gap > 0) {
        // Perform a gapped Insertion Sort for the current gap size.
        // The loop starts at 'gap' because elements before that are already the first element
        // in their respective gapped sub-arrays.
        for (i in gap until n) {
            // Store the value of arr[i] to be inserted into its correct position
            val temp = arr[i]

            // Shift earlier gap-sorted elements up until the correct location for arr[i] is found
            var j = i
            while (j >= gap && arr[j - gap] > temp) {
                // Move element down the list
                arr[j] = arr[j - gap]
                j -= gap
            }

            // Place the stored element (temp) in its correct gapped position
            arr[j] = temp
        }

        // Reduce the gap for the next pass
        gap /= 2
    }
}


/**
 * Main function to demonstrate the Bubble Sort implementation.
 */
fun main() {
    val numbers = intArrayOf(64, 34, 25, 12, 22, 11, 90)
    println("Original array: ${numbers.joinToString()}")

    // Perform the sort
//    bubbleSort(numbers)
//    insertionSort(numbers);
//    mergeSort(numbers)
//    heapSort(numbers)
    quickSort(numbers)

    println("Sorted array: ${numbers.joinToString()}")
}
