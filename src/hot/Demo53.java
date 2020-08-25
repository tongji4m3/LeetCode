package hot;

public class Demo53
{
    //子数组最少包含一个元素
    public int maxSubArray(int[] nums)
    {
        int N = nums.length;
        int result = Integer.MIN_VALUE,temp=0;//look,因为条件是至少包含
        for (int i = 0; i < N; i++)
        {
            temp = temp > 0 ? temp + nums[i] : nums[i];
            result = Math.max(result, temp);
        }
        return result;
    }
}
