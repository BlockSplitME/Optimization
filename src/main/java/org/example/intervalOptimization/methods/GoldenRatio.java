package org.example.intervalOptimization.methods;


import org.example.intervalOptimization.ui.IntervalOptimization;
import org.springframework.stereotype.Component;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;
import static org.example.intervalOptimization.configurations.Parameters.getValueFunction;
@Component

public class GoldenRatio extends IntervalOptimization {

    @Override
    public int search() {
        int k = 0;
        double coef = (3 - sqrt(5)) / 2;
        double y = this.interval[0] + coef * (this.interval[1] - this.interval[0]);
        double z = this.interval[0] + this.interval[1] - y;

        while(true) {
            double f_y = getValueFunction(y);
            double f_z = getValueFunction(z);
            //5 step
            if(f_y <= f_z) {
                this.interval[1] = z;
                z = y;
                y = this.interval[0] + this.interval[1] - y;
            } else {
                this.interval[0] = y;
                y = z;
                z = this.interval[0] + this.interval[1] - z;
            }
            //6 step
            double L_stop = abs(this.interval[0] - this.interval[1]);
            if(L_stop < this.delta) {
                this.x = (this.interval[0] + this.interval[1]) / 2;
                return k;
            } else {
                k++;
            }
        }
    }
}
