package org.example.linearProgramming.methods.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Comparator;
import java.util.Objects;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class Line implements Comparator, Comparable {
    protected final LinearFunction func;
    protected final double[] leftPoint;
    protected final double[] rightPoint;

    @Override
    public int compareTo(Object o) {
        return compare(this, o);
    }

    @Override
    public int compare(Object o1, Object o2) {
        Line m1 = (Line) o1;
        Line m2 = (Line) o2;
        return (m1.leftPoint[0] >= m2.leftPoint[0]) ? 1 : -1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Line line = (Line) o;
        return Objects.equals(leftPoint[0], line.leftPoint[0]);
    }
}
