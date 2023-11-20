package org.example.pointEstimationOptimization.methods;

import org.example.pointEstimationOptimization.configurations.Parameters;
import org.example.pointEstimationOptimization.ui.PointEstimationOptimization;
import org.springframework.stereotype.Component;

@Component
public class NewtonRaphson extends PointEstimationOptimization {
    @Override
    public int search() {
        double x0 = 1;
        this.L0 = this.getLengthInterval(this.interval);
        int M = (int)((this.interval[1] - this.interval[0]) / this.deltaX);
        for(int i = 1; i <= M; i++) {
            double f1 = Parameters.getValueFunctionDerivative(x0);
            double f2 = Parameters.getValueFunctionDerivative2(x0);
            this.LN = x0;
            x0 = x0 - f1 / f2;
            this.LN = Math.abs(this.LN - x0);
            if(Math.abs(Parameters.getValueFunctionDerivative(x0)) < this.delta) {
                this.minPoint = x0;
                return i;
            }
        }
        return M;
    }
}
