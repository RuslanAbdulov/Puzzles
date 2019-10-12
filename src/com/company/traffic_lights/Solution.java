package com.company.traffic_lights;

import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        try {
            //local env
            in = new Scanner(new FileReader("src/com/company/traffic_lights/input.txt"));
        } catch (FileNotFoundException e) {
            //use System.in
        }

        int speed = in.nextInt();
        List<TL> tLights = new ArrayList<>();
        int lightCount = in.nextInt();
        for (int i = 0; i < lightCount; i++) {
            int distance = in.nextInt();
            int duration = in.nextInt();
            tLights.add(new TL(distance, duration));
        }

        int result = solve(tLights, speed);

        System.out.println(result);
    }

    private static int solve(List<TL> tLights, int speed) {
        for (int speedToSet = speed; speedToSet > 0; speedToSet--) {
            boolean allGreen = true;
            for (TL tl : tLights) {
                double tlPhase = tl.distance * 1.0/(kmphToMps(speedToSet) * tl.duration);
                if (!isGreen(tlPhase)) {
                    allGreen = false;
                    break;
                }
            }

            if (allGreen) {
                return speedToSet;
            }
        }

        return 0;
    }

    private static boolean isGreen(double tlPhase) {
        int instantSwitch = tlPhase == Math.ceil(tlPhase)? 1: 0;
        return Math.ceil(tlPhase) % 2 + instantSwitch == 1;

    }

    private static double kmphToMps(int speedKmph) {
        return speedKmph * 1000.0 / 3600;
    }

    private static int mpsToKmph(int speedMps) {
        return speedMps * 3600 / 1000;
    }

    static class TL {
        int distance;
        int duration;

        public TL(int distance, int duration) {
            this.distance = distance;
            this.duration = duration;
        }
    }
}