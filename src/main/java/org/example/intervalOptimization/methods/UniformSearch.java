package org.example.intervalOptimization.methods;


import org.example.intervalOptimization.ui.IntervalOptimization;
import org.springframework.stereotype.Component;

import static org.example.intervalOptimization.configurations.Parameters.getValueFunction;

@Component

public class UniformSearch extends IntervalOptimization {


    @Override
    public int search() {
        int N = (int)(getLengthInterval(this.interval) / this.delta);
        double step = getLengthInterval(this.interval) / (N + 1);
        double res = 0;
        for(int i = 1; i < N + 1; i++) {
            double tmp_x = this.interval[0] + i * step;
            double f_x = getValueFunction(tmp_x);
            if(i == 1) {
                res = f_x;
            } else {
                if(res > f_x) {
                    res = f_x;
                    this.x = tmp_x;
                }
            }
        }
        this.interval[0] = 0;
        this.interval[1] = step;
        return N;
    }
}
