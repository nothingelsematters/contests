use std::collections::BTreeMap;

struct SummaryRanges {
    tree: BTreeMap<i32, i32>,
}

impl SummaryRanges {
    fn new() -> Self {
        Self {
            tree: BTreeMap::new(),
        }
    }

    fn add_num(&mut self, value: i32) {
        let mut left = None;
        let mut right = None;
        let mut equal = false;

        self.tree
            .range(i32::MIN..=value + 1)
            .rev()
            .take(3)
            .for_each(|(l, r)| {
                if *l <= value && value <= *r {
                    equal = true;
                } else if *l == value + 1 {
                    right = Some((*l, *r));
                } else if *r == value - 1 {
                    left = Some((*l, *r));
                }
            });

        if equal {
            return;
        }

        match (left, right) {
            (Some((left_key, _)), Some((right_key, right_value))) => {
                self.tree.remove(&right_key);
                self.tree.insert(left_key, right_value);
            }
            (None, Some((k, v))) => {
                self.tree.remove(&k);
                self.tree.insert(value, v);
            }
            (Some((k, _)), None) => {
                self.tree.insert(k, value);
            }
            (None, None) => {
                self.tree.insert(value, value);
            }
        }
    }

    fn get_intervals(&self) -> Vec<Vec<i32>> {
        self.tree.iter().map(|(k, v)| vec![*k, *v]).collect()
    }
}
