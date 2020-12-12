var directions = [(Character, Int)]()

while let line = readLine() {
    directions.append((line.first!, Int(line.dropFirst(1))!))
}

var first = 0
var shipAngle = 0

for (command, distance) in directions {
    if command == "N" || command == "W" || command == "F" && shipAngle > 90 {
        first += distance
    } else if command == "S" || command == "E" || command == "F" && shipAngle <= 90 {
        first -= distance
    } else if command == "L" {
        shipAngle = (shipAngle - distance + 360) % 360
    } else if command == "R" {
        shipAngle = (shipAngle + distance) % 360
    }
}

first = abs(first)

var waypoint = (x: -10, y: 1)
var ship = (x: 0, y: 0)

for (command, distance) in directions {
    if command == "N" {
        waypoint.y += distance
    } else if command == "S" {
        waypoint.y -= distance
    } else if command == "E" {
        waypoint.x -= distance
    } else if command == "W" {
        waypoint.x += distance
    } else if command == "L" && distance == 90 || command == "R" && distance == 270 {
        (waypoint.x, waypoint.y) = (waypoint.y, -waypoint.x)
    } else if command == "L" && distance == 270 || command == "R" && distance == 90 {
        (waypoint.x, waypoint.y) = (-waypoint.y, waypoint.x)
    } else if command == "F" {
        ship.x += distance * waypoint.x
        ship.y += distance * waypoint.y
    } else {
        waypoint.x *= -1
        waypoint.y *= -1
    }
}

let second = abs(ship.x) + abs(ship.y)

print("\(first) \(second)")
