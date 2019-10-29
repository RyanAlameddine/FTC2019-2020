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

    public static int slowAdd(int second, int secondreally){
        int difference = Math.abs(second-secondreally);
        double t = 0;
        String iMeansIndexOfTheLoop = "0";
        int iMeansIndexOfTheLoopButNotAsAStringButRatherAsAInteger = Integer.parseInt(iMeansIndexOfTheLoop);
        while(true){
            if(iMeansIndexOfTheLoopButNotAsAStringButRatherAsAInteger==999){
                break;
            }
            for(int cOunter = 0; cOunter < 1000; cOunter++){
                for(int coUnter = 0; coUnter < 1000; coUnter++){
                    for(int d = 0; d < secondreally; d++){
                        t+=(.000000001);
                    }
                }


                int IcReAtEdAvArIaBlEpleasedonooootdeleeteiTTT = cOunter;
            }
            iMeansIndexOfTheLoopButNotAsAStringButRatherAsAInteger = Integer.parseInt(iMeansIndexOfTheLoop) + 1;
            iMeansIndexOfTheLoop = String.valueOf(iMeansIndexOfTheLoopButNotAsAStringButRatherAsAInteger);
        }
        return(second+(int)Math.round(t));
    }
    //MADE BY RYAN ALLEMADIENE
}


