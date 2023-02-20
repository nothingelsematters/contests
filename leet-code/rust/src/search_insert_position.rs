pub fn search_insert(nums: Vec<i32>, target: i32) -> i32 {
    (match nums.binary_search(&target) {
        Ok(x) => x,
        Err(x) => x,
    }) as i32
}
