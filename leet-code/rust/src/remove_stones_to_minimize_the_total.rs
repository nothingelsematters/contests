use std::{cmp::min, collections::BTreeMap};

pub fn min_stone_sum(piles: Vec<i32>, mut k: i32) -> i32 {
    let mut sum: i32 = piles.iter().sum();
    let mut piles_map = BTreeMap::new();
    piles.into_iter().for_each(|x| {
        *piles_map.entry(x).or_insert(0) += 1;
    });

    while k > 0 {
        let (value, count) = piles_map
            .iter()
            .rev()
            .map(|(k, v)| (*k, *v))
            .next()
            .unwrap();

        piles_map.remove(&value);
        let new_value = if value % 2 == 0 {
            value / 2
        } else {
            value / 2 + 1
        };

        let iterations = min(count, k);
        sum -= (value - new_value) * iterations;
        k -= iterations;
        piles_map
            .entry(new_value)
            .and_modify(|curr| *curr += iterations)
            .or_insert(iterations);
    }

    sum
}

#[cfg(test)]
mod tests {
    #[test]
    fn sample() {
        assert_eq!(12, super::min_stone_sum(vec![5, 4, 9], 2))
    }
}
