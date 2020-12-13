package main

import (
	"bufio"
	"fmt"
	"math"
	"os"
	"strconv"
	"strings"
)

func main() {
	scanner := bufio.NewScanner(os.Stdin)
	scanner.Scan()
	time, _ := strconv.Atoi(scanner.Text())
	scanner.Scan()
	idsString := strings.Split(scanner.Text(), ",")

	min := math.MaxInt32
	minId := -1

	for _, idString := range idsString {
		if idString == "x" {
			continue
		}

		j, _ := strconv.Atoi(idString)
		wait := j*(time/j+1) - time

		if wait < min {
			min = wait
			minId = j
		}
	}

	first := min * minId

	step := int64(-1)
	second := int64(0)

	for index, idString := range idsString {
		if idString == "x" {
			continue
		}

		bus, _ := strconv.ParseInt(idString, 10, 64)
		if step == -1 {
			step = bus
			continue
		}

		int64index := int64(index)
		remainder := (bus*int64index - int64index) % bus
		for second%bus != remainder {
			second += step
		}
		step *= bus
	}

	fmt.Printf("%d %d\n", first, second)
}
