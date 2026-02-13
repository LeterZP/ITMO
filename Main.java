public class Main{
    public static void main(String[] args){
        long[] w = new long[(24-4)/2+1];
        for (int i = 0; i <= 10; i++) {
            w[i] = i*2 + 4;
        }
        float[] x = new float[14];
        for (int i =0; i < x.length; i++){
            x[i] = (float) Math.random()*21 - 10;
        }
        double[][] w1 = new double[11][14];
        for (int i = 0; i < w.length; i++){
            switch ((int) w[i]){
                case 24:
                    for (int j = 0; j < x.length; j++) {
                    w1[i][j] = typeOne(x[j]);
                } break;
                case 6 , 8 , 12 , 14 , 16:
                    for (int j = 0; j < x.length; j++) {
                        w1[i][j] = typeTwo(x[j]);
                    } break;
                default:
                    for (int j = 0; j < x.length; j++) {
                        w1[i][j] = typeThree(x[j]);
                    }
            }
        }
        matrix(w1);
    }
    public static double typeOne(float x){
        double result;
        result = Math.abs(x);
        result = Math.log(result);
        result = Math.tan(result);
        return result;
    }
    public static double typeTwo(float x){
        double base; double power;
        double power1 = ( (double) 1 / 2)/(Math.sin(x));
        base = Math.abs(x);
        base = Math.log(base);
        base = Math.pow(base, power1);
        power = x / (x - (double) 2/3);
        power = Math.pow(power, x);
        power = Math.tan(power) + (double) 1/4;
        power = Math.PI * power;
        double result = Math.pow(base, power);
        return result;
    }
    public static double typeThree(float x){
        double base; double power;
        double power1 = x / Math.PI;
        power1 = Math.pow(power1, x);
        base = Math.pow(Math.E, power1);
        double power2 = (x + (double) 3/4) / 3;
        power2 = Math.pow(power2, 3);
        power = Math.PI + Math.pow(Math.E, power2);
        power = 3 * power;
        double result = Math.pow(base, power) / 2;
        return result;
    }
    public static void matrix(double[][] w1) {
        for (int i = 0; i < w1.length; i++) {
            for (int j = 0; j < w1[i].length; j++) {
                System.out.printf("%.3f ", w1[i][j]);
            }
            System.out.println();
        }
    }
}