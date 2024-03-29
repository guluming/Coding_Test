package RealTest.Level2;

import java.util.Arrays;

public class Solution1 {
    public static double[] solution(int k, int[][] ranges) {
        double[] answer = new double[ranges.length];

        int cnt = count(k);

        double[] yValue = new double[cnt+1];
        yValue[0] = k;
        for(int i=1; i<cnt+1; i++){
            double pre = yValue[i-1];
            yValue[i] = calYValue(pre);
        }

        double[] area = new double[cnt+1];
        for(int i=1; i<cnt+1; i++){
            area[i] = (yValue[i-1] + yValue[i])/2;
        }

        double[] prefixSum = new double[cnt+1];
        prefixSum[1] = area[1];
        for(int i=2; i<cnt+1; i++){
            prefixSum[i] = (area[i] + prefixSum[i-1]);
        }

        for(int i=0; i< ranges.length; i++){
            int s = ranges[i][0];
            int e = cnt + ranges[i][1];

            if(e > s){
                double val = prefixSum[e] - prefixSum[s];
                String str = String.format("%.1f", val);
                answer[i] = (Double.parseDouble(str));
            }else if(s > e){
                answer[i] = -1.0;
            }else{
                answer[i] = 0.0;
            }
        }
        return answer;
    }

    public static int count(int y){
        int cnt = 0;
        while (y > 1){
            if(y %2 == 0)y = y/2;
            else y = (y*3)+1;
            cnt++;
        }
        return cnt;
    }

    public static double calYValue(double pre){
        if(pre%2 == 0){
            return (pre/2);
        }else{
            return ((3*pre) +1);
        }
    }

    public static void main(String[] args) {
        int k = 5;
        int[][] ranges = {{0,0},{0,-1},{2,-3},{3,-3}};
        double[] result = {33.0, 31.5, 0.0, -1.0};
        if (Arrays.equals(solution(k, ranges), result)) {
            System.out.println("통과했습니다.");
        } else {
            System.out.println("불일치했습니다.");
        }
    }
}