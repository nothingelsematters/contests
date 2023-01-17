use std::cmp::min;

pub fn min_flips_mono_incr(s: String) -> i32 {
    let binary: Vec<bool> = s.chars().map(|x| x == '1').collect();
    let mut current = binary.iter().filter(|x| !**x).count();
    let mut minimum = current;

    binary.into_iter().for_each(|i| {
        if i {
            current += 1;
        } else {
            current -= 1;
            minimum = min(minimum, current);
        }
    });

    minimum as i32
}

#[cfg(test)]
mod tests {
    use super::min_flips_mono_incr;

    #[test]
    fn sample_1() {
        assert_eq!(1, min_flips_mono_incr("00110".to_owned()))
    }

    #[test]
    fn sample_2() {
        assert_eq!(2, min_flips_mono_incr("010110".to_owned()))
    }

    #[test]
    fn sample_3() {
        assert_eq!(2, min_flips_mono_incr("00011000".to_owned()))
    }
}
