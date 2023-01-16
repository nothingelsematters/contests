use std::cmp::{max, min};

pub fn insert(mut intervals: Vec<Vec<i32>>, mut new_interval: Vec<i32>) -> Vec<Vec<i32>> {
    let mut left_index = match intervals.binary_search_by_key(&new_interval[0], |v| v[0]) {
        Ok(index) => index + 1,
        Err(index) => index,
    };

    while intervals.len() > left_index && intervals[left_index][0] <= new_interval[1] {
        new_interval[1] = max(new_interval[1], intervals[left_index][1]);
        intervals.remove(left_index);
    }

    while left_index > 0 && new_interval[0] <= intervals[left_index - 1][1] {
        new_interval[0] = min(new_interval[0], intervals[left_index - 1][0]);
        new_interval[1] = max(new_interval[1], intervals[left_index - 1][1]);
        intervals.remove(left_index - 1);
        left_index -= 1;
    }

    intervals.insert(left_index, new_interval);

    intervals
}
