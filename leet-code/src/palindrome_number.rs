pub fn is_palindrome(x: i32) -> bool {
    let mut num = x;
    let mut rev = 0;

    while num > 0 {
        rev = rev * 10 + num % 10;
        num /= 10;
    }

    x == rev
}

#[cfg(test)]
mod test {
    use super::*;

    #[test]
    fn test() {
        assert_eq!(is_palindrome(121), true)
    }
}
