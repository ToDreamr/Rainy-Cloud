package com.pray.config;

import org.springframework.scheduling.annotation.Scheduled;

/**
 * Scheduled
 *
 * @author 春江花朝秋月夜
 * @since 2024/1/18 12:43
 */
public class ScheduledConfig {
    //数组 ，长度为n，target，连续子数组和大于等于target，返回最小长度
    public static int minSubArrayLen(int target, int[] nums) {
        int slowIndex = 0;
        int fastIndex = 0;
        int sum = 0;
        int minLength = Integer.MAX_VALUE;
        while (slowIndex < nums.length && fastIndex < nums.length) {
            sum += nums[fastIndex++];
            while (sum >= target) {
                minLength = Math.min(minLength, fastIndex - slowIndex);
                sum -= nums[slowIndex++];
            }
        }
        return minLength == Integer.MAX_VALUE ? -1 : minLength;
    }

    public static void main(String[] args) {

        int[] nums={3,2,3,1,2,3,2,3,3,2,3,3,2,3,18,3,2,3};
        int target=23;
        System.out.println(minSubArrayLen(target,nums));
    }
    @Scheduled(fixedRate = 300000)
    public void scheduledCacheRemove() {

    }
}
