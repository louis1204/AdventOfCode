package main

import (
	"bufio"
	"fmt"
	"log"
	"os"
	"strconv"
	"strings"
)

func DoDay2() {
	file, err := os.Open("input2.txt")
	if err != nil {
		log.Fatal("Failed to open file")
	}
	scanner := bufio.NewScanner(file)
	scanner.Split(bufio.ScanLines)

	var depth int64 = 0
	var horiz int64 = 0
	var aim int64 = 0
	var depth2 int64 = 0
	for scanner.Scan() {
		parts := strings.Split(scanner.Text(), " ")
		diff, err := strconv.ParseInt(parts[1], 10, 0)
		if err != nil {
			log.Fatal("Error parsing string")
		}
		if parts[0] == "forward" {
			horiz += diff
			depth2 += aim * diff
		} else if parts[0] == "down" {
			depth += diff
			aim += diff
		} else {
			depth -= diff
			aim -= diff
		}
	}
	fmt.Printf("day 2 res part 1: %d\n", depth*horiz)
	fmt.Printf("day 2 res part 2: %d\n", depth2*horiz)
}
