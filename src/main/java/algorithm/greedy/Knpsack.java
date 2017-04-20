package algorithm.greedy;

import algorithm.SortUtil;

/**
 给定n种物品和一个背包。物品i的重量是Wi，其价值为Vi，背包的容量为C。
 应如何选择装入背包的物品，使得装入背包中物品的总价值最大?
 所不同的是在选择物品i装入背包时，可以选择物品i的一部分，
 而不一定要全部装入背包，1≤i≤n。

 用贪心算法解背包问题的基本步骤是，首先计算每种物品单位重量的价值Vi/Wi，
 然后，依贪心选择策略，将尽可能多的单位重量价值最高的物品装入背包。
 若将这种物品全部装入背包后，背包内的物品总重量未超过C，则选择单位重量
 价值次高的物品并尽可能多地装入背包。依此策略一直地进行下去，直到背包装满
 为止。
 */
public class Knpsack {
    public static void main(String[] args){
        float[] w = {10, 20, 25, 30};
        float[] v = {20, 30, 30, 40};
        float[] vp = new float[w.length];
        // 计算出商品的单位价值
        for(int i = 0; i < w.length; i++){
            vp[i] = v[i]/w[i];
        }
        SortUtil.out(vp);
        float max = 40;
        float[] c = new float[w.length];
        knapSack(w,vp, c, max);
        SortUtil.out(c);
    }

    private static void knapSack(float[] w, float[] vp, float[] c, float max) {
        float left = max;
        while (left > 0){
            int index = findMaxValueIndex(vp, w);
            if(index == -1){
                break;
            } else {
                if(w[index] > left){
                    c[index] = left;
                    w[index] -= left;
                    left = 0;
                } else{
                    c[index] = w[index];
                    left -= w[index];
                    w[index] = 0;
                }
            }


        }
    }

    private static int findMaxValueIndex(float[] vp, float[] w) {
        int index = -1;
        float curV = 0;
        for(int i = 0; i < vp.length; i++){
            if(w[i]>0){ // 还有没有选完的
                if(vp[i] > curV){
                    index = i;
                    curV = vp[i];
                }
            }
        }
        return index;
    }


}
