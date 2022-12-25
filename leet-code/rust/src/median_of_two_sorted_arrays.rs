use std::cmp::min;

pub fn find_median_sorted_arrays(a: Vec<i32>, b: Vec<i32>) -> f64 {
    let total = a.len() + b.len();

    if total % 2 != 0 {
        find(&a, &b, total / 2 + 1)
    } else {
        (find(&a, &b, total / 2) + find(&a, &b, total / 2 + 1)) / 2.0
    }
}

pub fn find(a: &[i32], b: &[i32], middle: usize) -> f64 {
    let a_len = a.len();
    let b_len = b.len();

    if a_len > b_len {
        return find(b, a, middle);
    } else if a_len == 0 {
        return b[middle - 1] as f64;
    } else if middle == 1 {
        return min(a[0], b[0]) as f64;
    }

    let mut trim = middle / 2;
    if min(a_len, b_len) < trim {
        trim = min(a_len, b_len);
    }

    let mut a = a;
    let mut b = b;
    if a[trim - 1] >= b[trim - 1] {
        std::mem::swap(&mut a, &mut b);
    }

    find(&a[trim..], b, middle - trim)
}

#[cfg(test)]
mod test {
    use super::find_median_sorted_arrays;

    #[test]
    fn sample_1() {
        assert_eq!(find_median_sorted_arrays(vec![1, 3], vec![2]), 2.0)
    }

    #[test]
    fn sample_2() {
        assert_eq!(find_median_sorted_arrays(vec![1, 2], vec![3, 4]), 2.5)
    }

    #[test]
    fn sample_3() {
        assert_eq!(find_median_sorted_arrays(vec![0, 0], vec![0, 0]), 0.0)
    }

    #[test]
    fn sample_4() {
        assert_eq!(find_median_sorted_arrays(vec![], vec![1]), 1.0)
    }

    #[test]
    fn sample_5() {
        assert_eq!(find_median_sorted_arrays(vec![2], vec![]), 2.0)
    }
}
