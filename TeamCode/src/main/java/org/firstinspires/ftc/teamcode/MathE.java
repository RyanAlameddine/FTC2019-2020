package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

import java.util.Locale;

/**
 * Static MathE functions
 *
 * Created September 21, 2017 by Ryan Alameddine
 *
 * Last Edited
 *
 * @author Ryan Alameddine
 *
 * @version 1.0
 */

public class MathE {

    /**
     * Linear interpolation between start and end point by interpolation percentage
     *
     * @param a start value
     * @param b end value
     * @param f interpolation percentage
     */
    public static float Lerp(float a, float b, float f)
    {
        return (a * (1.0f - f)) + (b * f);
    }

    /***
     * Format angles from IMU toj usable string
     *
     * @param angleUnit
     * @param angle
     */
    public static String formatAngle(AngleUnit angleUnit, double angle) {
        return formatDegrees(AngleUnit.DEGREES.fromUnit(angleUnit, angle));
    }

    /***
     * Format degrees from IMU to usable string1
     *
     * @param degrees
     */
    public static String formatDegrees(double degrees){
        return String.format(Locale.getDefault(), "%.1f", AngleUnit.DEGREES.normalize(degrees));
    }
}


