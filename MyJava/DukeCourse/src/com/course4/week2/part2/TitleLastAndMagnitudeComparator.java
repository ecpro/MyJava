package com.course4.week2.part2;

import java.util.Comparator;

/**
 * Created by PIYUSH on 28-02-2016.
 */
public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry> {

    @Override
    public int compare(QuakeEntry first, QuakeEntry sec) {
        String infoFirst = first.getInfo();
        infoFirst = infoFirst.substring(infoFirst.lastIndexOf(" ")+1);
        String infoSec = sec.getInfo();
        infoSec = infoSec.substring(infoSec.lastIndexOf(" ")+1);

        int compareVal = infoFirst.compareTo(infoSec);

        if(compareVal == 0) {
            compareVal = Double.compare(first.getMagnitude(),sec.getMagnitude());
        }

        return compareVal;
    }
}
