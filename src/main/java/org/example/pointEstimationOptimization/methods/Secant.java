package org.example.pointEstimationOptimization.methods;

import org.example.pointEstimationOptimization.configurations.Parameters;
import org.example.pointEstimationOptimization.ui.PointEstimationOptimization;
import org.springframework.stereotype.Component;

@Component
public class Secant extends PointEstimationOptimization {
    @Override
    public int search() {
        double L = -0.8;
        double R = this.interval[1];
        this.L0 = this.getLengthInterval(new double[]{L,R});
        int N = 0;
        while(true) {
            if(N > 100000) break;
            N++;
            double fdL = Parameters.getValueFunctionDerivative(L);
            double fdR =  Parameters.getValueFunctionDerivative(R);
            double z = R - ((fdR) / ((fdR - fdL) / (R - L)));
            double f = Parameters.getValueFunctionDerivative(z);
            if(Math.abs(f) <= this.delta) {
                this.minPoint = z;
                break;
            } else {
                if(f < 0) L = z;
                else R = z;
            }
        }
        this.LN = this.getLengthInterval(new double[]{L,R});
        return N;
    }
}
