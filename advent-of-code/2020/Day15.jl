function play(start, iteration)
    indices = Dict{Int, Int}()
    for i in 1:length(start) - 1
        indices[start[i]] = i
    end

    last = start[end]

    for i in length(start):iteration - 1
        index = get(indices, last, -1)
        indices[last] = i
        if index == -1
            last = 0
        else
            last = i - index
        end
    end

    return last
end

start = map((x) -> parse(Int, x), split(readline(), ","))
first = play(start, 2020)
second = play(start, 30000000)
println("$(first) $(second)")
