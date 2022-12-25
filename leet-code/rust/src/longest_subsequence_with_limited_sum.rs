pub fn answer_queries(mut nums: Vec<i32>, queries: Vec<i32>) -> Vec<i32> {
    nums.sort();
    let nums = nums.into_iter().fold(vec![0], |mut acc, i| {
        acc.push(acc.last().unwrap() + i);
        acc
    });

    queries
        .into_iter()
        .map(|q| {
            let index = match nums.binary_search(&q) {
                Ok(x) => x,
                Err(x) => x - 1,
            };
            index as i32
        })
        .collect()
}

#[cfg(test)]
mod test {
    #[test]
    fn sample() {
        let nums = vec![4, 5, 2, 1];
        let queries = vec![3, 10, 21];

        assert_eq!(super::answer_queries(nums, queries), vec![2, 3, 4]);
    }
}
