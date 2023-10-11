package org.example.intervalOptimization.methods;

import org.example.intervalOptimization.ui.IntervalOptimization;
import org.springframework.stereotype.Component;

import static java.lang.Math.abs;
import static org.example.intervalOptimization.configurations.Parameters.getValueFunction;

@Component
public class Dichotomy extends IntervalOptimization {
    @Override
    public int search() {
        int k = 0;
        this.x = (this.interval[0] + this.interval[1]) / 2;
        while(true) {
            double L = abs(this.interval[1] - this.interval[0]);
            double f_x = getValueFunction(this.x);
            //4 step
            double y = (this.interval[0] + L / 4);
            double z = (this.interval[1] - L / 4);
            double f_y = getValueFunction(y);
            double f_z = getValueFunction(z);
            // 6 step
            if(f_y < f_x) {
                this.interval[1] = this.x;
                this.x = y;
            } else {
                if(f_z < f_x) {
                    this.interval[0] = this.x;
                    this.x = z;
                } else {
                    this.interval[0] = y;
                    this.interval[1] = z;
                }
            }
            //7 step
            double L_stop = abs(this.interval[1] - this.interval[0]);
            if(L_stop <= this.delta) {
                return k;
            } else {
                k++;
            }
        }
    }
}
