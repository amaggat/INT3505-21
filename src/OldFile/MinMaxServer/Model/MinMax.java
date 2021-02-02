package OldFile.MinMaxServer.Model;

import java.util.ArrayList;

public class MinMax {

    private int min;
    private int max;

    public MinMax(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public static MinMax findMinAndMax(ArrayList<Integer> numberList)
    {
        Integer min = numberList.get(0);
        Integer max = numberList.get(0);

        for(Integer number : numberList)
        {
            if(number < min)
            {
                min = number;
            }
            if (number > max)
            {
                max = number;
            }
        }

        return new MinMax(min, max);
    }
}
