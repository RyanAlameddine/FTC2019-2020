package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

import java.util.Locale;

public class MathF {

    public static float Lerp(float a, float b, float f)
    {
        return (a * (1.0f - f)) + (b * f);
    }

    public static String formatAngle(AngleUnit angleUnit, float angle) {
        return formatDegrees(AngleUnit.DEGREES.fromUnit(angleUnit, angle));
    }

    public static String formatDegrees(float degrees){
        return String.format(Locale.getDefault(), "%.1f", AngleUnit.DEGREES.normalize(degrees));
    }
}


