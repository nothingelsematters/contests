pub fn partition(s: String) -> Vec<Vec<String>> {
    partition_bytes(s.as_bytes())
}

fn partition_bytes(b: &[u8]) -> Vec<Vec<String>> {
    if b.is_empty() {
        return vec![vec![]];
    }

    let mut result = Vec::new();

    for i in 1..=b.len() {
        let (l, r) = b.split_at(i);

        if (0..=i / 2).all(|j| l[j] == l[l.len() - 1 - j]) {
            for mut v in partition_bytes(r) {
                v.insert(0, String::from_utf8(l.to_vec()).unwrap());
                result.push(v);
            }
        }
    }

    result
}

#[cfg(test)]
mod tests {
    #[test]
    fn sample() {
        assert_eq!(
            super::partition("aab".to_owned()),
            vec![
                vec!["a".to_owned(), "a".to_owned(), "b".to_owned()],
                vec!["aa".to_owned(), "b".to_owned()]
            ]
        )
    }
}
