pub fn subarrays_div_by_k(nums: Vec<i32>, k: i32) -> i32 {
    let mut prefix_sum = 0;
    let mut prefix_mods = vec![0; k as usize];
    prefix_mods[0] = 1;
    let mut result = 0;

    nums.into_iter().for_each(|x| {
        prefix_sum += x;
        let modulo = ((prefix_sum % k) + k) % k;
        result += prefix_mods[modulo as usize];
        prefix_mods[modulo as usize] += 1;
    });

    result
}

#[cfg(test)]
mod test {
    #[test]
    fn sample() {
        let nums = vec![4, 5, 0, -2, -3, 1];
        let k = 5;
        assert_eq!(super::subarrays_div_by_k(nums, k), 7);
    }
}
