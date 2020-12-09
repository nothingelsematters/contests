use std::io::prelude::*;
use std::io::stdin;

fn main() {
    const PREAMBLE_SIZE: usize = 25;
    const FAILURE: &str = "failed to find weakness";

    let stdin = stdin();
    let numbers: Vec<_> = stdin.lock()
        .lines()
        .map(|line| line.expect("failed to read from stdin").parse::<i64>().unwrap())
        .collect();

    let first = (PREAMBLE_SIZE..numbers.len() - 1)
        .find(|&i| {
            let range = numbers.get(i - PREAMBLE_SIZE..i).expect(FAILURE);
            return range.iter().all(|&j| range.iter().all(|&k| j + k != numbers[i]));
        })
        .map(|i| numbers[i])
        .expect(FAILURE);

    let mut i = 0;
    let mut j = 0;
    let mut sum: i64 = 0;

    while sum != first && j != numbers.len() {
        while sum < first {
            sum += numbers[j];
            j += 1;
        }
        if sum > first {
            sum -= numbers[i];
            i += 1;
        }
    }

    let range = numbers.get(i..j - 1).expect(FAILURE);
    let second = range.iter().max().expect(FAILURE) + range.iter().min().expect(FAILURE);

    println!("{} {}", first, second);
}
