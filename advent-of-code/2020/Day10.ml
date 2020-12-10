open Printf

let max_jump = 3;;

let rev_lines =
  let lines = ref [] in
  try
    while true; do
      lines := input_line stdin :: !lines
    done;
    !lines
  with End_of_file -> !lines;;

let adapters = List.map int_of_string rev_lines |> List.sort compare;;
let chain = 0 :: adapters @ [3 + List.hd (List.rev adapters)];;


let first =
  let left = List.rev chain |> List.tl |> List.rev in
  let right = List.tl chain in
  let diffs = List.map2 (-) right left in
  let find n = List.filter (fun a -> a == n) diffs |> List.length in
  find 1 * find 3;;

let ways = ref [1];;
let second =
  let rec sum = function
    | [] -> 0
    | h::t -> h + (sum t)
  in
  let rec index_list index acc =
    if acc == max_jump then []
    else
    let tail_rec = index_list (index + 1) (acc + 1) in
    if index < 0 then tail_rec
    else index :: tail_rec
  in
  let chain_iter index el =
    ways := !ways @ [
        index_list (index - max_jump + 1) 0
        |> List.filter (fun it -> el - List.nth chain it <= max_jump)
        |> List.map (List.nth !ways)
        |> sum
    ]
  in
  List.tl chain |> List.iteri chain_iter;
  List.rev !ways |> List.hd;;

Printf.printf "%d %d\n" first second;;
