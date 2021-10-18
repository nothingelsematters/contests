use std::cmp;
use std::collections::HashMap;

pub fn length_of_longest_substring(s: String) -> i32 {
    let mut result = 0;
    let mut current_index = 0;
    let mut chars = HashMap::new();

    for (i, char) in s.chars().enumerate() {
        if let Some(index) = chars.get_mut(&char) {
            if *index < current_index {
                *index = i;
            } else {
                result = cmp::max(i - current_index, result);
                current_index = *index + 1;
            }
        }

        chars.insert(char, i);
    }

    cmp::max(result, s.len() - current_index) as i32
}

#[cfg(test)]
mod test {
    use super::*;

    #[test]
    fn test() {
        assert_eq!(length_of_longest_substring(String::from("abcabcbb")), 3)
    }

    #[test]
    fn abba() {
        assert_eq!(length_of_longest_substring(String::from("abba")), 2)
    }
}
