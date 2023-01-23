pub fn find_judge(n: i32, trust: Vec<Vec<i32>>) -> i32 {
    let mut trust_from = vec![0; n as usize];
    let mut trust_to = vec![0; n as usize];

    trust.into_iter().for_each(|v| {
        trust_from[v[0] as usize - 1] += 1;
        trust_to[v[1] as usize - 1] += 1;
    });

    trust_from
        .into_iter()
        .zip(trust_to.into_iter())
        .position(|(from, to)| from == 0 && to == n - 1)
        .map(|index| index as i32 + 1)
        .unwrap_or(-1)
}
