package com.company.max_time_intersection;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

/**
 * дан список постояльцев со временем въезда и выезда.
 * нужно вернуть максимальное количество человек которые одновременно проживали в гостинице в одно время
 */
public class MaxTimeIntersection {

    //split into connected subsets
    public static void main(String[] args) {
        List<Interval> data = prepareData();
        data.sort(null);

        int maxIntersections = 0;
        for (int i = 0; i < data.size(); i++) {
            int intersections = 0;
            for (int j = 0; j <= i; j++) {
                if (data.get(i).hasIntersectionWith(data.get(j))) {
                    intersections++;
                }
            }
            if (maxIntersections < intersections) {
                maxIntersections = intersections;
            }
        }

        System.out.println(maxIntersections);

    }

    //answer is 4 (9:00 - 9:30 AND 9:30 - 10:00 AND 10:00 - 11)
    private static List<Interval> prepareData() {
        return Arrays.asList(
                new Interval("08:00", "09:00"),
                new Interval("08:00", "10:00"),
                new Interval("08:30", "09:30"),
                new Interval("09:00", "11:00"),
                new Interval("09:00", "12:00"),
                new Interval("10:00", "14:00"),
                new Interval("09:30", "13:00")
        );
    }

    static class Interval implements Comparable {
        LocalTime start;
        LocalTime end;

        Interval(LocalTime start, LocalTime end) {
            this.start = start;
            this.end = end;
        }

        Interval(String start, String end) {
            this.start = LocalTime.parse(start);
            this.end = LocalTime.parse(end);
        }

        boolean hasIntersectionWith(Interval other) {
            return !(this.start.compareTo(other.end) >= 0 || other.start.compareTo(this.end) >= 0);
        }


        @Override
        public int compareTo(Object o) {
            Interval that = (Interval) o;
            int compareStart = this.start.compareTo(that.start);
            return  compareStart != 0
                    ? compareStart
                    : this.end.compareTo(that.end);
        }
    }
}
