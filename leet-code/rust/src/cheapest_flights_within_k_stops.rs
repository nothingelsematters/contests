use std::collections::VecDeque;

pub fn find_cheapest_price(n: i32, flights: Vec<Vec<i32>>, src: i32, dst: i32, k: i32) -> i32 {
    let n = n as usize;
    let src = src as usize;
    let dst = dst as usize;
    let k = k as usize;

    let mut graph = vec![vec![]; n];
    flights
        .into_iter()
        .for_each(|v| graph[v[0] as usize].push((v[1] as usize, v[2])));

    let mut q = VecDeque::new();
    q.push_back((src, 0, 0));
    let mut distances = vec![None; n];
    distances[src] = Some(0);

    while let Some((v, i, price)) = q.pop_front() {
        if matches!(distances[v], Some(x) if x < price) {
            continue;
        }
        distances[v] = Some(price);

        if i > k {
            continue;
        }

        graph[v]
            .iter()
            .for_each(|(u, flight_price)| q.push_back((*u, i + 1, price + flight_price)));
    }

    distances[dst].unwrap_or(-1)
}

#[cfg(test)]
mod tests {
    use super::find_cheapest_price;

    #[test]
    fn sample_0() {
        assert_eq!(
            700,
            find_cheapest_price(
                4,
                vec![
                    vec![0, 1, 100],
                    vec![1, 2, 100],
                    vec![2, 0, 100],
                    vec![1, 3, 600],
                    vec![2, 3, 200]
                ],
                0,
                3,
                1,
            ),
        )
    }

    #[test]
    fn sample_1() {
        assert_eq!(
            200,
            find_cheapest_price(
                3,
                vec![vec![0, 1, 100], vec![1, 2, 100], vec![0, 2, 500]],
                0,
                2,
                1,
            ),
        )
    }
}
