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
    #[test]
    fn sample() {
        assert!(super::is_palindrome(121))
    }
}
