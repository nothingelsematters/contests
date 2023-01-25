use std::cmp::max;

pub fn closest_meeting_node(edges: Vec<i32>, node1: i32, node2: i32) -> i32 {
    let mut distances1 = vec![None; edges.len()];
    distances1[node1 as usize] = Some(0);
    dfs(&edges, node1 as usize, &mut distances1);

    let mut distances2 = vec![None; edges.len()];
    distances2[node2 as usize] = Some(0);
    dfs(&edges, node2 as usize, &mut distances2);

    distances1
        .into_iter()
        .zip(distances2.into_iter())
        .enumerate()
        .filter(|(_, (a, b))| a.is_some() && b.is_some())
        .map(|(i, (a, b))| (i, (a.unwrap(), b.unwrap())))
        .min_by_key(|(_, (a, b))| max(*a, *b))
        .map(|(i, _)| i as i32)
        .unwrap_or(-1)
}

fn dfs(edges: &[i32], current: usize, distances: &mut [Option<usize>]) {
    if edges[current] == -1 {
        return;
    }

    let to = edges[current] as usize;
    if distances[to].is_some() {
        return;
    }

    distances[to] = distances[current].map(|x| x + 1);
    dfs(edges, to, distances)
}

#[cfg(test)]
mod tests {
    #[test]
    fn wa70() {
        assert_eq!(
            super::closest_meeting_node(vec![4, 4, 8, -1, 9, 8, 4, 4, 1, 1], 5, 6),
            1,
        )
    }

    #[test]
    fn wa71() {
        assert_eq!(super::closest_meeting_node(vec![2, 0, 0], 2, 0), 0)
    }
}
