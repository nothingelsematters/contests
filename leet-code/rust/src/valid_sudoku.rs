pub fn is_valid_sudoku(board: Vec<Vec<char>>) -> bool {
    let board: Vec<Vec<Option<usize>>> = board
        .into_iter()
        .map(|x| {
            x.into_iter()
                .map(|x| String::from(x).parse().ok())
                .collect()
        })
        .collect();

    // horizontally
    for i in board.iter() {
        let mut met = vec![false; 10];

        for j in i.iter().flatten() {
            if met[*j] {
                return false;
            }
            met[*j] = true;
        }
    }

    // vertically
    for i in 0..9 {
        let mut met = vec![false; 10];

        for j in board.iter().take(9).filter_map(|b| b[i]) {
            if met[j] {
                return false;
            }
            met[j] = true;
        }
    }

    // sections
    for i in 0..3 {
        for j in 0..3 {
            let mut met = vec![false; 10];

            for k in 0..9 {
                let x = i * 3 + k / 3;
                let y = j * 3 + k % 3;

                let b = board[x][y];
                if let Some(b) = b {
                    if met[b] {
                        return false;
                    }
                    met[b] = true;
                }
            }
        }
    }

    true
}

#[cfg(test)]
mod test {
    #[test]
    fn sample() {
        let board = vec![
            vec!['5', '3', '.', '.', '7', '.', '.', '.', '.'],
            vec!['6', '.', '.', '1', '9', '5', '.', '.', '.'],
            vec!['.', '9', '8', '.', '.', '.', '.', '6', '.'],
            vec!['8', '.', '.', '.', '6', '.', '.', '.', '3'],
            vec!['4', '.', '.', '8', '.', '3', '.', '.', '1'],
            vec!['7', '.', '.', '.', '2', '.', '.', '.', '6'],
            vec!['.', '6', '.', '.', '.', '.', '2', '8', '.'],
            vec!['.', '.', '.', '4', '1', '9', '.', '.', '5'],
            vec!['.', '.', '.', '.', '8', '.', '.', '7', '9'],
        ];

        assert!(super::is_valid_sudoku(board));
    }
}
