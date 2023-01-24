use std::{cmp::min, collections::VecDeque};

pub fn snakes_and_ladders(board: Vec<Vec<i32>>) -> i32 {
    let n = board.len();
    let cells = {
        let mut cells = vec![(0, 0); n * n];
        let mut label = 0;
        let mut flag = true;

        for row in (0..n).rev() {
            let columns: Box<dyn Iterator<Item = usize>> = if flag {
                Box::new(0..n)
            } else {
                Box::new((0..n).rev())
            };
            flag = !flag;
            columns.for_each(|column| {
                cells[label] = (row, column);
                label += 1;
            });
        }

        cells
    };

    let mut distance = vec![-1; n * n];
    let mut q = VecDeque::new();
    distance[0] = 0;
    q.push_back(0);

    while let Some(current) = q.pop_front() {
        cells
            .iter()
            .enumerate()
            .take(min(current + 7, n * n))
            .skip(current + 1)
            .for_each(|(next, cell)| {
                let (row, column) = *cell;
                let destination = if board[row][column] != -1 {
                    (board[row][column] - 1) as usize
                } else {
                    next
                };

                if distance[destination] == -1 {
                    distance[destination] = distance[current] + 1;
                    q.push_back(destination);
                }
            });
    }

    distance[n * n - 1]
}

#[cfg(test)]
mod tests {
    use super::snakes_and_ladders;

    #[test]
    fn sample() {
        assert_eq!(
            snakes_and_ladders(vec![
                vec![-1, -1, -1, -1, -1, -1],
                vec![-1, -1, -1, -1, -1, -1],
                vec![-1, -1, -1, -1, -1, -1],
                vec![-1, 35, -1, -1, 13, -1],
                vec![-1, -1, -1, -1, -1, -1],
                vec![-1, 15, -1, -1, -1, -1]
            ]),
            4,
        )
    }

    #[test]
    fn wa107() {
        assert_eq!(
            snakes_and_ladders(vec![
                vec![-1, 1, 2, -1],
                vec![2, 13, 15, -1],
                vec![-1, 10, -1, -1],
                vec![-1, 6, 2, 8]
            ]),
            2,
        )
    }
}
