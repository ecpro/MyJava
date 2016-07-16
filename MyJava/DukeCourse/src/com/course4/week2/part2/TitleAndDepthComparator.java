package com.course4.week2.part2;

import java.util.Comparator;

/**
 * Created by PIYUSH on 28-02-2016.
 */
public class TitleAndDepthComparator implements Comparator<QuakeEntry> {
    @Override
    public int compare(QuakeEntry first, QuakeEntry second) {
        String infoFirst = first.getInfo();
        String infoSec = second.getInfo();

        int compareValue = infoFirst.compareTo(infoSec);

        if(compareValue == 0) {
            return Double.compare(first.getDepth(), second.getDepth());
        }

        return compareValue;
    }
}
