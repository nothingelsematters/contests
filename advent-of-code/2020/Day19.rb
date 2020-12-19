class DfaNode
    def match(string)
        raise "abstract lol"
    end

    def matchList(strings)
        strings.flat_map {|it| match(it) }.uniq
    end
end

def listMatchList(nodes, string)
    list = [string]
    nodes.each {|x| list = x.matchList(list) }
    return list
end

class Leaf < DfaNode
    def initialize(char)
        @char = char
    end

    def match(string)
        if string.length == 0 || string[0] != @char
            []
        else
            string[1..]
        end
    end
end

class Node < DfaNode
    def initialize(rules)
        @rules = rules
    end

    def match(string)
        @rules.flat_map {|x| listMatchList(x, string) }
    end

    def add(rules)
        for i in rules
            @rules.append(i)
        end
    end
end

def dfa(rules, current, nodes)
    if nodes.include? current
        return nodes[current]
    end

    rule = rules[current]

    if rule.include? '"'
        node = Leaf.new rule[1]
        nodes[current] = node
        return node
    end

    node = Node.new []
    nodes[current] = node
    node.add(rule.split(" | ").map {|x| x.split(' ').map {|y| dfa(rules, y.to_i, nodes)} })
    return node
end

def count_valid(rules, messages)
    root = dfa(rules, 0, {})
    messages.filter {|x| root.match(x).include? "" }.length
end

input = $stdin.read.split("\n\n").map {|x| x.split("\n")}
rules = input[0].map {|x|
    splitted = x.split(": ")
    [splitted[0].to_i, splitted[1]]
}
messages = input[1]
first = count_valid(rules.to_h, messages)
rules.append([8, "42 | 42 8"], [11, "42 31 | 42 11 31"])
second = count_valid(rules.to_h, messages)
puts "#{first} #{second}"

