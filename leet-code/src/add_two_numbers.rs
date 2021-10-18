#[derive(PartialEq, Eq, Clone, Debug)]
pub struct ListNode {
    pub val: i32,
    pub next: Option<Box<ListNode>>,
}

impl ListNode {
    #[inline]
    fn new(val: i32) -> Self {
        ListNode { next: None, val }
    }
}

pub fn add_two_numbers_with_remainder(
    l1: Option<Box<ListNode>>,
    l2: Option<Box<ListNode>>,
    remainder: i32,
) -> Option<Box<ListNode>> {
    let sum = remainder
        + match (&l1, &l2, remainder) {
            (None, None, 0) => return None,
            (None, None, _) => return Some(Box::new(ListNode::new(remainder))),
            (Some(node), None, _) => node.val,
            (None, Some(node), _) => node.val,
            (Some(left), Some(right), _) => left.val + right.val,
        };

    let node = ListNode {
        val: sum % 10,
        next: add_two_numbers_with_remainder(
            l1.map(|x| x.next).flatten(),
            l2.map(|x| x.next).flatten(),
            sum / 10,
        ),
    };
    Some(Box::new(node))
}

pub fn add_two_numbers(
    l1: Option<Box<ListNode>>,
    l2: Option<Box<ListNode>>,
) -> Option<Box<ListNode>> {
    add_two_numbers_with_remainder(l1, l2, 0)
}

#[cfg(test)]
mod test {
    use super::*;

    macro_rules! list {
        ( $( $x:expr ),* ) => {
            {
                let mut current: Option<Box<ListNode>> = None;
                $(
                    let node = ListNode {
                        val: $x,
                        next: current,
                    };
                    current = Some(Box::new(node));
                )*
                current
            }
        };
    }

    #[test]
    fn test() {
        assert_eq!(
            add_two_numbers(list![3, 4, 2], list![4, 6, 5]),
            list![8, 0, 7],
        )
    }
}
