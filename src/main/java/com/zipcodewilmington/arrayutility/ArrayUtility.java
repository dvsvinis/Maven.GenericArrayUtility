package com.zipcodewilmington.arrayutility;

import com.sun.tools.javac.util.ArrayUtils;

import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by leon on 3/6/18.
 */
public class ArrayUtility <T>{

    T[] genericArray;

    public ArrayUtility(T[] genericArray) {
        this.genericArray = genericArray;
    }


    public T[] merge(T[] arrayToMerge) {
        ArrayList<T> arrayList = new ArrayList<>();
        arrayList.addAll(Arrays.asList(genericArray));
        arrayList.addAll(Arrays.asList(arrayToMerge));
        T[] mergedArray = (T[]) arrayList.toArray();

        return mergedArray;
    }


    public Integer getNumberOfOccurrences(T valueToEvaluate) {
        Integer count = 0;
        for (T t : genericArray) {
            if(valueToEvaluate == t) {
                count ++;
            }
        }
        return count;
    }


    public Integer countDuplicatesInMerge(T[] arrayToMerge, T valueToEvaluate) {
        T[] mergedArray = merge(arrayToMerge);
        ArrayUtility workingArray = new ArrayUtility(mergedArray);
        return workingArray.getNumberOfOccurrences(valueToEvaluate);

    }


    public T getMostCommonFromMerge(T[] arrayToMerge) {
        T mostFrequent = null;
        int count = 0;
        for (T t : merge(arrayToMerge)) {
            int thiscount = countDuplicatesInMerge(arrayToMerge, t);
            if(count <= thiscount){
                mostFrequent = t;
                count = thiscount;
            }
        }
        return mostFrequent;
    }


    public T[] removeValue(T valueToRemove) {
        List<T> list = Arrays.stream(genericArray)
                .filter(x -> !valueToRemove.equals(x))
                .collect(Collectors.toList());
        T[] result = (T[]) Array.newInstance(genericArray.getClass().getComponentType(), list.size());
        return list.toArray(result);
    }


}
