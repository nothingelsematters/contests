use std::{
    cmp::Reverse,
    collections::{BTreeMap, BinaryHeap},
};

pub fn get_order(mut tasks: Vec<Vec<i32>>) -> Vec<i32> {
    tasks
        .iter_mut()
        .enumerate()
        .for_each(|(i, v)| v.push(i as i32));

    tasks.sort_by_key(|v| v[0]);

    let mut time = 0;
    let mut i = 0;
    let mut schedule = Vec::new();
    let mut available: BTreeMap<i32, BinaryHeap<Reverse<i32>>> = BTreeMap::new();

    while i < tasks.len() || !available.is_empty() {
        while i < tasks.len() && tasks[i][0] <= time {
            available
                .entry(tasks[i][1])
                .and_modify(|x| x.push(Reverse(tasks[i][2])))
                .or_insert_with(|| {
                    let mut vd = BinaryHeap::new();
                    vd.push(Reverse(tasks[i][2]));
                    vd
                });

            i += 1;
        }

        if available.is_empty() {
            time = tasks[i][0];
            continue;
        }

        let (duration, remove_duration) = {
            let (duration, indices) = available.iter_mut().next().unwrap();
            schedule.push(indices.pop().unwrap().0);

            (*duration, indices.is_empty())
        };

        if remove_duration {
            available.remove(&duration);
        }

        time += duration;
    }

    schedule
}

#[cfg(test)]
mod tests {
    #[test]
    fn sample() {
        let tasks = vec![vec![1, 2], vec![2, 4], vec![3, 2], vec![4, 1]];
        assert_eq!(super::get_order(tasks), vec![0, 2, 3, 1])
    }

    #[test]
    fn wa16() {
        let tasks = vec![
            vec![19, 13],
            vec![16, 9],
            vec![21, 10],
            vec![32, 25],
            vec![37, 4],
            vec![49, 24],
            vec![2, 15],
            vec![38, 41],
            vec![37, 34],
            vec![33, 6],
            vec![45, 4],
            vec![18, 18],
            vec![46, 39],
            vec![12, 24],
        ];

        assert_eq!(
            super::get_order(tasks),
            vec![6, 1, 2, 9, 4, 10, 0, 11, 5, 13, 3, 8, 12, 7]
        )
    }
}
